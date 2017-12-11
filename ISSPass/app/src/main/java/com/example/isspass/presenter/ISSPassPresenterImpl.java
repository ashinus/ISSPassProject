package com.example.isspass.presenter;

import com.example.isspass.listeners.ISSPassListener;

import com.example.isspass.bean.ISSPassModelResponse;
import com.example.isspass.model.ISSPassErrorModel;
import com.example.isspass.model.ISSPassRequest;
import com.example.isspass.service.ISSPassServiceClass;
import com.example.isspass.ui.view.ISSPassUIInterface;
import com.example.isspass.interactor.ISSPassServiceInteractor;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.http.HTTP;

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
    public void getISSPassData(ISSPassRequest request)  {
        // Show the spinner
        mISSPassUIInterface.showSpinner();
        try{

            mISSPassServiceInteractor.getDatafromService(request.getLatitude(),request.getLongitude(), new ISSPassListener() {
                    @Override
                    public void onSuccess(Object success) {
                        com.example.isspass.model.ISSPassResponse[] resp=null;
                        ISSPassErrorModel errorModel=null;
                        Response<ISSPassModelResponse> serverResponse  = (Response<ISSPassModelResponse> ) success;
                        // Response received from the server
                        if(serverResponse!=null && serverResponse.body()!=null) {
                            resp = serverResponse.body().getResponse();
                            //Pass the response to the adapter and display it to the list
                            if (resp != null)
                                mISSPassUIInterface.ShowTSSPassList(resp);
                        }
                    }

                    @Override
                    public void onError(Object error) {
                        if(error instanceof HttpException) {
                            ResponseBody respBody = ((HttpException) error).response().errorBody();

                            TypeAdapter<ISSPassErrorModel> errorParserAdapter = new Gson().getAdapter(ISSPassErrorModel.class);
                            try {
                                ISSPassErrorModel errorModel = errorParserAdapter.fromJson(respBody.string());
                                mISSPassUIInterface.ShowError(errorModel);
                            }catch(IOException ie){
                                new ISSPassErrorModel().setErrorMessage("Unable to parse the response string");
                            }

                        }

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
