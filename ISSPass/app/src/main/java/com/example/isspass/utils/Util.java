package com.example.isspass.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Ashutosh Singh on 11/30/2017.
 * Class for Utility Methods
 */

public class Util {

    /**
     * Method for Converting the Milliseconds into Date and Time
     */
    public static String ConvertMilliIntoDateTime(String milliseconds){
        SimpleDateFormat dateformat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTimeInMillis(Long.parseLong(milliseconds));
        }catch(NumberFormatException ne){
            ne.printStackTrace();
        }
        return dateformat.format(calendar.getTime());
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        //should check null because in airplane mode it will be null
        return (netInfo != null && netInfo.isConnected());
    }

}
