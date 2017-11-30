package com.example.isspass.Listener;

import com.example.isspass.constants.ISSPassPermissionConstants;

/**
 * Created by Ashutosh Singh on 11/30/2017.
 * Used for Handling the Permissions
 */

public interface ISSPassPermissionListener {

    /**
     * Callback to listen if requested permission has been granted.
     *
     *                          which can be used to know that which permission has been granted. Caller should always check it before doing
     *                          further task on permission granted.
     */
    void onPermissionGranted(ISSPassPermissionConstants.Permissions permission);

    /**
     * Callback to listen if requested permission has not been granted.
     *
     *                         which can be used to know that which permission has not been granted. Sometime a permission can be optional,
     *                         like you can show a page without having that permission having other features. So by checking which permission
     *                         has not been granted you can proceed further.
     */
    void onPermissionDenied(ISSPassPermissionConstants.Permissions permission);



}
