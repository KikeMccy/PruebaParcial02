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
import com.example.pruebaparcial02.modelo.ModelEdiciones;


import java.util.List;

public class AdapterEdiciones extends RecyclerView.Adapter<AdapterEdiciones.ViewHolder> {

    private List<ModelEdiciones> modelEdicionesList ;
    private Context context;
    public AdapterEdiciones(List<ModelEdiciones> modelActivityList, Context context) {
        this.modelEdicionesList = modelActivityList;
        this.context = context;
    }

    @Override
    public AdapterEdiciones.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view_ediciones,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ModelEdiciones modelActivity = modelEdicionesList.get(position);

        String currentUrl = modelActivity.getCover();
        Glide.with(context)
                .load(currentUrl)
                .into(holder.cover);

        holder.issue_id.setText(modelActivity.getIssue_id());
        holder.title.setText(modelActivity.getTitle());
        holder.volume.setText(Html.fromHtml(modelActivity.getVolume()));
        holder.number.setText(modelActivity.getNumber());
        holder.year.setText(modelActivity.getYear());
        holder.doi.setText(modelActivity.getDoi());

        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.itemView.getContext(), EdicionesRevistas.class);
                intent.putExtra("itemDetail", modelActivity);
                holder.itemView.getContext().startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return modelEdicionesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        /*public TextView journal_id;
        ImageView portada;
        public TextView name;
        public TextView description;*/
        public TextView issue_id;
        ImageView cover;
        public TextView  title;
        public TextView volume;
        public TextView  number;
        public TextView year;
        public TextView  doi;
        public ViewHolder(View itemView) {
            super(itemView);
            /*journal_id=itemView.findViewById(R.id.tvJournalID);
            portada=(ImageView) itemView.findViewById(R.id.ivPortada);
            name=itemView.findViewById(R.id.tvName);
            description=itemView.findViewById(R.id.tvDescription);*/
            issue_id=itemView.findViewById(R.id.tvIdEdiciones);
            cover=(ImageView) itemView.findViewById(R.id.ivCoverEdiciones);
            title=itemView.findViewById(R.id.tvTitleEdiciones);
            volume=itemView.findViewById(R.id.tvVolEdiciones);
            number=itemView.findViewById(R.id.tvNumEdiciones);
            year=itemView.findViewById(R.id.tvYearEdiciones);
            doi=itemView.findViewById(R.id.tvDoiEdiciones);
        }
    }
}
