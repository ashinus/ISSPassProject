package com.example.isspass.ui.Activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.isspass.R;
import com.example.isspass.Listener.ISSPassPermissionListener;
import com.example.isspass.constants.ISSPassPermissionConstants;
import com.example.isspass.model.Request;
import com.example.isspass.model.Response;
import com.example.isspass.presenter.ISSPassPresenterImpl;
import com.example.isspass.service.ISSPassLocationServiceClass;
import com.example.isspass.ui.Adapter.ISSPassListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import com.example.isspass.Utility.Util;
/**
 * Created by Ashutosh Singh on 11/30/2017.
 * Launcher Activity to show the list
 */

public class ISSPassActivity extends BaseActivity {

    private Request request;
    private ISSPassLocationServiceClass locationService;
    ListView  listview=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isspass);
        //Init Location Service
        locationService = new ISSPassLocationServiceClass(this);
        //Create the Presenter for network calls
        ISSPassPresenterImpl.createPresenter(this);
        //Create new request for getting the server response
        request = new Request();
        // Get ListView object from XML
        listview = (ListView) findViewById(R.id.customList);

    }

    @Override
    protected void onResume() {
        super.onResume();

        checkPermission(ISSPassPermissionConstants.Permissions.LOCATION, permissionListener);
        if (mISSPassPresenter != null && request != null)
            mISSPassPresenter.getISSPassData(request);


    }

    ISSPassPermissionListener permissionListener = new ISSPassPermissionListener() {
        @Override
        public void onPermissionGranted(ISSPassPermissionConstants.Permissions permission) {
            ISSPassLocationServiceClass locationService = new ISSPassLocationServiceClass(ISSPassActivity.this);
            locationService.getLocationLatAndLongForRequest(request);
        }

        @Override
        public void onPermissionDenied(ISSPassPermissionConstants.Permissions permission) {

        }
    };
    /**
     * Show the TSSPass List with the data fetched from the server
     */
    @Override
    public void ShowTSSPassList(Response[] resp){

        ArrayList list = new ArrayList();
        list.addAll(Arrays.asList(resp));
        ISSPassListAdapter listAdapter = new ISSPassListAdapter(this,list);
        listview.setAdapter(listAdapter);

    }

}
