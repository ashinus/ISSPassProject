package com.example.isspass.ui.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.example.isspass.Listener.ISSPassPermissionListener;
import com.example.isspass.R;
import com.example.isspass.constants.ISSPassPermissionConstants;
import com.example.isspass.model.Response;
import com.example.isspass.presenter.ISSPassPresenter;
import com.example.isspass.ui.view.ISSPassUIInterface;

/**
 * Created by Ashutosh Singh on 11/30/2017.
 * Helper Activity which has the generic methods. All Activity should extend the base Activity
 */

public class BaseActivity extends AppCompatActivity implements ISSPassUIInterface {

    /* Listener to be set to give a call back to the corresponding Activity for callbacks */
    private ISSPassPermissionListener mPermissionListener;
    /* Type of permission that is being reg */
    private ISSPassPermissionConstants.Permissions mPermission;
    private static ProgressDialog progressBar;
    Context mContext;
    public ISSPassPresenter mISSPassPresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void showSpinner() {

        if (mContext == null || (mContext instanceof Activity && ((Activity) mContext).isFinishing())) {
            return;
        }

        if (null == progressBar) {
            progressBar = new ProgressDialog(this);
            progressBar.setMessage(mContext.getResources().getString(R.string.spinner_text));
            progressBar.setTitle(mContext.getResources().getString(R.string.spinner_title));
            progressBar.setCancelable(false);
            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            if (mContext instanceof Activity) {
                if (!((Activity) mContext).isFinishing()) {
                    progressBar.show();
                }
            }
        }
    }

    @Override
    public void hideSpinner() {
            if (null != progressBar) {
                if (progressBar.isShowing()) {
                        try {
                            progressBar.dismiss();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                progressBar = null;
            }

    @Override
    public void setPresenter(ISSPassPresenter isspassPresenter) {
        mISSPassPresenter = isspassPresenter;
    }

    /*Check the location service if disable the user will be taken to the settings screen once location service
    *is enabled we check if the app has location permission enabled. If the app doesnot have the location service enabled
    *will ask the user if he wants to allo the location permission*/

    public void checkPermission(ISSPassPermissionConstants.Permissions requestingPermission, ISSPassPermissionListener listener) {

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if(!isGPSEnabled) {
            showSettingsAlert();
            return;
        }

        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // Current device is running on the OS version before Marshmallow,
            // All permission will be granted to application at install time.
            // No need to check further.
            listener.onPermissionGranted(requestingPermission);
            return;
        }
        // Set requesting permission and callback listener
        this.mPermission = requestingPermission;
        this.mPermissionListener = listener;
        // Check if app has requesting-permission already.
        if (ContextCompat.checkSelfPermission(this, mPermission.toString()) == PackageManager.PERMISSION_GRANTED) {
            // Has permission. Notify the caller
            listener.onPermissionGranted(mPermission);
            doNullifyPermissionVariables();
        } else {
                ActivityCompat.requestPermissions(this, new String[]{mPermission.toString()}, mPermission.getRequestCode());
        }
    }

    private void doNullifyPermissionVariables() {
        mPermissionListener = null;
        mPermission = null;
    }

    // Show settings dialog in case the location service in settings is off
    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        // Setting Dialog Title
        alertDialog.setTitle(getResources().getString(R.string.gps_settings));

        // Setting Dialog Message
        alertDialog.setMessage(getResources().getString(R.string.gps_disabled));

        // On pressing Settings button
        alertDialog.setPositiveButton(getResources().getString(R.string.settings), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton(getResources().getString(R.string.cancel_text), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    @Override
    public void ShowTSSPassList(Response[] resp) {

    }
}
