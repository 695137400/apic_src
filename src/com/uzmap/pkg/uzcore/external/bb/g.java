package com.uzmap.pkg.uzcore.external.bb;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class g extends ViewGroup {
    public g(Context context, Object o) {
        super(context);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = this.getChildCount();
        int maxHeight = 0;
        int maxWidth = 0;
        this.measureChildren(widthMeasureSpec, heightMeasureSpec);

        for (int i = 0; i < count; ++i) {
            View child = this.getChildAt(i);
            if (child.getVisibility() != 8) {
                g.a lp = (g.a) child.getLayoutParams();
                int childRight = lp.a + child.getMeasuredWidth();
                int childBottom = lp.b + child.getMeasuredHeight();
                maxWidth = Math.max(maxWidth, childRight);
                maxHeight = Math.max(maxHeight, childBottom);
            }
        }

        maxWidth += this.getPaddingLeft() + this.getPaddingRight();
        maxHeight += this.getPaddingTop() + this.getPaddingBottom();
        maxHeight = Math.max(maxHeight, this.getSuggestedMinimumHeight());
        maxWidth = Math.max(maxWidth, this.getSuggestedMinimumWidth());
        this.setMeasuredDimension(resolveSize(maxWidth, widthMeasureSpec), resolveSize(maxHeight, heightMeasureSpec));
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new g.a(-2, -2, 0, 0);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = this.getChildCount();

        for (int i = 0; i < count; ++i) {
            View child = this.getChildAt(i);
            if (child.getVisibility() != 8) {
                g.a lp = (g.a) child.getLayoutParams();
                int childLeft = this.getPaddingLeft() + lp.a;
                int childTop = this.getPaddingTop() + lp.b;
                child.layout(childLeft, childTop, childLeft + child.getMeasuredWidth(), childTop + child.getMeasuredHeight());
            }
        }

    }

    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof g.a;
    }

    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new g.a(p);
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public static class a extends LayoutParams {
        public int a;
        public int b;

        public a(int width, int height, int x, int y) {
            super(width, height);
            this.a = x;
            this.b = y;
        }

        public a(LayoutParams source) {
            super(source);
        }
    }
}
