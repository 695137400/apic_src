package com.uzmap.pkg.uzcore.external.bb;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import com.uzmap.pkg.uzcore.external.UzResourceCache;
import com.uzmap.pkg.uzcore.uzmodule.ApiConfig;
import com.uzmap.pkg.uzkit.UZUtility;

public class j extends ImageView implements Runnable {
    private int a = 5000;
    private float b;
    private float c;
    private float d;
    private float e;
    private final int f;
    private long g;
    private boolean h;
    private final int i;
    private final int j;
    private final LayoutParams k;
    private final WindowManager l;
    private boolean m;
    private long n;
    private j.a o;

    public j(Context context, int size) {
        super(context);
        this.i = size;
        this.l = ((Activity) context).getWindowManager();
        this.setScaleType(ScaleType.CENTER_CROP);
        this.f = ViewConfiguration.get(context).getScaledTouchSlop() / 3 * 2;
        this.j = com.uzmap.pkg.uzcore.d.a().p;
        this.k = new LayoutParams();
        this.k.type = 2;
        this.k.flags = 40;
        this.k.height = size;
        this.k.width = size;
        this.k.alpha = 1.0F;
        this.k.format = -2;
        this.k.gravity = 51;
        this.k.x = 0;
        this.k.y = 0;
        this.k.windowAnimations = 17432577;
        this.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                long timemap = SystemClock.uptimeMillis();
                long step = timemap - j.this.n;
                j.this.n = timemap;
                if (step >= 500L) {
                    if (j.this.o != null) {
                        j.this.o.a();
                    }

                }
            }
        });
    }

    public LayoutParams a() {
        return this.k;
    }

    public void a(j.a listener) {
        this.o = listener;
    }

    public void a(int duration, Drawable drawable) {
        if (duration <= 2000) {
            duration = 5000;
        }

        this.a = duration;
        this.setImageDrawable(drawable);
    }

    public void a(com.uzmap.pkg.uzcore.uzmodule.aa.k args, ApiConfig wInfo) {
        int duration = args.optInt("duration", 5000);
        Drawable drawable = null;
        String iconPath = args.optString("iconPath", null);
        if (iconPath != null) {
            iconPath = UZUtility.makeRealPath(iconPath, wInfo.i());
            drawable = UzResourceCache.get().getDrawable(iconPath, this.getContext());
        }

        if (drawable == null) {
            drawable = this.getContext().getResources().getDrawable(com.uzmap.pkg.uzcore.d.a().w);
        }

        this.a(duration, drawable);
    }

    private void d() {
        this.h();
        this.removeCallbacks(this);
    }

    private void e() {
        this.postDelayed(this, this.a);
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        this.b = event.getRawX();
        this.c = event.getRawY() - (float) this.j;
        switch (action) {
            case 0:
                this.d();
                this.d = event.getX();
                this.e = event.getY();
                this.g = SystemClock.uptimeMillis();
                break;
            case 1:
            case 3:
                this.e();
                this.d = this.e = 0.0F;
                long now = SystemClock.uptimeMillis();
                if (now - this.g <= 400L && !this.h) {
                    this.performClick();
                }

                this.h = false;
                break;
            case 2:
                float x = event.getX();
                float y = event.getY();
                if (Math.abs(x - this.d) > (float) this.f || Math.abs(y - this.e) > (float) this.f) {
                    this.f();
                    this.h = true;
                }
        }

        return true;
    }

    private void f() {
        int x = (int) (this.b - (float) (this.i / 2));
        int y = (int) (this.c - (float) (this.i / 2));
        this.k.x = x;
        this.k.y = y;
        this.l.updateViewLayout(this, this.k);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.e();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.d();
    }

    private void g() {
        this.k.alpha = 0.4F;
        this.l.updateViewLayout(this, this.k);
    }

    private void h() {
        this.k.alpha = 1.0F;
        this.l.updateViewLayout(this, this.k);
    }

    public void run() {
        this.g();
    }

    public boolean isShown() {
        return this.m;
    }

    public void b() {
        if (this.m) {
            this.l.removeViewImmediate(this);
            this.m = false;
        }

    }

    public void c() {
        if (!this.m) {
            this.l.addView(this, this.a());
            this.m = true;
        }
    }

    public interface a {
        void a();
    }
}
