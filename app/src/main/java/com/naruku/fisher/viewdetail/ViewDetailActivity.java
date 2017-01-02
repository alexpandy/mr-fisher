package com.naruku.fisher.viewdetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.naruku.fisher.BadgeDrawable;
import com.naruku.fisher.Logger;
import com.naruku.fisher.NavigationDrawerActivity;
import com.naruku.fisher.R;
import com.naruku.fisher.cart.CartActivity;
import com.naruku.fisher.homescreen.HomeActivity;

public class ViewDetailActivity extends NavigationDrawerActivity {

    LayerDrawable mCartMenuIcon;
    int mCartCount;

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

    public static void setBadgeCount(Context context, LayerDrawable icon, String count) {

        BadgeDrawable badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_badge);
        if (reuse != null && reuse instanceof BadgeDrawable) {
            badge = (BadgeDrawable) reuse;
        } else {
            badge = new BadgeDrawable(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_badge, badge);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_detail_menu, menu);
        mCartMenuIcon = (LayerDrawable) menu.findItem(R.id.action_cart).getIcon();
        setBadgeCount(this, mCartMenuIcon, String.valueOf(mCartCount++));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_cart:
                Intent cartIntent = new Intent(this, CartActivity.class);
              //  cartIntent.putExtra("", errDesc);
                startActivity(cartIntent);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    /*     public void setMenuEnable(boolean status) {

        mFeedbackMenu.findItem(R.id.menu_feedback_send).setEnabled(status);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_feedback_send:
                FeedbackFragment feedbackFragment = (FeedbackFragment) FeedbackActivity.this.getSupportFragmentManager().
                        findFragmentByTag(FeedbackActivity.this.getString(R.string.frag_tag_feedback));
                feedbackFragment.sendFeedback();

                break;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
