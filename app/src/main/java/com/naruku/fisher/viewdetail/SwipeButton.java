package com.naruku.fisher.viewdetail;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;


import com.naruku.fisher.Logger;

import io.victoralbertos.swipe_coordinator.SwipeCoordinator;
import io.victoralbertos.swipe_coordinator.SwipeDirection;

/**
 * Created by NSM Services on 11/23/16.
 */

public class SwipeButton extends SwipeCoordinator {
    ActionDownSwipeListener actionDownSwipeListener;
    private ViewGroup parentViewGroup;

    /**
     * @param parentViewGroup must contain a child view annotated with the id swipeable_view
     * @param swipeDirection  the expected direction for the gesture recognition
     */
    public SwipeButton(ViewGroup parentViewGroup, SwipeDirection swipeDirection) {
        super(parentViewGroup, swipeDirection);
        this.parentViewGroup = parentViewGroup;
    }

    @Override
    public void setOnActionUpSwipeListener(ActionUpSwipeListener actionUpSwipeListener) {
        super.setOnActionUpSwipeListener(actionUpSwipeListener);
        actionDownSwipeListener = (ActionDownSwipeListener) actionUpSwipeListener;

    }

    @Override
    public void setVariancePercentage(float variancePercentage) {
        super.setVariancePercentage(variancePercentage);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (actionDownSwipeListener != null) {
                    actionDownSwipeListener.onActionDown();
                }
                break;
            case MotionEvent.ACTION_OUTSIDE:
                Logger.e("DEBUG_TAG", "Movement occurred outside bounds " +
                        "of current screen element");

                break;

        }
        super.onTouch(view, event);
        return true;
    }

    public interface ActionDownSwipeListener extends ActionUpSwipeListener {
        /**
         * Signal the action up motion.
         *
         * @param thresholdReached true when the threshold is reached, false otherwise.
         */
        void onActionUp(boolean thresholdReached);

        void onActionDown();
    }


    public void doSwipeReset() {
        parentViewGroup.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        parentViewGroup.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        final View swipeableView = parentViewGroup.findViewById(io.victoralbertos.swipe_coordinator.R.id.swipeable_view);

                        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(swipeableView,
                                PropertyValuesHolder.ofFloat("translationX", 0),
                                PropertyValuesHolder.ofFloat("translationY", 0))
                                .setDuration(500);

                        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                swipeableView.requestLayout();
                            }
                        });
                        animator.start();
                    }
                });
    }
}
