package com.colis.android.colis.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.colis.android.colis.R;
import com.colis.android.colis.appviews.SearchTown;

public class Accueil extends Fragment{

    private int day;
    private int month;
    private int year;
    static final int DATE_DIALOG_ID = 999;
    private EditText date_annoonce;
    private EditText lieux_depart;
    private EditText lieux_darrivee;
    private EditText heure_depart;
    private EditText heure_darrivee;
    private EditText prix_transaction;
    private EditText poids;

    public static final int REQUEST_CODE = 11;
    public static final int REQUEST_CODE12 = 12;
    public static final int REQUEST_CODE13 = 13;
    String selectedDate;
    String selectedDate1;
    String selectedDate2;
    private Accueil.OnFragmentInteractionListener mListener;

    public static Accueil newInstance() {
        Accueil fragment = new Accueil();
        return fragment;
    }

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
        final FragmentManager fm = ((AppCompatActivity)getActivity()).getSupportFragmentManager();


        date_annoonce = bossmaleo.findViewById(R.id.dateannonce);
        lieux_depart = bossmaleo.findViewById(R.id.depart);
        lieux_darrivee = bossmaleo.findViewById(R.id.arrivvee);
        heure_depart = bossmaleo.findViewById(R.id.heure_depart);
        heure_darrivee = bossmaleo.findViewById(R.id.heure_arrivee);
        prix_transaction = bossmaleo.findViewById(R.id.prix);
        poids = bossmaleo.findViewById(R.id.kilo);

        lieux_depart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchTown.class);
                startActivity(intent);
            }
        });

        lieux_darrivee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchTown.class);
                startActivity(intent);
            }
        });

        lieux_depart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchTown.class);
                startActivity(intent);
            }
        });

        lieux_depart.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    Intent intent = new Intent(getActivity(), SearchTown.class);
                    startActivity(intent);
                }
            }
        });

        lieux_darrivee.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    Intent intent = new Intent(getActivity(), SearchTown.class);
                    startActivity(intent);
                }
            }
        });


        date_annoonce.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    // create the datePickerFragment
                    AppCompatDialogFragment newFragment = new DatePickerFragment();
                    // set the targetFragment to receive the results, specifying the request code
                    newFragment.setTargetFragment(Accueil.this, REQUEST_CODE);
                    // show the datePicker
                    newFragment.show(fm, "datePicker");
                }
            }
        });

        date_annoonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create the datePickerFragment
                AppCompatDialogFragment newFragment = new DatePickerFragment();
                // set the targetFragment to receive the results, specifying the request code
                newFragment.setTargetFragment(Accueil.this, REQUEST_CODE);
                // show the datePicker
                newFragment.show(fm, "datePicker");
            }
        });

        heure_depart.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    // create the datePickerFragment
                    AppCompatDialogFragment newFragment = new TimePickerFragment();
                    // set the targetFragment to receive the results, specifying the request code
                    newFragment.setTargetFragment(Accueil.this, REQUEST_CODE12);
                    // show the datePicker
                    newFragment.show(fm, "selectedTime");
                }
            }
        });

        heure_depart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create the datePickerFragment
                AppCompatDialogFragment newFragment = new TimePickerFragment();
                // set the targetFragment to receive the results, specifying the request code
                newFragment.setTargetFragment(Accueil.this, REQUEST_CODE12);
                // show the datePicker
                newFragment.show(fm, "selectedTime");
            }
        });

        heure_darrivee.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    // create the datePickerFragment
                    AppCompatDialogFragment newFragment = new TimePickerFragment();
                    // set the targetFragment to receive the results, specifying the request code
                    newFragment.setTargetFragment(Accueil.this, REQUEST_CODE13);
                    // show the datePicker
                    newFragment.show(fm, "selectedTime");
                }
            }
        });
        heure_darrivee.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // create the datePickerFragment
                        AppCompatDialogFragment newFragment = new TimePickerFragment();
                        // set the targetFragment to receive the results, specifying the request code
                        newFragment.setTargetFragment(Accueil.this, REQUEST_CODE13);
                        // show the datePicker
                        newFragment.show(fm, "selectedTime");
                    }
        });



        return  bossmaleo;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // check for the results
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // get date from string
            selectedDate = data.getStringExtra("selectedDate");
            // set the value of the editText
            date_annoonce.setText(selectedDate);
        }else if (requestCode == REQUEST_CODE12 && resultCode == Activity.RESULT_OK) {
            selectedDate1 = data.getStringExtra("selectedTime");
            heure_depart.setText(selectedDate1+":00");
        }else if (requestCode == REQUEST_CODE13 && resultCode == Activity.RESULT_OK) {
            selectedDate2 = data.getStringExtra("selectedTime");
            heure_darrivee.setText(selectedDate2+":00");
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Accueil.OnFragmentInteractionListener) {
            mListener = (Accueil.OnFragmentInteractionListener) context;
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

        String _depart_voyage = lieux_depart.getText().toString();
        String _arrivee_voyage = lieux_darrivee.getText().toString();
        String _date_voyage = date_annoonce.getText().toString();
        String _prix_transaction = prix_transaction.getText().toString();
        String _poids = poids.getText().toString();
        String _heure_depart = heure_depart.getText().toString();
        String _heure_darrivee = heure_darrivee.getText().toString();


        if (_depart_voyage.isEmpty()) {
            lieux_depart.setError("Veuillez remplir la ville de depart svp !");
            valid = false;
        } else {
            lieux_depart.setError(null);
        }

        if (_arrivee_voyage.isEmpty()) {
            lieux_darrivee.setError("Veuillez remplir la ville d'arrivee svp!");
            valid = false;
        }else {
            lieux_darrivee.setError(null);
        }

        if (_date_voyage.isEmpty()) {
            date_annoonce.setError("Veuillez remplir la date de voyage svp !");
            valid = false;
        }else {
            date_annoonce.setError(null);
        }

        if (_prix_transaction.isEmpty()) {
            prix_transaction.setError("Veuillez indiquer le prix de la transaction svp !");
            valid = false;
        }else {
            prix_transaction.setError(null);
        }

        if (_poids.isEmpty()) {
            poids.setError("Veuillez indiquer le poids par kilo svp !");
            valid = false;
        }else {
            poids.setError(null);
        }

        if (_heure_depart.isEmpty()) {
            date_annoonce.setError("Veuillez remplir l'heure de  depart svp !");
            valid = false;
        }else {
            date_annoonce.setError(null);
        }

        if (_heure_darrivee.isEmpty()) {
            heure_darrivee.setError("Veuillez remplir l'heure d'arrivee svp !");
            valid = false;
        }else {
            heure_darrivee.setError(null);
        }


        return valid;
    }

}