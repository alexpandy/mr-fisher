package com.naruku.fisher.login;

import android.content.Context;

import com.android.volley.Request;
import com.naruku.fisher.AppConstants;
import com.naruku.fisher.network.JsonResponseParser;
import com.naruku.fisher.network.RequestExecutor;
import com.naruku.fisher.network.VolleyRequest;

import bolts.Task;

/**
 * Created by apandy on 2/1/2017.
 */

public class LoginController {
    public Context mContext;
public LoginController(Context context){
    mContext = context;
}
    public static LoginController newInstance(Context context){

        return new LoginController(context);
    }
    public Task<Object> doLoginCall(){
        String url = AppConstants.HOST+"signin";
        VolleyRequest<LoginModel> loginModelVolleyRequest = VolleyRequest.newInstance(Request.Method.POST,url);
        JsonResponseParser<LoginModel> parser = new JsonResponseParser<>(LoginModel.class);
        loginModelVolleyRequest.setResponseParser(parser);

        return RequestExecutor.getInstance(mContext).makeRequestCall(loginModelVolleyRequest);
    }
}

