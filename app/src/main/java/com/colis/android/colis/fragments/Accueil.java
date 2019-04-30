package com.colis.android.colis.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

public class Accueil extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    public static RecyclerView recyclerView;
    private accueil_adapter allUsersAdapter;
    private FloatingActionButton fab;
    private CoordinatorLayout coordinatorLayout;
    //private ProgressBar progressBar;
    private JSONObject object;
    private Snackbar snackbar;
    private Resources res;
    private Context context;
    private DatabaseHandler database;
    private SessionManager session;
    private List<AnnonceItem> data = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private ShimmerFrameLayout mShimmerViewContainer;

    public Accueil() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View bossmaleo =  inflater.inflate(R.layout.accueil, container, false);
        fab = bossmaleo.findViewById(R.id.fab);
        context = getActivity();
        res = getResources();
        session = new SessionManager(getActivity());
        database = new DatabaseHandler(getActivity());
        mShimmerViewContainer = bossmaleo.findViewById(R.id.shimmer_view_container);
        //progressBar = (ProgressBar) bossmaleo.findViewById(R.id.progressbar);
        coordinatorLayout = (CoordinatorLayout) bossmaleo.findViewById(R.id.coordinatorLayout);
        recyclerView = (RecyclerView)bossmaleo.findViewById(R.id.my_recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) bossmaleo.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        allUsersAdapter = new accueil_adapter(getActivity(),data);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(allUsersAdapter);
        //ConnexionProblematique();


        return  bossmaleo;
    }

    @Override
    public void onRefresh() {
        Toast.makeText(getContext(),"Le machin vient de subir un refresh scarla!!!", Toast.LENGTH_LONG).show();
        swipeRefreshLayout.setRefreshing(false);
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