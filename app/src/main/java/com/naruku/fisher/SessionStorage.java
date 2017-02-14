package com.naruku.fisher;

import com.naruku.fisher.cart.CartList;

import java.util.ArrayList;

/**
 * Created by apandy on 2/1/2017.
 */

public class SessionStorage {
//
    private String mCookie;
    private ArrayList<CartList> mCartList;

    public String getSessionCookies() {
        return mCookie;
    }

    public void setSessionCookies(String sessionCookies) {
        this.mCookie = sessionCookies;
    }
//
    private static SessionStorage sInstance;
    public static synchronized SessionStorage getInstance() {
        if (sInstance == null) {
            sInstance = new SessionStorage();
        }
        return sInstance;
    }

    public void setCartList(ArrayList<CartList> cartList) {
        mCartList = cartList;
    }

    public ArrayList<CartList> getmCartList() {
        return mCartList;
    }
}
