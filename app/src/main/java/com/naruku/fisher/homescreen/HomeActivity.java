package com.naruku.fisher.homescreen;

import android.os.Bundle;

import com.naruku.fisher.NavigationDrawerActivity;
import com.naruku.fisher.R;


public class HomeActivity extends NavigationDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.activity_home);
    }

    @Override
    protected boolean shouldShowNavigationDrawer() {
        return true;
    }

    @Override
    protected boolean shouldShowToolbarBackButton() {
        return false;
    }

    @Override
    protected String getActivityTitle() {
        return "Home";
    }
}
