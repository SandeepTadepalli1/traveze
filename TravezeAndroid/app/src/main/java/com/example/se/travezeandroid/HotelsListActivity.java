package com.example.se.travezeandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HotelsListActivity extends BaseActivity {

    private static final String TAG = AdminActivity.class.getName();
    ArrayList<Hotel> arrayOfHotels = new ArrayList<Hotel>();
    class Hotel{
        public String name;
        public int numberOfRooms;
    }

//    MyAdapter hotelListAdapter = null;

//    class MyAdapter extends ArrayAdapter<Hotel>{
//        MyAdapter() {
//            super(HotelsListActivity.this, android.R.layout.simple_list_item_1,arrayOfHotels);
//        }
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ViewHolder holder;
//            //we call an if statement on our view that is passed in,
//            //to see if it has been recycled or not.  if it has been recycled,
//            //then it already exists and we do not need to call the inflater function
//            //this saves us A HUGE AMOUNT OF RESOURCES AND PROCESSING
//            //this is the proper way to do it
//            if (convertView==null) {
//                LayoutInflater inflater=getLayoutInflater();
//                convertView=inflater.inflate(R.layout.hotel_list_item, null);
//
//                //here is something new.  we are using a class called a view holder
//                holder=new ViewHolder(convertView);
//                //we are using that class to cache the result of the findViewById function
//                //which we then store in a tag on the view
//                convertView.setTag(holder);
//            }
//            else
//            {
//                holder=(ViewHolder)convertView.getTag();
//            }
//            holder.populateFrom(arrayOfHotels.get(position));
//
//            return(convertView);
//        }
//        class ViewHolder {
//            public TextView name=null;
//            public TextView numberOfRooms=null;
//
//            ViewHolder(View row) {
//                name=(TextView)row.findViewById(R.id.txt_hotelName);
//                numberOfRooms=(TextView)row.findViewById(R.id.txt_numberOfRooms);
//            }
//            //notice we had to change our populate from to take an arguement of type person
//            void populateFrom(Hotel r) {
//                name.setText(r.name);
//                numberOfRooms.setText(Integer.toString(r.numberOfRooms));
//            }
//        }
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels_list);
        Intent intent = getIntent();
        JSONArray hotels = null;
        try {
            hotels = new JSONArray(intent.getStringExtra("hotels"));
            Log.v(TAG,hotels.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        try {
//            for (int i = 0; i<hotels.length(); i++){
//                JSONObject hotelObj = hotels.getJSONObject(i);
//                Hotel row = new Hotel();
//                row.name = hotelObj.getString("name");
//                row.numberOfRooms = hotelObj.getInt("numberofrooms");
//                arrayOfHotels.add(row);
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        ListView hotelListView = (ListView) findViewById(R.id.lst_hotelListView);
//        hotelListAdapter = new MyAdapter();
//        hotelListView.setAdapter(hotelListAdapter);

    }
}
