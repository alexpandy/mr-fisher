package com.naruku.fisher;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;




import java.util.Timer;
import java.util.TimerTask;

/**
 * Create apandy on
 */
public class RootActivity extends AppCompatActivity {


    protected final String TAG = getClass().getSimpleName();

    /**
     * Our primary toolbar that is going to be made
     * the ActionBar.
     */
    protected Toolbar mToolbar;

    /**
     * The actual ActionBar instance.
     */
    protected ActionBar mActionBar;

    /**
     * The layout holding the ToolBar (ActionBar) and TabLayout if any.
     * This layout allows us to control scroll behavior or the child elements
     * and its motion more easily and gracefully.
     */
    protected AppBarLayout mAppBarLayout;

    /**
     * The layout that is going to hold the actual content of the activity,
     * fragment, tab etc.
     */
    protected FrameLayout mFlContentLayout;

    /**
     * Instance of Alert dialog to ensure dismiss of the dialog can be
     * handled separately.
     */
    protected AlertDialog mAlertDialog;

    /**
     * Dialogs are primarily UI components and hence convenience to declare
     * in RootActivity since any screen in Android is going to be under an
     * activity.
     */
    protected CustomProgressDialog mProgressDialog;

    /**
     * Parent drawer layout that holds the content and navigation view.
     */
    protected DrawerLayout mDrawerLayout;

    /**
     * Navigation view that gets launched on swiping from the left edge
     * of the screen or by clicking on the hamburger icon.
     */
    protected NavigationView mNavigationView;
    protected NavigationView mNavigationFooterView;

    /**
     * Floating Action button on each screen. To be used if necessary.
     * By default, VISIBILITY of this item is set to GONE since not all
     * screens would actually need this functionality.
     */
    protected FloatingActionButton mFloatingActionButton;
    private OnDialogListner mDialogListner;

    /**
     * Handler to this activity thread.
     */
    protected final Handler mHandler = new Handler();
    CountDownTimer waitTimer;
    private TimerTask mTimerTask;
    private Timer mIdleTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * Method to customize the action bar based on each activity's needs.
     */
    protected void customizeActionBar(ActionBar actionBar, Toolbar toolbar) {
        Logger.d(TAG, "customizeActionBar - setting TITLE");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);

        actionBar.setTitle("");
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);

    }

    public final void setActionBarTitle(String title) {
        mActionBar.setTitle(title);
    }

    // ----------------------------------------------
    // - LAYOUT STRUCTURE CHANGES
    // ----------------------------------------------

    /**
     * Sets the content view for this activity. Since we are using navigation
     * bar, content view cannot be used as before.
     *
     * @param view View to be set for the fragment or layout that is to be shown
     *             as the main content layout.
     */
    protected final void setMainContentView(View view) {
        try {
            if (mFlContentLayout != null) {

                mFlContentLayout.addView(view);
            }
        } catch (Exception e) {
            Logger.d(e.getLocalizedMessage());
            Logger.d(e.getMessage());
            //AirbrakeNotifier.notify(e);

        }
    }

    /**
     * Sets the content view for this activity. Since we are using navigation
     * bar, content view cannot be used as before.
     *
     * @param layout Layout to be set for the fragment or layout that is to be shown
     *               as the main content layout.
     */
    protected final void setMainContentView(int layout) {
        try {
            if (mFlContentLayout != null) {
                Logger.d(TAG, "setMainContentView - setting the Layout with ID: " + layout);
                mFlContentLayout.addView(getLayoutInflater().inflate(layout, null));
            }
        } catch (Exception e) {
            Logger.d(e.getLocalizedMessage());
            Logger.d(e.getMessage());
         //   AirbrakeNotifier.notify(e);

        }
    }

    /**
     * Clears all the child views associated with the content view.
     */
    protected final void cleanMainContentView() {
        mFlContentLayout.removeAllViews();
    }

    /**
     * Look for a child with the given id in the Content layout.
     *
     * @param id Id of the view to search for in Content layout
     * @return View associated with the given ID
     */
    protected final View findViewByIdInContent(int id) {
        return mFlContentLayout.findViewById(id);
    }

    /**
     * Look for a child with the given id in the Navigation drawer.
     *
     * @param id Id of the view to search for in Navigation drawer
     * @return View associated with the given ID
     */
    protected final View findViewByIdInDrawer(int id) {
        return mNavigationView.findViewById(id);
    }


    // ---------------------------------------------
    // - TAB BAR RELATED
    // ---------------------------------------------

    protected final void addTabLayout(int layout) {
        View view = getLayoutInflater().inflate(layout, null);
        mAppBarLayout.addView(view);
    }

    protected final void addTabLayout(View view) {
        mAppBarLayout.addView(view);
    }

    // ----------------------------------------------
    // - PROGRESS DIALOG FUNCTIONS
    // ----------------------------------------------

    protected void showNonCancelableProgressDialog(int msgResourceId) {
        showNonCancelableProgressDialog(getString(msgResourceId));
    }

    protected void showNonCancelableProgressDialog(String message) {
        initDialog();
        mProgressDialog.showNonCancelable(message);
    }

    public void showProgressDialog(int msgResourceId) {
        showProgressDialog(getString(msgResourceId));
    }

    protected void showProgressDialog(String message) {
        initDialog();
        mProgressDialog.setCancelable(false);
        mProgressDialog.show(message);
    }

    private void initDialog() {
        // If a progress dialog instance is not available, create one.
        if (mProgressDialog == null) {
            mProgressDialog = new CustomProgressDialog(this);
        }
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.hide();
        }
    }

    // -------------------------------------------
    // - ALERT DIALOG FUNCTIONS
    // -------------------------------------------

    public void showErrorDialog(String message) {
        showErrorDialog(null, message);
    }

    public void showErrorDialog(String title, String message) {
        showErrorDialog(title, message, getString(R.string.ok));
    }

    public boolean isNetworkConnectionAvailable() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    public void showErrorDialog(String title, String message, String buttonText) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setMessage(message);

        if (title != null && title.length() > 0) {
            // We can show an alert dialog with an empty title. Hence this check.
            builder.setTitle(title);
        }

        // Generic error dialogs are used for information only. User only clicks on OK to dismiss it.
        builder.setNegativeButton(buttonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        mAlertDialog = builder.create();
        mAlertDialog.show();
    }

    public void showErrorDialog(String title, String message, String negativeButtonText, String positiveButtonText) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setMessage(message);

        if (title != null && title.length() > 0) {
            // We can show an alert dialog with an empty title. Hence this check.
            builder.setTitle(title);
        }

        // Generic error dialogs are used for information only. User only clicks on OK to dismiss it.
        builder.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              mDialogListner.onPositiveButtonClick();

            }
        });
        mAlertDialog = builder.create();
        mAlertDialog.show();
    }

       /**
     * Used to hide both Error dialog and Long press dialog
     */

    public void hideAlertDialog() {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
    }

    public void showListAlertDialog(String title, final String[] array,
                                    int indexOfSelectedItem,
                                    DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(title);

        builder.setSingleChoiceItems(array, indexOfSelectedItem, onClickListener);

        mAlertDialog = builder.create();
        mAlertDialog.show();
    }

    // -------------------------------------------
    // - UTILITY FUNCTIONS
    // -------------------------------------------

    /**
     * Hides the virtual Keyboard if its showing currently
     */
    public void hideSoftKeyBoard() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager.isAcceptingText()) {
            if (getCurrentFocus() != null) {
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        //yogesh please check here
        /*if (SessionStorage.getInstance().isAppTimedOut()) {
            Toast.makeText(this, getString(R.string.signout_msg), Toast.LENGTH_LONG).show();
            NavigationFooterController mNavigationFooterController = new NavigationFooterController(this);
            mNavigationFooterController.signOut();
        } else {
            startAlert();
        }*/
    }
    public void setOnDialogListener(OnDialogListner listner) {
        this.mDialogListner = listner;
    }
}
