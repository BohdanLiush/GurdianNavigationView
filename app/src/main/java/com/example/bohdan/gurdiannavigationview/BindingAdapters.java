package com.example.bohdan.gurdiannavigationview;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class BindingAdapters {

    @BindingAdapter("android:src")
    public static void loadImage(ImageView view, String string) {
        Picasso.get()
                .load(string)
                .into(view);
    }

    @BindingAdapter("bind:items")
    public static void listBindGridview (RecyclerView view,  List<Result> results){
        RecyclerViewAdapter adapterNew = new RecyclerViewAdapter(results);
        view.setAdapter(adapterNew);
        adapterNew.notifyDataSetChanged();
    }
}
