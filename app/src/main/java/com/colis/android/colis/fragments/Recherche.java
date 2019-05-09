package com.colis.android.colis.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.colis.android.colis.R;
import com.colis.android.colis.appviews.AnnoncesList;
import com.colis.android.colis.appviews.SearchTown;

import org.json.JSONObject;

import java.util.Calendar;

public class Recherche extends Fragment {

    private Button rechercher;
    private EditText dateannonce;
    private EditText depart;
    private EditText arrivee;
    public static final int REQUEST_CODE = 11;
    public static final int REQUEST_CODE_DEPART = 12;
    public static final int REQUEST_CODE_ARRIVEE = 13;
    String selectedDate;
    private OnFragmentInteractionListener mListener;
    private int idaeroportdepart;
    private int idaeroportarrivee;

    public static Recherche newInstance() {
        Recherche fragment = new Recherche();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View inflatedView = inflater.inflate(R.layout.recherche, container, false);
        // get fragment manager so we can launch from fragment
        final FragmentManager fm = ((AppCompatActivity)getActivity()).getSupportFragmentManager();


        rechercher = inflatedView.findViewById(R.id.rechercher);
        depart = inflatedView.findViewById(R.id.depart);
        arrivee = inflatedView.findViewById(R.id.arrivvee);
        dateannonce = inflatedView.findViewById(R.id.dateannonce);
        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AnnoncesList.class);
                intent.putExtra("depart",String.valueOf(idaeroportdepart));
                intent.putExtra("arrivee",String.valueOf(idaeroportarrivee));
                intent.putExtra("date",String.valueOf(dateannonce.getText().toString()));
                startActivity(intent);
            }
        });


        depart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchTown.class);
                intent.putExtra("title","Ville depart");
                startActivityForResult(intent, REQUEST_CODE_DEPART);
            }
        });

        arrivee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchTown.class);
                intent.putExtra("title","Ville d'arrivee");
                startActivityForResult(intent, REQUEST_CODE_ARRIVEE);
            }
        });

        depart.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    Intent intent = new Intent(getActivity(), SearchTown.class);
                    intent.putExtra("title","Ville depart");
                    startActivityForResult(intent, REQUEST_CODE_DEPART);
                }
            }
        });

        arrivee.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    Intent intent = new Intent(getActivity(), SearchTown.class);
                    intent.putExtra("title","Ville d'arrivee");
                    startActivityForResult(intent, REQUEST_CODE_ARRIVEE);
                }
            }
        });

        dateannonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create the datePickerFragment
                AppCompatDialogFragment newFragment = new DatePickerFragment();
                // set the targetFragment to receive the results, specifying the request code
                newFragment.setTargetFragment(Recherche.this, REQUEST_CODE);
                // show the datePicker
                newFragment.show(fm, "datePicker");
            }
        });

        dateannonce.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    // create the datePickerFragment
                    AppCompatDialogFragment newFragment = new DatePickerFragment();
                    // set the targetFragment to receive the results, specifying the request code
                    newFragment.setTargetFragment(Recherche.this, REQUEST_CODE);
                    // show the datePicker
                    newFragment.show(fm, "datePicker");
                }
            }
        });



        return  inflatedView;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // check for the results
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // get date from string
            selectedDate = data.getStringExtra("selectedDate");
            // set the value of the editText
            dateannonce.setText(selectedDate);
        } else if(requestCode == REQUEST_CODE_ARRIVEE && resultCode == Activity.RESULT_OK) {

            arrivee.setText(data.getStringExtra("ville"));
            idaeroportarrivee =  data.getIntExtra("id",0);

        } else if(requestCode == REQUEST_CODE_DEPART && resultCode == Activity.RESULT_OK) {
            depart.setText(data.getStringExtra("ville"));
            idaeroportdepart = data.getIntExtra("id",0);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public boolean validate() {
        boolean valid = true;

        String _depart_voyage = depart.getText().toString();
        String _arrivee_voyage = arrivee.getText().toString();
        String _date_voyage = dateannonce.getText().toString();


        if (_depart_voyage.isEmpty()) {
            depart.setError("La ville de depart doit etre non vide");
            valid = false;
        } else {
            depart.setError(null);
        }

        if (_arrivee_voyage.isEmpty()) {
            arrivee.setError("La ville d'arrivee doit etre non vide");
            valid = false;
        }else {
            arrivee.setError(null);
        }

        if (_date_voyage.isEmpty()) {
            dateannonce.setError("La date de l'annonce doit etre non vide");
            valid = false;
        }else {
            dateannonce.setError(null);
        }



        return valid;
    }





}