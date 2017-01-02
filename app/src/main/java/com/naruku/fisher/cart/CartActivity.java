package com.naruku.fisher.cart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.naruku.fisher.NavigationDrawerActivity;
import com.naruku.fisher.R;

public class CartActivity extends NavigationDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.activity_cart);
    }

    @Override
    protected boolean shouldShowNavigationDrawer() {
        return false;
    }

    @Override
    protected boolean shouldShowToolbarBackButton() {
        return true;
    }

    @Override
    protected String getActivityTitle() {
        return getString(R.string.cart);
    }
}
