package com.colis.android.colis.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.colis.android.colis.R;
import com.colis.android.colis.connInscript.Connexion;
import com.colis.android.colis.model.Const;
import com.colis.android.colis.model.Database.SessionManager;
import com.colis.android.colis.model.dao.DatabaseHandler;
import com.colis.android.colis.model.data.User;
import com.colis.android.colis.model.data.ValidationItem;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationAdapter extends RecyclerView.Adapter<ValidationAdapter.MyViewHolder>  implements MenuItem.OnMenuItemClickListener{


    List<ValidationItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    private String etat;
    private ProgressDialog pDialog;
    public String id_validation;
    public String id_emmeteur;
    public String message;
    private SessionManager session;
    private User user;
    private DatabaseHandler database;


    public ValidationAdapter(Context context,List<ValidationItem> data)
    {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
        database = new DatabaseHandler(context);
        session = new SessionManager(context);
        user = database.getUSER(Integer.valueOf(session.getUserDetail().get(SessionManager.Key_ID)));
    }

    public void delete(int position)
    {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public ValidationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.validation_adapter,parent,false);
        ValidationAdapter.MyViewHolder holder = new ValidationAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ValidationAdapter.MyViewHolder holder, int position) {
        final ValidationItem current = data.get(position);
        holder.title.setText(current.getPrenom_emmetteur()+" "+current.getNom_emmetteur());
        holder.title1.setText(current.getDate_validation());
        holder.picture.setImageResource(R.drawable.ic_profile_colorier);
        holder.poids.setText(current.getNombre_kilo()+" Kg");
        holder.description.setText(current.getDescription());
        holder.telephone.setText(current.getTelephone());
        if(!current.getPhoto_emmetteur().equals("null")) {
            Glide.with(current.getContext())
                    .load(Const.dns+"/colis/uploads/photo_de_profil/" + current.getPhoto_emmetteur())
                    .into(holder.picture);
        }else
        {
            holder.picture.setImageResource(R.drawable.ic_profile_colorier);
        }

        holder.valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog = new ProgressDialog(context);
                pDialog.setMessage("Connexion en cours...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();
                String message = "Votre demande d'expedition de colis vient d'etre accepter par l'utilisateur "
                        +user.getPRENOM()+" "+user.getNOM()+" son numero de telephone est le : "+current.getTelephone();
                Connexion(String.valueOf(current.getId()),String.valueOf(user.getID()),message,"1",current.getTelephone(),holder.user_first_name);
            }
        });

        holder.annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Vous avez cliquer sur Annuler",Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;
        TextView title1;
        ImageView picture;
        TextView description;
        TextView poids;
        TextView telephone;
        Button valider;
        Button annuler;
        CardView user_first_name;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            title1 = itemView.findViewById(R.id.title1);
            picture = itemView.findViewById(R.id.icon);
            description = itemView.findViewById(R.id.contenu);
            poids = itemView.findViewById(R.id.contenu_ville_arrivee_block);
            telephone = itemView.findViewById(R.id.telephone);
            valider = itemView.findViewById(R.id.send);
            annuler = itemView.findViewById(R.id.send2);
            user_first_name = itemView.findViewById(R.id.user_first_name);        }
    }


    private void Connexion(String id_validation, String id_emmeteur, final String message, String etat, final String phone, final CardView card)
    {
        String url_connexion = Const.dns+"/colis/ColisApi/public/api/ValidationAnnulationDemandeExpedition?id_validation="+id_validation
                +"&id_emmeteur="+id_emmeteur+"&message="+message+"&etat="+etat;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_connexion,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();
                        card.setVisibility(View.GONE);
                        Toast.makeText(context,"Votre demande a ete valider avec succes !!",Toast.LENGTH_LONG).show();
                        volley_de_sms_notification(message,phone);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();
                        Toast.makeText(context,"Une erreur au niveau du serveur viens de survenir ",Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }


    private void volley_de_sms_notification(String message,String phone)
    {
        String url_connexion = "https://api.smsbox.fr/api.php?apikey=pub-ad1746a3c1fa0266937010c56e18e0b0-7dd7c66d-e54b-4b17-9c1d-73c4215482c1&msg="+
                message+"&dest=" +
                phone+"&mode=Expert";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_connexion,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }




}
