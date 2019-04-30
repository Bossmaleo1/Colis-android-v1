package com.colis.android.colis.connInscript;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.colis.android.colis.R;
import com.colis.android.colis.appviews.Home;
import com.colis.android.colis.model.Database.SessionManager;

public class MainActivity extends AppCompatActivity {

    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new SessionManager(this);
        Thread background = new Thread() {
            public void run() {

                try {

                    sleep(3*1000);
                    if(!session.IsLoggedIn()) {
                        Intent i = new Intent(getApplicationContext(), Connexion.class);
                        startActivity(i);
                        finish();
                    }else
                    {

                        Intent i = new Intent(getApplicationContext(), Home.class);
                        startActivity(i);
                        finish();
                    }


                } catch (Exception e) {

                }
            }
        };

        background.start();
    }


}
