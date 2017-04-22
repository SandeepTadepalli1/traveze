package com.example.se.travezeandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MyAccountActivity extends AppCompatActivity {

    MyPreference myPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myPreference = MyPreference.getInstance(getApplicationContext());

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
        Log.i("Main activity","menu item clicked");

        if (id == R.id.action_my_account) {
            myPreference.startMyAccountActivity();
        } else if(id == R.id.action_logout) {
            Log.i("Menu Item","logout");
            myPreference.logout();
            myPreference.startLoginActivity();
        } else if(id == R.id.action_login){
            myPreference.startLoginActivity();
        } else if(id == R.id.action_admin){
            myPreference.startAdminActivity();
        } else if(id == R.id.action_manager){
            myPreference.startManagerActivity();
        }


        return super.onOptionsItemSelected(item);
    }

}
