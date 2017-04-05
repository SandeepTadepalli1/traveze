package com.example.se.travezeandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = SignUpActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText etEmail  = (EditText) findViewById(R.id.etEmail);
        final EditText etName   = (EditText) findViewById(R.id.etName);
        final EditText etPassword  = (EditText) findViewById(R.id.etPassword);
        final EditText etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);

        final Button btnSignUp = (Button) findViewById(R.id.btnSignUp);
        Log.i(TAG,"created activity");
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,"OnClick Listener called");

                final String email      = etEmail.getText().toString();
                final String name       = etName.getText().toString();
                final String password   = etPassword.getText().toString();
                final String confirmPassword = etConfirmPassword.getText().toString();
                Response.Listener<JSONObject> responseListner = new Response.Listener<JSONObject>(){

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.v("StartActivity", response.toString());
                    }
                };
                Log.v(TAG,"Creating register Request ");

                JSONObject signUpObj = new JSONObject();
                JSONObject userObj = new JSONObject();
                try {
                    userObj.put("email",email);
                    userObj.put("name",name);
                    userObj.put("password",password);
                    userObj.put("password_confirmation",confirmPassword);
                    signUpObj.put("user",userObj);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.v("Json request object",signUpObj.toString());
                String SIGN_UP_URL= Constants.HOSTNAME +"/register";
                JsonObjectRequest registerRequest = new JsonObjectRequest(SIGN_UP_URL,signUpObj,responseListner,null);
                RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}
