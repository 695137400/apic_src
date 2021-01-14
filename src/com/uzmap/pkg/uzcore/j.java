package com.uzmap.pkg.uzcore;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.uzmap.pkg.uzcore.aa.AssetsFileUtil;
import com.uzmap.pkg.uzcore.uzmodule.ApiConfig;
import org.json.JSONObject;

public class j {
    private final f a;
    private final k b;
    private ApiConfig c;
    private i d;
    private i e;

    public j(f engine) {
        this.a = engine;
        this.b = new k();
    }

    protected void a(ApiConfig rootWidgetInfo) {
        AssetsFileUtil.a(rootWidgetInfo);
        this.c = rootWidgetInfo;
        this.d = new i(this.a.c(), rootWidgetInfo);
        this.d.b(true);
        this.d.b(2);
        LayoutParams parm = com.uzmap.pkg.uzcore.external.l.d(com.uzmap.pkg.uzcore.external.l.d, com.uzmap.pkg.uzcore.external.l.d);
        this.d.setLayoutParams(parm);
        this.a((View) this.d);
        this.d.a();
        this.b(this.d);
        this.e = this.d;
    }

    protected void b(ApiConfig wgtInfo) {
        if (!this.c(wgtInfo)) {
            AssetsFileUtil.a(wgtInfo);
            i newWidget = new i(this.a.c(), wgtInfo);
            newWidget.a();
            LayoutParams parm = com.uzmap.pkg.uzcore.external.l.d(com.uzmap.pkg.uzcore.external.l.d, com.uzmap.pkg.uzcore.external.l.d);
            newWidget.setLayoutParams(parm);
            newWidget.setVisibility(4);
            newWidget.b(false);
            newWidget.b(1);
            this.a((View) newWidget);
            this.b(newWidget);
            newWidget.d();
        }
    }

    protected void a(com.uzmap.pkg.uzcore.uzmodule.aa.r args) {
        i target = null;
        String appId = null;
        if (args != null) {
            appId = args.d;
        }

        if (appId != null) {
            target = this.a(appId);
        } else {
            target = this.e;
        }

        if (target != null) {
            if (target.o()) {
                boolean silent = args != null && args.g;
                this.a.b(silent);
            } else {
                this.a(target, args);
            }

        }
    }

    private boolean c(ApiConfig info) {
        String key = info.r;
        return this.b.a(key) != null;
    }

    private void b(i widget) {
        this.b.a(widget);
    }

    protected ApiConfig a() {
        return this.c;
    }

    protected i a(String wgtNmae) {
        return this.b.a(wgtNmae);
    }

    protected void b() {
        this.e.d();
    }

    protected void a(final i showWidget) {
        int animType = showWidget.l();
        int animSubType = showWidget.m();
        long duration = showWidget.k();
        v animPair = w.a(animType, animSubType, duration);
        final i closeWidget = this.e;
        showWidget.a(new y() {
            public void a() {
                j.this.e = showWidget;
                closeWidget.setAnimation(null);
                j.this.b((View) closeWidget);
            }
        });
        if (animPair.b()) {
            closeWidget.bringToFront();
        }

        showWidget.startAnimation(animPair.a);
        showWidget.setVisibility(0);
        closeWidget.startAnimation(animPair.b);
        closeWidget.setVisibility(8);
    }

    private void a(final i closeWidget, final com.uzmap.pkg.uzcore.uzmodule.aa.r moduleArgs) {
        int oldAnim = closeWidget.l();
        int oldSubAnim = closeWidget.m();
        int newAnim = -1;
        int newSubAnim = -1;
        long animDuration = closeWidget.k();
        if (moduleArgs != null) {
            newAnim = moduleArgs.a;
            newSubAnim = moduleArgs.b;
            animDuration = moduleArgs.c;
        }

        if (newAnim == -1) {
            newAnim = oldAnim;
        }

        if (newSubAnim == -1) {
            newSubAnim = w.a(oldSubAnim, newSubAnim);
        }

        v animPair = w.a(newAnim, newSubAnim, animDuration);
        final i showWidget = this.b.b(closeWidget);
        if (showWidget.getParent() == null) {
            showWidget.a(new y() {
                public void a() {
                    j.this.e = showWidget;
                    j.this.e.clearAnimation();
                    j.this.b.c(closeWidget);
                    j.this.b((View) closeWidget);
                    com.uzmap.pkg.uzcore.uzmodule.aa.r callback = closeWidget.j().U;
                    if (callback != null) {
                        JSONObject o = new JSONObject();
                        if (moduleArgs != null) {
                            o = moduleArgs.c();
                        }

                        callback.success(o, true);
                    }

                    closeWidget.p();
                    if (j.this.e.o()) {
                        j.this.a.q();
                    }

                }
            });
            this.a((View) showWidget);
            if (animPair.b()) {
                closeWidget.bringToFront();
            }

            showWidget.startAnimation(animPair.a);
            showWidget.setVisibility(0);
            closeWidget.startAnimation(animPair.b);
            closeWidget.setVisibility(8);
        }
    }

    protected boolean c() {
        if (!this.e.e()) {
            this.a((com.uzmap.pkg.uzcore.uzmodule.aa.r) null);
            return true;
        } else {
            return false;
        }
    }

    protected void d() {
        this.e.g();
    }

    protected void e() {
        this.e.h();
    }

    protected void a(String callingPackage, Intent intent) {
        this.e.a(callingPackage, intent);
    }

    public void a(boolean connected, String type) {
        this.e.a(connected, type);
    }

    protected final void a(Intent intent) {
        this.e.a(intent);
    }

    protected final void a(boolean longPress) {
        this.e.a(longPress);
    }

    public void a(boolean success, com.uzmap.pkg.uzkit.a.d lastPackage) {
        this.e.a(success, lastPackage);
    }

    public void a(com.uzmap.pkg.uzcore.uzmodule.aa.d event) {
        this.b.a(event);
    }

    public final void a(String winName, String frameName, String script) {
        com.uzmap.pkg.uzcore.uzmodule.aa.o moduleArgs = new com.uzmap.pkg.uzcore.uzmodule.aa.o(script, null);
        moduleArgs.a = winName;
        moduleArgs.b = frameName;
        moduleArgs.c = script;
        this.e.a(moduleArgs);
    }

    public final void a(com.uzmap.pkg.uzcore.uzmodule.aa.s args) {
        this.e.a(args);
    }

    protected void a(int keyCode) {
        this.e.c(keyCode);
    }

    protected boolean f() {
        return this.e.i();
    }

    protected void g() {
        this.b.a();
    }

    protected Bitmap h() {
        return this.e.q();
    }

    private void a(View child) {
        FrameLayout rootlayout = this.a.a();
        if (rootlayout != null) {
            if (child.getLayoutParams() == null) {
                child.setLayoutParams(com.uzmap.pkg.uzcore.external.l.d(com.uzmap.pkg.uzcore.external.l.d, com.uzmap.pkg.uzcore.external.l.d));
            }

            rootlayout.addView(child);
        }
    }

    private void b(View child) {
        FrameLayout rootlayout = this.a.a();
        if (rootlayout != null) {
            rootlayout.removeView(child);
        }
    }
}
