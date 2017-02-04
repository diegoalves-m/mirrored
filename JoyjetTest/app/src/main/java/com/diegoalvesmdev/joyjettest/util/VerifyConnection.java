package com.diegoalvesmdev.joyjettest.util;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by diegoalves on 04/02/2017.
 */

public class VerifyConnection {

    Context context;

    public VerifyConnection(Context context) {
        this.context = context;
    }

    public  boolean hasConnection() {
        boolean connected;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isAvailable()
                && connectivityManager.getActiveNetworkInfo().isConnected()) {
            connected = true;
        } else {
            connected = false;
        }
        return connected;
    }

}
