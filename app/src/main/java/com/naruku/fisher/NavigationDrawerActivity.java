package com.naruku.fisher;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.naruku.fisher.navdrawer.NavigationDrawerMenuAdapter;
import com.naruku.fisher.navdrawer.NavigationMenuModel;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by apandy.
 */
public abstract class NavigationDrawerActivity extends RootActivity implements DrawerLayout.DrawerListener{

  /*  protected NavigationController mNavController;
    protected NavigationFooterController mNavFooterController;*/

    private ListView mLstLoansList;
    private RecyclerView mLstMenu;
    private NavigationDrawerMenuAdapter menuNavigationDrawer;

  /*  private ArrayList<LoanListModel> mLoanListModel;
    private NavLoanAdapter navLoanAdapter;
    private SessionStorage sessionStorage;*/
    private Bundle mSaveInstance = null;
    ArrayList<NavigationMenuModel> navigationMenuList;
    ArrayList<String> navMenuList;

   // private TimeoutDetector mTimeoutDetector;

 //   private OnLoanItemChangeListner mOnLoanItemChangeListner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer_base_layout);
        mSaveInstance = savedInstanceState;
        // Initialize the toolbar layout and assign it as the Action Bar for
        // all screens.
        mToolbar = (Toolbar) findViewById(R.id.ga_ActionBar);
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();

        // Initialize all the parent level views. They will be modified or added
        // by child activities and fragments based on their need.
        mNavigationView = (NavigationView) findViewById(R.id.base_NavigationView);
        mNavigationFooterView = (NavigationView) findViewById(R.id.base_NavigationViewFooter);
        mLstLoansList = (ListView) findViewById(R.id.base_lstLoan);
        mLstMenu = (RecyclerView) findViewById(R.id.nav_Lst_menu);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.base_DrawerLayout);
        mFlContentLayout = (FrameLayout) findViewById(R.id.base_FlContentHolder);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.base_AppBar);
        // Call the ActionBar customizer. We do the basic setup. Child activities
        // can override this function to get better control over the action bar.
        // The action bar and toolbar instances should not be misused anywhere else.
        // Hence made private and setup here. Child activities can save a local instance
        // of action bar from here on. But they cannot manipulate before this level.
        customizeActionBar(mActionBar, mToolbar);

     /*   mNavFooterController = new NavigationFooterController(this, mDrawerLayout);
        mNavFooterController.init(savedInstanceState, mNavigationFooterView);
     */   if (shouldShowNavigationDrawer()) {
            // Initialize the navigation drawer. Specific to this base activity alone.
            mDrawerLayout.addDrawerListener(this);
          /*  mNavController = new NavigationController(this, mDrawerLayout, mLstLoansList, mNavigationFooterView);
            mNavController.init(savedInstanceState, mNavigationView);
//            mNavFooterController = new NavigationFooterController(this, mDrawerLayout);
//            mNavFooterController.init(savedInstanceState, mNavigationFooterView);

            initializeView();
            // mNavController.setAddress(sessionStorage.getLoans().loans[0].address.line1);

            // Initialize Loan related information.
          /*  mLoanListModel = new ArrayList<LoanListModel>();
            sessionStorage = SessionStorage.getInstance();

            loadLoans();*/

            loadMenu();

        }
      //  startAlarmTimer();
    }

    private void loadMenu() {
        // Defined Array values to show in ListView
        String[] menu = new String[]{getString(R.string.nav_fishhome),
                getString(R.string.nav_payment), getString(R.string.nav_deals),
                getString(R.string.nav_facts)
        };
        int[] menuDrawable = new int[]{R.drawable.ic_action_overview, R.drawable.ic_action_payments, R.drawable.ic_action_statements, R.drawable.ic_action_support};
        String[] footerMenu = new String[]{getString(R.string.nav_diet),
                getString(R.string.nav_legal),
                getString(R.string.nav_feedback), getString(R.string.nav_signout)
        };
        int[] footerMenuDrawable = new int[]{R.drawable.ic_action_settings, R.drawable.ic_action_legal, R.drawable.ic_action_feedback, R.drawable.ic_action_exit};

        navigationMenuList = new ArrayList<NavigationMenuModel>();
        addValues(menu, menuDrawable, StringUtil.MENU_NORMAL);
        addValues(footerMenu, footerMenuDrawable, StringUtil.MENU_FOOTER);
        navMenuList = new ArrayList<String>();
        Collections.addAll(navMenuList, menu);
        Collections.addAll(navMenuList, footerMenu);

        if (navigationMenuList.size() > 0) {
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            mLstMenu.setLayoutManager(mLayoutManager);
            mLstMenu.setItemAnimator(new DefaultItemAnimator());

            menuNavigationDrawer = new NavigationDrawerMenuAdapter(NavigationDrawerActivity.this, navigationMenuList);
            mLstMenu.setAdapter(menuNavigationDrawer);
        }
//        mLstMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                mDrawerLayout.closeDrawer(GravityCompat.START);
//                updatePositionHighlight(navigationMenuList.get(position).getMenu());
//                //
//                handleNavMenuClicks(position);
//            }
//        });
    }

   /* @Override
    public void loadMenuFromUserSelection(int position) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        updatePositionHighlight(navigationMenuList.get(position).getMenu());
        handleNavMenuClicks(position);
    }

    public void updatePositionHighlight(String screenName) {
        for (int i = 0; i < navMenuList.size(); i++) {
            String menuTitle = navMenuList.get(i);
            if (menuTitle.equalsIgnoreCase(screenName)) {
                SessionStorage.getInstance().setMenuSelectedPosition(i);
                menuNavigationDrawer.notifyDataSetChanged();
            }
        }
    }*/

    private void addValues(String[] menu, int[] drawable, int type) {
        for (int i = 0; i < menu.length; i++) {
            NavigationMenuModel navMenuModel = new NavigationMenuModel();
            navMenuModel.setMenu(menu[i]);
            navMenuModel.setIcon(drawable[i]);
            navMenuModel.setMenutype(type);
            navigationMenuList.add(navMenuModel);
        }
    }

    /*public void initializeView() {
        mCtvLoanChooser = (CheckedTextView) findViewById(R.id.nav_CtvLoanSelector);
        mCtvLoanChooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCtvLoanChooser.setChecked(mCtvLoanChooser.isChecked() ? false : true);
                if (SessionStorage.getInstance().getLoans().loans.length > 1) {
                    setloanDisplay(mCtvLoanChooser.isChecked());
                }
            }
        });

        setloanDisplay(false);
        if(SessionStorage.getInstance().getLoans()!=null)
        if (SessionStorage.getInstance().getLoans().loans.length == 1) {
            mCtvLoanChooser.setCheckMarkDrawable(null);
        } else {
            mCtvLoanChooser.setCheckMarkDrawable(R.drawable.checkbox_dropdown_selector);
        }
    }

    public void setAddress(String address) {
        mCtvLoanChooser = (CheckedTextView) findViewById(R.id.nav_CtvLoanSelector);
        mCtvLoanChooser.setText(address);
    }

    private void setloanDisplay(boolean isDisplayed) {
        if (isDisplayed) {
            mLstLoansList.setVisibility(View.VISIBLE);
            mCtvLoanChooser.setChecked(true);
            mNavigationView.setVisibility(View.GONE);
            mNavigationFooterView.setVisibility(View.GONE);
        } else {
            mLstLoansList.setVisibility(View.GONE);
            mNavigationView.setVisibility(View.VISIBLE);
            mNavigationFooterView.setVisibility(View.VISIBLE);
            mCtvLoanChooser.setChecked(false);
        }
    }*/

    private void handleNavMenuClicks(int position) {

        switch (position) {
            case 0:
             //   launchActivity(LoanOverviewActivity.class);
                break;
          /*  case 1:
           //     launchActivity(PaymentOverviewActivity.class);
                break;
            case 2:
           //     launchActivity(StatementActivity.class);
                break;
            case 3:
                launchActivity(SupportActivity.class);
                break;
            case 4:
                launchActivity(AccountActivity.class);
                break;

            case 5:
                launchActivity(LegalActivity.class);
                break;

            case 6:
                launchActivity(FeedbackActivity.class);
                break;

            case 7:

                logOutApp();

                break;*/
        }
    }

   /* public void logOutApp() {

        Signout.signoutApp(this);
        stopAlarm();
    }

    private void stopAlarm() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(1000);
                    mTimeoutDetector.stopAlarmForInactivity();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    AirbrakeNotifier.notify(e);
                }
            }
        };

        thread.start();

    }*/

    // allow some time after closing the drawer before performing real navigation
    // so the user can see what is happening

    private void launchActivity(Class activityClz) {
        Intent intent = new Intent(this, activityClz);
        startActivity(intent);
    }


    /**
     * Abstract methods
     */
    protected abstract boolean shouldShowNavigationDrawer();

    protected abstract boolean shouldShowToolbarBackButton();

    protected abstract String getActivityTitle();

    @Override
    protected void onResume() {
        super.onResume();
       /*   if (navMenuList != null) {
            updatePositionHighlight(getActivityTitle());
        }
        // Logout the user if timeout has triggered.
      if (SessionStorage.getInstance().shouldTimeoutUser() && mNavFooterController != null) {
            Signout.signoutApp(this);
            Toast.makeText(this, getString(R.string.inactivity_signout_toast), Toast.LENGTH_SHORT).show();
        }*/
    }

    @Override
    protected void customizeActionBar(ActionBar actionBar, Toolbar toolbar) {
        super.customizeActionBar(actionBar, toolbar);

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.actionbar_title);
        TextView actionBarTitle = (TextView) actionBar.getCustomView();
        actionBarTitle.setText(getActivityTitle());

        if (shouldShowToolbarBackButton()) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            return;
        }

        if (shouldShowNavigationDrawer()) {
            actionBar.setHomeAsUpIndicator(R.mipmap.menu_icon);
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        } else {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                if (shouldShowToolbarBackButton()) {
                    onBackPressed();
                    return true;
                }

                if (shouldShowNavigationDrawer()) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                    return true;
                }
            }
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        if (shouldShowNavigationDrawer()) {
      //      mNavController.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        // Whenever user interacts, we need to start the inactivity timer again. To update this everytime
        // in our timer task or calendar is too much load. onUserInteraction gets called thrice if the user
        // presses the HOME button. To avoid too many calls, we invoke a thread that waits for 5 seconds to
        // see if the user is still not interacting with the app. If he is found to be not interacting,
        // then we set the alarm for inactivity.
      //  startAlarmTimer();
    }

   /* private void startAlarmTimer() {
        if (mTimeoutDetector == null || !mTimeoutDetector.isRunning()) {
            mTimeoutDetector = new TimeoutDetector(this);
        }

        mTimeoutDetector.updateUserInteraction(true);

        if (!mTimeoutDetector.isRunning()) {
            mTimeoutDetector.start();
        }
    }*/

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        Logger.d("NavigationDrawerActivity :: onDrawerOpened");
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        Logger.d("NavigationDrawerActivity :: onDrawerClosed");
    }

    @Override
    public void onDrawerStateChanged(int newState) {
    }


    /**
     * Loan related methods
     */
 /*   private void loadLoans() {
        mLoanListModel.clear();
        Logger.d("LOAD LOANS");
        if(sessionStorage.getLoans()!=null)
        if (sessionStorage.getLoans().loans != null) {
            if (sessionStorage.getLoans().loans.length > 1) {
                Logger.d("LOAD LOANS if");
                dataToLoad(sessionStorage.getSelectedLoanPosition());
                if (mLoanListModel.size() > 0) {
                    Logger.d("LOAD LOANS if");
                    navLoanAdapter = new NavLoanAdapter(NavigationDrawerActivity.this, mLoanListModel);
                    mLstLoansList.setAdapter(navLoanAdapter);
                }
            } else {
                setAddress(sessionStorage.getLoans().loans[0].address.line1);
                Logger.d("LOAD LOANS else");

            }
        }
        mLstLoansList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                sessionStorage.setSelectedLoanPosition(position);
                dataToLoad(sessionStorage.getSelectedLoanPosition());
                navLoanAdapter.notifyDataSetChanged();
                initializeView();

                if (mOnLoanItemChangeListner != null) {
                    mOnLoanItemChangeListner.loadLoan();
                }

                updatePaymentmethod();
            }
        });
    }

    private void updatePaymentmethod() {
        Loan loan = SessionStorage.getInstance().getLoans().loans[sessionStorage.getSelectedLoanPosition()];
        if (loan.paymentMethods != null) {

            if (loan.paymentMethods.length > 0) {

                ArrayList<PaymentData> paymentDataList = new ArrayList<PaymentData>();

                for (int i = 0; i < loan.paymentMethods.length; i++) {
                    PaymentData paymentData = new PaymentData(loan.paymentMethods[i].bankName, loan.paymentMethods[i].routingNumber, StringUtil.getAccountNumberForDisplay(loan.paymentMethods[i].accountNumber), loan.paymentMethods[i].bankAccountType, loan.paymentMethods[i].transactionType, false);
                    paymentDataList.add(paymentData);
                }
                if (paymentDataList.size() > 0) {
                    SessionStorage.getInstance().setPaymentMethod(paymentDataList);
                }

                SessionStorage.getInstance().setPaymentMethodSize(paymentDataList.size());
            }

        }
        SessionStorage.getInstance().setLoan(loan);

    }

    private void dataToLoad(int aCheckedPosition) {
        mLoanListModel.clear();
        for (int i = 0; i < sessionStorage.getLoans().loans.length; i++) {
            LoanListModel loanListModal = new LoanListModel();
            loanListModal.setAddressLine1(sessionStorage.getLoans().loans[i].address.line1);
            loanListModal.setAddressLine2(sessionStorage.getLoans().loans[i].address.line2);
            loanListModal.setCity(sessionStorage.getLoans().loans[i].address.city);
            loanListModal.setState(sessionStorage.getLoans().loans[i].address.state);
            loanListModal.setZipCode(sessionStorage.getLoans().loans[i].address.postalCode);
            loanListModal.setLoanNumber(sessionStorage.getLoans().loans[i].loanNumber);
            loanListModal.setUnPaidPrincipal(sessionStorage.getLoans().loans[i].unpaidPrincipalBalance);
            if (i == aCheckedPosition) {
                setAddress(sessionStorage.getLoans().loans[i].address.line1);

                //   mNavController.setAddress(sessionStorage.getLoans().loans[i].address.line1);
                loanListModal.setCheckedPosition(true);
            } else {
                loanListModal.setCheckedPosition(false);
            }
            mLoanListModel.add(loanListModal);
        }
        Logger.d("Changed Loan Number :: " + sessionStorage.getLoans().loans[aCheckedPosition].loanNumber);
        sessionStorage.setLoan(sessionStorage.getLoans().loans[aCheckedPosition]);
    }

    public void setOnLoanItemChangeListner(OnLoanItemChangeListner onLoanItemChangeListner) {
        mOnLoanItemChangeListner = onLoanItemChangeListner;
    }*/
}
