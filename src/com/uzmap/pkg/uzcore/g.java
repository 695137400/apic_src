package com.uzmap.pkg.uzcore;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.Scroller;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;
import org.json.JSONObject;

public class g extends FrameLayout {
    private static final int s;
    private static final float t;

    static {
        s = com.uzmap.pkg.uzcore.d.a().r / 2;
        t = com.uzmap.pkg.uzcore.d.a().n;
    }

    private int a;
    private final Context b;
    private final Scroller c;
    private a d;
    private com.uzmap.pkg.uzcore.external.bb.h e;
    private final int f;
    private final int g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private int n;
    private y o;
    private com.uzmap.pkg.uzcore.uzmodule.aa.n p;
    private boolean q;
    private boolean r;
    private boolean u;
    private int v;
    private int w;
    private long x;
    private final Runnable y = new Runnable() {
        public void run() {
            g.this.s();
        }
    };

    public g(Context context, Object o) {
        super(context);
        this.b = context;
        this.c = new Scroller(this.b, new DecelerateInterpolator());
        this.g = com.uzmap.pkg.uzcore.d.a().k / 3 * 2;
        this.setFadingEdgeLength(0);
        this.setBackgroundColor(com.uzmap.pkg.uzcore.external.l.c);
        this.f = -com.uzmap.pkg.uzcore.d.a().q;
    }

    public void a(a child) {
        this.e = new com.uzmap.pkg.uzcore.external.bb.h(this.b, null);
        LayoutParams hlp = com.uzmap.pkg.uzcore.external.l.d(com.uzmap.pkg.uzcore.external.l.d, this.g);
        hlp.topMargin = -this.g;
        hlp.gravity = 48;
        this.addView(this.e, hlp);
        this.e.setVisibility(8);
        this.d = child;
        LayoutParams wlp = com.uzmap.pkg.uzcore.external.l.d(com.uzmap.pkg.uzcore.external.l.d, com.uzmap.pkg.uzcore.external.l.d);
        this.d.setLayoutParams(wlp);
        this.addView(this.d);
    }

    public a a() {
        return this.d;
    }

    public void a(View child, boolean fixed, boolean needVerScroll) {
        if (fixed) {
            this.addView(child);
        } else {
            this.d.a(child, needVerScroll);
        }

    }

    public void a(String url) {
        if (this.d != null) {
            this.d.a(url);
            this.r = true;
        }

    }

    public void b() {
        if (this.d != null) {
            String curUrl = this.d.q();
            curUrl = curUrl != null ? curUrl : "";
            this.d.a(curUrl);
        }

    }

    public boolean c() {
        return this.r;
    }

    public boolean onInterceptTouchEvent(MotionEvent e) {
        if (!this.i) {
            return false;
        } else {
            int action = e.getAction();
            int y = (int) e.getRawY();
            switch (action) {
                case 0:
                    this.a = y;
                case 1:
                default:
                    break;
                case 2:
                    int distance = y - this.a;
                    boolean slop = Math.abs(distance) > s;
                    if (distance > 0 && slop) {
                        if (this.q() && !this.m) {
                            this.j = true;
                            return true;
                        }
                    } else if (distance < 0 && slop && this.r() && !this.m) {
                        this.k = true;
                        this.d.y();
                        return true;
                    }

                    this.a = y;
            }

            return false;
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getRawY();
        int action = event.getAction();
        switch (action) {
            case 0:
                this.a = y;
                break;
            case 1:
            case 3:
            case 4:
                this.k();
                break;
            case 2:
                int distance = y - this.a;
                if (this.j) {
                    this.c(distance);
                } else if (this.k) {
                    this.d(distance);
                }

                this.a = y;
        }

        return true;
    }

    private void c(int distance) {
        int f1 = this.getScrollY();
        int f2;
        if (distance > 0) {
            f2 = (int) ((float) distance * 0.5F);
        } else {
            f2 = (int) ((float) distance * 0.7F);
            int bound = f1 - f2;
            if (bound >= 0) {
                f2 += bound;
                this.scrollBy(0, -f2);
                return;
            }
        }

        this.scrollBy(0, -f2);
        this.e(f1);
    }

    private void d(int distance) {
        int f1 = this.getScrollY();
        int f2;
        if (distance < 0) {
            f2 = (int) ((float) distance * 0.5F);
        } else {
            f2 = (int) ((float) distance * 0.7F);
            int bound = f1 - f2;
            if (bound <= 0) {
                f2 += bound;
                this.scrollBy(0, -f2);
                return;
            }
        }

        this.scrollBy(0, -f2);
    }

    private void k() {
        int sy = this.getScrollY();
        if (this.j) {
            if (!this.h) {
                this.m();
            } else if (sy <= this.f) {
                this.l();
            } else {
                this.m();
            }
        } else if (this.k) {
            this.n();
        }

    }

    private void l() {
        this.m = true;
        this.n = 0;
        int sy = this.getScrollY();
        int dist = this.f - sy;
        if (this.h) {
            this.e.a(8);
            this.e.c(0);
            this.e.d(0);
            this.e.d();
        }

        this.c.startScroll(0, sy, 0, dist);
        this.invalidate();
        if (this.p != null) {
            this.p.success("", true, false);
        }

    }

    private void m() {
        int sy = this.getScrollY();
        int dist = 0 - sy;
        this.c.startScroll(0, sy, 0, dist);
        this.postInvalidate();
    }

    private void n() {
        int sy = this.getScrollY();
        this.c.startScroll(0, -sy, 0, sy);
        this.postInvalidate();
    }

    private void e(int curScrollY) {
        if (curScrollY <= this.f && this.n == 0) {
            if (this.h) {
                this.e.a(0);
                this.e.c(8);
                this.e.c();
                this.e.b(1);
            }

            if (this.m) {
                this.m = false;
            }

            this.n = 1;
        } else if (curScrollY > this.f && this.n == 1) {
            if (this.h) {
                this.e.a(0);
                this.e.c(8);
                this.e.b();
                this.e.b(0);
            }

            this.n = 0;
        }

    }

    public void computeScroll() {
        if (this.c.computeScrollOffset()) {
            int sy = this.c.getCurrY();
            if (this.j) {
                this.scrollTo(0, sy);
            } else if (this.k) {
                this.scrollTo(0, -sy);
            } else if (this.l) {
                this.scrollTo(0, -sy);
                this.e(this.getScrollY());
            }

            this.postInvalidate();
            if (sy == 0) {
                this.j = false;
                this.k = false;
            }
        } else if (this.l) {
            this.l = false;
            this.j = true;
            this.k();
        }

    }

    private void o() {
        if (this.m) {
            if (this.h) {
                this.e.a(0);
                this.e.c(8);
                this.e.a();
                this.e.b();
            }

            this.m();
            this.p();
        }
    }

    private void p() {
        this.m = false;
    }

    private boolean q() {
        return this.d.getScrollY() == 0;
    }

    private boolean r() {
        int h1 = (int) ((float) this.d.getContentHeight() * t);
        int h2 = this.d.getScrollY() + this.d.getHeight();
        return h1 == h2;
    }

    public void a(boolean flag) {
        this.i = flag;
    }

    public void d() {
        this.o();
    }

    public void e() {
        this.d();
    }

    public void f() {
        if (!this.l && !this.m && this.c.isFinished()) {
            this.l = true;
            if (!this.h || this.e.getVisibility() != 0) {
                this.a(true);
                this.e.a(true);
                this.e.setVisibility(0);
                this.e.a((Drawable) null);
                this.h = true;
            }

            this.c.startScroll(0, 0, 0, -this.f * 2);
            this.invalidate();
        }
    }

    public void a(UZModuleContext args) {
        this.p = (com.uzmap.pkg.uzcore.uzmodule.aa.n) args;
        this.h = this.p.a;
        this.e.e(this.p.c);
        this.e.setBackgroundColor(this.p.d);
        this.e.c(this.p.f);
        this.e.d(this.p.g);
        this.e.e(this.p.h);
        this.e.a(this.p.i);
        this.e.b(this.p.e);
        this.e.a(this.p.b);
        this.e.setVisibility(0);
        this.a(true);
    }

    protected void onAnimationEnd() {
        super.onAnimationEnd();
        if (!this.post(this.y)) {
            this.s();
        }

    }

    private void s() {
        if (this.o != null) {
            this.o.a();
            this.a((y) null);
        }

    }

    protected synchronized void a(final com.uzmap.pkg.uzcore.uzmodule.aa.e args) {
        if (!this.w()) {
            this.u();
            if (this.t()) {
                int var10000 = com.uzmap.pkg.uzcore.external.l.a;
            }

            Animation animation = args.b();
            this.a(new y() {
                public void a() {
                    if (args.c() && args.d()) {
                        g.this.a(args.j, args.k);
                    }

                    g.this.clearAnimation();
                    int var10000 = com.uzmap.pkg.uzcore.external.l.a;
                    args.success(new JSONObject(), true);
                    g.this.v();
                }
            });
            this.startAnimation(animation);
        }
    }

    public void a(y listener) {
        this.o = listener;
    }

    private void a(int transX, int transY) {
        android.widget.RelativeLayout.LayoutParams oldlp = (android.widget.RelativeLayout.LayoutParams) this.getLayoutParams();
        int nx = oldlp.leftMargin + transX;
        int ny = oldlp.topMargin + transY;
        int nw = oldlp.width;
        int nh = oldlp.height;
        android.widget.RelativeLayout.LayoutParams rlp = com.uzmap.pkg.uzcore.external.l.b(nw, nh);
        View win = (View) this.getParent();
        int winRight = win.getRight();
        int winBottom = win.getBottom();
        if (nw < 0) {
            nw = winRight;
        }

        if (nh < 0) {
            nh = winBottom;
        }

        int frameRight = nx + nw;
        if (frameRight <= winRight && nw != winRight) {
            rlp.rightMargin = 0;
        } else {
            rlp.rightMargin = winRight - frameRight;
        }

        int frameBottom = ny + nh;
        if (frameBottom <= winBottom && nh != winBottom) {
            if (frameBottom == 0) {
                ++ny;
                rlp.bottomMargin = 0;
            } else {
                rlp.bottomMargin = 0;
            }
        } else {
            rlp.bottomMargin = winBottom - frameBottom;
        }

        rlp.topMargin = ny;
        rlp.leftMargin = nx;
        this.setLayoutParams(rlp);
    }

    private boolean t() {
        android.widget.RelativeLayout.LayoutParams lp = (android.widget.RelativeLayout.LayoutParams) this.getLayoutParams();
        View win = (View) this.getParent();
        int winRight = win.getRight();
        int winBottom = win.getBottom();
        int nx = lp.leftMargin;
        int nw = lp.width;
        int ny = lp.topMargin;
        int nh = lp.width;
        boolean outOffX = nw < 0 && nx > 0 || nx < 0 || nx + nw > winRight;
        boolean outOffY = nh < 0 && ny > 0 || ny < 0 || ny + nh > winBottom;
        return outOffX || outOffY;
    }

    private void u() {
        this.q = true;
    }

    private void v() {
        this.q = false;
    }

    private boolean w() {
        return this.q;
    }

    public long g() {
        return this.x;
    }

    public void a(long animDuration) {
        this.x = animDuration;
    }

    public boolean h() {
        return this.u;
    }

    public int i() {
        return this.v;
    }

    public void a(int animType) {
        this.v = animType;
        if (this.v != -1) {
            this.u = true;
        }

    }

    public int j() {
        return this.w;
    }

    public void b(int subAnimType) {
        this.w = subAnimType;
    }

    public String toString() {
        return "frameh[" + (this.d != null ? this.d.B() : "null") + "]" + "@" + Integer.toHexString(this.hashCode());
    }
}
