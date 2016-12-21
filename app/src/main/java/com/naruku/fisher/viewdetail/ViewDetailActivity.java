package com.naruku.fisher.viewdetail;

import android.os.Bundle;

import com.naruku.fisher.NavigationDrawerActivity;
import com.naruku.fisher.R;

public class ViewDetailActivity extends NavigationDrawerActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.activity_view_detail);



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
        return getString(R.string.fish_details);
    }


}
