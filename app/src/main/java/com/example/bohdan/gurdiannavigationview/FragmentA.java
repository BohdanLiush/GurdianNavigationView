package com.example.bohdan.gurdiannavigationview;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bohdan.gurdiannavigationview.databinding.FragmentBinding;

import java.io.IOException;
import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentA extends android.support.v4.app.Fragment implements Serializable{

    public Model model;
    //public String s = "football";
    FragmentBinding fragmentBinding;

    static FragmentA newInstanceModel(Model model) {
        // Base fragment to reuse
        FragmentA fragment = new FragmentA();

        // Initialize bundle to store arguments
        Bundle bundle = new Bundle(1);

        // String url parameter to pass arguments when recreating {@link TopStoriesFragment}
        bundle.putSerializable("tits", model);

        // Save arguments to the fragment instance to be called upon later
        fragment.setArguments(bundle);

        // Create and return {@link TopStoriesFragment} with the passed-in string parameter
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragmentBinding = DataBindingUtil.inflate(inflater,R.layout.fragment, container,false);

        Bundle bundle = getArguments();
        if (bundle!=null){
            model = (Model) bundle.getSerializable("tits");
            System.out.println("hello");
        }

        fragmentBinding.setModelFor(model);

        /* MainActivity activityHome = (MainActivity) container.getContext();
        CallbackClass callbacks = new CallbackClass();
        callbacks.registerCallBack(activityHome);

        try {
            model = callbacks.loadObject(s);
            System.out.println("good");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        fragmentBinding.recyclerView.setLayoutManager(layoutManager);

        fragmentBinding.recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(container.getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                MainActivity activityHome = (MainActivity) view.getContext();
                CallbackClass callbacks = new CallbackClass();
                callbacks.registerCallBack(activityHome);
                callbacks.loadObjectSecondFr(model.getResponse().getResults().get(position).getWebUrl());

            }
        }));

        return fragmentBinding.getRoot();
    }
}
