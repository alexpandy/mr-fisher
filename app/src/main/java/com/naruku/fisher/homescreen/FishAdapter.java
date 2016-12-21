package com.naruku.fisher.homescreen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.naruku.fisher.Logger;
import com.naruku.fisher.R;
import com.naruku.fisher.viewdetail.ViewDetailActivity;

import java.util.List;

/**
 * Created by apandy on 18/05/16.
 */
public class FishAdapter extends RecyclerView.Adapter<FishAdapter.MyViewHolder> {

    private Context mContext;
    private List<Fishes> fishList;
    Fishes fishPosition;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.fish_tvTitle);
            count = (TextView) view.findViewById(R.id.fish_tvCount);
            thumbnail = (ImageView) view.findViewById(R.id.fish_ivThumbnail);

        }
    }


    public FishAdapter(Context mContext, List<Fishes> fishList) {
        this.mContext = mContext;
        this.fishList = fishList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fish_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        fishPosition = fishList.get(position);
        holder.title.setText(fishPosition.getName());
    //    holder.count.setText(holder.getAdapterPosition());
        //   holder.count.setText(album.getNumOfSongs() + " songs");
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.e("holder.thumbnail", "VVVVV" +holder.getAdapterPosition());
               // Logger.e("holderstatus", "Status"+holder.);
                Logger.e("holder.thumbnail", "Status"+fishList.get(holder.getAdapterPosition()).getName());

                Intent signinIntent = new Intent(mContext, ViewDetailActivity.class);
                //  intent.putExtra("Error Desc", errDesc);
                mContext.startActivity(signinIntent);

            }
        });
        // loading album cover using Glide library
        Glide.with(mContext).load(fishPosition.getThumbnail()).into(holder.thumbnail);

//        holder.overflow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showPopupMenu(holder.overflow);
//            }
//        });
    }

    /**
     * Showing popup menu when tapping on 3 dots

     private void showPopupMenu(View view) {
     // inflate menu
     PopupMenu popup = new PopupMenu(mContext, view);
     MenuInflater inflater = popup.getMenuInflater();
     inflater.inflate(R.menu.menu_album, popup.getMenu());
     popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
     popup.show();
     }*/

    /**
     * Click listener for popup menu items
     */
   /* class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }*/
    @Override
    public int getItemCount() {
      //  Logger.e("holder.thumbnail", "fishList" +fishList.size());

        return fishList.size();
    }
}
