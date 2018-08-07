package com.example.bohdan.gurdiannavigationview;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import java.util.List;

public class FragmentD extends android.app.Fragment {
    String s;
    private WebView webView = null;
    Button button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(
                R.layout.fragment_web_view_d, container, false);



        Bundle bundle = getArguments();
        if (bundle!=null){
            s = (String) bundle.getSerializable("ForCastDay");
        }

        this.webView = rootView.findViewById(R.id.webview);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        webView.setHorizontalScrollBarEnabled(true);
        webView.setVerticalScrollBarEnabled(true);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.getSettings().setLoadWithOverviewMode(true);


        webView.setWebViewClient(new WebViewClient());



        webView.loadUrl(s);

        //button = rootView.findViewById(R.id.button);

       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activityHome = (MainActivity) container.getContext();
                CallbackClass callbacks = new CallbackClass();
                callbacks.registerCallBack(activityHome);
                callbacks.buttonBack();
            }
        });*/




        return rootView;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }*/


}
