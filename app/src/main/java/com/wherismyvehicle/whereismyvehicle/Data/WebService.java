package com.wherismyvehicle.whereismyvehicle.Data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class WebService {
    private final Context context;

    public WebService(Context context) {
        this.context = context;
    }

    protected void checkConnection() {
        if(!connectionAvailable()){
            throw new ConnectionUnavailableException("Device is not connected to the internet");
        }
    }

    protected boolean connectionAvailable(){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
