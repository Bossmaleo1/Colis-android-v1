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
import com.colis.android.colis.adapter.PaymentAdapter;
import com.colis.android.colis.decor.DividerItemDecoration;
import com.colis.android.colis.model.Database.SessionManager;
import com.colis.android.colis.model.dao.DatabaseHandler;
import com.colis.android.colis.model.data.Annonce;
import com.colis.android.colis.model.data.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentList extends AppCompatActivity {
    private Toolbar toolbar;
    private Resources res;
    private RecyclerView recyclerView;
    private PaymentAdapter allUsersAdapter;
    private DatabaseHandler database;
    private SessionManager session;
    private List<com.colis.android.colis.model.data.Payment> data = new ArrayList<>();
    private Snackbar snackbar;
    private CoordinatorLayout coordinatorLayout;
    private Context context;
    private Annonce annonce;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);
        intent = getIntent();

        res = getResources();
        toolbar =  findViewById(R.id.toolbar);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        setSupportActionBar(toolbar);
        context = this;
        database = new DatabaseHandler(this);
        session = new SessionManager(getApplicationContext());
        getSupportActionBar().setTitle("Moyens Paiements");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //addElement();
        data.add(new Payment(this,"CARTE BANCAIRE",R.drawable.baseline_credit_card_black_48));
        data.add(new Payment(this,"VISA",R.drawable.visa));
        data.add(new Payment(this,"MASTERCARD",R.drawable.mastercard));
        data.add(new Payment(this,"PAYPAL",R.drawable.paypal));
        annonce = intent.getParcelableExtra("annonce");
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        allUsersAdapter = new PaymentAdapter(this,data);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(allUsersAdapter);
        recyclerView.addOnItemTouchListener(new com.colis.android.colis.appviews.PaymentList.RecyclerTouchListener(this, recyclerView, new com.colis.android.colis.appviews.PaymentList.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                    Intent intent = new Intent(getApplicationContext(),Validation.class);
                    intent.putExtra("annonce",annonce);
                    startActivity(intent);
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
        private com.colis.android.colis.appviews.PaymentList.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final com.colis.android.colis.appviews.PaymentList.ClickListener clickListener) {
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
