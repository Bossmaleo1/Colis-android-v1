package com.colis.android.colis.appviews;

import android.content.Intent;
import android.content.res.Resources;
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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;

import com.colis.android.colis.R;
import com.colis.android.colis.connInscript.MainActivity;
import com.colis.android.colis.fragments.Accueil;
import com.colis.android.colis.fragments.Notification;
import com.colis.android.colis.fragments.Recherche;
import com.colis.android.colis.model.Database.SessionManager;
import com.colis.android.colis.model.dao.DatabaseHandler;
import com.colis.android.colis.model.data.User;

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
        newIcon_notification = res.getDrawable(R.drawable.ic_notifications_black_24dp);
        newIcon_search = res.getDrawable(R.drawable.baseline_search_black_24);

        newIcon_search.mutate().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        menu.findItem(R.id.recherche).setIcon(newIcon_search);

        menu_accueil = "Ajouter Annonce";
        menu_recherche = "Recherche";
        menu_notification = "Notifications";
        accueil_title_text = new SpannableString(menu_accueil);
        recherche_title_text = new SpannableString(menu_recherche);
        notification_text = new SpannableString(menu_notification);
        recherche_title_text.setSpan(new ForegroundColorSpan(res.getColor(R.color.colorPrimary)),0,menu_recherche.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        menu.findItem(R.id.recherche).setTitle(recherche_title_text);
        loadFragment(new Recherche());
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
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
