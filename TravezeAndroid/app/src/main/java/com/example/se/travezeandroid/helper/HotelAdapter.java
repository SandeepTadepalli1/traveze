package com.example.se.travezeandroid.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.se.travezeandroid.R;

import java.util.ArrayList;

/**
 * Created by sandeep on 24/4/17.
 */

public class HotelAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Hotel> mDataSource;

    public HotelAdapter(Context context, ArrayList<Hotel> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        View rowView = mInflater.inflate(R.layout.hotel_list_item, parent, false);
        TextView txt_hotelName = (TextView) rowView.findViewById(R.id.txt_hotelName);
        TextView txt_numberOfRooms = (TextView) rowView.findViewById(R.id.txt_numberOfRooms);

        Hotel hotel = (Hotel) getItem(position);
        txt_hotelName.setText(hotel.name);
        txt_numberOfRooms.setText(Integer.toString(hotel.numberOfRooms));
        return rowView;
    }

}
