package com.example.se.travezeandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;


class MyPreference {
    private static MyPreference myPreference;
    private final Context actContext;
    private SharedPreferences sharedPreferences;
    static MyPreference getInstance(Context context) {
        if (myPreference == null) {
            myPreference = new MyPreference(context);
        }
        return myPreference;
    }

    private MyPreference(Context context) {
        this.actContext = context;
        sharedPreferences = context.getSharedPreferences("com.example.se.traveze",Context.MODE_PRIVATE);
    }

    public void saveData(String key,String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor .putString(key, value);
        prefsEditor.apply();
    }

    public String getData(String key) {
        if (sharedPreferences!= null) {
            return sharedPreferences.getString(key, "");
        }
        return "";
    }

    public boolean isLoggedIn(){
        String authToken = getData("Authorization");
        if(Objects.equals(authToken, "")){
            return false;
        }else return true;
    }

    public void saveAuthToken(String authToken){
        saveData("Authorization",authToken);
    }

    public String getUserName(){
        return getData("username");
    }

    public String getEmail(){
        return getData("email");
    }

    public void saveUserInfo(JSONObject user){
        try {
            saveData("username",user.getString("name"));
            saveData("email",user.getString("email"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void startLoginActivity(){
        Intent intent = new Intent(actContext,LoginActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        actContext.startActivity(intent);
    }

    public void startMyAccountActivity(){

        Intent intent = new Intent(actContext,MyAccountActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        actContext.startActivity(intent);

    }

    public  void logout(){
        saveData("Authorization","");
    }

    public void setNavigationHeadear(){
        // TODO if Logged in then should set the username and email in the navigation Header.
    }
}
