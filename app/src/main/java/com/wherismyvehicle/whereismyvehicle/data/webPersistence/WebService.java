package com.wherismyvehicle.whereismyvehicle.data.webPersistence;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.wherismyvehicle.whereismyvehicle.data.authentication.Singleton;
import com.wherismyvehicle.whereismyvehicle.data.webPersistence.exceptions.ConnectionUnavailableException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class WebService {
    protected static final MediaType JSON_CONTENT_TYPE = MediaType.parse("application/json; charset=utf-8");
    protected final OkHttpClient client;

    private final Context context;
    private String host;

    protected WebService(Context context) {
        this(context, "http://whereismyvehicle.azurewebsites.net/");
    }

    private WebService(Context context, String host) {
        this.context = context;
        this.host = host;
        this.client = new OkHttpClient();
    }

    protected Request.Builder createRequest(String endpoint) {
        Request.Builder builder = new Request.Builder()
            .url(host.concat(endpoint));

        if(getToken() != null) {
            builder = builder.addHeader("Authorization", getToken());
        }

        return builder;
    }

    void checkConnection() {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if(activeNetwork == null || !activeNetwork.isConnectedOrConnecting()){
            throw new ConnectionUnavailableException("Device is not connected to the internet");
        }
    }

    private String getToken(){
        String token = Singleton.getInstance().getToken();
        if(token == null) return null;

        return "Bearer ".concat(token);
    }
}
