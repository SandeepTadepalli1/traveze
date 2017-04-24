package com.example.se.travezeandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.example.se.travezeandroid.helper.BaseActivity;
import com.example.se.travezeandroid.helper.Constants;
import com.example.se.travezeandroid.helper.MyPreference;
import com.example.se.travezeandroid.helper.MyRequest;
import com.example.se.travezeandroid.helper.Routes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.et_startLocation) EditText et_startLocation;
    @Bind(R.id.et_endLocation) EditText et_endLocation;
    @Bind(R.id.et_startDate) EditText et_startDate;
    @Bind(R.id.et_returnDate) EditText et_returnDate;
    @Bind(R.id.btn_searchHotel) Button btn_searchHotel;
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"Created Main Activity");
        myPreference = MyPreference.getInstance(getApplicationContext());
        ButterKnife.bind(this);
        checkLoggedIn();

        btn_searchHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchHotel();
            }
        });

    }

    private void searchHotel() {
        if(!validate()){
            onSearchFailed(null);
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Searching....");
        progressDialog.show();

        JSONObject reqObj = new JSONObject();
        try {
            reqObj.put("place",et_endLocation.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Response.Listener<JSONObject> responseListner = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG,"Response: "+response.toString());
                try {
                    if(response.getInt("status") == Constants.STATUS_BAD_REQUEST){
                        onSearchFailed(response);
                    }else if (response.getInt("status") == Constants.STATUS_OK) {
                        onSearchSuccess(response);
                    }else {
                        onSearchFailed(response);
                    }
                    progressDialog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Log.v(TAG,"Creating a Request");
        MyRequest getHotelsRequest = new MyRequest(Request.Method.POST, Routes.GetHotels, reqObj,
                responseListner, null, getApplicationContext());
        sendRequest(getHotelsRequest);

    }

    private void onSearchSuccess(JSONObject response) {
        JSONObject details = createJsonObjectDetails();
        JSONArray hotels = new JSONArray();
        try {
            hotels = response.getJSONArray("hotels");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(getApplicationContext(),HotelsListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("details",details.toString());
        intent.putExtra("hotels",hotels.toString());
        startActivity(intent);

    }
    private JSONObject createJsonObjectDetails() {
        String startLocation = et_startLocation.getText().toString();
        String endLocation = et_endLocation.getText().toString();
        String startDate = et_startDate.getText().toString();
        String returnDate = et_returnDate.getText().toString();

        JSONObject details = new JSONObject();
        try {
            details.put("startLocation",startLocation);
            details.put("endLocation",endLocation);
            details.put("startDate",startDate);
            details.put("returnDate",returnDate);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return details;
    }

    private void onSearchFailed(JSONObject response) {
        createShortToast("Please correct the errors");
        if(response != null)
            Log.v(TAG,response.toString());
        else
            Log.v(TAG,"Response is null");
    }



    private boolean validate() {
        String startLocation = et_startLocation.getText().toString();
        String endLocation = et_endLocation.getText().toString();
        String startDate = et_startDate.getText().toString();
        String returnDate = et_returnDate.getText().toString();

        Boolean valid =true;
        if (startLocation.isEmpty()) {
            et_startLocation.setError("can Not be Empty");
            valid = false;
        } else {
            et_startLocation.setError(null);
        }

        if (endLocation.isEmpty()) {
            et_endLocation.setError("can Not be Empty");
            valid = false;
        } else {
            et_endLocation.setError(null);
        }
        if (startDate.isEmpty()) {
            et_startDate.setError("can Not be Empty");
            valid = false;
        } else {
            et_startDate.setError(null);
        }
        if (returnDate.isEmpty()) {
            et_returnDate.setError("can Not be Empty");
            valid = false;
        } else {
            et_returnDate.setError(null);
        }
        return valid;
    }
    private void checkLoggedIn() {
        if(!myPreference.isLoggedIn()){
            startLoginActivity();
            finish();
        }
    }



}
