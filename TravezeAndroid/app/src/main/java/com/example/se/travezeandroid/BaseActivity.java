package com.example.se.travezeandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class BaseActivity extends AppCompatActivity {
    protected MyPreference myPreference;
    private static final String TAG = BaseActivity.class.getName();

    private RequestQueue queue;
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
        Log.i(TAG,"menu item clicked");

        if (id == R.id.action_my_account) {
            startMyAccountActivity();
        } else if(id == R.id.action_logout) {
            Log.i("Menu Item","logout");
            myPreference.logout();
            startLoginActivity();
        } else if(id == R.id.action_login){
            startLoginActivity();
        } else if(id == R.id.action_admin){
            myPreference.startAdminActivity();
        } else if(id == R.id.action_manager){
            myPreference.startManagerActivity();
        }


        return super.onOptionsItemSelected(item);
    }

    protected void startLoginActivity(){
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
    }

    protected void startMyAccountActivity(){
        Intent intent = new Intent(getApplicationContext(),MyAccountActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
    }

    protected void startMainActivity(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
    }

    protected void createShortToast(String content){
        Toast.makeText(getBaseContext(), content, Toast.LENGTH_SHORT).show();
    }

    protected void createLongToast(String contest){
        Toast.makeText(getBaseContext(), contest, Toast.LENGTH_LONG).show();
    }

    protected void sendRequest(MyRequest req){
        if(queue == null) {
            queue = Volley.newRequestQueue(getApplicationContext());
        }else {
            queue.add(req);
        }
    }

}
