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
import com.naruku.fisher.SessionStorage;

import java.util.ArrayList;

public class CartFragment extends Fragment {


    private View mCartView;
    private ListView mCartList;
    private CartList[] cartListsDummy;
    private ArrayList<CartList> mCartArrayList;

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

                updateCartList();
        mCartList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private void updateCartList() {
        Logger.e("updateCartList", "temp");
        mCartArrayList = SessionStorage.getInstance().getmCartList();
        Logger.e("mCartArrayList", "temp--->"+mCartArrayList.size());

        CartBaseAdapter cartBaseAdapter = new CartBaseAdapter(getActivity(), mCartArrayList);
        mCartList.setAdapter(cartBaseAdapter);

    }

    ArrayList<CartList> dummyData() {
        mCartArrayList = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            CartList cartList = new CartList("Tuna Fish","10",300,"");
           /* cartList.setStrItemName();
            cartList.product_count = "300";
            cartList.product_value = 300;*/
            mCartArrayList.add(cartList);
        }


        return mCartArrayList;
    }


}
