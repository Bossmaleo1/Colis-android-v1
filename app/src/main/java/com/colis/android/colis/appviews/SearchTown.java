package com.colis.android.colis.appviews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.colis.android.colis.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.colis.android.colis.adapter.search_town_adapter;
import com.colis.android.colis.model.Const;
import com.colis.android.colis.model.Database.SessionManager;
import com.colis.android.colis.model.dao.DatabaseHandler;
import com.colis.android.colis.model.data.TownItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchTown extends AppCompatActivity  {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private search_town_adapter allUsersAdapter;
    private Intent intent;
    private ProgressBar progressBar;
    private JSONObject object;
    private CoordinatorLayout coordinatorLayout;
    private Snackbar snackbar;
    private Resources res;
    private List<TownItem> data = new ArrayList<>();
    private DatabaseHandler database;
    private boolean test=true;
    private ProgressDialog pDialog;
    private int ID_user;
    private int ID_prob;
    private String Libelle;
    private SessionManager session;
    private EditText searchview;
    private FloatingActionButton fab;
    private int ID = 0;
    private String IDCAT = null;
    private TextView message_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchtown);

        intent = getIntent();
        database = new DatabaseHandler(this);
        session = new SessionManager(getApplicationContext());
        res = getResources();
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.slide_in);
        findViewById(R.id.coordinatorLayout).startAnimation(anim);
        toolbar = findViewById(R.id.toolbar);
        searchview = findViewById(R.id.searchview);
        message_error = findViewById(R.id.message_error);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(intent.getStringExtra("title"));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*data.add(new TownItem(1,"Paris (France (Roissy Charle degaulle,RCG)) "));
        data.add(new TownItem(1,"Brazzaville (Congo (MAYA-MAYA,BZV))"));*/

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        allUsersAdapter = new search_town_adapter(this,data);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(allUsersAdapter);

        searchview.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
               /* SearchTown(String.valueOf(searchview.getText().toString()));*/
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
                /*SearchTown(String.valueOf(searchview.getText().toString()));*/
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    SearchTown(String.valueOf(searchview.getText().toString()));
            }
        });

        recyclerView.addOnItemTouchListener(new AnnoncesList.RecyclerTouchListener(this, recyclerView, new AnnoncesList.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent();
                i.putExtra("ville",data.get(position).getLibelle());
                i.putExtra("id",data.get(position).getID());
                setResult(RESULT_OK, i);
                finish();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // User chose the "Settings" item, show the app settings UI...
                Intent i = new Intent();
                setResult(RESULT_OK, i);
                finish();
                return true;



            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        setResult(RESULT_OK, i);
        finish();
    }

    private void SearchTown(String townvalue)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Const.dns+"/colis/ColisApi/public/api/SearchTown?town="+String.valueOf(townvalue),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        data.clear();
                        //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                        try {
                            JSONArray reponse = new JSONArray(response);
                            for(int i = 0;i<reponse.length();i++)
                            {
                                object = reponse.getJSONObject(i);
                                data.add(new TownItem(object.getInt("ID"),object.getString("Libelle")));
                                //allUsersAdapter.notifyDataSetChanged();
                               // data.add(new Categorie_prob(object.getInt("ID"), object.getString("Libelle")));
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        allUsersAdapter.notifyDataSetChanged();
                        if (data.size() == 0) {
                            message_error.setVisibility(View.VISIBLE);
                        }else {
                            message_error.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if(error instanceof ServerError)
                        {
                            snackbar = Snackbar
                                    .make(coordinatorLayout, res.getString(R.string.error_volley_servererror), Snackbar.LENGTH_LONG)
                                    .setAction(res.getString(R.string.try_again), new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                        }
                                    });

                            snackbar.show();
                        }else if(error instanceof NetworkError)
                        {
                            snackbar = Snackbar
                                    .make(coordinatorLayout, res.getString(R.string.error_volley_servererror), Snackbar.LENGTH_LONG)
                                    .setAction(res.getString(R.string.try_again), new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                        }
                                    });

                            snackbar.show();
                        }else if(error instanceof AuthFailureError)
                        {
                            snackbar = Snackbar
                                    .make(coordinatorLayout, res.getString(R.string.error_volley_servererror), Snackbar.LENGTH_LONG)
                                    .setAction(res.getString(R.string.try_again), new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                        }
                                    });

                            snackbar.show();
                        }else if(error instanceof ParseError)
                        {
                            snackbar = Snackbar
                                    .make(coordinatorLayout, res.getString(R.string.error_volley_servererror), Snackbar.LENGTH_LONG)
                                    .setAction(res.getString(R.string.try_again), new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                        }
                                    });

                            snackbar.show();
                        }else if(error instanceof NoConnectionError)
                        {
                            snackbar = Snackbar
                                    .make(coordinatorLayout, res.getString(R.string.error_volley_noconnectionerror), Snackbar.LENGTH_LONG)
                                    .setAction(res.getString(R.string.try_again), new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                        }
                                    });

                            snackbar.show();
                        }else if(error instanceof TimeoutError)
                        {
                            snackbar = Snackbar
                                    .make(coordinatorLayout, res.getString(R.string.error_volley_timeouterror), Snackbar.LENGTH_LONG)
                                    .setAction(res.getString(R.string.try_again), new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                        }
                                    });
                            snackbar.show();
                        }else
                        {
                            snackbar = Snackbar
                                    .make(coordinatorLayout, res.getString(R.string.error_volley_error), Snackbar.LENGTH_LONG)
                                    .setAction(res.getString(R.string.try_again), new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                        }
                                    });

                            snackbar.show();
                        }
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


    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private SearchTown.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final SearchTown.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }


    }



}
