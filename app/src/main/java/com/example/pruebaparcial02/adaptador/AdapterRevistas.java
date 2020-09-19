package com.example.pruebaparcial02.adaptador;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pruebaparcial02.EdicionesRevistas;
import com.example.pruebaparcial02.R;
import com.example.pruebaparcial02.modelo.ModelRevistas;

import java.io.Serializable;
import java.util.List;

public class AdapterRevistas extends RecyclerView.Adapter<AdapterRevistas.ViewHolder> {

    private List<ModelRevistas> modelRevistasList ;
    private Context context;
    public AdapterRevistas(List<ModelRevistas> modelActivityList, Context context) {
        this.modelRevistasList = modelActivityList;
        this.context = context;
    }

    @Override
    public AdapterRevistas.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view_revistas,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ModelRevistas modelActivity = modelRevistasList.get(position);

        String currentUrl = modelActivity.getPortada();
        Glide.with(context)
                .load(currentUrl)
                .into(holder.portada);

        holder.journal_id.setText(modelActivity.getJournal_id());
        holder.name.setText(modelActivity.getName());
        holder.description.setText(Html.fromHtml(modelActivity.getDescription()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.itemView.getContext(), EdicionesRevistas.class);
                intent.putExtra("itemDetail", modelActivity);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelRevistasList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView journal_id;
        ImageView portada;
        public TextView name;
        public TextView description;

        public ViewHolder(View itemView) {
            super(itemView);
            journal_id=itemView.findViewById(R.id.tvJournalID);
            portada=(ImageView) itemView.findViewById(R.id.ivPortada);
            name=itemView.findViewById(R.id.tvName);
            description=itemView.findViewById(R.id.tvDescription);
        }
    }
}
