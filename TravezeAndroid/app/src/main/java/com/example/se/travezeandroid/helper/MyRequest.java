package com.example.se.travezeandroid.helper;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.se.travezeandroid.helper.MyPreference;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sandeep on 23/4/17.
 */

public class MyRequest extends JsonObjectRequest {
    private Context mContext;
    /**
     * Creates a new request.
     *
     * @param method        the HTTP method to use
     * @param url           URL to fetch the JSON from
     * @param jsonRequest   A {@link JSONObject} to post with the request. Null is allowed and
     *                      indicates no parameters will be posted along with request.
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public MyRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener,Context context) {
        super(method, url, jsonRequest, listener, errorListener);
        this.mContext = context;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String, String>();
        //headers.put("Content-Type", "application/json");
        MyPreference myPreference=MyPreference.getInstance(mContext);
        headers.put("Authorization", myPreference.getAuthToken());
        return headers;
    }
}
