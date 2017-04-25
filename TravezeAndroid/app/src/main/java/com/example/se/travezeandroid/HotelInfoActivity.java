package com.example.se.travezeandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.example.se.travezeandroid.helper.BaseActivity;
import com.example.se.travezeandroid.helper.MyPreference;
import com.example.se.travezeandroid.helper.MyRequest;
import com.example.se.travezeandroid.helper.Room;
import com.example.se.travezeandroid.helper.RoomAdapter;
import com.example.se.travezeandroid.helper.Routes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HotelInfoActivity extends BaseActivity {

    @Bind(R.id.lst_rooms) ListView lst_rooms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_info);
        myPreference = MyPreference.getInstance(getApplicationContext());
        ButterKnife.bind(this);
        Intent intent = getIntent();
        try {
            JSONObject hotel = new JSONObject(intent.getStringExtra("hotel"));
            getRooms(hotel);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getRooms(JSONObject hotel) throws JSONException {
        JSONObject reqObj = new JSONObject();
        reqObj.put("hotel_id",hotel.getInt("id"));
        Response.Listener<JSONObject> responseListner = new Response.Listener<JSONObject>() {
            /**
             * Called when a response is received.
             *
             * @param response
             */
            @Override
            public void onResponse(JSONObject response) {
                Log.v("Get Rooms", response.toString());
                try {
                    JSONArray jsonArrayRooms = response.getJSONArray("rooms");
                    RoomAdapter roomAdapter = new RoomAdapter(HotelInfoActivity.this,Room.getListFromJson(jsonArrayRooms));
                    lst_rooms.setAdapter(roomAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        MyRequest myRequest = new MyRequest(Request.Method.POST, Routes.GetRooms,reqObj,
                responseListner,null,getApplicationContext());
        sendRequest(myRequest);
    }
}
