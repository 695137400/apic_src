package com.uzmap.pkg.uzcore.external;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.*;
import android.view.animation.Animation.AnimationListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import com.uzmap.pkg.uzcore.UZCoreUtil;

import java.util.Hashtable;

public class k {
    static int a = UZCoreUtil.dipToPix(80);
    static int b = UZCoreUtil.dipToPix(18);
    static Hashtable<Integer, k> c = new Hashtable();
    private Animation d;
    private Animation e;
    private int f;
    private int g;
    private boolean h;
    private final View i;
    private Toast j;
    private final Integer k;
    private final Runnable l = new Runnable() {
        public void run() {
            k.this.c();
        }
    };

    private k(Toast toast, View nextView, int location, int duration, Integer tag) {
        this.f = duration;
        this.i = nextView;
        this.g = location;
        this.k = tag;
        this.j = toast;
        this.a(nextView.getContext());
    }

    public static k a(com.uzmap.pkg.uzcore.m window, String msg, int location, int duration) {
        Integer tag = window.hashCode();
        k toast = c.get(tag);
        if (toast != null) {
            toast.a(duration);
            toast.b(location);
            toast.a(msg);
            return toast;
        } else {
            Context context = window.getContext();
            LayoutParams parm = com.uzmap.pkg.uzcore.external.l.b(com.uzmap.pkg.uzcore.external.l.e, com.uzmap.pkg.uzcore.external.l.e);
            parm.addRule(14, -1);
            if (location == 2) {
                parm.addRule(10, -1);
                parm.topMargin = a;
            } else if (location == 1) {
                parm.addRule(13, -1);
            } else {
                parm.addRule(12, -1);
                parm.bottomMargin = a;
            }

            parm.leftMargin = b;
            parm.rightMargin = b;
            Toast t = Toast.makeText(context, msg, 0);
            View view = t.getView();
            if (view != null) {
                view.setLayoutParams(parm);
                window.addView(view);
            }

            toast = new k(t, view, location, duration, tag);
            c.put(tag, toast);
            return toast;
        }
    }

    public void a(String text) {
        if (this.j != null) {
            this.j.setText(text);
        }

    }

    public void a(int duration) {
        this.f = duration;
    }

    public void b(int location) {
        if (this.g != location) {
            this.g = location;
            LayoutParams parm = com.uzmap.pkg.uzcore.external.l.b(com.uzmap.pkg.uzcore.external.l.e, com.uzmap.pkg.uzcore.external.l.e);
            parm.addRule(14, -1);
            if (location == 2) {
                parm.addRule(10, -1);
                parm.topMargin = a;
            } else if (location == 1) {
                parm.addRule(13, -1);
            } else {
                parm.addRule(12, -1);
                parm.bottomMargin = a;
            }

            parm.leftMargin = b;
            parm.rightMargin = b;
            this.i.setLayoutParams(parm);
        }
    }

    public void a() {
        if (this.f <= 0) {
            this.f = 2000;
        }

        this.d.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                com.uzmap.pkg.uzcore.c.b(new Runnable() {
                    public void run() {
                        k.this.b();
                    }
                });
            }
        });
        com.uzmap.pkg.uzcore.c.a(this.l);
        com.uzmap.pkg.uzcore.c.a(this.l, this.f);
        this.i.startAnimation(this.d);
    }

    private void b() {
        if (this.h) {
            this.i.setVisibility(4);
            this.d();
        }

    }

    private void c() {
        this.h = true;
        this.e.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                com.uzmap.pkg.uzcore.c.b(new Runnable() {
                    public void run() {
                        k.this.b();
                    }
                });
            }
        });
        this.i.startAnimation(this.e);
    }

    private void d() {
        ViewGroup parent = (ViewGroup) this.i.getParent();
        if (parent != null) {
            parent.removeView(this.i);
        }

        if (this.j != null) {
            this.j.cancel();
            this.j = null;
        }

        c.remove(this.k);
    }

    private void a(Context context) {
        this.e = new AlphaAnimation(1.0F, 0.0F);
        this.e.setDuration(150L);
        AlphaAnimation alpha = new AlphaAnimation(0.3F, 1.0F);
        TranslateAnimation ta = new TranslateAnimation(0.0F, 0.0F, 120.0F, 0.0F);
        AnimationSet inSet = new AnimationSet(true);
        inSet.addAnimation(alpha);
        inSet.addAnimation(ta);
        Interpolator i = AnimationUtils.loadInterpolator(context, 17432584);
        inSet.setInterpolator(i);
        this.d = inSet;
        this.d.setDuration(300L);
    }
}
