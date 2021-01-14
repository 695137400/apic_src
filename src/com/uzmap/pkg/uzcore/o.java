package com.uzmap.pkg.uzcore;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;
import org.json.JSONObject;

public class o extends com.uzmap.pkg.uzcore.external.bb.f implements com.uzmap.pkg.uzcore.external.bb.f.d1, x {
    private Context b;
    private i c;
    private x d;
    private x e;
    private String f;
    private int g;
    private int h;
    private boolean i;
    private y j;
    private int k;
    private int l;
    private long m;
    private boolean n;
    private UZModuleContext o;
    private String p;
    private final Runnable q = new Runnable() {
        public void run() {
            o.this.q();
        }
    };

    public o(Context context, i inWgt, UZModuleContext jsCallback) {
        super(context, null);
        this.b = context;
        this.c = inWgt;
        this.o = jsCallback;
        this.a(this.o);
    }

    public void a(UZModuleContext args) {
        com.uzmap.pkg.uzcore.uzmodule.aa.p sargs = (com.uzmap.pkg.uzcore.uzmodule.aa.p) args;
        this.b("slidLayout");
        int[] shadowColor = new int[]{0, 285212672, 855638016};
        GradientDrawable shadow = new GradientDrawable(Orientation.LEFT_RIGHT, shadowColor);
        this.a(shadow);
        this.l(0);
        m fixedPane = new m(this.b, this.c);
        fixedPane.b(1);
        c1 parm = new c1(com.uzmap.pkg.uzcore.external.l.d, com.uzmap.pkg.uzcore.external.l.d);
        parm.a = 1.0F;
        fixedPane.setLayoutParams(parm);
        com.uzmap.pkg.uzcore.uzmodule.aa.s fixedPaneArgs = sargs.g;
        if (fixedPaneArgs != null && fixedPaneArgs.h()) {
            com.uzmap.pkg.uzcore.external.l.a(fixedPane, fixedPaneArgs.a(this.c.j()));
        }

        this.addView(fixedPane);
        fixedPane.a(sargs.g);
        this.d = fixedPane;
        m slidPane = new m(this.b, this.c);
        slidPane.b(1);
        parm = new c1(com.uzmap.pkg.uzcore.external.l.d, com.uzmap.pkg.uzcore.external.l.d);
        slidPane.setLayoutParams(parm);
        com.uzmap.pkg.uzcore.uzmodule.aa.s slidPaneArgs = sargs.g;
        if (slidPaneArgs != null && slidPaneArgs.h()) {
            com.uzmap.pkg.uzcore.external.l.a(slidPane, slidPaneArgs.a(this.c.j()));
        }

        this.addView(slidPane);
        slidPane.a(sargs.h);
        this.e = slidPane;
        if (15 == com.uzmap.pkg.uzcore.external.l.a) {
            this.setLayerType(1, null);
        }

    }

    public boolean j(int type) {
        switch (type) {
            case 0:
            case 1:
            default:
                return super.l();
        }
    }

    private void a(JSONObject json) {
        if (this.o != null && json != null) {
            this.o.success(json, false);
        }

    }

    public x a(String name) {
        if (TextUtils.isEmpty(name)) {
            return null;
        } else {
            String fixedName = this.d.m();
            if (name.equals(fixedName)) {
                return this.d;
            } else {
                String slidName = this.e.m();
                return name.equals(slidName) ? this.e : null;
            }
        }
    }

    public boolean a(int type) {
        return this.g == type;
    }

    public void b(int type) {
        this.g = type;
    }

    protected void onVisibilityChanged(View v, int visibility) {
        super.onVisibilityChanged(v, visibility);
        if (v == this && visibility != 0) {
            UZCoreUtil.hideSoftKeyboard(this.b, v);
        }

    }

    protected void onAnimationStart() {
        super.onAnimationStart();
        this.d(true);
    }

    protected void onAnimationEnd() {
        super.onAnimationEnd();
        this.d(false);
        if (!this.post(this.q)) {
            this.q();
        }

    }

    private void q() {
        if (this.j != null) {
            this.j.a();
            this.a((y) null);
        }

    }

    private void d(boolean flag) {
        this.i = flag;
    }

    public void a(y listener) {
        this.j = listener;
    }

    public boolean c(int flag) {
        return (this.h & flag) != 0;
    }

    public void d(int flag) {
        this.h |= flag;
    }

    public void b() {
        this.h &= 0;
    }

    public String m() {
        return this.f;
    }

    public void b(String name) {
        this.f = name;
    }

    public void c() {
        this.e.c();
        this.d.c();
    }

    public void d() {
        this.e.d();
        this.d.d();
    }

    public void a(String callingPackage, Intent intent) {
        this.e.a(callingPackage, intent);
        this.d.a(callingPackage, intent);
    }

    public void a(boolean connected, String type) {
        this.e.a(connected, type);
        this.d.a(connected, type);
    }

    public void a(boolean success, com.uzmap.pkg.uzkit.a.d lastPackage) {
        this.e.a(success, lastPackage);
        this.d.a(success, lastPackage);
    }

    public void a(Intent intent) {
        this.e.a(intent);
        this.d.a(intent);
    }

    public void b(boolean longPress) {
        this.e.b(longPress);
        this.d.b(longPress);
    }

    public void e() {
        this.e.e();
        this.d.e();
    }

    public void f() {
        this.e.f();
        this.d.f();
    }

    public void a(com.uzmap.pkg.uzcore.uzmodule.aa.d event) {
        this.e.a(event);
        this.d.a(event);
    }

    public void a(WebView view, com.uzmap.pkg.uzcore.uzmodule.aa.o moduleArgs) {
    }

    public void c(boolean flag) {
    }

    public boolean k() {
        return false;
    }

    public void a(String... content) {
        if (content != null && 2 <= content.length) {
            this.e.a(content[0]);
            this.d.a(content[1]);
        }
    }

    public void i() {
    }

    public long r() {
        return this.m;
    }

    public void a(long animDuration) {
        this.m = animDuration;
    }

    public int s() {
        return this.k;
    }

    public void g(int animType) {
        this.k = animType;
    }

    public int t() {
        return this.l;
    }

    public void h(int subAnimType) {
        this.l = subAnimType;
    }

    public void g() {
        this.e.g();
        this.d.g();
    }

    public void e(boolean enable) {
        this.e.e(enable);
        this.d.e(enable);
    }

    public void f(boolean enable) {
        this.e.f(enable);
        this.d.f(enable);
    }

    public void f(int visibility) {
        super.setVisibility(visibility);
    }

    public int u() {
        return 0;
    }

    public void i(int delay) {
    }

    public boolean h() {
        return false;
    }

    public void e(int keyCode) {
        if (keyCode == 4 && this.p()) {
            this.o();
        } else {
            this.e.e(keyCode);
        }
    }

    public boolean n() {
        return true;
    }

    public String j() {
        return this.e.j();
    }

    public void d(String name) {
        this.p = name;
        if (this.d != null) {
            this.d.d(name);
        }

        if (this.e != null) {
            this.e.d(name);
        }

    }

    public void a(com.uzmap.pkg.uzcore.uzmodule.d pageParam) {
    }

    public void w() {
        this.e.w();
        this.d.w();
        this.removeAllViews();
        ViewGroup parent = (ViewGroup) this.getParent();
        if (parent != null) {
            parent.removeView(this);
        }

        this.c = null;
        this.f = null;
        this.b = null;
        this.o = null;
    }

    public void a(View panel, float slideOffset) {
        if (!this.n) {
            JSONObject json = new JSONObject();

            try {
                json.put("type", "left");
                json.put("event", "slide");
            } catch (Exception var5) {
            }

            this.a(json);
            this.n = true;
        }

    }

    public void a(View panel) {
        JSONObject json = new JSONObject();

        try {
            json.put("type", "left");
            json.put("event", "open");
        } catch (Exception var4) {
        }

        this.a(json);
    }

    public void b(View panel) {
        this.n = false;
        JSONObject json = new JSONObject();

        try {
            json.put("type", "left");
            json.put("event", "close");
        } catch (Exception var4) {
        }

        this.a(json);
    }

    public String toString() {
        return "slidwin[" + this.f + "]" + "@" + Integer.toHexString(this.hashCode());
    }
}
