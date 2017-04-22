package com.example.se.travezeandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class TestActivity extends BaseActivity {

    private static final String TAG = "TestActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate Test");
//        setContentView(R.layout.activity_test);
    }
}
