package com.example.isspass.network;


import com.example.isspass.constants.ISSPassConstants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ashutosh Singh on 11/29/2017.
 * Class used to build the initialize and call the Retrofit http client
 */


public class ApiClientProvider {

    protected BaseRequestInterceptor mBaseRequestInterceptor;
    private Retrofit retrofitClient = null;
    protected String mBaseUrl;

    /**
     * Method to choose if the default http headers will be used or the customised headers will be used in the HTTP request using interceptors
     */
    public ApiClientProvider(BaseRequestInterceptor mBaseRequestInterceptor) {

        mBaseUrl = ISSPassConstants.BASE_URL;

        if (mBaseRequestInterceptor != null) {
            this.mBaseRequestInterceptor = mBaseRequestInterceptor;
        } else {
            this.mBaseRequestInterceptor = new BaseRequestInterceptor(null);
        }
    }

    void myNewFunction(){

    }
    /**
     * Method to get the Retrofit rest client object to make the HTTP Calls
     */
    public Retrofit getRestClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            httpClient.addInterceptor(mBaseRequestInterceptor);

            OkHttpClient okHttpClient = httpClient.build();

            if (retrofitClient == null) {
                retrofitClient = new Retrofit.Builder()
                        .baseUrl(mBaseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient)
                        .build();
            }

        return retrofitClient;
    }
}

