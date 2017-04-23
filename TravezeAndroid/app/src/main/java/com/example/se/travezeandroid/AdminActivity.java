package com.example.se.travezeandroid;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

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
        final ProgressDialog progressDialog = new ProgressDialog(AdminActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating....");
        progressDialog.show();

        Log.v(TAG,"TO create a request");
        Response.Listener<JSONObject> responseListner = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getInt("status") == Constants.STATUS_BAD_REQUEST){
                        onAddHotelFailed(response);
                    }else if (response.getInt("status") == Constants.STATUS_CREATED) {
                        onAddHotelSuccess(response);
                    }else {
                        onAddHotelFailed(response);
                    }
                    progressDialog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        JSONObject hotelObject = createHotelObject();
        MyRequest addHotelRequest = new MyRequest(Request.Method.POST, Routes.AddHotel, hotelObject,
                responseListner, null, getApplicationContext());
//        TODO Create a request to add hotel to the database
        sendRequest(addHotelRequest);
    }

    private JSONObject createHotelObject() {
        JSONObject hotelObject = new JSONObject();
        String hotelName = _hotelName.getText().toString();
        String place = _place.getText().toString();
        String state = _state.getText().toString();
        String numberOfRooms = _numberOfRooms.getText().toString();
        String managerEmail = _managerEmail.getText().toString();
        try {
            hotelObject.put("hotel_name",hotelName);
            hotelObject.put("place",place);
            hotelObject.put("state",state);
            hotelObject.put("manager_email",managerEmail);
            hotelObject.put("number_of_rooms",numberOfRooms);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return hotelObject;
    }

    private void onAddHotelSuccess(JSONObject response) {
        createShortToast("Hotel Successfully Added");
        startMainActivity();
    }

    private void onAddHotelFailed(JSONObject response) {
        createShortToast("Creation Failed");
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
