package com.example.se.travezeandroid;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;


public class MyPreference {
    private static MyPreference myPreference;
    private SharedPreferences sharedPreferences;

    public static MyPreference getInstance(Context context) {
        if (myPreference == null) {
            myPreference = new MyPreference(context);
        }
        return myPreference;
    }

    private MyPreference(Context context) {
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

    public  void logout(){
        saveData("Authorization","");
    }
}
