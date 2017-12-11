package com.example.isspass.service;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.example.isspass.R;
import com.example.isspass.constants.ISSPassConstants;
import com.example.isspass.model.ISSPassRequest;


/**
 * Created by Ashutosh Singh on 11/30/2017.
 * Interface for Location services to get the GPS coordinatede of the user i.e.e latitude and longitude
 */

public class ISSPassLocationServiceClass implements LocationListener {

    Context mContext;

   public ISSPassLocationServiceClass(Context context){
        mContext = context;
    }
    /**
     * This method check the modes to get the Location Information. It checks if the location service is availbe with the provider
     * and returns the location object though which the ISSPass request is made
     */
    public void  getLocationLatAndLongForRequest(ISSPassRequest request ) {

        boolean isGPSEnabled = false;
        boolean isNetworkEnabled = false;
        Location location = null;

        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

        // getting GPS status
        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // getting Network status
        isNetworkEnabled = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        //check for network permission
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (isNetworkEnabled) {
                locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        ISSPassConstants.MIN_TIME_BTW_UPDATES,
                        ISSPassConstants.MIN_DISTANCE_CHANGEFOR_UPDATE, this);

                if (locationManager != null) {
                    location = locationManager
                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                }
            } else if (isGPSEnabled) {
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        }
        // set latitude and longitude  from location
        if(location!=null) {
            request.setLatitude("" + location.getLatitude());
            request.setLongitude("" + location.getLongitude());
        }else {
            Toast.makeText(mContext, mContext.getResources().getString(R.string.no_location_available), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }



}
