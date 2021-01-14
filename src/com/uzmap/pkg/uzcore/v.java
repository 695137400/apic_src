package com.uzmap.pkg.uzcore;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;

public class v {
    public Animation a;
    public Animation b;
    private boolean c;

    public v(Animation in, Animation out) {
        this.a = in;
        this.b = out;
    }

    public void a(long duration) {
        this.a.setDuration(duration);
        this.b.setDuration(duration);
    }

    public void a() {
        this.a.setFillEnabled(true);
        this.a.setFillAfter(true);
        if (com.uzmap.pkg.uzcore.external.l.a >= 14) {
            this.a.setBackgroundColor(com.uzmap.pkg.uzcore.external.l.c);
        }

        this.a.setDetachWallpaper(false);
        this.a.setInterpolator(new AccelerateDecelerateInterpolator());
        if (com.uzmap.pkg.uzcore.external.l.a >= 14) {
            this.b.setBackgroundColor(com.uzmap.pkg.uzcore.external.l.c);
        }

        this.b.setDetachWallpaper(false);
        this.b.setInterpolator(new AccelerateDecelerateInterpolator());
        if (!this.c) {
            this.a.setZAdjustment(1);
            this.b.setZAdjustment(0);
        }

    }

    public boolean b() {
        return this.c;
    }

    public void c() {
        this.c = true;
        this.a.setZAdjustment(0);
        this.b.setZAdjustment(1);
    }
}
