package com.example.isspass.presenter;

import com.example.isspass.interactor.ISSPassServiceInteractor;
import com.example.isspass.model.ISSPassRequest;

/**
 * Created by Ashutosh Singh on 11/29/2017.
 * Interface for presenter class
 */

public interface ISSPassPresenter {
    void setInteractor(ISSPassServiceInteractor isspassServiceInteractor);
    void getISSPassData(ISSPassRequest request) ;
}
