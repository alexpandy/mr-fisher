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

/*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.feedback_menu, menu);
        mFeedbackMenu = menu;
        setMenuEnable(false);
        return super.onCreateOptionsMenu(menu);
    }

    public void setMenuEnable(boolean status) {

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
