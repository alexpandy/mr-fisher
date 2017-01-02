package com.naruku.fisher.cart;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.naruku.fisher.R;

import java.util.ArrayList;

import me.himanshusoni.quantityview.QuantityView;

/**
 * Created by NSM Services on 10/11/16.
 */

public class CartBaseAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<CartList> mItems;

    public CartBaseAdapter(Context context, ArrayList<CartList> mCartArrayList) {

        this.mContext = context;
        this.mItems = mCartArrayList;
    }

    private class ViewHolder {
        QuantityView tvCartCount;
        TextView tvCartTitle;
        TextView tvCartCost;

    }


    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mItems.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater)
                mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.cart_list, null);
            holder = new ViewHolder();
            holder.tvCartTitle = (TextView) convertView.findViewById(R.id.cart_tvItems);
            holder.tvCartCost = (TextView) convertView.findViewById(R.id.cart_tvRate);
            holder.tvCartCount = (QuantityView) convertView.findViewById(R.id.quantity_selector);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CartList cartListView = (CartList) getItem(position);
        holder.tvCartTitle.setText(cartListView.product_title);
        holder.tvCartCost.setText(cartListView.product_count);
        return convertView;
    }
}
