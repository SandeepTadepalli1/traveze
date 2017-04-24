package com.example.se.travezeandroid;

/**
 * Created by sandeep on 9/4/17.
 */

class Routes {
    private static final String HOSTNAME= "http://172.16.83.136:2500";
    static final String Authenticate = HOSTNAME+"/authenticate";
    static final String Register = HOSTNAME +"/user/register";
    static final String Update = HOSTNAME + "/user/update";
    static final String AddHotel = HOSTNAME + "/admin/add_hotel";
    static final String GetHotels = HOSTNAME + "/place/hotels";
}
