package com.example.se.travezeandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.se.travezeandroid.helper.BaseActivity;
import com.example.se.travezeandroid.helper.MyPreference;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyAccountActivity extends BaseActivity {


    @Bind(R.id.btn_editProfile) Button _editProfile;
    @Bind(R.id.btn_myTrips) Button _myTrips;
    @Bind(R.id.btn_buyPremium) Button _buyPremium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        myPreference = MyPreference.getInstance(getApplicationContext());
        ButterKnife.bind(this);

        _editProfile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EditProfileActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        _buyPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Should take to buy Premium activity
            }
        });

        _myTrips.setOnClickListener(new View.OnClickListener(){

            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                //TODO Should take to my trips acitivity
            }
        });

    }
}
