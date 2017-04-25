package com.example.se.travezeandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.se.travezeandroid.helper.BaseActivity;
import com.example.se.travezeandroid.helper.Hotel;
import com.example.se.travezeandroid.helper.HotelAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HotelsListActivity extends BaseActivity {

    @Bind(R.id.lst_hotelListView) ListView lst_hotelListView;
    private static final String TAG = HotelsListActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels_list);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String details;
        try {
//            final JSONObject details = new JSONObject(intent.getStringExtra("details"));
            details = intent.getStringExtra("details");
            final JSONArray hotels = new JSONArray(intent.getStringExtra("hotels"));
            Log.v(TAG,hotels.toString());
            Log.i(TAG,"Creating Array list of Hotels");
            final ArrayList<Hotel> arrayListHotel = Hotel.getListFromJson(hotels);
            Log.v("Hotels length ",Integer.toString(hotels.length()));
            Log.i(TAG,"Creating Hotel Adapter");
            HotelAdapter adapter = new HotelAdapter(this,arrayListHotel);
            Log.i(TAG,"Setting the adapter");
            lst_hotelListView.setAdapter(adapter);
            lst_hotelListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                /**
                 * Callback method to be invoked when an item in this AdapterView has
                 * been clicked.
                 * <p>
                 * Implementers can call getItemAtPosition(position) if they need
                 * to access the data associated with the selected item.
                 *
                 * @param parent   The AdapterView where the click happened.
                 * @param view     The view within the AdapterView that was clicked (this
                 *                 will be a view provided by the adapter)
                 * @param position The position of the view in the adapter.
                 * @param id       The row id of the item that was clicked.
                 */
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    createShortToast(Integer.toString(position));
                    try {
                        JSONObject hotel = hotels.getJSONObject(position);
                        Log.v(TAG,hotel.toString());

                        Intent intent = new Intent(getApplicationContext(),HotelInfoActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("hotel",hotel.toString());

                        getApplicationContext().startActivity(intent);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
