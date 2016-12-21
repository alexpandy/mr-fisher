package com.naruku.fisher.navdrawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.naruku.fisher.R;

import java.util.ArrayList;

/**
 * Created by apandy on 9/8/16.
 */

public class NavigationDrawerMenuAdapter extends RecyclerView.Adapter<NavigationDrawerMenuAdapter.MyViewHolder> {

    ArrayList<NavigationMenuModel> mList;
    Context mContext;
    //OnSelectDrawerMenu onSelectDrawerMenu;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMenuIcon;
        TextView tvMenuName;
        LinearLayout llContainer;

        public MyViewHolder(View view) {
            super(view);

            ivMenuIcon = (ImageView) view.findViewById(R.id.layout_navimgIcon);
            tvMenuName = (TextView) view.findViewById(R.id.layout_navtxtMenu);
            llContainer = (LinearLayout) view.findViewById(R.id.layout_navllcontainer);
        }
    }


    public NavigationDrawerMenuAdapter(Context context, ArrayList<NavigationMenuModel> list) {
        mList = list;
        mContext = context;
     //   onSelectDrawerMenu= (OnSelectDrawerMenu) context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_navmenu, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        NavigationMenuModel navigationMenuModel = mList.get(position);
        holder.tvMenuName.setText(navigationMenuModel.getMenu());
        holder.ivMenuIcon.setImageDrawable(mContext.getResources().getDrawable(navigationMenuModel.getIcon()));
       /* if (position == SessionStorage.getInstance().getMenuSelectedPosition()) {
            holder.ivMenuIcon.setColorFilter(mContext.getResources().getColor(R.color.colorPrimary));
            holder.tvMenuName.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        } else {
            holder.tvMenuName.setTextColor(mContext.getResources().getColor(R.color.taupeGrey));
            holder.ivMenuIcon.setColorFilter(mContext.getResources().getColor(R.color.taupeGrey));
        }*/
        /*if (navigationMenuModel.getMenutype() == StringUtil.MENU_FOOTER) {
            holder.llContainer.setBackgroundColor(mContext.getResources().getColor(R.color.menu_bg_footer));
            holder.tvMenuName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        } else {
            holder.llContainer.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            holder.tvMenuName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        }
        holder.llContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelectDrawerMenu.loadMenuFromUserSelection(position);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

