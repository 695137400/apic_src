package com.uzmap.pkg.uzcore.external.bb;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.aa.AssetsFileUtil;
import com.uzmap.pkg.uzcore.external.l;
import com.uzmap.pkg.uzcore.v;

import java.lang.reflect.Method;

public class c extends FrameLayout {
    private boolean a;
    private c.a b;
    private final d c;

    public c(Context context, Dialog o) {
        super(context);
        this.c = (d) o;
        this.a(context);
    }

    private static boolean b(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }

        try {
            Class<?> systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception var7) {
        }

        return hasNavigationBar;
    }

    private void a(Context context) {
        this.setClickable(true);
        LayoutParams backgroundPa = null;
        int splash = com.uzmap.pkg.uzcore.d.a().x;
        if (splash > 0) {
            ImageView background = new ImageView(context);
            if (b(context)) {
                background.setScaleType(ScaleType.FIT_XY);
            } else {
                background.setScaleType(ScaleType.CENTER_CROP);
            }

            background.setImageResource(splash);
            backgroundPa = l.d(l.d, l.d);
            background.setLayoutParams(backgroundPa);
            this.addView(background);
        }

        boolean showCopyRight = com.uzmap.pkg.uzcore.b.a().p();
        if (showCopyRight) {
            this.b = new c.a(context);
            backgroundPa = l.d(l.d, l.d);
            this.b.setLayoutParams(backgroundPa);
            this.addView(this.b);
        }

    }

    protected void onAnimationStart() {
        super.onAnimationStart();
        if (this.c != null) {
            this.c.b();
        }

    }

    protected void onAnimationEnd() {
        super.onAnimationEnd();
        this.setVisibility(4);
        this.post(new Runnable() {
            public void run() {
                c.this.b();
            }
        });
    }

    public void a(v animPair) {
        if (this.b == null || com.uzmap.pkg.uzcore.d.v) {
            Animation outAnim = null;
            Animation inAnim = null;
            if (animPair == null) {
                outAnim = new AlphaAnimation(1.0F, 0.0F);
                outAnim.setDuration(300L);
            } else {
                outAnim = animPair.b;
                inAnim = animPair.a;
            }

            this.a(false);
            this.startAnimation(outAnim);
            if (com.uzmap.pkg.uzapp.b.t()) {
                String title = "温馨提示";
                String msg = "APP已过期，让Ta给你“正式版”的爱吧！";
                if (com.uzmap.pkg.uzapp.b.o()) {
                    title = "强制关闭";
                    msg = "自定义Apploader已超过30天的使用期限\n请重新为本应用编译新的Apploader";
                }

                com.uzmap.pkg.uzcore.f engine = com.uzmap.pkg.uzcore.f.b((Activity) this.getContext());
                if (engine != null) {
                    engine.b(title, msg, "退出");
                }
            }

        }
    }

    private void b(final v animPair) {
        this.removeView(this.b);
        this.b = null;
        this.postDelayed(new Runnable() {
            public void run() {
                c.this.a(animPair);
            }
        }, 1000L);
    }

    private void b() {
        ViewGroup parent = (ViewGroup) this.getParent();
        if (parent != null) {
            parent.removeView(this);
        }

        if (this.c != null) {
            this.c.dismiss();
        }

        if (AssetsFileUtil.a()) {
            com.uzmap.pkg.uzcore.external.j sre = new com.uzmap.pkg.uzcore.external.j();
            com.uzmap.pkg.uzcore.c.a(sre, 500L);
        }

    }

    public void a(boolean flag) {
        if (this.a != flag) {
            this.a = flag;
        }

    }

    public boolean a() {
        return this.a;
    }

    private class a extends RelativeLayout {
        public a(Context context) {
            super(context);
            this.a(context);
        }

        private void a(Context context) {
            this.setBackgroundColor(-1);
            TextView text = new TextView(context);
            text.setId(1111);
            text.setTextColor(-16777216);
            text.setTextSize(36.0F);
            text.setText("此版本为测试版");
            text.setGravity(17);
            android.widget.RelativeLayout.LayoutParams lp = l.b(l.d, l.e);
            lp.addRule(14, -1);
            int h = com.uzmap.pkg.uzcore.d.a().k / 3;
            lp.topMargin = h;
            text.setLayoutParams(lp);
            this.addView(text);
            text = new TextView(context);
            text.setTextColor(-13421773);
            text.setTextSize(14.0F);
            text.setText("仅供开发者调试使用，不得用于商业用途");
            text.setGravity(17);
            lp = l.b(l.d, l.e);
            lp.addRule(3, 1111);
            lp.topMargin = UZCoreUtil.dipToPix(10);
            text.setLayoutParams(lp);
            this.addView(text);
            text = new TextView(context);
            text.setTextColor(-1);
            text.setTextSize(22.0F);
            text.setText("进入应用");
            text.setGravity(17);
            int tb = UZCoreUtil.dipToPix(10);
            int lr = UZCoreUtil.dipToPix(40);
            text.setPadding(lr, tb, lr, tb);
            lp = l.b(l.e, l.e);
            lp.addRule(12, -1);
            lp.addRule(14, -1);
            lp.bottomMargin = UZCoreUtil.dipToPix(80);
            text.setLayoutParams(lp);
            GradientDrawable grade = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{-10701596, -10701596});
            grade.setCornerRadius(8.0F);
            l.a(text, grade);
            this.addView(text);
            text.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    c.this.b((v) null);
                }
            });
        }
    }
}
