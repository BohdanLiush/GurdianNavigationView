package com.example.bohdan.gurdiannavigationview;


import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.PersonalViewHolder>  {


    public List<Result> results;

    public RecyclerViewAdapter(List<Result> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public PersonalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list_item, parent, false);
        PersonalViewHolder pvh = new PersonalViewHolder(v);

        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalViewHolder holder, int position) {
        holder.textView.setText(results.get(position).getSectionName());
        holder.textView2.setText(results.get(position).getWebTitle());
        Picasso.get()
                .load(results.get(position).getFields().getThumbnail())
                .into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        if (results != null && !results.isEmpty()) {
            return results.size();
        }
        return 0;
    }

    public class PersonalViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView textView;
        TextView textView2;
        ImageView imageView;



        public PersonalViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardviewNew);
            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }


}
