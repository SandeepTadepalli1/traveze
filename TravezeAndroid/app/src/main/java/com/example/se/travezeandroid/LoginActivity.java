package com.example.se.travezeandroid;

import android.app.ProgressDialog;
import android.os.Bundle;
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

import butterknife.ButterKnife;
import butterknife.Bind;


/**
 * @author sandeep
 * @version 1.0
 */
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    MyPreference myPreference;

    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login) Button _loginButton;
    @Bind(R.id.link_signup) TextView _signupLink;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myPreference = MyPreference.getInstance(getApplicationContext());
        checkLoggedIn();
        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            /**
             * Starts the Signup activity
             * @param v Current view
             */
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

    /**
     * Validates the edit text input fields and creates a json request sends it to the server
     * and processes the response
     */
    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed(null);
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        JSONObject signInObject = getSignObject(email,password);

        Response.Listener<JSONObject> responseListner = new Response.Listener<JSONObject>(){

            /**
             * If the authentication is successful it calls {@link LoginActivity#onLoginSuccess(JSONObject)}
             * else if the authenticaion is a failure it calls {@link LoginActivity#onLoginFailed(JSONObject)}
             * @param response Response from the Server upon processing the request
             */
            @Override
            public void onResponse(JSONObject response) {
                Log.i("SignIn", response.toString());

                try {
                    if(response.getInt("status") == Constants.STATUS_UNAUTHORIZED){
                        onLoginFailed(response);
                    }else if (response.getInt("status") == Constants.STATUS_OK) {
                        onLoginSuccess(response);
                    }else {
                        onLoginFailed(response);
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

    /**
     * Creates a sign in object that should be sent to server
     * @param email email that should be sent
     * @param password  password that should be sent
     * @return SignIn json object if in valid json format else returns null
     */
    private JSONObject getSignObject(String email, String password) {
        JSONObject signInObject = new JSONObject();
        try {
            signInObject.put("email",email);
            signInObject.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return signInObject;
    }


    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }


    /**
     * If login is successful it stores authtoken to the shared preferences {@link MyPreference#saveAuthToken(String)}
     * @param response Response from the authentication request sent to the server
     * @throws JSONException if Response does not have auth_token
     */
    public void onLoginSuccess(JSONObject response) throws JSONException {

        Toast.makeText(LoginActivity.this, "LoggedIN", Toast.LENGTH_SHORT).show();

        String authToken = response.getString("auth_token");
        myPreference.saveAuthToken(authToken);
        Log.v("On Login Success",response.toString());
        finish();
    }

    /**
     * Shows a Toast to the user saying that Authentication failed
     * @param response Response from the authentication request sent to the server
     * @see Toast
     */
    public void onLoginFailed(JSONObject response) {

        Toast.makeText(getBaseContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
        if (response != null){
            Log.d(TAG,response.toString());
        }
        else
            Log.d(TAG,"Response is null");
    }

    /**
     * Validates the Edit text fields and checks if they contain valid content so we can create a
     * valid request
     * Sets Error over the Edit text fields if they contain non valid content
     * @return true if valid else false
     * @see EditText#setError(CharSequence)
     */
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

        if (password.isEmpty() || password.length() < 8) {
            _passwordText.setError("should be a min of 8 characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    /**
     * Checks if the user is logged in.
     * If he is already logged in then it starts the main activity
     * @see MyPreference
     * {@link #startMainActivity()}
     */
    private void checkLoggedIn() {
        if(myPreference.isLoggedIn()){
            startMainActivity();
        }
    }

    /**
     * Starts Main activity
     * @see Intent
     */
    private void startMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        LoginActivity.this.startActivity(intent);
    }
}
