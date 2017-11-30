package com.example.isspass.presenter;

import com.example.isspass.Listener.ISSPassListener;
import com.example.isspass.model.Request;
import com.example.isspass.beans.ISSPassModelResponse;
import com.example.isspass.service.ISSPassServiceClass;
import com.example.isspass.ui.view.ISSPassUIInterface;
import com.example.isspass.interactor.ISSPassServiceInteractor;

import org.json.JSONException;

import retrofit2.Response;

/** Created by Ashutosh Singh on 11/30/2017.
        * This is Presenter for ISSPass list screen to implement the business logic
        * and making webservice calls. It will call appropriate ISSPassUIInterface  to update UI.
 */


public class ISSPassPresenterImpl implements ISSPassPresenter {

    private ISSPassUIInterface mISSPassUIInterface;
    private com.example.isspass.interactor.ISSPassServiceInteractor mISSPassServiceInteractor;

    public static void createPresenter(ISSPassUIInterface isspassUIInterface) {
        ISSPassPresenterImpl isspassPresenterImpl = new ISSPassPresenterImpl(isspassUIInterface);
        isspassUIInterface.setPresenter(isspassPresenterImpl);
        isspassPresenterImpl.setInteractor(new ISSPassServiceClass());
    }

    public ISSPassPresenterImpl(ISSPassUIInterface isspassUIInterface) {
        this.mISSPassUIInterface = isspassUIInterface;
    }

    @Override
    public void setInteractor(ISSPassServiceInteractor isspassServiceInteractor) {
        this.mISSPassServiceInteractor = isspassServiceInteractor;
    }

    @Override
    public void getISSPassData(Request request)  {
        // Show the spinner
        mISSPassUIInterface.showSpinner();
        try{

            mISSPassServiceInteractor.getDatafromService(request.getLatitude(),request.getLongitude(), new ISSPassListener() {
                    @Override
                    public void onSuccess(Object success) {
                        com.example.isspass.model.Response[] resp=null;
                        Response<ISSPassModelResponse> serverResponse  = (Response<ISSPassModelResponse> ) success;
                        // Response received from the server
                        if(serverResponse!=null && serverResponse.body()!=null)
                        resp = serverResponse.body().getResponse();
                        //Pass the response to the adapter and display it to the list
                        if(resp!=null)
                        mISSPassUIInterface.ShowTSSPassList(resp);
                    }

                    @Override
                    public void onError(Object error) {

                    }
                });

        }catch (JSONException e){
           e.printStackTrace();
        }finally {
            //Hide the Spinner
            mISSPassUIInterface.hideSpinner();
        }
    }


}
