package com.colis.android.colis.appviews;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.colis.android.colis.R;
import com.colis.android.colis.adapter.accueil_adapter;
import com.colis.android.colis.decor.DividerItemDecoration;
import com.colis.android.colis.model.Database.SessionManager;
import com.colis.android.colis.model.dao.DatabaseHandler;
import com.colis.android.colis.model.data.AnnonceItem;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AnnoncesList extends AppCompatActivity {

    public static RecyclerView recyclerView;
    private accueil_adapter allUsersAdapter;
    private CoordinatorLayout coordinatorLayout;
    private JSONObject object;
    private Snackbar snackbar;
    private Resources res;
    private Context context;
    private DatabaseHandler database;
    private SessionManager session;
    private List<AnnonceItem> data = new ArrayList<>();
    private ShimmerFrameLayout mShimmerViewContainer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.annonceslist);

        toolbar =  findViewById(R.id.toolbar);
        context = this;
        res = getResources();
        session = new SessionManager(this);
        database = new DatabaseHandler(this);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        coordinatorLayout =  findViewById(R.id.coordinatorLayout);
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        allUsersAdapter = new accueil_adapter(this,data);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(allUsersAdapter);
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setTitle("Annonces");
        data.add(new AnnonceItem(context,1,3,"MALEO-SAMA","MALEO-SAMA","A l'instant","15"
                ,"maleo",R.drawable.ic_question_answer_black_24dp,"maleo-sama","maleo-sama"));
        data.add(new AnnonceItem(context,1,3,"MALEO-SAMA","MALEO-SAMA","A l'instant","15"
                ,"maleo",R.drawable.ic_question_answer_black_24dp,"maleo-sama","maleo-sama"));

        data.add(new AnnonceItem(context,1,3,"MALEO-SAMA","MALEO-SAMA","A l'instant","15"
                ,"maleo",R.drawable.ic_question_answer_black_24dp,"maleo-sama","maleo-sama"));

        data.add(new AnnonceItem(context,1,3,"MALEO-SAMA","MALEO-SAMA","A l'instant","15"
                ,"maleo",R.drawable.ic_question_answer_black_24dp,"maleo-sama","maleo-sama"));

        data.add(new AnnonceItem(context,1,3,"MALEO-SAMA","MALEO-SAMA","A l'instant","15"
                ,"maleo",R.drawable.ic_question_answer_black_24dp,"maleo-sama","maleo-sama"));

        data.add(new AnnonceItem(context,1,3,"MALEO-SAMA","MALEO-SAMA","A l'instant","15"
                ,"maleo",R.drawable.ic_question_answer_black_24dp,"maleo-sama","maleo-sama"));

        recyclerView.addOnItemTouchListener(new AnnoncesList.RecyclerTouchListener(this, recyclerView, new AnnoncesList.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(),DetailsAnnonces.class);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        setResult(RESULT_OK, i);
        finish();
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

    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private AnnoncesList.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final AnnoncesList.ClickListener clickListener) {
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



    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }


}
