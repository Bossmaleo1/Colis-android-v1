package com.colis.android.colis.connInscript;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.colis.android.colis.R;
import com.colis.android.colis.appviews.Home;
import com.colis.android.colis.model.Const;
import com.colis.android.colis.model.Database.SessionManager;
import com.colis.android.colis.model.dao.DatabaseHandler;
import com.colis.android.colis.model.data.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Connexion extends AppCompatActivity {


    private TextView about;
    private Resources res;
    private LinearLayout aboutblock;
    private Toolbar toolbar;
    private TextView passwordforget;
    private Button Connexion;
    private EditText email;
    private EditText password;
    private Snackbar snackbar;
    private CoordinatorLayout coordinatorLayout;
    private ProgressDialog pDialog;
    private JSONObject reponse;
    private JSONObject data;
    private int succes;
    private SessionManager session;
    private DatabaseHandler database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        res = getResources();
        about = findViewById(R.id.about);
        aboutblock = findViewById(R.id.aboutblock);
        passwordforget = findViewById(R.id.passforget);
        Connexion = findViewById(R.id.connexion);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(res.getString(R.string.app_name));
        //initialisation des datas conteneurs
        session = new SessionManager(this);
        database = new DatabaseHandler(this);

        Connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pDialog = new ProgressDialog(Connexion.this);
                pDialog.setMessage("Connexion en cours...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();
                Connexion();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //je suis entrain de souligner le bouton s'inscrire
        getMenuInflater().inflate(R.menu.menu_inscript, menu);
        /*SpannableString content = new SpannableString(res.getString(R.string.inscript));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        Inscript = menu.findItem(R.id.inscript);
        Inscript.setTitle(content);*/
        return true;
    }

    //gère le click sur une action de l'ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.inscript:
                Intent intent = new Intent(getApplicationContext(),formInscript1.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void Connexion()
    {
        String url_connexion = Const.dns+"/colis/ColisApi/public/api/connexion?email=" + String.valueOf(email.getText().toString())+"&password="+ String.valueOf(password.getText().toString());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_connexion,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();
                        showJSON(response);
                        if(succes!=1) {
                            email.setText("");
                            password.setText("");
                            Toast.makeText(Connexion.this, "votre mot de passe ou votre adresse e-mail est incorrecte" , Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();

                        if(error instanceof ServerError)
                        {
                            Toast.makeText(Connexion.this,"Une erreur au niveau du serveur viens de survenir ",Toast.LENGTH_LONG).show();
                            email.setText("");
                            password.setText("");
                        }else if(error instanceof NetworkError)
                        {
                            Toast.makeText(Connexion.this,"Une erreur  du réseau viens de survenir ",Toast.LENGTH_LONG).show();
                            email.setText("");
                            password.setText("");
                        }else if(error instanceof AuthFailureError)
                        {
                            Toast.makeText(Connexion.this,"Une erreur d'authentification réseau viens de survenir ",Toast.LENGTH_LONG).show();
                            email.setText("");
                            password.setText("");
                        }else if(error instanceof ParseError)
                        {
                            Toast.makeText(Connexion.this,"Une erreur  du réseau viens de survenir ",Toast.LENGTH_LONG).show();
                            email.setText("");
                            password.setText("");
                        }else if(error instanceof NoConnectionError)
                        {
                            Toast.makeText(Connexion.this,"Une erreur  du réseau viens de survenir, veuillez revoir votre connexion internet ",Toast.LENGTH_LONG).show();
                            email.setText("");
                            password.setText("");
                        }else if(error instanceof TimeoutError)
                        {
                            Toast.makeText(Connexion.this,"Le delai d'attente viens d'expirer,veuillez revoir votre connexion internet ! ",Toast.LENGTH_LONG).show();
                            email.setText("");
                            password.setText("");
                        }else
                        {

                            Toast.makeText(Connexion.this,"Une erreur  du réseau viens de survenir ", Toast.LENGTH_LONG).show();
                            email.setText("");
                            password.setText("");
                        }
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("email",email.getText().toString());
                params.put("password",password.getText().toString());
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void showJSON(String response)
    {
        try {
            reponse = new JSONObject(response);
            succes = reponse.getInt("succes");
            if(succes==1)
            {
                session.createLoginSession(reponse.getInt("id"));
                User user = new User(reponse.getInt("id"),reponse.getString("nom")
                        ,reponse.getString("prenom"),reponse.getString("datenaissance")
                        ,reponse.getString("sexe"),reponse.getString("email"),
                        reponse.getString("photo"),reponse.getString("keypush")//ici c'est le keypush, il va falloir l'ajouter sur le script php et ajouter libelle prob
                        ,reponse.getString("langue"),reponse.getString("etat")
                        ,reponse.getString("pays"),reponse.getString("ville")
                        ,reponse.getString("telephone"));
                database.addUSER(user);
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
