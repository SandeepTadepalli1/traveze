package com.example.se.travezeandroid.helper;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sandeep on 24/4/17.
 */

public class Hotel{
    public String name;
    public int numberOfRooms;

    public static ArrayList<Hotel> getListFromJson(JSONArray jsonArrayHotels){
        ArrayList<Hotel> arrayListHotel = new ArrayList<Hotel>();
        int i =0;
        try {
            for (i = 0; i<jsonArrayHotels.length(); i++){
                JSONObject hotelObj = jsonArrayHotels.getJSONObject(i);
                Hotel row = new Hotel();
                row.name = hotelObj.getString("name");
                row.numberOfRooms = hotelObj.getInt("numberofrooms");
                arrayListHotel.add(row);
            }
            Log.v("Get List From Json" ,jsonArrayHotels.toString());
            Log.v("Get Json: Length",Integer.toString(jsonArrayHotels.length()));
            Log.v("Array List Hotel Length",Integer.toString(arrayListHotel.size()));
        } catch (JSONException e) {
            Log.e("Get List From Json" ,jsonArrayHotels.toString());
            Log.e("Get Json: Length",Integer.toString(jsonArrayHotels.length()));
            Log.e("Array List Hotel Length",Integer.toString(arrayListHotel.size()));
            Log.e("in Catch ",Integer.toString(i));
            Log.e("Error",e.toString());
//            Log.e("Error",jsonArrayHotels.getJSONObject(i).toString())
            e.printStackTrace();
        }
        return arrayListHotel;
    }
}