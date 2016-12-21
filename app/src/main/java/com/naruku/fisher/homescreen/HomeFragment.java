package com.naruku.fisher.homescreen;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.naruku.fisher.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private View homeView;
    private RecyclerView recyclerView;
    private List<Fishes> fishList;
    private FishAdapter fishadapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        homeView = inflater.inflate(R.layout.fragment_home, container, false);
        initWidget(homeView);
        return homeView;
    }

    private void initWidget(View homeView) {

        initCollapsingToolbar(homeView);
        recyclerView = (RecyclerView) homeView.findViewById(R.id.recycler_view);

        fishList = new ArrayList<>();
        fishadapter = new FishAdapter(getActivity(), fishList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(fishadapter);
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Logger.e("recyclerView","recyclerView"+);
            }
        });
        prepareFishCards(homeView);

        try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) homeView.findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //

    }

    private void prepareFishCards(View homeView) {
        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album2,
                R.drawable.album3,
                R.drawable.album4,
                R.drawable.album5,
                R.drawable.album6,
                R.drawable.album7,
                R.drawable.album8,
                R.drawable.album9,
                R.drawable.album10,
                R.drawable.album11};

        Fishes a = new Fishes("Transport", 13, covers[0]);
        fishList.add(a);

        a = new Fishes("Transport", 8, covers[1]);
        fishList.add(a);

        a = new Fishes("Bridges", 11, covers[2]);
        fishList.add(a);

        a = new Fishes("Bridges", 12, covers[3]);
        fishList.add(a);

        a = new Fishes("Bridges", 14, covers[4]);
        fishList.add(a);

        a = new Fishes("Bridges", 1, covers[5]);
        fishList.add(a);

        a = new Fishes("IT", 11, covers[6]);
        fishList.add(a);

        a = new Fishes("Theatres", 14, covers[7]);
        fishList.add(a);

        a = new Fishes("Lakes", 11, covers[8]);
        fishList.add(a);

        a = new Fishes("Lakes", 17, covers[9]);
        fishList.add(a);

        fishadapter.notifyDataSetChanged();
    }

    private void initCollapsingToolbar(View homeView) {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) homeView.findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) homeView.findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
