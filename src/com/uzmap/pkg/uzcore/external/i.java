package com.uzmap.pkg.uzcore.external;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.text.TextUtils.TruncateAt;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.uzmap.pkg.uzcore.UZCoreUtil;

public class i extends RelativeLayout {
    private final TextView a;
    private final TextView b;
    private final ProgressBar c;
    private final LinearLayout d;
    private Animation e;
    private Animation f;
    private boolean g;
    private final Runnable h = new Runnable() {
        public void run() {
            i.this.d();
        }
    };

    public i(Context context, Object o) {
        super(context);
        this.d = new LinearLayout(context);
        this.d.setOrientation(1);
        int mini = UZCoreUtil.dipToPix(140);
        LayoutParams lp = l.b(mini, mini);
        lp.addRule(13, -1);
        this.d.setLayoutParams(lp);
        this.addView(this.d);
        GradientDrawable grade = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{-1275068416, -1275068416});
        grade.setCornerRadius(10.0F);
        l.a(this.d, grade);
        int pad = UZCoreUtil.dipToPix(20);
        this.d.setPadding(pad, pad, pad, pad);
        this.c = new ProgressBar(context);
        this.c.setIndeterminate(true);
        int use = UZCoreUtil.dipToPix(40);
        android.widget.LinearLayout.LayoutParams llp = l.c(use, use);
        llp.gravity = 17;
        llp.bottomMargin = UZCoreUtil.dipToPix(5);
        this.c.setLayoutParams(llp);
        this.c.setMinimumHeight(10);
        this.d.addView(this.c);
        this.a = new TextView(context);
        this.a.setTextColor(-1);
        this.a.setTextSize(17.0F);
        this.a.setVisibility(8);
        this.a.setGravity(17);
        this.a.setSingleLine(true);
        this.a.setEllipsize(TruncateAt.END);
        llp = l.c(l.d, l.e);
        llp.topMargin = UZCoreUtil.dipToPix(5);
        llp.weight = 1.0F;
        this.a.setLayoutParams(llp);
        this.d.addView(this.a);
        this.b = new TextView(context);
        llp = l.c(l.d, l.e);
        llp.topMargin = UZCoreUtil.dipToPix(5);
        llp.weight = 1.0F;
        this.b.setLayoutParams(llp);
        this.b.setTextColor(-1);
        this.b.setTextSize(14.0F);
        this.b.setGravity(17);
        this.b.setSingleLine(true);
        this.b.setEllipsize(TruncateAt.END);
        this.d.addView(this.b);
    }

    public void a() {
        this.c.setVisibility(0);
    }

    public void a(String msg) {
        this.b.setText(msg);
    }

    public void b(String title) {
        if (title != null) {
            this.a.setText(title);
            if (!this.a.isShown()) {
                this.a.setVisibility(0);
            }
        }

    }

    public void a(boolean modal) {
        this.setClickable(modal);

    }

    public void b(boolean mask) {
        if (mask) {
            this.setBackgroundColor(-1879048192);
        } else {
            this.setBackgroundColor(l.c);
        }

    }

    public void b() {
        this.bringToFront();
        this.setVisibility(0);
        this.startAnimation(this.e);
    }

    public void c() {
        if (this.getParent() != null) {
            this.g = true;
            this.startAnimation(this.f);
        }
    }

    public void a(int animType) {
        if (2 == animType) {
            this.e = new AlphaAnimation(0.0F, 1.0F);
            this.f = new AlphaAnimation(1.0F, 0.0F);
        } else {
            this.e = new ScaleAnimation(1.5F, 1.0F, 1.5F, 1.0F, 1, 0.5F, 1, 0.5F);
            this.f = new ScaleAnimation(1.0F, 0.4F, 1.0F, 0.4F, 1, 0.5F, 1, 0.5F);
            AlphaAnimation inap = new AlphaAnimation(0.5F, 1.0F);
            AlphaAnimation outap = new AlphaAnimation(1.0F, 0.5F);
            AnimationSet inSet = new AnimationSet(true);
            AnimationSet outSet = new AnimationSet(true);
            inSet.addAnimation(inap);
            inSet.addAnimation(this.e);
            outSet.addAnimation(outap);
            outSet.addAnimation(this.f);
            this.e = inSet;
            this.f = outSet;
        }

        this.e.setDuration(250L);
        this.f.setDuration(250L);
    }

    protected void onAnimationEnd() {
        super.onAnimationEnd();
        if (this.g) {
            this.setVisibility(4);
            if (!this.post(this.h)) {
                this.d();
            }
        }

    }

    private void d() {
        ViewGroup parent = (ViewGroup) this.getParent();
        if (parent != null) {
            parent.removeView(this);
        }

        this.g = false;
    }
}
