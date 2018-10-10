package com.metalpay.trailers.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.metalpay.trailers.R;

public class NetworkUtil {
    private static final String LOG_TAG = NetworkUtil.class.getSimpleName();

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        // if no network is available networkInfo will be null
        // otherwise check if we are connected
        if (networkInfo != null && networkInfo.isConnected()) {
            Log.d(LOG_TAG, "Network Available.");
            return true;
        }
        Log.d(LOG_TAG, context.getString(R.string.no_network_connected));
        return false;
    }
}
