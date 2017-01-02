package com.naruku.fisher.cart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.naruku.fisher.Logger;
import com.naruku.fisher.R;

import java.util.ArrayList;

public class CartFragment extends Fragment {


    private View mCartView;
    private ListView mCartList;
    private ArrayList<CartList> mCartArrayList;
    private CartList[] cartListsDummy;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mCartView = inflater.inflate(R.layout.fragment_cart, container, false);
        mCartList = (ListView) mCartView.findViewById(R.id.cart_list_lvDetails);
        return mCartView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

                updateCartList(dummyData());
        mCartList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private void updateCartList(ArrayList<CartList> cartLists) {
        Logger.e("updateCartList", "temp");
       // mCartArrayList = new ArrayList<>();
        CartBaseAdapter cartBaseAdapter = new CartBaseAdapter(getActivity(), cartLists);
        mCartList.setAdapter(cartBaseAdapter);
    }

    ArrayList<CartList> dummyData() {
        mCartArrayList = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            CartList cartList = new CartList();
            cartList.product_title = "Tuna Fish";
            cartList.product_count = "300";
            cartList.product_value = 300;
            mCartArrayList.add(cartList);
        }


        return mCartArrayList;
    }


}
