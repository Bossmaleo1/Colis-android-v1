package com.colis.android.colis.appviews;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.colis.android.colis.R;
import com.colis.android.colis.connInscript.MainActivity;
import com.colis.android.colis.fragments.Accueil;
import com.colis.android.colis.fragments.Notification;
import com.colis.android.colis.fragments.Recherche;
import com.colis.android.colis.model.Config;
import com.colis.android.colis.model.Const;
import com.colis.android.colis.model.Database.SessionManager;
import com.colis.android.colis.model.dao.DatabaseHandler;
import com.colis.android.colis.model.data.User;
import com.colis.android.colis.utils.NotificationUtils;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Home extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;
    private Toolbar toolbar;
    private Resources res;
    private BottomNavigationView navigation;
    private Drawable newIcon;
    private Drawable newIcon_notification;
    private Drawable newIcon_search;
    private Drawable newIcon_home;
    private Menu menu;
    private SpannableString accueil_title_text;
    private SpannableString recherche_title_text;
    private SpannableString notification_text;
    private String menu_accueil;
    private String menu_recherche;
    private String menu_notification;
    private DatabaseHandler database;
    private SessionManager session;
    private User user;
    private String Keypush = null;
    private static final String TAG = Home.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private View notificationBadge;
    private TextView textCartItemCount;
    public static final String REQUEST_CODE12 = "12";
    private JSONObject reponse;
    int mCartItemCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        toolbar =  findViewById(R.id.toolbar);
        res = getResources();
        database = new DatabaseHandler(this);
        session = new SessionManager(this);
        user = database.getUSER(Integer.valueOf(session.getUserDetail().get(SessionManager.Key_ID)));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        this.getSupportActionBar().setTitle("Colis");
        navigation =  findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        newIcon_home = res.getDrawable(R.drawable.baseline_flight_black_24);
        newIcon_notification = res.getDrawable(R.drawable.baseline_check_circle_black_24);
        newIcon_search = res.getDrawable(R.drawable.baseline_search_black_24);

        newIcon_search.mutate().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        menu.findItem(R.id.recherche).setIcon(newIcon_search);

        menu_accueil = "Ajouter Annonce";
        menu_recherche = "Recherche";
        menu_notification = "Validations";
        accueil_title_text = new SpannableString(menu_accueil);
        recherche_title_text = new SpannableString(menu_recherche);
        notification_text = new SpannableString(menu_notification);
        recherche_title_text.setSpan(new ForegroundColorSpan(res.getColor(R.color.colorPrimary)),0,menu_recherche.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        menu.findItem(R.id.recherche).setTitle(recherche_title_text);
        loadFragment(new Recherche());

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), REQUEST_CODE12)
                            .setSmallIcon(R.drawable.colis_1)
                            .setContentTitle("Colis")
                            .setContentText("Hello bossmaleo")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                    Intent intent2 = new Intent(getApplicationContext(), Home.class);
                    PendingIntent pi = PendingIntent.getActivity(getApplicationContext(),0,intent2,Intent.FLAG_ACTIVITY_NEW_TASK);
                    mBuilder.setContentIntent(pi);
                    NotificationManager mNotificationManager =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotificationManager.notify(0, mBuilder.build());

                    //Intent intent1 = new Intent(getApplicationContext(),Home.class);

                    /*NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(this)
                                    .setSmallIcon(R.drawable.colis_1)
                                    .setContentTitle("My notification")
                                    .setContentText("Hello World!")
                                    .setContentIntent(intent1);*/

                }
            }
        };

        displayFirebaseRegId();
        CountNotification();
        //navigation.inflateMenu(R.menu.navigation);
        //addBadgeView();
        //BadgeDrawable badge = bottomNavigationView.showBadge(menuItemId);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.home:
                    //on prepare les couleurs des icons
                    newIcon_home.mutate().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
                    newIcon_notification.mutate().setColorFilter(getResources().getColor(R.color.graydark), PorterDuff.Mode.SRC_IN);
                    newIcon_search.mutate().setColorFilter(getResources().getColor(R.color.graydark), PorterDuff.Mode.SRC_IN);
                    //on prepare les couleurs des texts
                    accueil_title_text.setSpan(new ForegroundColorSpan(res.getColor(R.color.colorPrimary)),0,menu_accueil.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    recherche_title_text.setSpan(new ForegroundColorSpan(res.getColor(R.color.graydark)),0,menu_recherche.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    notification_text.setSpan(new ForegroundColorSpan(res.getColor(R.color.graydark)),0,menu_notification.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    //on set maintenant tout ce que nous les textcoloravons preparer
                    menu.findItem(R.id.home).setTitle(accueil_title_text);
                    menu.findItem(R.id.recherche).setTitle(recherche_title_text);
                    menu.findItem(R.id.notification).setTitle(notification_text);
                    //on set maintenant tout ce que nous avons preparer comme icon drawable color
                    menu.findItem(R.id.home).setIcon(newIcon_home);
                    menu.findItem(R.id.recherche).setIcon(newIcon_search);
                    menu.findItem(R.id.notification).setIcon(newIcon_notification);
                    fragment = new Accueil();
                    loadFragment(fragment);
                    return true;
                case R.id.recherche:
                    //on prepare les couleurs des icons
                    newIcon_home.mutate().setColorFilter(getResources().getColor(R.color.graydark), PorterDuff.Mode.SRC_IN);
                    newIcon_notification.mutate().setColorFilter(getResources().getColor(R.color.graydark), PorterDuff.Mode.SRC_IN);
                    newIcon_search.mutate().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
                    //on prepare les couleurs des texts
                    accueil_title_text.setSpan(new ForegroundColorSpan(res.getColor(R.color.graydark)),0,menu_accueil.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    recherche_title_text.setSpan(new ForegroundColorSpan(res.getColor(R.color.colorPrimary)),0,menu_recherche.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    notification_text.setSpan(new ForegroundColorSpan(res.getColor(R.color.graydark)),0,menu_notification.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    //on set maintenant tout ce que nous les textcoloravons preparer
                    menu.findItem(R.id.home).setTitle(accueil_title_text);
                    menu.findItem(R.id.recherche).setTitle(recherche_title_text);
                    menu.findItem(R.id.notification).setTitle(notification_text);
                    //on set maintenant tout ce que nous avons preparer comme icon drawable color
                    menu.findItem(R.id.home).setIcon(newIcon_home);
                    menu.findItem(R.id.recherche).setIcon(newIcon_search);
                    menu.findItem(R.id.notification).setIcon(newIcon_notification);
                    fragment = new Recherche();
                    loadFragment(fragment);
                    return true;
                case R.id.notification:
                    newIcon_home.mutate().setColorFilter(getResources().getColor(R.color.graydark), PorterDuff.Mode.SRC_IN);
                    newIcon_notification.mutate().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
                    newIcon_search.mutate().setColorFilter(getResources().getColor(R.color.graydark), PorterDuff.Mode.SRC_IN);
                    //on prepare les couleurs des texts
                    accueil_title_text.setSpan(new ForegroundColorSpan(res.getColor(R.color.graydark)),0,menu_accueil.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    recherche_title_text.setSpan(new ForegroundColorSpan(res.getColor(R.color.graydark)),0,menu_recherche.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    notification_text.setSpan(new ForegroundColorSpan(res.getColor(R.color.colorPrimary)),0,menu_notification.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    //on set maintenant tout ce que nous les textcoloravons preparer
                    menu.findItem(R.id.home).setTitle(accueil_title_text);
                    menu.findItem(R.id.recherche).setTitle(recherche_title_text);
                    menu.findItem(R.id.notification).setTitle(notification_text);
                    //on set maintenant tout ce que nous avons preparer comme icon drawable color
                    menu.findItem(R.id.home).setIcon(newIcon_home);
                    menu.findItem(R.id.recherche).setIcon(newIcon_search);
                    menu.findItem(R.id.notification).setIcon(newIcon_notification);
                    fragment = new Notification();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        /*MenuItem favoriteItem = menu.findItem(R.id.send);
        MenuItem favoriteItem1 = menu.findItem(R.id.add_picture);
        MenuItem favoriteItem2 = menu.findItem(R.id.insert_picture);
        Drawable newIcon = favoriteItem.getIcon();
        Drawable newIcon1 = favoriteItem1.getIcon();
        Drawable newIcon2 = favoriteItem2.getIcon();
        newIcon.mutate().setColorFilter(Color.rgb(255, 255, 255), PorterDuff.Mode.SRC_IN);
        newIcon1.mutate().setColorFilter(Color.rgb(255, 255, 255), PorterDuff.Mode.SRC_IN);
        newIcon2.mutate().setColorFilter(Color.rgb(255, 255, 255), PorterDuff.Mode.SRC_IN);
        favoriteItem.setIcon(newIcon);
        favoriteItem1.setIcon(newIcon1);
        favoriteItem2.setIcon(newIcon2);*/
        final MenuItem menuItem = menu.findItem(R.id.notification);

        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount =  actionView.findViewById(R.id.cart_badge);

        setupBadge();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        MenuItem favoriteItem = menu.findItem(R.id.notification);
        Drawable newIcon = (Drawable)favoriteItem.getIcon();
        newIcon.mutate().setColorFilter(Color.rgb(255, 255, 255), PorterDuff.Mode.SRC_IN);
        favoriteItem.setIcon(newIcon);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.deco:
                session.logoutUser();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                return true;
            case R.id.notification: {
                // Do something
                return true;
            }
            case R.id.profil:
                Intent intent = new Intent(getApplicationContext(),Profil.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);
        Log.e(TAG, "Firebase reg id: " + regId);
        if(regId!=null)
        {
            Keypush = regId;
            if(database.getUSER(Integer.valueOf(session.getUserDetail().get(SessionManager.Key_ID))).getKEYPUSH().length()<9){
                Connexion();
            }
            //Toast.makeTxt(getApplicationContext(),"Firebase Reg Id: " + regId,Toast.LENGTH_LONG).show();
        }

        //Toast.makeText(getApplicationContext(),"Firebase Reg Id: " + regId,Toast.LENGTH_LONG).show();
    }



    private void Connexion()
    {
        String url_sendkey = Const.dns.concat("/colis/ColisApi/public/api/UpdateKeyPush?ID=").concat(String.valueOf(database.getUSER(Integer.valueOf(session.getUserDetail().get(SessionManager.Key_ID))).getID())).concat("&PUSHKEY=").concat(Keypush);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_sendkey,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        database.UpdateKeyPush(database.getUSER(Integer.valueOf(session.getUserDetail().get(SessionManager.Key_ID))).getID(),Keypush);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void CountNotification()
    {
        String url_sendkey = Const.dns.concat("/colis/ColisApi/public/api/AfficherCountNotification?ID_USER=").concat(String.valueOf(database.getUSER(Integer.valueOf(session.getUserDetail().get(SessionManager.Key_ID))).getID()));
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_sendkey,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            reponse = new JSONObject(response);
                            mCartItemCount = reponse.getInt("count_notification");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());

    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    private void setupBadge() {

        if (textCartItemCount != null) {
            if (mCartItemCount == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(mCartItemCount, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }





}
