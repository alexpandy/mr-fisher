package com.naruku.fisher.network;

import android.content.Context;
import android.util.Base64;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;
import com.naruku.fisher.Logger;

import java.util.HashMap;
import java.util.Map;

import bolts.Task;
import bolts.TaskCompletionSource;

/**
 * Created by apandy Services on 09/06/16.
 * <p>
 * Request executor is a bridge between Volley network component and the
 * controllers. Controllers can use request executor to initiate an
 * API call to the network with a @link{VolleyRequest} object.
 */
public class RequestExecutor {

    private RequestExecutor() {
        // Private constructor to avoid instantiation
    }

    private static RequestExecutor sInstance;

    public static synchronized RequestExecutor getInstance(Context context) {//  synchronized (sInstance) {
        if (sInstance == null) {
            sInstance = new RequestExecutor(context);
        }
        return sInstance;
    }

    private Context mContext;
    private RequestQueue mRequestQueue;

    private RequestExecutor(Context context) {
        mContext = context;
       // mRequestQueue = Volley.newRequestQueue(mContext);
        mRequestQueue = Volley.newRequestQueue(context, new HurlStack(null, ClientSSLSocketFactory.getSocketFactory(mContext)));
    }


    public void makeRequestCall(VolleyRequest<?> volleyRequest, OnServiceListener onServiceListener) {
        volleyRequest = addAuthHeaders(volleyRequest);
        volleyRequest.setOnServiceListener(onServiceListener);
        mRequestQueue.add(volleyRequest);
    }

    public Task<Object> makeRequestCall(VolleyRequest<?> volleyRequest) {
        final TaskCompletionSource<Object> task = new TaskCompletionSource<>();
        volleyRequest = addAuthHeaders(volleyRequest);
        volleyRequest.setBodyContentType("application/json");
        volleyRequest.setOnServiceListener(new OnServiceListener() {
            @Override
            public void onSuccess(int serviceIdentifier, final Object response) {
                Logger.d("Task is successfully completed");
                task.setResult(response);
            }

            @Override
            public void onError(int serviceIdentifier, NetworkError networkError) {
                Logger.d("Task is completed with error");
                task.setError(networkError);
            }
        });

        mRequestQueue.add(volleyRequest);
        return task.getTask();

    }

    private VolleyRequest<?> addAuthHeaders(VolleyRequest<?> volleyRequest) {
        Map<String, String> authHeaders = new HashMap<>();
        authHeaders.put("Authorization", "Basic " + Base64.encodeToString("denton:ap0ll0".getBytes(), Base64.DEFAULT));
        authHeaders.put("Accept", "application/json");

        volleyRequest.setHeaders(authHeaders);

        return volleyRequest;
    }
}
