package com.naruku.fisher.viewdetail;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.naruku.fisher.Logger;
import com.naruku.fisher.R;
import com.naruku.fisher.SessionStorage;
import com.naruku.fisher.cart.CartActivity;
import com.naruku.fisher.cart.CartList;

import java.util.ArrayList;

import me.himanshusoni.quantityview.QuantityView;


public class ViewDetailFragment extends Fragment implements QuantityView.OnQuantityChangeListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View mViewDetail;
    private ViewGroup parentSwipeableView;
    private TextView mSwipeToPaytv;
    private ImageView mImgLoading;
    private ImageView mImgThumb;
    private ImageView mImgLoader;
    private SwipeButton swipeButton;
    private boolean swiped = false;
    private QuantityView quantitySelector;
    protected int selectedQuantity;
    private TextView mProductTitle;
    private String strProductTitle;
    private TextView mProductAmount;
    private int iProductCost;
    private ArrayList<CartList> cartList;

    public ViewDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewDetailFragment newInstance(String param1, String param2) {
        ViewDetailFragment fragment = new ViewDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mViewDetail = inflater.inflate(R.layout.fragment_view_detail, container, false);
        mSwipeToPaytv = (TextView) mViewDetail.findViewById(R.id.vd_tvSwipeToPay);
        mProductTitle = (TextView)mViewDetail.findViewById(R.id.vd_tvTitle);
        mProductAmount = (TextView)mViewDetail.findViewById(R.id.vd_tvRate);
        mImgLoading = (ImageView) mViewDetail.findViewById(R.id.img_rotate);
        parentSwipeableView = (ViewGroup) mViewDetail.findViewById(R.id.vd_rlSwipeable_view);
        quantitySelector = (QuantityView) mViewDetail.findViewById(R.id.quantity_selector);
        mImgThumb = (ImageView) mViewDetail.findViewById(R.id.swipeable_view);
      //  mImgLoader = (ImageView) mViewDetail.findViewById(R.id.decorator_view);

        quantitySelector.setOnQuantityChangeListener(this);

        return mViewDetail;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeToPay();
        cartList = new ArrayList<CartList>();

        quantitySelector.setQuantityClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedQuantity = quantitySelector.getQuantity();
            }
        });
    }

    private void swipeToPay() {

        mImgThumb.setImageResource(R.drawable.fish_slide);
        mSwipeToPaytv.setTextColor(getResources().getColor(R.color.davyGrey));
        swipeButton = new SwipeButton(parentSwipeableView, io.victoralbertos.swipe_coordinator.SwipeDirection.LEFT_TO_RIGHT);
        swipeButton.setThreshold(0.9f);
        swipeButton.setVariancePercentage(1f);
        swipeButton.setOnActionUpSwipeListener(new SwipeButton.ActionDownSwipeListener() {
            @Override
            public void onActionUp(boolean thresholdReached) {

                if (thresholdReached) {

                    swiped = true;

                 //   mImgLoader.setVisibility(View.VISIBLE);
                    mImgThumb.setVisibility(View.INVISIBLE);
                    mSwipeToPaytv.setVisibility(View.INVISIBLE);
                   /* Vibrator vib = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

                    vib.vibrate(50);*/
                   // rotate();
                    Intent cartIntent = new Intent(getActivity(), CartActivity.class);
                    //  cartIntent.putExtra("", errDesc);
                    startActivity(cartIntent);
                    getActivity().finish();
                } /*else {
                        swipeButton.doSwipeReset();
                        mImgThumb.setImageResource(R.drawable.icn_slider_lightblue);
                        Toast.makeText(getActivity(), getString(R.string.warning_check_internet_connection), Toast.LENGTH_LONG).show();
                    }
                }*/ else {
                    swiped = false;
                    mImgThumb.setImageResource(R.drawable.fish_slide);
                    mSwipeToPaytv.setVisibility(View.VISIBLE);
                    mSwipeToPaytv.setTextColor(Color.alpha(0));
                    mSwipeToPaytv.setTextColor(getResources().getColor(R.color.davyGrey));
                }
            }

            @Override
            public void onActionDown() {
                mImgThumb.setImageResource(R.drawable.fish_slide);
                mSwipeToPaytv.setTextColor(getResources().getColor(R.color.taupeGrey));

            }
        });


    }

    private void rotate() {
        RotateAnimation rotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);

        rotate.setDuration(500);
        rotate.setRepeatCount(Animation.INFINITE);
     //   mImgLoader.setAnimation(rotate);


    }

    @Override
    public void onQuantityChanged(int newQuantity, boolean programmatically) {
        Toast.makeText(getActivity(), "Quantity: " + newQuantity, Toast.LENGTH_LONG).show();
 //       ((ViewDetailActivity) getActivity()).mCartCount = newQuantity;
        ((ViewDetailActivity) getActivity()).setBadgeCount(getActivity(), ((ViewDetailActivity) getActivity()).mCartMenuIcon, String.valueOf(newQuantity));
      //  sendCartCount();
        cartList();
    }

    private void cartList() {
        strProductTitle = mProductTitle.getText().toString();
        iProductCost = 230/*Integer.parseInt(mProductAmount.getText().toString())*/;
        CartList cartArrayList = new CartList(strProductTitle,"250",iProductCost,"");
        cartList.add(cartArrayList);

        SessionStorage.getInstance().setCartList(cartList);

    }

    @Override
    public void onLimitReached() {
        Logger.e(getClass().getSimpleName(), "Limit reached");
    }

    public int sendCartCount() {
        return 0;
    }
}

