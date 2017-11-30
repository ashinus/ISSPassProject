package com.example.isspass.Listener;

/**
 * Created by Ashutosh Singh on 11/29/2017.
 * Interface for listening the retrofit response
 */

public interface ISSPassListener {
    void onSuccess(Object success);
    void onError(Object error);
}