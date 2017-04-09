package com.example.se.travezeandroid;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import butterknife.ButterKnife;
import butterknife.Bind;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    SharedPreferences prefs;

    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login) Button _loginButton;
    @Bind(R.id.link_signup) TextView _signupLink;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        prefs = this.getSharedPreferences("com.example.se.traveze", Context.MODE_PRIVATE);

        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();


        JSONObject signInObject = new JSONObject();
        try {
            signInObject.put("email",email);
            signInObject.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // TODO: Implement your own authentication logic here.
        Response.Listener<JSONObject> responseListner = new Response.Listener<JSONObject>(){

            @Override
            public void onResponse(JSONObject response) {
                Log.v("SignIn", response.toString());

                try {
                    if(response.getInt("status") == Constants.STATUS_UNAUTHORIZED){
                        onLoginFailed();
                    }else if (response.getInt("status") == Constants.STATUS_OK) {
                        onLoginSuccess(response);
                    }else {
                        onLoginFailed();
                    }
                    progressDialog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        JsonObjectRequest signInRequest = new JsonObjectRequest(Request.Method.POST,Routes.Authenticate,signInObject,responseListner,null);
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        queue.add(signInRequest);
    }



    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess(JSONObject response) throws JSONException {
        _loginButton.setEnabled(true);
        Toast.makeText(LoginActivity.this, "LoggedIN", Toast.LENGTH_SHORT).show();

        String authToken = response.getString("auth_token");
        prefs.edit().putString("Authorization",authToken).apply();
        Log.v("On Login Success",response.toString());
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
