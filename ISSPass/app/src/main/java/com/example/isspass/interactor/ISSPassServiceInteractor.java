package com.example.isspass.interactor;

import com.example.isspass.listeners.ISSPassListener;

import org.json.JSONException;

/**
 * Created by Ashutosh Singh on 11/29/2017.
 */

public interface ISSPassServiceInteractor {

    void getDatafromService(String latitude,String longitude, final ISSPassListener isspasslist) throws JSONException;
}
