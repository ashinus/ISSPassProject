package com.example.isspass.ui.view;

import android.content.Context;

import com.example.isspass.model.ISSPassErrorModel;
import com.example.isspass.model.ISSPassResponse;
import com.example.isspass.presenter.ISSPassPresenter;

import okhttp3.ResponseBody;


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
     * Show any error
     */
    void ShowError(ISSPassErrorModel error_model);

    /**
     * Show the TSS Pas list received from the server
     */
    void ShowTSSPassList(ISSPassResponse[] resp);

}
