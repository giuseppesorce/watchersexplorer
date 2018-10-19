package com.giuseppesorce.commonui.decorators;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import com.giuseppesorce.commonui.R;


/**
 * @author Giuseppe Sorce
 */

public class BiDirectionalDivider extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{ android.R.attr.listDivider };


    private Drawable mDivider;

    private final Rect mBounds = new Rect();

    private Mode mMode;
    private int mDividerSpace;

    public enum Mode {
        MODE_DIVIDER_LINE, MODE_DIVIDER_SPACE
    }

    /**
     * Creates a divider {@link RecyclerView.ItemDecoration} that can be used with a

     *
     * @param context Current context, it will be used to access resources.
     */
    public BiDirectionalDivider(Context context, Mode mode) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        mMode = mode;
        mDividerSpace = context.getResources().getDimensionPixelSize(R.dimen.flm_one_u);

    }

    public void setDividerSpace(int mDividerSpace) {
        this.mDividerSpace = mDividerSpace;
    }

    /**
     * Sets the {@link Drawable} for this divider.
     *
     * @param drawable Drawable that should be used as a divider.
     */
    public void setDrawable(@NonNull Drawable drawable) {
        mDivider = drawable;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (parent.getLayoutManager() == null || mMode == Mode.MODE_DIVIDER_SPACE) {
            return;
        }
        Pair<Integer, Integer> spanAndOrientation = getSpanCountAndOrientation(parent.getLayoutManager());
        drawBorders(c, parent, state, spanAndOrientation);
    }

    private Pair<Integer, Integer> getSpanCountAndOrientation(RecyclerView.LayoutManager lm) {
        int spanCount = 1;
        int orientation = -1;
        if (lm instanceof LinearLayoutManager) {
            orientation = ((LinearLayoutManager) lm).getOrientation();
            if (lm instanceof GridLayoutManager) {
                spanCount = ((GridLayoutManager) lm).getSpanCount();
            }
        } else if (lm instanceof StaggeredGridLayoutManager) {
            orientation = ((StaggeredGridLayoutManager) lm).getOrientation();
            spanCount = ((StaggeredGridLayoutManager) lm).getSpanCount();
        }
        return Pair.create(spanCount, orientation);
    }

    @SuppressWarnings("SuspiciousNameCombination")
    private boolean shouldDrawRight(RecyclerView parent, View view, RecyclerView.State state, Pair<Integer, Integer> spanCountAndOrientation) {
        int position = parent.getChildLayoutPosition(view);
        int itemCount = state.getItemCount();
        int spanCount = spanCountAndOrientation.first;
        int spanIndex = getSpanIndex(view, spanCount);
        if (spanCountAndOrientation.second < 0) {
            // orientation undefined, weird state, possibly custom layout manager, we draw anyway
            return true;
        } else if (spanCountAndOrientation.second == OrientationHelper.HORIZONTAL) {
            // orientation is horizontal, we swap
            return defaultDrawBottom(parent.getLayoutManager(), position, itemCount, spanCount);
        } else return defaultDrawRight(position, itemCount, spanIndex, spanCount);
    }

    @SuppressWarnings("SuspiciousNameCombination")
    private boolean shouldDrawBottom(RecyclerView parent, View view, RecyclerView.State state, Pair<Integer, Integer> spanCountAndOrientation) {
        int position = parent.getChildLayoutPosition(view);
        int itemCount = state.getItemCount();
        int spanCount = spanCountAndOrientation.first;
        int spanIndex = getSpanIndex(view, spanCount);
        if (spanCountAndOrientation.second < 0) {
            // orientation undefined, weird state, possibly custom layout manager, we draw anyway
            return true;
        } else if (spanCountAndOrientation.second == OrientationHelper.HORIZONTAL) {
            // orientation is horizontal, we swap
            return defaultDrawRight(position, itemCount, spanIndex, spanCount);
        } else return defaultDrawBottom(parent.getLayoutManager(), position, itemCount, spanCount);
    }

    private int getSpanIndex(View view, int spanCount) {
        int spanIndex = 0;
        if (view.getLayoutParams() instanceof GridLayoutManager.LayoutParams) {
            GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) view.getLayoutParams();
            spanIndex = params.getSpanIndex();
            spanIndex += params.getSpanSize() - 1;
        } else if (view.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            if (params.isFullSpan()) {
                spanIndex = spanCount - 1;
            } else {
                spanIndex = params.getSpanIndex();
            }
        }
        return spanIndex;
    }

    private boolean defaultDrawBottom(RecyclerView.LayoutManager lm, int position, int itemCount, int spanCount) {
        if (!(position + 1 < itemCount)) return false;
        if (lm instanceof GridLayoutManager) {
            int viewGroup = ((GridLayoutManager) lm).getSpanSizeLookup().getSpanGroupIndex(position, spanCount);
            int lastGroup = ((GridLayoutManager) lm).getSpanSizeLookup().getSpanGroupIndex(itemCount - 1, spanCount);
            return viewGroup < lastGroup;
        }
        return true;
    }

    private boolean defaultDrawRight(int position, int itemCount, int spanIndex, int spanCount) {
        return position + 1 <= itemCount && spanIndex + 1 < spanCount;
    }

    @SuppressLint("NewApi")
    private void drawBorders(Canvas canvas, RecyclerView parent, RecyclerView.State state, Pair<Integer, Integer> spanAndOrientation) {
        canvas.save();

        RecyclerView.LayoutManager lm = parent.getLayoutManager();
        int vLeft;
        int vRight;
        int vTop;
        int vBottom;
        if (parent.getClipToPadding()) {
            vLeft = parent.getPaddingLeft();
            vRight = parent.getWidth() - parent.getPaddingRight();
            vTop = parent.getPaddingTop();
            vBottom = parent.getHeight() - parent.getPaddingBottom();
            canvas.clipRect(vLeft, vTop, vRight, vBottom);
        }

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            final boolean drawRight = shouldDrawRight(parent, child, state, spanAndOrientation);
            final boolean drawBottom = shouldDrawBottom(parent, child, state, spanAndOrientation);

            lm.getDecoratedBoundsWithMargins(child, mBounds);
            final int bottom = mBounds.bottom + Math.round(ViewCompat.getTranslationY(child));
            final int top = bottom - mDivider.getIntrinsicHeight();
            final int right = mBounds.right + Math.round(ViewCompat.getTranslationX(child));
            final int left = right - mDivider.getIntrinsicWidth();
            if (drawRight) {
                mDivider.setBounds(left, child.getTop(), right, child.getBottom());
                mDivider.draw(canvas);
            }
            if (drawBottom) {
                mDivider.setBounds(child.getLeft(), top, child.getRight(), bottom);
                mDivider.draw(canvas);
            }
        }
        canvas.restore();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        if (parent.getLayoutManager() == null) {
            return;
        }
        Pair<Integer, Integer> spanAndOrientation = getSpanCountAndOrientation(parent.getLayoutManager());
        final boolean drawRight = shouldDrawRight(parent, view, state, spanAndOrientation);
        boolean lineMode = mMode == Mode.MODE_DIVIDER_LINE;
        if (drawRight) {
            outRect.set(0, 0, lineMode ? mDivider.getIntrinsicWidth() : mDividerSpace, lineMode ? mDivider.getIntrinsicHeight() : mDividerSpace);
        } else {
            outRect.set(0, 0, 0, lineMode ? mDivider.getIntrinsicHeight() : mDividerSpace);
        }
    }

}
