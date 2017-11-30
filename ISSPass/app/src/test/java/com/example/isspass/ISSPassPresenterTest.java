package com.example.isspass;

import com.example.isspass.Beans.ISSPassModelResponse;
import com.example.isspass.Listener.ISSPassListener;
import com.example.isspass.interactor.ISSPassServiceInteractor;
import com.example.isspass.model.Response;
import com.example.isspass.presenter.ISSPassPresenter;
import com.example.isspass.presenter.ISSPassPresenterImpl;
import com.example.isspass.ui.view.ISSPassUIInterface;

import junit.framework.Assert;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by 582827 on 11/30/2017.
 */

public class ISSPassPresenterTest {

    private ISSPassPresenter mISSPassPresenter;
    private FakeISSPassListener  mFakeISSPassListener;
    private FakeISSPassServiceInteractor mFakeISSPassServiceInteractor;
    private FakeISSPassUIInterface mFakeISSPassUIInterface;

    @Before
    public void setUp() {

        mFakeISSPassServiceInteractor = new FakeISSPassServiceInteractor();
        mFakeISSPassUIInterface = new FakeISSPassUIInterface();
        mISSPassPresenter = new ISSPassPresenterImpl(mFakeISSPassUIInterface);
        mFakeISSPassListener = new FakeISSPassListener();
    }

    @Test
    public void test_ifUIComponentsCalled() {

        //Success
        mFakeISSPassServiceInteractor.isSuccess = true;
        try {
            mFakeISSPassServiceInteractor.getDatafromService("56.66644", "45.55564", mFakeISSPassListener);
        }catch(JSONException je){
            je.printStackTrace();
        }
        Assert.assertTrue(mFakeISSPassListener.onSuccessCalled);
    }
    private class FakeISSPassListener  implements ISSPassListener{
        boolean onSuccessCalled=false;

        @Override
        public void onError(Object error) {

        }

        @Override
        public void onSuccess(Object success) {
            onSuccessCalled=true;
        }

    }
private class FakeISSPassUIInterface implements ISSPassUIInterface {

    @Override
    public void showSpinner() {

    }

    @Override
    public void hideSpinner() {

    }

    @Override
    public void setPresenter(ISSPassPresenter isspassPresenter) {

    }

    @Override
    public void ShowTSSPassList(Response[] resp) {

    }
}

    private class FakeISSPassServiceInteractor implements ISSPassServiceInteractor {
        boolean isSuccess;

        @Override
        public void getDatafromService(String latitude, String longitude, ISSPassListener listener) throws JSONException {
            if (isSuccess) {
                listener.onSuccess(null);
            } else {
                listener.onError(null);
            }
        }
    }
}
