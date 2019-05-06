package com.colis.android.colis.appviews;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.colis.android.colis.R;

public class DetailsAnnonces extends AppCompatActivity {

    private Toolbar toolbar;
    private Resources res;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_annonces);
        toolbar =  findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setTitle("Details Annonce");
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

}
