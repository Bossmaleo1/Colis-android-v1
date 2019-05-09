package com.colis.android.colis.connInscript;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.colis.android.colis.model.Const;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class formInscriptSms extends AppCompatActivity {

    private Resources res;
    private Toolbar toolbar;
    private Button send;
    private EditText email;
    private String code_de_verfication;
    private JSONObject reponse;
    private ProgressDialog pDialog;
    private EditText code_verification_edittext;
    private LinearLayout code_block;
    private LinearLayout email_block;
    private TextView message;
    private String getEmail;
    private String getCode_de_verfication;
    private CoordinatorLayout coordinatorLayout;
    private Random random = new Random();
    private String code_generer;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forminscriptsms);

        res = getResources();
        toolbar = findViewById(R.id.toolbar);
        intent = getIntent();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Inscription 2/4");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        email = findViewById(R.id.email);
        send = findViewById(R.id.send);
        code_verification_edittext = findViewById(R.id.code_editext);
        message =  findViewById(R.id.message);
        email_block =  findViewById(R.id.email_block);
        code_block =  findViewById(R.id.code_block);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()==true) {
                    if (code_verification_edittext.getText().length()==0) {
                        pDialog = new ProgressDialog(formInscriptSms.this);
                        pDialog.setMessage("Connexion en cours...");
                        pDialog.setIndeterminate(false);
                        pDialog.setCancelable(false);
                        pDialog.show();
                        getCode_de_verfication = code_de_verfication;
                        volley_de_verification_de_email();
                    }else if(code_verification_edittext.getText().length()!=0)
                    {

                        if (String.valueOf(code_generer).trim().equals(String.valueOf(code_verification_edittext.getText()).trim())) {
                            Intent intent1 = new Intent(getApplicationContext(), formInscript2.class);
                            intent1.putExtra("phonenumber",getEmail);
                            intent1.putExtra("code",intent.getStringExtra("code"));
                            intent1.putExtra("email",intent.getStringExtra("email"));
                            startActivity(intent1);
                        } else {
                            Toast.makeText(formInscriptSms.this,"Vous avez introduit le mauvais code de verification !!! ",Toast.LENGTH_LONG).show();
                        }
                    }
                }


            }
        });
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

    public boolean validate() {
        boolean valid = true;

        String _email = email.getText().toString();

        if (_email.isEmpty()) {
            email.setError(res.getString(R.string.email_error));
            valid = false;
        } else {
            email.setError(null);
        }

        return valid;
    }

    private void volley_de_verification_de_email()
    {
        code_generer = String.valueOf(random.nextInt(8)+""+random.nextInt(8)+""+random.nextInt(8)+""+random.nextInt(8));
        String message_envoyer = "Le code de verification est : "+code_generer;
        String url_connexion = "https://api.smsbox.fr/api.php?apikey=pub-ad1746a3c1fa0266937010c56e18e0b0-7dd7c66d-e54b-4b17-9c1d-73c4215482c1&msg="+
                message_envoyer+"&dest=" +
                String.valueOf(email.getText())+"&mode=Expert";

        // Const.dns+"/colis/ColisApiOthers/send_mail.php?email="+String.valueOf(email.getText()) ;
        this.getEmail = String.valueOf(email.getText());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_connexion,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();
                        code_block.setVisibility(View.VISIBLE);
                        message.setVisibility(View.VISIBLE);
                        email_block.setVisibility(View.GONE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();

                        if(error instanceof ServerError)
                        {
                            Toast.makeText(formInscriptSms.this,"Une erreur au niveau du serveur viens de survenir ",Toast.LENGTH_LONG).show();
                            email.setText("");
                            //password.setText("");
                        }else if(error instanceof NetworkError)
                        {
                            Toast.makeText(formInscriptSms.this,"Une erreur  du réseau viens de survenir ",Toast.LENGTH_LONG).show();
                            email.setText("");
                            //password.setText("");
                        }else if(error instanceof AuthFailureError)
                        {
                            Toast.makeText(formInscriptSms.this,"Une erreur d'authentification réseau viens de survenir ",Toast.LENGTH_LONG).show();
                            //email.setText("");
                            //password.setText("");
                        }else if(error instanceof ParseError)
                        {
                            Toast.makeText(formInscriptSms.this,"Une erreur  du réseau viens de survenir ",Toast.LENGTH_LONG).show();
                            //email.setText("");
                            //password.setText("");
                        }else if(error instanceof NoConnectionError)
                        {
                            Toast.makeText(formInscriptSms.this,"Une erreur  du réseau viens de survenir, veuillez revoir votre connexion internet ",Toast.LENGTH_LONG).show();
                            //email.setText("");
                            //password.setText("");
                        }else if(error instanceof TimeoutError)
                        {
                            Toast.makeText(formInscriptSms.this,"Le delai d'attente viens d'expirer,veuillez revoir votre connexion internet ! ",Toast.LENGTH_LONG).show();
                            //email.setText("");
                            //password.setText("");
                        }else
                        {

                            Toast.makeText(formInscriptSms.this,"Une erreur  du réseau viens de survenir ",Toast.LENGTH_LONG).show();
                            //email.setText("");
                            //password.setText("");
                        }
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        setResult(RESULT_OK, i);
        finish();
    }

}
