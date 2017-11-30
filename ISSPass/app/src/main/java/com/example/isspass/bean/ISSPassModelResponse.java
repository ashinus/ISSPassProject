package com.example.isspass.bean;

import com.example.isspass.model.Request;
import com.example.isspass.model.Response;
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
    private Request request;
    @SerializedName("response")
    private Response[] response;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Response [] getResponse() {
        return response;
    }

    public void setResponse(Response [] response) {
        this.response = response;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }



}
