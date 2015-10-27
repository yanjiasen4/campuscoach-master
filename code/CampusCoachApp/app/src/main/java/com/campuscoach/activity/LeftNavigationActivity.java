package com.campuscoach.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuItem;

import com.campuscoach.campuscoachapp.R;


public class LeftNavigationActivity extends Activity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.left_navigation);
        drawerLayout = (DrawerLayout) findViewById(R.id.left_drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.left_navigation);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                System.out.println("setting--------");
                //drawerLayout.closeDrawers();
                menuItem.setChecked(true);
                //((DrawerLayout) findViewById(R.id.drawerlayout)).closeDrawers();
                //if(R.id.setting){
                 //   startActivity(new Intent(LeftNavigationActivity.this, SettingsActivity.class));
                //}
                return true;
            }
        });
    }
}
