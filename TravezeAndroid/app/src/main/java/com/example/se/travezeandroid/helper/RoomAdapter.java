package com.example.se.travezeandroid.helper;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.se.travezeandroid.R;

import java.util.ArrayList;

/**
 * Created by sandeep on 25/4/17.
 */

public class RoomAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Room> mDataSource;

    public RoomAdapter(Context context, ArrayList<Room> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        Log.d("Room Adapter",Integer.toString(items.size()));
    }
    //1
    @Override
    public int getCount() {
        return mDataSource.size();
    }

    //2
    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    //3
    @Override
    public long getItemId(int position) {
        return position;
    }

    //4
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item
        View rowView = mInflater.inflate(R.layout.room_list_item, parent, false);
        TextView  txt_roomType = (TextView) rowView.findViewById(R.id.txt_roomType);
        TextView  txt_roomCost = (TextView) rowView.findViewById(R.id.txt_cost);
//
        Room room = (Room) getItem(position);
        txt_roomType.setText(Room.mapRoomTypeToString(room.roomType));
        txt_roomCost.setText(Integer.toString(room.cost));

        return rowView;
    }
}
