package com.colis.android.colis.appviews;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.colis.android.colis.R;
import com.colis.android.colis.model.Const;
import com.colis.android.colis.model.Database.SessionManager;
import com.colis.android.colis.model.dao.DatabaseHandler;
import com.colis.android.colis.model.data.Annonce;
import com.colis.android.colis.model.data.User;

public class Profil extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Resources res;
    private Intent intent;
    private ImageView pictureuser;
    private DatabaseHandler database;
    private SessionManager session;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailsannonceur);
        toolbar = findViewById(R.id.toolbar);
        res = getResources();
        intent = getIntent();
        database = new DatabaseHandler(this);
        session = new SessionManager(this);
        user = database.getUSER(Integer.valueOf(session.getUserDetail().get(SessionManager.Key_ID)));
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(user.getPRENOM()+" "+user.getNOM());
        collapsingToolbarLayout.setContentScrimColor(res.getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);
        pictureuser = findViewById(R.id.backdrop);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(!user.getPHOTO().equals("null")) {
            Glide.with(this)
                    .load(Const.dns+"/colis/uploads/photo_de_profil/" + user.getPHOTO())
                    .into(pictureuser);
        }else
        {
            pictureuser.setImageResource(R.drawable.ic_profile_colorier);
        }
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
