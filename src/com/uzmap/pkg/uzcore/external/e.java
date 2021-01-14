package com.uzmap.pkg.uzcore.external;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.*;
import com.uzmap.pkg.uzcore.UZCoreUtil;

public class e extends Dialog {
    private e.a a;
    private boolean b;
    private final int c = -921103;
    private final int d = -1644826;

    public e(Context context, Object o) {
        super(context, 16973835);
        this.requestWindowFeature(1);
        Window window = this.getWindow();
        window.setBackgroundDrawableResource(17170445);
        window.setGravity(55);
        if (l.a >= 14) {
            window.setDimAmount(0.0F);
        }

        window.clearFlags(2);
        this.setCanceledOnTouchOutside(false);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(this.a, l.a(l.d, l.e));
    }

    public void a(Context context, String error) {
        this.a = new e.a(context);
        this.a.a(error);
        l.a(this.a, 2);
    }

    public void a(String error) {
        if (!TextUtils.isEmpty(error)) {
            this.a.a(error);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode == 4 || super.onKeyDown(keyCode, event);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            if (this.b) {
                return true;
            }

            this.a();
        }

        return super.onKeyUp(keyCode, event);
    }

    public void show() {
        super.show();
        if (this.a != null) {
            this.b = true;
            Animation anim = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, -1.0F, 2, 0.0F);
            anim.setAnimationListener(new AnimationListener() {
                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    e.this.b = false;
                }
            });
            anim.setDuration(150L);
            anim.setInterpolator(new DecelerateInterpolator());
            this.a.startAnimation(anim);
        }
    }

    public void a() {
        this.b = true;
        Animation anim = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, 0.0F, 2, -1.0F);
        anim.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                e.super.dismiss();
                e.this.b = false;
            }
        });
        anim.setDuration(150L);
        anim.setInterpolator(new DecelerateInterpolator());
        this.a.startAnimation(anim);
    }

    protected StateListDrawable b() {
        int[] normalShap = new int[]{this.c, this.c, this.c, this.c};
        int[] pressShap = new int[]{this.d, this.d, this.d, this.d};
        GradientDrawable normalState = new GradientDrawable(Orientation.TOP_BOTTOM, normalShap);
        GradientDrawable pressState = new GradientDrawable(Orientation.TOP_BOTTOM, pressShap);
        StateListDrawable selector = new StateListDrawable();
        selector.addState(new int[]{16842919}, pressState);
        selector.addState(new int[0], normalState);
        return selector;
    }

    private class a extends RelativeLayout {
        public TextView a;
        public TextView b;
        public TextView c;
        public ScrollView d;

        public a(Context context) {
            super(context);
            LayoutParams glp = (LayoutParams) l.a(l.d, l.d);
            this.setLayoutParams(glp);
            this.a(context);
        }

        public void a(String msg) {
            String ot = this.c.getText().toString();
            ot = ot + msg;
            ot = ot + "\n";
            this.c.setText(ot);
            this.post(new Runnable() {
                public void run() {
                    a.this.d.fullScroll(130);
                }
            });
        }

        private void a(Context context) {
            LinearLayout lLayout = new LinearLayout(context);
            lLayout.setOrientation(1);
            lLayout.setBackgroundColor(-1);
            android.widget.RelativeLayout.LayoutParams rlp = l.b(l.d, l.e);
            rlp.addRule(10, -1);
            lLayout.setLayoutParams(rlp);
            this.addView(lLayout);
            this.a = new TextView(context);
            this.a.setTextSize(18.0F);
            int p = UZCoreUtil.dipToPix(5);
            this.a.setPadding(p, p, p, p);
            this.a.setText("Javascript Error");
            this.a.setTextColor(-16777216);
            this.a.setBackgroundColor(e.this.c);
            this.a.setGravity(17);
            android.widget.LinearLayout.LayoutParams listlp = l.c(l.d, l.e);
            this.a.setLayoutParams(listlp);
            lLayout.addView(this.a);
            this.d = new ScrollView(context);
            listlp = l.c(l.d, UZCoreUtil.dipToPix(200));
            this.d.setLayoutParams(listlp);
            this.c = new TextView(context);
            this.c.setTextSize(15.0F);
            p = UZCoreUtil.dipToPix(10);
            this.c.setPadding(p, p, p, p);
            this.c.setTextColor(-65536);
            this.d.addView(this.c);
            lLayout.addView(this.d);
            this.b = new TextView(context);
            this.b.setTextSize(18.0F);
            this.b.setText("关闭");
            p = UZCoreUtil.dipToPix(10);
            this.b.setPadding(p, p, p, p);
            this.b.setGravity(17);
            this.b.setTextColor(-16776961);
            listlp = l.c(l.d, l.e);
            this.b.setLayoutParams(listlp);
            this.b.setBackgroundDrawable(e.this.b());
            lLayout.addView(this.b);
            this.b.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    e.this.a();
                }
            });
            ImageView line = new ImageView(context);
            line.setBackgroundColor(-3355444);
            listlp = l.c(l.d, 2);
            line.setLayoutParams(listlp);
            lLayout.addView(line);
        }
    }
}
