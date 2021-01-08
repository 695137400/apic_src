package com.uzmap.pkg.uzcore.external.bb;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class f extends ViewGroup {
    static final ecl a;

    static {
        int deviceVersion = VERSION.SDK_INT;
        if (deviceVersion >= 17) {
            a = new hcl();
        } else if (deviceVersion >= 16) {
            a = new gcl();
        } else {
            a = new f1();
        }

    }

    private final ViewDragHelper p;
    private final Rect t;
    private final ArrayList<acl> v;
    private int b;
    private int c;
    private Drawable d;
    private int e;
    private boolean f;
    private View g;
    private float h;
    private float i;
    private int j;
    private boolean k;
    private int l;
    private float m;
    private float n;
    private d1 o;
    private boolean q;
    private boolean r;
    private boolean s;
    private final int u;

    public f(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public f(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.b = -858993460;
        this.r = true;
        this.t = new Rect();
        this.v = new ArrayList();
        float density = context.getResources().getDisplayMetrics().density;
        this.e = (int) (60.0F * density + 0.5F);
        this.u = (int) (5.0F * density + 0.5F);
        ViewConfiguration.get(context);
        this.setWillNotDraw(false);
        this.p = ViewDragHelper.create(this, 0.5F, new bcl(null));
        this.p.setEdgeTrackingEnabled(1);
        this.p.setMinVelocity(400.0F * density);
        this.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            }
        });
    }

    private static boolean a(View v) {
        if (ViewCompat.isOpaque(v)) {
            return true;
        } else if (VERSION.SDK_INT >= 18) {
            return false;
        } else {
            Drawable bg = v.getBackground();
            if (bg != null) {
                return bg.getOpacity() == -1;
            } else {
                return false;
            }
        }
    }

    public void k(int overhangSize) {
        this.e = overhangSize;
        this.requestLayout();
    }

    public void l(int color) {
        this.b = color;
    }

    public void a(d1 listener) {
        this.o = listener;
    }

    void c(View panel) {
        if (this.o != null) {
            this.o.a(panel, this.h);
        }

    }

    void d(View panel) {
        if (this.o != null) {
            this.o.a(panel);
        }

    }

    void e(View panel) {
        if (this.o != null) {
            this.o.b(panel);
        }

    }

    void f(View panel) {
        int leftBound = this.getPaddingLeft();
        int rightBound = this.getWidth() - this.getPaddingRight();
        int topBound = this.getPaddingTop();
        int bottomBound = this.getHeight() - this.getPaddingBottom();
        int left;
        int right;
        int top;
        int bottom;
        if (panel != null && a(panel)) {
            left = panel.getLeft();
            right = panel.getRight();
            top = panel.getTop();
            bottom = panel.getBottom();
        } else {
            bottom = 0;
            top = 0;
            right = 0;
            left = 0;
        }

        int i = 0;

        for (int childCount = this.getChildCount(); i < childCount; ++i) {
            View child = this.getChildAt(i);
            if (child == panel) {
                break;
            }

            int clampedChildLeft = Math.max(leftBound, child.getLeft());
            int clampedChildTop = Math.max(topBound, child.getTop());
            int clampedChildRight = Math.min(rightBound, child.getRight());
            int clampedChildBottom = Math.min(bottomBound, child.getBottom());
            byte vis;
            if (clampedChildLeft >= left && clampedChildTop >= top && clampedChildRight <= right && clampedChildBottom <= bottom) {
                vis = 4;
            } else {
                vis = 0;
            }

            child.setVisibility(vis);
        }

    }

    void a() {
        int i = 0;

        for (int childCount = this.getChildCount(); i < childCount; ++i) {
            View child = this.getChildAt(i);
            if (child.getVisibility() == 4) {
                child.setVisibility(0);
            }
        }

    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.r = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.r = true;
        int i = 0;

        for (int count = this.v.size(); i < count; ++i) {
            acl dlr = this.v.get(i);
            dlr.run();
        }

        this.v.clear();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode != 1073741824) {
            if (!this.isInEditMode()) {
                throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
            }

            if (widthMode == Integer.MIN_VALUE) {
                widthMode = 1073741824;
            } else if (widthMode == 0) {
                widthMode = 1073741824;
                widthSize = 300;
            }
        } else if (heightMode == 0) {
            if (!this.isInEditMode()) {
                throw new IllegalStateException("Height must not be UNSPECIFIED");
            }

            if (heightMode == 0) {
                heightMode = Integer.MIN_VALUE;
                heightSize = 300;
            }
        }

        int layoutHeight = 0;
        int maxLayoutHeight = -1;
        switch (heightMode) {
            case Integer.MIN_VALUE:
                maxLayoutHeight = heightSize - this.getPaddingTop() - this.getPaddingBottom();
                break;
            case 1073741824:
                layoutHeight = maxLayoutHeight = heightSize - this.getPaddingTop() - this.getPaddingBottom();
        }

        float weightSum = 0.0F;
        boolean canSlide = false;
        int widthRemaining = widthSize - this.getPaddingLeft() - this.getPaddingRight();
        int childCount = this.getChildCount();
        this.g = null;

        int fixedPanelWidthLimit;
        int measuredWidth;
        int childHeightSpec;
        int horizontalMargin;
        for (fixedPanelWidthLimit = 0; fixedPanelWidthLimit < childCount; ++fixedPanelWidthLimit) {
            View child = this.getChildAt(fixedPanelWidthLimit);
            c1 lp = (c1) child.getLayoutParams();
            if (child.getVisibility() == 8) {
                lp.c = false;
            } else {
                if (lp.a > 0.0F) {
                    weightSum += lp.a;
                    if (lp.width == 0) {
                        continue;
                    }
                }

                horizontalMargin = lp.leftMargin + lp.rightMargin;
                int childWidthSpec;
                if (lp.width == -2) {
                    childWidthSpec = MeasureSpec.makeMeasureSpec(widthSize - horizontalMargin, Integer.MIN_VALUE);
                } else if (lp.width == -1) {
                    childWidthSpec = MeasureSpec.makeMeasureSpec(widthSize - horizontalMargin, 1073741824);
                } else {
                    childWidthSpec = MeasureSpec.makeMeasureSpec(lp.width, 1073741824);
                }

                if (lp.height == -2) {
                    measuredWidth = MeasureSpec.makeMeasureSpec(maxLayoutHeight, Integer.MIN_VALUE);
                } else if (lp.height == -1) {
                    measuredWidth = MeasureSpec.makeMeasureSpec(maxLayoutHeight, 1073741824);
                } else {
                    measuredWidth = MeasureSpec.makeMeasureSpec(lp.height, 1073741824);
                }

                child.measure(childWidthSpec, measuredWidth);
                childHeightSpec = child.getMeasuredWidth();
                horizontalMargin = child.getMeasuredHeight();
                if (heightMode == Integer.MIN_VALUE && horizontalMargin > layoutHeight) {
                    layoutHeight = Math.min(horizontalMargin, maxLayoutHeight);
                }

                widthRemaining -= childHeightSpec;
                canSlide |= lp.b = widthRemaining < 0;
                if (lp.b) {
                    this.g = child;
                }
            }
        }

        if (canSlide || weightSum > 0.0F) {
            fixedPanelWidthLimit = widthSize;

            for (int i = 0; i < childCount; ++i) {
                View child = this.getChildAt(i);
                if (child.getVisibility() != 8) {
                    c1 lp = (c1) child.getLayoutParams();
                    if (child.getVisibility() != 8) {
                        boolean skippedFirstPass = lp.width == 0 && lp.a > 0.0F;
                        measuredWidth = skippedFirstPass ? 0 : child.getMeasuredWidth();
                        if (canSlide && child != this.g) {
                            if (lp.width < 0 && (measuredWidth > fixedPanelWidthLimit || lp.a > 0.0F)) {
                                if (skippedFirstPass) {
                                    if (lp.height == -2) {
                                        childHeightSpec = MeasureSpec.makeMeasureSpec(maxLayoutHeight, Integer.MIN_VALUE);
                                    } else if (lp.height == -1) {
                                        childHeightSpec = MeasureSpec.makeMeasureSpec(maxLayoutHeight, 1073741824);
                                    } else {
                                        childHeightSpec = MeasureSpec.makeMeasureSpec(lp.height, 1073741824);
                                    }
                                } else {
                                    childHeightSpec = MeasureSpec.makeMeasureSpec(child.getMeasuredHeight(), 1073741824);
                                }

                                horizontalMargin = MeasureSpec.makeMeasureSpec(fixedPanelWidthLimit, 1073741824);
                                child.measure(horizontalMargin, childHeightSpec);
                            }
                        } else if (lp.a > 0.0F) {
                            if (lp.width == 0) {
                                if (lp.height == -2) {
                                    childHeightSpec = MeasureSpec.makeMeasureSpec(maxLayoutHeight, Integer.MIN_VALUE);
                                } else if (lp.height == -1) {
                                    childHeightSpec = MeasureSpec.makeMeasureSpec(maxLayoutHeight, 1073741824);
                                } else {
                                    childHeightSpec = MeasureSpec.makeMeasureSpec(lp.height, 1073741824);
                                }
                            } else {
                                childHeightSpec = MeasureSpec.makeMeasureSpec(child.getMeasuredHeight(), 1073741824);
                            }

                            int newWidth;
                            int childWidthSpec;
                            if (canSlide) {
                                horizontalMargin = lp.leftMargin + lp.rightMargin;
                                newWidth = widthSize - horizontalMargin;
                                childWidthSpec = MeasureSpec.makeMeasureSpec(newWidth, 1073741824);
                                if (measuredWidth != newWidth) {
                                    child.measure(childWidthSpec, childHeightSpec);
                                }
                            } else {
                                horizontalMargin = Math.max(0, widthRemaining);
                                newWidth = (int) (lp.a * (float) horizontalMargin / weightSum);
                                childWidthSpec = MeasureSpec.makeMeasureSpec(measuredWidth + newWidth, 1073741824);
                                child.measure(childWidthSpec, childHeightSpec);
                            }
                        }
                    }
                }
            }
        }

        this.setMeasuredDimension(widthSize, layoutHeight);
        this.f = canSlide;
        if (this.p.getViewDragState() != 0 && !canSlide) {
            this.p.abort();
        }

    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = r - l;
        int paddingLeft = this.getPaddingLeft();
        int paddingRight = this.getPaddingRight();
        int paddingTop = this.getPaddingTop();
        int childCount = this.getChildCount();
        int xStart = paddingLeft;
        int nextXStart = paddingLeft;
        if (this.r) {
            this.h = this.f && this.q ? 1.0F : 0.0F;
        }

        int i;
        for (i = 0; i < childCount; ++i) {
            View child = this.getChildAt(i);
            if (child.getVisibility() != 8) {
                c1 lp = (c1) child.getLayoutParams();
                int childWidth = child.getMeasuredWidth();
                int offset = 0;
                int childLeft;
                int childRight;
                if (lp.b) {
                    childLeft = lp.leftMargin + lp.rightMargin;
                    childRight = Math.min(nextXStart, width - paddingRight - this.e) - xStart - childLeft;
                    this.j = childRight;
                    lp.c = xStart + lp.leftMargin + childRight > 0;
                    xStart += (int) ((float) childRight * this.h) + lp.leftMargin;
                } else if (this.f && this.l != 0) {
                    offset = (int) ((1.0F - this.h) * (float) this.l);
                    xStart = nextXStart;
                } else {
                    xStart = nextXStart;
                }

                childLeft = xStart - offset;
                childRight = childLeft + childWidth;
                int childBottom = paddingTop + child.getMeasuredHeight();
                child.layout(childLeft, paddingTop, childRight, childBottom);
                nextXStart += child.getWidth();
            }
        }

        if (this.r) {
            if (this.f) {
                if (this.l != 0) {
                    this.a(this.h);
                }

                if (((c1) this.g.getLayoutParams()).c) {
                    this.a(this.g, this.h, this.b);
                }
            } else {
                for (i = 0; i < childCount; ++i) {
                    this.a(this.getChildAt(i), 0.0F, this.b);
                }
            }

            this.f(this.g);
        }

        this.r = false;
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw) {
            this.r = true;
        }

    }

    public void requestChildFocus(View child, View focused) {
        super.requestChildFocus(child, focused);
        if (!this.isInTouchMode() && !this.f) {
            this.q = child == this.g;
        }

    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (this.s && !this.p()) {
            return false;
        } else {
            int action = MotionEventCompat.getActionMasked(ev);
            if (!this.f && action == 0 && this.getChildCount() > 1) {
                View secondChild = this.getChildAt(1);
                if (secondChild != null) {
                    this.q = !this.p.isViewUnder(secondChild, (int) ev.getX(), (int) ev.getY());
                }
            }

            if (!this.f || this.k && action != 0) {
                this.p.cancel();
                return super.onInterceptTouchEvent(ev);
            } else if (action != 3 && action != 1) {
                boolean interceptTap = false;
                float x;
                float y;
                switch (action) {
                    case 0:
                        this.k = false;
                        x = ev.getX();
                        y = ev.getY();
                        this.m = x;
                        this.n = y;
                        boolean isViewUnder = this.p.isViewUnder(this.g, (int) x, (int) y);
                        if (isViewUnder && this.g(this.g)) {
                            interceptTap = true;
                        }
                    case 1:
                    default:
                        break;
                    case 2:
                        x = ev.getX();
                        y = ev.getY();
                        float adx = Math.abs(x - this.m);
                        float ady = Math.abs(y - this.n);
                        int slop = this.p.getTouchSlop();
                        if (adx > (float) slop && ady > adx) {
                            this.p.cancel();
                            this.k = true;
                            return false;
                        }
                }

                boolean interceptForDrag = this.p.shouldInterceptTouchEvent(ev);
                return interceptForDrag || interceptTap;
            } else {
                this.p.cancel();
                return false;
            }
        }
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (!this.f) {
            return super.onTouchEvent(ev);
        } else {
            if (!this.s) {
                this.p.processTouchEvent(ev);
            }

            int action = ev.getAction();
            boolean wantTouchEvents = true;
            float x;
            float y;
            switch (action & 255) {
                case 0:
                    x = ev.getX();
                    y = ev.getY();
                    this.m = x;
                    this.n = y;
                    break;
                case 1:
                    if (this.g(this.g)) {
                        x = ev.getX();
                        y = ev.getY();
                        float dx = x - this.m;
                        float dy = y - this.n;
                        int slop = this.p.getTouchSlop();
                        if (dx * dx + dy * dy < (float) (slop * slop) && this.p.isViewUnder(this.g, (int) x, (int) y)) {
                            this.a(this.g, 0);
                        }
                    }
            }

            return wantTouchEvents;
        }
    }

    private boolean a(View pane, int initialVelocity) {
        if (!this.r && !this.a(0.0F, initialVelocity)) {
            return false;
        } else {
            this.q = false;
            return true;
        }
    }

    private boolean b(View pane, int initialVelocity) {
        if (!this.r && !this.a(1.0F, initialVelocity)) {
            return false;
        } else {
            this.q = true;
            return true;
        }
    }

    public boolean l() {
        return this.b(this.g, 0);
    }

    public boolean o() {
        return this.a(this.g, 0);
    }

    public void a(boolean flag) {
        this.s = flag;
    }

    public boolean p() {
        return !this.f || this.h == 1.0F;
    }

    private void a(int newLeft) {
        c1 lp = (c1) this.g.getLayoutParams();
        int leftBound = this.getPaddingLeft() + lp.leftMargin;
        this.h = (float) (newLeft - leftBound) / (float) this.j;
        if (this.l != 0) {
            this.a(this.h);
        }

        if (lp.c) {
            this.a(this.g, this.h, this.b);
        }

        this.c(this.g);
    }

    private void a(View v, float mag, int fadeColor) {
        c1 lp = (c1) v.getLayoutParams();
        if (mag > 0.0F && fadeColor != 0) {
            int baseAlpha = (fadeColor & -16777216) >>> 24;
            int imag = (int) ((float) baseAlpha * mag);
            int color = imag << 24 | fadeColor & 16777215;
            if (lp.d == null) {
                lp.d = new Paint();
            }

            lp.d.setColorFilter(new PorterDuffColorFilter(color, Mode.SRC_OVER));
            ViewCompat.getLayerType(v);
            this.b(v);
        } else if (ViewCompat.getLayerType(v) != 0) {
            if (lp.d != null) {
                lp.d.setColorFilter(null);
            }

            acl dlr = new acl(v);
            this.v.add(dlr);
            ViewCompat.postOnAnimation(this, dlr);
        }

    }

    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        c1 lp = (c1) child.getLayoutParams();
        int save = canvas.save();
        if (this.f && !lp.b && this.g != null) {
            canvas.getClipBounds(this.t);
            this.t.right = Math.min(this.t.right, this.g.getLeft());
            canvas.clipRect(this.t);
        }

        int sdk = VERSION.SDK_INT;
        boolean result;
        if (sdk >= 11) {
            result = super.drawChild(canvas, child, drawingTime);
        } else {
            boolean drawingCache = lp.c && this.h > 0.0F;
            if (drawingCache) {
                if (!child.isDrawingCacheEnabled()) {
                    child.setDrawingCacheEnabled(true);
                }

                Bitmap cache = child.getDrawingCache();
                if (cache != null) {
                    canvas.drawBitmap(cache, (float) child.getLeft(), (float) child.getTop(), lp.d);
                    result = false;
                } else {
                    result = super.drawChild(canvas, child, drawingTime);
                }
            } else {
                if (child.isDrawingCacheEnabled()) {
                    child.setDrawingCacheEnabled(false);
                }

                result = super.drawChild(canvas, child, drawingTime);
            }
        }

        canvas.restoreToCount(save);
        return result;
    }

    private void b(View v) {
        a.a(this, v);
    }

    boolean a(float slideOffset, int velocity) {
        if (!this.f) {
            return false;
        } else {
            c1 lp = (c1) this.g.getLayoutParams();
            int leftBound = this.getPaddingLeft() + lp.leftMargin;
            int x = (int) ((float) leftBound + slideOffset * (float) this.j);
            if (this.p.smoothSlideViewTo(this.g, x, this.g.getTop())) {
                this.a();
                ViewCompat.postInvalidateOnAnimation(this);
                return true;
            } else {
                return false;
            }
        }
    }

    public void computeScroll() {
        if (this.p.continueSettling(true)) {
            if (!this.f) {
                this.p.abort();
                return;
            }

            ViewCompat.postInvalidateOnAnimation(this);
        }

    }

    public void a(Drawable d) {
        this.d = d;
    }

    public void draw(Canvas c) {
        super.draw(c);
        View shadowView = this.getChildCount() > 1 ? this.getChildAt(1) : null;
        if (shadowView != null && this.d != null) {
            int shadowWidth = this.u;
            int right = shadowView.getLeft();
            int top = shadowView.getTop();
            int bottom = shadowView.getBottom();
            int left = right - shadowWidth;
            this.d.setBounds(left, top, right, bottom);
            this.d.draw(c);
        }
    }

    private void a(float slideOffset) {
        c1 slideLp = (c1) this.g.getLayoutParams();
        boolean dimViews = slideLp.c && slideLp.leftMargin <= 0;
        int childCount = this.getChildCount();

        for (int i = 0; i < childCount; ++i) {
            View v = this.getChildAt(i);
            if (v != this.g) {
                int oldOffset = (int) ((1.0F - this.i) * (float) this.l);
                this.i = slideOffset;
                int newOffset = (int) ((1.0F - slideOffset) * (float) this.l);
                int dx = oldOffset - newOffset;
                v.offsetLeftAndRight(dx);
                if (dimViews) {
                    this.a(v, 1.0F - this.i, this.c);
                }
            }
        }

    }

    boolean g(View child) {
        if (child == null) {
            return false;
        } else {
            c1 lp = (c1) child.getLayoutParams();
            return this.f && lp.c && this.h > 0.0F;
        }
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new c1();
    }

    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return p instanceof MarginLayoutParams ? new c1((MarginLayoutParams) p) : new c1(p);
    }

    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof c1 && super.checkLayoutParams(p);
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new c1(this.getContext(), attrs);
    }

    protected Parcelable onSaveInstanceState() {
        return null;
    }

    protected void onRestoreInstanceState(Parcelable state) {
    }

    public interface d1 {
        void a(View var1, float var2);

        void a(View var1);

        void b(View var1);
    }

    interface ecl {
        void a(com.uzmap.pkg.uzcore.external.bb.f var1, View var2);
    }

    public static class c1 extends MarginLayoutParams {
        private static final int[] e = new int[]{16843137};
        public float a = 0.0F;
        boolean b;
        boolean c;
        Paint d;

        public c1() {
            super(-1, -1);
            this.a();
        }

        public c1(int width, int height) {
            super(width, height);
            this.a();
        }

        public c1(LayoutParams source) {
            super(source);
            this.a();
        }

        public c1(MarginLayoutParams source) {
            super(source);
            this.a();
        }

        public c1(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray a = c.obtainStyledAttributes(attrs, e);
            this.a = a.getFloat(0, 0.0F);
            a.recycle();
            this.a();
        }

        private void a() {
            this.d = new Paint();
        }
    }

    static class f1 implements ecl {
        public void a(com.uzmap.pkg.uzcore.external.bb.f parent, View child) {
            int l = child.getLeft();
            int t = child.getTop();
            int r = child.getRight();
            int b = child.getBottom();
            ViewCompat.postInvalidateOnAnimation(parent, l, t, r, b);
        }
    }

    static class gcl extends f1 {
        private Method a;
        private Field b;

        gcl() {
            try {
                this.a = View.class.getDeclaredMethod("getDisplayList", (Class[]) null);
            } catch (NoSuchMethodException var3) {
            }

            try {
                this.b = View.class.getDeclaredField("mRecreateDisplayList");
                this.b.setAccessible(true);
            } catch (NoSuchFieldException var2) {
            }

        }

        public void a(com.uzmap.pkg.uzcore.external.bb.f parent, View child) {
            if (this.a != null && this.b != null) {
                try {
                    this.b.setBoolean(child, true);
                    this.a.invoke(child, (Object[]) null);
                } catch (Exception var4) {
                }

                super.a(parent, child);
            } else {
                child.invalidate();
            }
        }
    }

    static class hcl extends f1 {
        public void a(com.uzmap.pkg.uzcore.external.bb.f parent, View child) {
            ViewCompat.setLayerPaint(child, ((c1) child.getLayoutParams()).d);
        }
    }

    private class acl implements Runnable {
        final View a;

        acl(View childView) {
            this.a = childView;
        }

        public void run() {
            if (this.a.getParent() == this) {
                b(this.a);
            }

            v.remove(this);
        }
    }

    private class bcl extends Callback {
        private bcl() {
        }

        // $FF: synthetic method
        bcl(bcl var2) {
            this();
        }

        public boolean tryCaptureView(View child, int pointerId) {
            return !k && ((c1) child.getLayoutParams()).b;
        }

        public void onViewDragStateChanged(int state) {
            if (p.getViewDragState() == 0) {
                if (h == 0.0F) {
                    f(g);
                    e(g);
                    q = false;
                } else {
                    d(g);
                    q = true;
                }
            }

        }

        public void onViewCaptured(View capturedChild, int activePointerId) {
            a();
        }

        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            a(left);
            invalidate();
        }

        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            c1 lp = (c1) releasedChild.getLayoutParams();
            int left = getPaddingLeft() + lp.leftMargin;
            if (xvel > 0.0F || xvel == 0.0F && h > 0.5F) {
                left += j;
            }

            p.settleCapturedViewAt(left, releasedChild.getTop());
            invalidate();
        }

        public int getViewHorizontalDragRange(View child) {
            return j;
        }

        public int clampViewPositionHorizontal(View child, int left, int dx) {
            c1 lp = (c1) g.getLayoutParams();
            int leftBound = getPaddingLeft() + lp.leftMargin;
            int rightBound = leftBound + j;
            int newLeft = Math.min(Math.max(left, leftBound), rightBound);
            return newLeft;
        }

        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            p.captureChildView(g, pointerId);
        }
    }
}
