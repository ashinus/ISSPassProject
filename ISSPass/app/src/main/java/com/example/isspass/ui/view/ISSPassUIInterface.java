package com.example.isspass.ui.view;

import android.content.Context;

import com.example.isspass.model.Response;
import com.example.isspass.presenter.ISSPassPresenter;


/**
 * Created by Ashutosh Singh on 11/30/2017.
 * Interface for calling the UI methods in Activity from Presenter
 */
public interface ISSPassUIInterface {

    /**
     * Set the presenter for calling the UI methods in the presenter
     */
    void setPresenter(ISSPassPresenter isspassPresenter);

    /**
     * Show spinner during network call
     */
    void showSpinner();

    /**
     * Hide spinner after Network call
     */
    void hideSpinner();

    /**
     * Show the TSS Pas list received from the server
     */
    void ShowTSSPassList(Response [] resp);

}
