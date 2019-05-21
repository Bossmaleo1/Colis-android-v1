package com.colis.android.colis.fragments;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.colis.android.colis.R;

import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
import com.colis.android.colis.adapter.ValidationAdapter;
import com.colis.android.colis.model.Const;
import com.colis.android.colis.model.Database.SessionManager;
import com.colis.android.colis.model.dao.DatabaseHandler;
import com.colis.android.colis.model.data.ValidationItem;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Notification extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

	public static RecyclerView recyclerView;
	private ValidationAdapter allUsersAdapter;
	private CoordinatorLayout coordinatorLayout;
	//private ProgressBar progressBar;
	private JSONObject object;
	private Snackbar snackbar;
	private Resources res;
	private Context context;
	private DatabaseHandler database;
	private SessionManager session;
	private List<ValidationItem> data = new ArrayList<>();
	private SwipeRefreshLayout swipeRefreshLayout;
	private ShimmerFrameLayout mShimmerViewContainer;
	private TextView message_error;

	public Notification() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View bossmaleo =  inflater.inflate(R.layout.validation, container, false);
		context = getActivity();
		res = getResources();
		session = new SessionManager(getActivity());
		database = new DatabaseHandler(getActivity());
		message_error = bossmaleo.findViewById(R.id.message_error);
		mShimmerViewContainer = bossmaleo.findViewById(R.id.shimmer_view_container);
		//progressBar = (ProgressBar) bossmaleo.findViewById(R.id.progressbar);
		coordinatorLayout =  bossmaleo.findViewById(R.id.coordinatorLayout);
		recyclerView = bossmaleo.findViewById(R.id.my_recycler_view);
		swipeRefreshLayout =  bossmaleo.findViewById(R.id.swipe_refresh_layout);
		swipeRefreshLayout.setOnRefreshListener(this);
		swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		allUsersAdapter = new ValidationAdapter(getActivity(),data);
		recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setAdapter(allUsersAdapter);
		AffichageValidation();
		return bossmaleo;
	}



	private void AffichageValidation()
	{

		StringRequest stringRequest = new StringRequest(Request.Method.GET, Const.dns+"/colis/ColisApi/public/api/AfficherValidation?ID_USER="+String.valueOf(database.getUSER(Integer.valueOf(session.getUserDetail().get(SessionManager.Key_ID))).getID()),
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {

						JSONArray reponse = null;
						// Toast.makeText(getActivity()," "+response,Toast.LENGTH_LONG).show();
						try {
							reponse = new JSONArray(response);
							for(int i = 0;i<reponse.length();i++)
							{
								object = reponse.getJSONObject(i);

								/*String count = null;
								if(object.getString("countcomment").equals("0") || object.getString("countcomment").equals("1"))
								{
									count = object.getString("countcomment")+" "+res.getString(R.string.convertpublic_inter);
								}else
								{
									count = object.getString("countcomment")+" "+res.getString(R.string.convertpublic_inter);
								}*/

								ValidationItem validationItem = new ValidationItem(getActivity(),object.getInt("id"),object.getString("description"),object.getString("date_validation"),object.getString("statut_validation")
										,object.getString("nombre_kilo"),object.getString("id_emmeteur"),object.getString("id_annonce"),object.getString("nom_emmeteur"),object.getString("prenom_emmeteur"),object.getString("photo_emmeteur"),"0616296905");

								data.add(validationItem);
							}




						} catch (JSONException e) {
							e.printStackTrace();
						}

						allUsersAdapter.notifyDataSetChanged();
						mShimmerViewContainer.stopShimmerAnimation();
						mShimmerViewContainer.setVisibility(View.GONE);
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
											AffichageValidation();
										}
									});

							snackbar.show();
							//progressBar.setVisibility(View.GONE);
						}else if(error instanceof NetworkError)
						{
							snackbar = Snackbar
									.make(coordinatorLayout, res.getString(R.string.error_volley_servererror), Snackbar.LENGTH_LONG)
									.setAction(res.getString(R.string.try_again), new View.OnClickListener() {
										@Override
										public void onClick(View view) {
											AffichageValidation();
										}
									});

							snackbar.show();
							//progressBar.setVisibility(View.GONE);
						}else if(error instanceof AuthFailureError)
						{
							snackbar = Snackbar
									.make(coordinatorLayout, res.getString(R.string.error_volley_servererror), Snackbar.LENGTH_LONG)
									.setAction(res.getString(R.string.try_again), new View.OnClickListener() {
										@Override
										public void onClick(View view) {
											AffichageValidation();
										}
									});

							snackbar.show();
							//progressBar.setVisibility(View.GONE);
						}else if(error instanceof ParseError)
						{
							snackbar = Snackbar
									.make(coordinatorLayout, res.getString(R.string.error_volley_servererror), Snackbar.LENGTH_LONG)
									.setAction(res.getString(R.string.try_again), new View.OnClickListener() {
										@Override
										public void onClick(View view) {
											AffichageValidation();
										}
									});

							snackbar.show();
							//progressBar.setVisibility(View.GONE);
						}else if(error instanceof NoConnectionError)
						{
							snackbar = Snackbar
									.make(coordinatorLayout, res.getString(R.string.error_volley_noconnectionerror), Snackbar.LENGTH_LONG)
									.setAction(res.getString(R.string.try_again), new View.OnClickListener() {
										@Override
										public void onClick(View view) {
											AffichageValidation();
										}
									});

							snackbar.show();
							//progressBar.setVisibility(View.GONE);
						}else if(error instanceof TimeoutError)
						{
							snackbar = Snackbar
									.make(coordinatorLayout, res.getString(R.string.error_volley_timeouterror), Snackbar.LENGTH_LONG)
									.setAction(res.getString(R.string.try_again), new View.OnClickListener() {
										@Override
										public void onClick(View view) {
											AffichageValidation();
										}
									});
							snackbar.show();
							//progressBar.setVisibility(View.GONE);
						}else
						{
							snackbar = Snackbar
									.make(coordinatorLayout, res.getString(R.string.error_volley_error), Snackbar.LENGTH_LONG)
									.setAction(res.getString(R.string.try_again), new View.OnClickListener() {
										@Override
										public void onClick(View view) {
											AffichageValidation();
										}
									});

							snackbar.show();
							//progressBar.setVisibility(View.GONE);
						}
					}
				}){
			@Override
			protected Map<String,String> getParams(){
				Map<String,String> params = new HashMap<String, String>();
				return params;
			}

		};

		RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
		requestQueue.add(stringRequest);
	}

	@Override
	public void onRefresh() {
		Toast.makeText(getContext(),"Le machin vient de subir un refresh scarla!!!",Toast.LENGTH_LONG).show();
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