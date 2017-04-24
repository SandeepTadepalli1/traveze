package com.example.se.travezeandroid.helper;

/**
 * Created by sandeep on 9/4/17.
 */

public class Routes {
    private static final String HOSTNAME= "http://172.16.83.136:2500";
    public static final String Authenticate = HOSTNAME+"/authenticate";
    public static final String Register = HOSTNAME +"/user/register";
    public static final String Update = HOSTNAME + "/user/update";
    public static final String AddHotel = HOSTNAME + "/admin/add_hotel";
    public static final String GetHotels = HOSTNAME + "/place/hotels";
}
