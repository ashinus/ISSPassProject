package com.example.isspass.model;

/**
 * Created by Ashutosh Singh on 11-30-2017.
 */

public class ISSPassErrorModel {
    int errorCode;
    String errorMessage;

    public ISSPassErrorModel(){

    }
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


}
