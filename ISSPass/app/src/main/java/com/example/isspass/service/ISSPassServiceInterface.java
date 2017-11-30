package com.example.isspass.service;

import com.example.isspass.beans.ISSPassModelResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ashutosh Singh on 11/29/2017.
 * Interface for appending the required parameters to the base URL
 */

public interface ISSPassServiceInterface {
    @GET("iss-pass.json")
    Call<ISSPassModelResponse> getISSPassResponse(@Query("lat") String latitude,
                                                  @Query("lon") String longitude);
}
