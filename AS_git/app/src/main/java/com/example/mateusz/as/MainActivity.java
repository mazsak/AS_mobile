package com.example.mateusz.as;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.mateusz.as.barcode.BarcodeFragment;
import com.example.mateusz.as.map.MapFragment;
import com.example.mateusz.as.saveModelFragments.CowshedFragment;
import com.example.mateusz.as.saveModelFragments.TeamFragment;
import com.example.mateusz.as.show.ListFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String OPEN_WEATHER_MAP_API = "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preparation();

    }

    public void preparation() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        chosenFragment(0);
    }

    public void fabActionListener(View view) {
        chosenFragment(1);
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_show_cowshed:
                chosenFragment(0);
                break;
            case R.id.nav_barcode_search:
                chosenFragment(1);
                break;
            case R.id.nav_add_cowshed:
                chosenFragment(2);
                break;
            case R.id.nav_add_group:
                chosenFragment(3);
                break;
            case R.id.nav_map:
                chosenFragment(4);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void loadingFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container_fragment, fragment);
        ft.addToBackStack("tag");
        ft.commit();
    }

    public void chosenFragment(int i) {
        switch (i) {
            case 0:
                loadingFragment(new ListFragment());
                break;
            case 1:
                loadingFragment(new BarcodeFragment());
                break;
            case 2:
                loadingFragment(new CowshedFragment());
                break;
            case 3:
                loadingFragment(new TeamFragment());
                break;
            case 4:
                loadingFragment(new MapFragment());
                break;
        }
    }


}
