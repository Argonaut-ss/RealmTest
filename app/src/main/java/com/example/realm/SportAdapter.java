package com.example.realm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.MyViewHolder> {

    private List<SportModel> sportModel;
    Context context;

    public SportAdapter(Context context, List<SportModel> mahasiswaModels){
        this.context = context;
        this.sportModel = mahasiswaModels;
    }

    @Override
    public SportAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final SportModel model = sportModel.get(position);
        holder.sportnama.setText(model.getSportName());
        holder.sportformat.setText(model.getFormatSport());
        holder.sportdescription.setText(model.getSportDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("id", model.getId());
                intent.putExtra("Nama", model.getSportName());
                intent.putExtra("Format", model.getFormatSport());
                intent.putExtra("Deskripsi", model.getSportDescription());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sportModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        EditText sportnama, sportformat, sportdescription;

        public MyViewHolder(View itemView){
            super(itemView);
            sportnama = itemView.findViewById(R.id.namaSport);
            sportformat = itemView.findViewById(R.id.formatSport);
            sportdescription = itemView.findViewById(R.id.deskripsiSport);
        }
    }

}
