package com.example.isspass.constants;

/**
 * Created by Ashutosh Singh on 11/30/2017.
 * The permission which are handled in the code
 */

public final class ISSPassPermissionConstants {

    private ISSPassPermissionConstants() {

        throw new UnsupportedOperationException("Cannot instantiate the type " + ISSPassPermissionConstants.class.getSimpleName());
    }

    /**
     * List down all the permission you want to handle in the code. For ISSPass we just need one permission for location
     */
    public static final int REQUEST_LOCATION = 0;

    public enum Permissions {

        LOCATION {
            @Override
            public String toString() {

                return android.Manifest.permission.ACCESS_FINE_LOCATION;
            }

            @Override
            public int getRequestCode() {

                return REQUEST_LOCATION;
            }
        };
        abstract public int getRequestCode();
    }



}
