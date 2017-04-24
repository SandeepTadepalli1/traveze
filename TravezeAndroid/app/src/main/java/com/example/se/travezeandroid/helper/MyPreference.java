package com.example.se.travezeandroid.helper;

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
        return !Objects.equals(authToken, "");
    }

    public void saveAuthToken(String authToken){
        saveData("Authorization",authToken);
    }

    public String getAuthToken(){
        return getData("Authorization");
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
            saveData("mobilenumber",user.getString("mobilenumber"));
            saveData("user_role", String.valueOf(user.getInt("role")));
            saveData("user_id",String.valueOf(user.getInt("id")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void logout(){
        saveData("Authorization","");
    }



    public String getMobileNumber() {
        return getData("mobilenumber");
    }
}
