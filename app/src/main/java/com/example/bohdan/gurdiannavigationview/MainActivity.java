package com.example.bohdan.gurdiannavigationview;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Serializable, CallbackClass.Callback {

    NetworkManager networkManager = new NetworkManager();
    FragmentA fragmentA = new FragmentA();
    FragmentD fragmentD = new FragmentD();
    Model model;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        try {
            loadFragment();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void loadFragment() throws InterruptedException {
        s = "tennis";
        model = getModelNetworkManagerToFragment(s);
        android.support.v4.app.Fragment fragment = null;
        fragment = FragmentA.newInstanceModel(model);

        FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.appbar_base, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        android.support.v4.app.Fragment fragment = null;

        switch (id){
            case R.id.nav_camera:
                s = "football";
                try {
                    model = getModelNetworkManagerToFragment(s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                fragment = FragmentA.newInstanceModel(model);
                getSupportActionBar().setTitle("Football");
                break;

            case R.id.nav_gallery:
                s = "business";
                try {
                    model = getModelNetworkManagerToFragment(s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                fragment = FragmentA.newInstanceModel(model);
                getSupportActionBar().setTitle("Business");
                break;

            case R.id.nav_slideshow:
                s = "world news";
                try {
                    model = getModelNetworkManagerToFragment(s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                fragment = FragmentA.newInstanceModel(model);
                getSupportActionBar().setTitle("Tennis");
                break;
        }


         /*else if (id == R.id.nav_slideshow){} {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.appbar_base, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public Model callingBack(String string) throws InterruptedException {
        networkManager.loadStringFromMain(string);
        return networkManager.model;
    }

    @Override
    public void callingBackSecondFr(String s) {
        Bundle argument = new Bundle();
        argument.putSerializable("ForCastDay", s);

        fragmentD.setArguments(argument);

        android.app.FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.appbar_base, fragmentD).addToBackStack(null).commit();
    }

    @Override
    public void callingBackButton() {
        android.app.FragmentManager fm = getFragmentManager();
        fm.popBackStack();
    }

    public Model getModelNetworkManagerToFragment(String s) throws InterruptedException {
        networkManager.loadStringFromMain(s);
        return networkManager.model;
    }
}
