package com.example.isspass.bean;

import com.example.isspass.model.ISSPassRequest;
import com.example.isspass.model.ISSPassResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Ashutosh Singh on 11/30/2017.
 * Bean for ISSPass Response
 */

public class ISSPassModelResponse implements Serializable {


    @SerializedName("message")
    private String message;
    @SerializedName("request")
    private ISSPassRequest request;
    @SerializedName("response")
    private ISSPassResponse[] response;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ISSPassResponse [] getResponse() {
        return response;
    }

    public void setResponse(ISSPassResponse [] response) {
        this.response = response;
    }

    public ISSPassRequest getRequest() {
        return request;
    }

    public void setRequest(ISSPassRequest request) {
        this.request = request;
    }



}
