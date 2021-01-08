//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzcore.uzmodule.aa;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.UZWebView;
import org.json.JSONObject;

public class e extends s {
    public int d;
    public int e;
    public String f;
    public int g;
    public boolean h;
    public float i;
    public int j;
    public int k;
    public int l;
    public float m;
    public float n;
    public float o;
    public float p;
    public float q;
    public float r;
    public float s;
    public boolean t;
    public boolean u;
    public boolean v;
    public boolean w;

    public e(String json, UZWebView webView) {
        super(json, webView, false);
        this.e();
    }

    private void e() {
        if (!this.empty()) {
            this.d = this.optInt("delay");
            this.e = this.optInt("duration", 300);
            this.f = this.optString("curve");
            this.g = this.optInt("repeatCount");
            this.h = this.optBoolean("autoreverse");
            if (!this.isNull("alpha")) {
                this.t = true;
                this.i = (float)this.optDouble("alpha");
            }

            JSONObject transo = this.optJSONObject("translation");
            if (transo != null) {
                this.u = true;
                String x = transo.optString("x");
                String y = transo.optString("y");
                String z = transo.optString("z");
                this.j = UZCoreUtil.parseCssPixel(x);
                this.k = UZCoreUtil.parseCssPixel(y);
                this.l = UZCoreUtil.parseCssPixel(z);
            }

            JSONObject scaleo = this.optJSONObject("scale");
            if (scaleo != null) {
                this.v = true;
                this.m = (float)transo.optInt("x");
                this.n = (float)transo.optInt("y");
                this.o = (float)transo.optInt("z");
            }

            JSONObject rotationo = this.optJSONObject("rotation");
            if (rotationo != null) {
                this.w = true;
                this.p = (float)transo.optInt("x");
                this.q = (float)transo.optInt("y");
                this.r = (float)transo.optInt("z");
                this.s = (float)transo.optInt("degree");
            }

        }
    }

    public Animation b() {
        AnimationSet animset = new AnimationSet(true);
        Interpolator interpolator = new LinearInterpolator();
        if ("ease_in_out".equals(this.f)) {
            interpolator = new AccelerateDecelerateInterpolator();
        } else if ("ease_in".equals(this.f)) {
            interpolator = new AccelerateInterpolator();
        } else if ("ease_out".equals(this.f)) {
            interpolator = new DecelerateInterpolator();
        }

        if (this.u) {
            TranslateAnimation tranAnim = new TranslateAnimation(0.0F, (float)this.j, 0.0F, (float)this.k);
            tranAnim.setRepeatCount(this.g);
            animset.addAnimation(tranAnim);
        }

        animset.setInterpolator((Interpolator)interpolator);
        if (this.d > 0) {
            animset.setStartOffset((long)this.d);
        }

        animset.setDuration((long)this.e);
        if (!this.h) {
            animset.setFillEnabled(true);
            animset.setFillAfter(true);
        }

        animset.setStartTime(-1L);
        return animset;
    }

    public boolean c() {
        return !this.h;
    }

    public boolean d() {
        return this.u;
    }
}
