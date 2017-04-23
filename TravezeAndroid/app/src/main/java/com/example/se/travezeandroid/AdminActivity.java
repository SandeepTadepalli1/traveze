package com.example.se.travezeandroid;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AdminActivity extends BaseActivity {

    @Bind(R.id.et_hotelName) EditText _hotelName;
    @Bind(R.id.et_place) EditText _place;
    @Bind(R.id.et_state) EditText _state;
    @Bind(R.id.et_numberOfRooms) EditText _numberOfRooms;
    @Bind(R.id.et_managerEmail) EditText _managerEmail;
    @Bind(R.id.btn_addHotel) Button _addHotel;
    @Bind(R.id.btn_viewHotels) Button _viewHotels;
    private static final String TAG = AdminActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        myPreference = MyPreference.getInstance(getApplicationContext());
        ButterKnife.bind(this);

        _addHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHotel();
            }
        });

        _viewHotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHotels();
            }
        });
    }

    private void addHotel() {
        Log.d(TAG,"Add Hotel");
        if (!validate()) {
            onAddHotelFailed(null);
            return;
        }
        Log.v(TAG,"TO create a request");

//        TODO Create a request to add hotel to the database
    }

    private void onAddHotelFailed(MyRequest response) {

    }

    private boolean validate() {

        Log.i(TAG,"IN Validate");
        boolean valid = true;

        String hotelName = _hotelName.getText().toString();
        String place = _place.getText().toString();
        String state = _state.getText().toString();
        String numberOfRooms = _numberOfRooms.getText().toString();
        String managerEmail = _managerEmail.getText().toString();

        if (hotelName.isEmpty()) {
            _hotelName.setError("can Not be Empty");
            valid = false;
        } else {
            _hotelName.setError(null);
        }

        if (place.isEmpty()) {
            _place.setError("can Not be Empty");
            valid = false;
        } else {
            _place.setError(null);
        }

        if (state.isEmpty()) {
            _state.setError("can Not be Empty");
            valid = false;
        } else {
            _state.setError(null);
        }

        if (numberOfRooms.isEmpty()) {
            _numberOfRooms.setError("can Not be Empty");
            valid = false;
        } else {
            _numberOfRooms.setError(null);
        }

        if (managerEmail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(managerEmail).matches()) {
            _managerEmail.setError("enter a valid email address");
            valid = false;
        } else {
            _managerEmail.setError(null);
        }
        return valid;
    }

    private void viewHotels(){
        Log.d(TAG,"View Hotels");
//        TODO Create a request to go to the view hotels page
    }
}
