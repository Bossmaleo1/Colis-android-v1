package com.colis.android.colis.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.colis.android.colis.R;
import com.colis.android.colis.model.data.TownItem;
import java.util.Collections;
import java.util.List;

public class search_town_adapter  extends RecyclerView.Adapter<search_town_adapter.MyViewHolder>  {

    List<TownItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public search_town_adapter(Context context,List<TownItem> data)
    {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position)
    {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public search_town_adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.searchadapter,parent,false);
        search_town_adapter.MyViewHolder holder = new search_town_adapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(search_town_adapter.MyViewHolder holder, int position) {
        TownItem current = data.get(position);
        holder.title.setText(current.getLibelle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
        }
    }
}
