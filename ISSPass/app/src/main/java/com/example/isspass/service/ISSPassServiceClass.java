package com.example.isspass.service;

import com.example.isspass.Listener.ISSPassListener;
import com.example.isspass.interactor.ISSPassServiceInteractor;
import com.example.isspass.beans.ISSPassModelResponse;
import com.example.isspass.network.ApiClientProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ashutosh Singh on 11/29/2017.
 * Service class for calling the service for feting the data from the server. It appends the parameters with the base URL and call the
 * rest client API to fetch the data from server
 */

public class ISSPassServiceClass implements ISSPassServiceInteractor{

    public ISSPassServiceClass() {
    }
    /**
     * Method for calling the rest api and feting the data
     */
    @Override
    public void getDatafromService(String latitude,String longitude,final ISSPassListener listener) {
        ISSPassServiceInterface apiService = new ApiClientProvider(null).getRestClient().create(ISSPassServiceInterface.class);
        final Call<ISSPassModelResponse> call = apiService.getISSPassResponse(latitude,longitude);
        call.enqueue(new Callback<ISSPassModelResponse>(){
            @Override
            public void onResponse(Call<ISSPassModelResponse> call, Response<ISSPassModelResponse> response) {
                listener.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ISSPassModelResponse> call, Throwable t) {
                listener.onError(t);
            }
        });
    }
}
