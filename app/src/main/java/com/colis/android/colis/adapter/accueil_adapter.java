package com.colis.android.colis.adapter;

import com.colis.android.colis.R;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.colis.android.colis.R;
import com.colis.android.colis.model.Const;
import com.colis.android.colis.model.data.Annonce;
import com.colis.android.colis.model.data.AnnonceItem;

import java.util.Collections;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class accueil_adapter extends RecyclerView.Adapter<accueil_adapter.MyViewHolder>  implements MenuItem.OnMenuItemClickListener{


    List<Annonce> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;


    public accueil_adapter(Context context,List<Annonce> data)
    {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
    }

    public void delete(int position)
    {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public accueil_adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.accueil_adapter,parent,false);
        accueil_adapter.MyViewHolder holder = new accueil_adapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final accueil_adapter.MyViewHolder holder, int position) {
        final Annonce current = data.get(position);
        holder.title.setText(current.getNOM_USER());
        holder.title1.setText(current.getDATE_ANNONCE());
        if(!current.getPHOTO_USER().equals("null")) {
            Glide.with(current.getContext())
                    .load(Const.dns+"/colis/uploads/photo_de_profil/" + current.getPHOTO_USER())
                    .into(holder.picture);
        }else
        {
            holder.picture.setImageResource(R.drawable.ic_profile_colorier);
        }
        holder.depart.setText(current.getVILLE_DEPART());
        holder.arrivee.setText(current.getVILLE_ARRIVEE());
        holder.Prix.setText(current.getPRIX()+" euros/Kg");
        holder.dateannonce.setText(current.getDATE_ANNONCE_VOYAGE());
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
        TextView depart;
        TextView arrivee;
        TextView dateannonce;
        TextView Prix;
        ImageView picture;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            title1 = itemView.findViewById(R.id.title1);
            depart =  itemView.findViewById(R.id.contenu);
            arrivee =  itemView.findViewById(R.id.contenu_ville_arrivee_block);
            dateannonce = itemView.findViewById(R.id.contenu_heure_depart);
            Prix = itemView.findViewById(R.id.contenu_heure_arrivee);
            picture = itemView.findViewById(R.id.icon);
        }
    }


}
