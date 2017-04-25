package com.example.se.travezeandroid.helper;

import android.support.annotation.NonNull;
import android.util.Log;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sandeep on 25/4/17.
 */

public class Room {
    public int roomType;
    public int cost;

    public static final int ROOM_NORMAL = 1100;
    public static final int ROOM_DELUX = 1101;
    public static final int ROOM_THREE_BEDS = 1102;
    public static final int ROOM_FOUR_BEDS =1103;
    public static final int ROOM_DUPLEX = 1104;
    public static ArrayList<Room> getListFromJson(JSONArray jsonArrayRooms){
        ArrayList<Room> arrayListRoom = new ArrayList<Room>();
        int i =0;
        try {
            for (i = 0; i<jsonArrayRooms.length(); i++){
                JSONObject roomObj = jsonArrayRooms.getJSONObject(i);
                Room row = new Room();
                row.roomType = roomObj.getInt("room_type");
                row.cost = roomObj.getInt("cost");
                Log.d("Room Object",Integer.toString(row.roomType)+" "+row.cost );
                arrayListRoom.add(row);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.v("get List From Json",Integer.toString(arrayListRoom.size()));
        return arrayListRoom;
    }


    public static String mapRoomTypeToString(@NotNull int roomType){
        if (roomType == ROOM_NORMAL) return "Normal";
        else if (roomType == ROOM_DELUX) return "Delux";
        else if (roomType == ROOM_THREE_BEDS) return "Three Beds";
        else if (roomType == ROOM_FOUR_BEDS) return "Four Beds";
        else if (roomType == ROOM_DUPLEX) return "Duplex";
        else return "";
    }
}
