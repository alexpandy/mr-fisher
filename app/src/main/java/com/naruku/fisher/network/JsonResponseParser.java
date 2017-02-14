package com.naruku.fisher.network;

import com.bluelinelabs.logansquare.LoganSquare;
import com.naruku.fisher.Logger;

import java.io.IOException;

/**
 * Created by s on 08/06/16.
 */
public class JsonResponseParser<T> implements ResponseParser {

    private Class<T> mClz;

    public JsonResponseParser(Class<T> clz) {
        mClz = clz;
    }

    @Override
    public T parse(String response) {
        try {
            Logger.d("Response -- "+response);
            return LoganSquare.parse(response, mClz);
        } catch (IOException e) {
/*
            AirbrakeNotifier.notify(e);
*/
            e.printStackTrace();
        }
        return null;
    }
}
