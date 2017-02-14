package com.naruku.fisher.network;

/**
 * Created by NSM Sevices on 6/16/16.
 */
public class ErrorResponseParser<T> implements ResponseParser {

    private Class<T> mClz;

  /*  public ErrorResponseParser(){

    }*/

    @Override
    public <T> T parse(String response) {
        return null;
    }
}
