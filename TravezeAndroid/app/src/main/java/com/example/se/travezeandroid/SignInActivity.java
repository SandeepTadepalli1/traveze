package com.example.se.travezeandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        final Button btnSignIn = (Button) findViewById(R.id.btnSingIn);
        final SharedPreferences prefs = this.getSharedPreferences(
                "com.example.se.traveze", Context.MODE_PRIVATE);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = etEmail.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<JSONObject> responseListner = new Response.Listener<JSONObject>(){

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.v("SignIn", response.toString());
                        try {
                            if(response.getInt("status") == Constants.STATUS_UNAUTHORIZED){
                                Toast.makeText(SignInActivity.this, "Invalid Credentials",
                                        Toast.LENGTH_SHORT).show();
                            }else if (response.getInt("status") == Constants.STATUS_OK) {
                                Toast.makeText(SignInActivity.this, "LoggedIN",
                                        Toast.LENGTH_SHORT).show();
                                String authToken = response.getString("auth_token");
                                prefs.edit().putString("Authorization",authToken).apply();
                            } else {
                                Toast.makeText(SignInActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                JSONObject signInObject = new JSONObject();
                try {
                    signInObject.put("email",email);
                    signInObject.put("password",password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                final String loginURL = Constants.HOSTNAME+"/authenticate";

                JsonObjectRequest signInRequest = new JsonObjectRequest(Request.Method.POST,loginURL,signInObject,responseListner,null);
                RequestQueue queue = Volley.newRequestQueue(SignInActivity.this);
                queue.add(signInRequest);
            }
        });
    }
}
