package com.example.bohdan.gurdiannavigationview;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentB extends android.support.v4.app.Fragment  {

    RecyclerView recyclerView;
    public Model model;
    public String s = "business";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment, container, false);


        recyclerView = rootView.findViewById(R.id.recycler_view);



        MainActivity activityHome = (MainActivity) container.getContext();
        CallbackClass callbacks = new CallbackClass();
        callbacks.registerCallBack(activityHome);

        try {
            model = callbacks.loadObject(s);
            System.out.println("good");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(model.getResponse().getResults());
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(container.getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                MainActivity activityHome = (MainActivity) view.getContext();
                CallbackClass callbacks = new CallbackClass();
                callbacks.registerCallBack(activityHome);
                callbacks.loadObjectSecondFr(model.getResponse().getResults().get(position).getWebUrl());

            }
        }));


        return rootView;
    }
}
