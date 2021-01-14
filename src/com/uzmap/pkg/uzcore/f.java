package com.uzmap.pkg.uzcore;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.uzmap.pkg.ui.intent.d;
import com.uzmap.pkg.openapi.WebViewProvider;
import com.uzmap.pkg.uzcore.aa.AssetsUtil;
import com.uzmap.pkg.uzcore.annotation.InstanceMethod;
import com.uzmap.pkg.uzcore.uzmodule.ApiConfig;
import com.uzmap.pkg.uzcore.uzmodule.UZActivityResult;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.Hashtable;

public class f {
    private static boolean l;
    private static final Hashtable<Activity, f> m = new Hashtable();
    protected com.uzmap.pkg.ui.intent.d a;
    private final Activity b;
    private boolean c;
    private com.uzmap.pkg.uzcore.external.bb.j d;
    private FrameLayout e;
    private String f;
    private af g;
    private UZActivityResult h;
    private j i;
    private final ApplicationProcess j;
    private String k;

    private f(Activity activity) {
        this.b = activity;
        this.j = ApplicationProcess.initialize();
        this.a = new d();
        this.a.init(activity);
    }

    public static f a(Activity activity) {
        if (activity == null) {
            return null;
        } else {
            f engine = m.get(activity);
            if (engine == null) {
                engine = new f(activity);
                m.put(activity, engine);
            }

            return engine;
        }
    }

    public static f b(Activity activity) {
        return activity == null ? null : m.get(activity);
    }

    public static void d() {
        l = true;
    }

    public static void e() {
        l = false;
    }

    public static boolean f() {
        return l;
    }

    private static void v() {
        try {
            Class[] nones = new Class[0];
            Class<?> clazz = WebView.class;
            Method g = clazz.getDeclaredMethod(AssetsUtil.b, nones);
            g.setAccessible(true);
            Object factory = g.invoke(null);
            clazz = factory.getClass();
            Class[] booleans = new Class[]{Boolean.TYPE};
            Method m = clazz.getDeclaredMethod(AssetsUtil.a, booleans);
            m.setAccessible(true);
            m.invoke(factory, true);
            m.invoke(factory, false);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    public void a(ApiConfig rootWidgetInfo) {
        if (this.i == null) {
            this.i = new j(this);
            this.i.a(rootWidgetInfo);
        }

    }

    public void a(FrameLayout rootlayout) {
        this.e = rootlayout;
    }

    public FrameLayout a() {
        return this.e;
    }

    public void a(af listener) {
        this.g = listener;
    }

    public boolean b() {
        return this.g().P;
    }

    public Activity c() {
        return this.b;
    }

    public ApiConfig g() {
        return this.i.a();
    }

    public void h() {
        if (this.i != null) {
            this.i.b();
            if (com.uzmap.pkg.uzcore.external.l.SDK_INT >= 19) {
                WebView.setWebContentsDebuggingEnabled(false);
                v();
            }

        }
    }

    public void i() {
        if (this.i != null) {
            e();
            this.i.g();
            m.remove(this.b);
        }
    }

    public Bitmap j() {
        return this.i.h();
    }

    public final boolean k() {
        return this.c;
    }

    public final boolean l() {
        return this.i != null && this.i.c();
    }

    public final void m() {
        if (this.i != null) {
            this.i.d();
            com.uzmap.pkg.uzcore.external.l.a(false);
        }
    }

    public final void n() {
        if (this.i != null) {
            this.i.e();
            com.uzmap.pkg.uzcore.external.l.a(true);
        }
    }

    public final void a(String callingPackage, Intent intent) {
        if (this.i != null) {
            this.i.a(callingPackage, intent);
        }
    }

    public final void a(boolean connected, String type) {
        if (this.i != null) {
            this.i.a(connected, type);
        }
    }

    public final void a(Intent intent) {
        if (this.i != null) {
            this.i.a(intent);
        }
    }

    public final void a(boolean longPress) {
        if (this.i != null) {
            this.i.a(longPress);
        }
    }

    public final void a(boolean success, com.uzmap.pkg.uzkit.a.d lastPackage) {
        if (this.i != null) {
            this.i.a(success, lastPackage);
        }
    }

    public final void a(com.uzmap.pkg.uzcore.a view, com.uzmap.pkg.uzcore.uzmodule.aa.d eventContext) {
        if (this.i != null) {
            this.i.a(eventContext);
            if (this.g != null) {
                this.g.a(view, eventContext.a, eventContext.c());
            }

        }
    }

    final void a(String winName, String frameName, String script) {
        if (this.i != null) {
            this.i.a(winName, frameName, script);
        }
    }

    final void a(int event, JSONObject extra) {
        if (this.i != null) {
            com.uzmap.pkg.uzcore.uzmodule.aa.d e = new com.uzmap.pkg.uzcore.uzmodule.aa.d(null, null);
            e.a = event;
            e.c = extra;
            this.i.a(e);
        }
    }

    public final void a(String assignUrl) {
        if (!this.k()) {
            this.f = assignUrl;
        } else {
            com.uzmap.pkg.uzcore.uzmodule.aa.s args = new com.uzmap.pkg.uzcore.uzmodule.aa.s();
            args.y = "msmAuth";
            args.z = assignUrl;
            args.B = false;
            args.a = -1;
            args.C = false;
            args.J = true;
            args.I = true;
            this.i.a(args);
        }
    }

    public void a(int keyCode) {
        if (this.i != null) {
            this.i.a(keyCode);
        }
    }

    public boolean o() {
        return this.i != null && this.i.f();
    }

    public void b(ApiConfig wgtInfo) {
        if (this.i != null) {
            this.i.b(wgtInfo);
        }
    }

    public void a(com.uzmap.pkg.uzcore.uzmodule.aa.r args) {
        if (this.i != null) {
            this.i.a(args);
        }
    }

    protected void a(i widget) {
        if (this.i != null) {
            this.i.a(widget);
        }
    }

    public final void a(int requestCode, int resultCode, Intent data) {
        if (this.h != null) {
            this.h.onActivityResult(requestCode, resultCode, data);
            this.h = null;
        }

    }

    public final String p() {
        return this.k != null ? this.k : "{}";
    }

    public final void b(String appParam) {
        this.k = appParam;
    }

    public void a(com.uzmap.pkg.uzcore.uzmodule.aa.k args, ApiConfig wInfo) {
        if (this.d == null) {
            int aid = 17104896;
            int appsize = this.b.getResources().getDimensionPixelSize(aid);
            appsize = (int) ((float) appsize * 1.1F);
            this.d = new com.uzmap.pkg.uzcore.external.bb.j(this.b, appsize);
            this.d.a(args, wInfo);
        }

        if (!this.d.isShown()) {
            this.d.a(new com.uzmap.pkg.uzcore.external.bb.j.a() {
                public void a() {
                    f.this.i.a();
                }
            });
            this.d.c();
        }
    }

    public void q() {
        if (this.d != null) {
            this.d.b();
        }

    }

    @InstanceMethod
    public final String r() {
        return this.j.c();
    }

    @InstanceMethod
    public final String s() {
        return this.j.d();
    }

    @InstanceMethod
    public final boolean a(WebView view, String url) {
        return this.a.loadUrl(url);
    }

    @InstanceMethod
    public final void c(String error) {
        if (this.g != null && this.b()) {
            this.g.a(error);
        }

    }

    @InstanceMethod
    public final void a(com.uzmap.pkg.uzcore.a view, int newProgress) {
        if (this.g != null) {
            this.g.a(view.b(), newProgress);
        }

    }

    @InstanceMethod
    public final void a(com.uzmap.pkg.uzcore.a view, String url, Bitmap favicon) {
        if (this.g != null) {
            this.g.a(view.b(), url, favicon);
        }

    }

    @InstanceMethod
    public final void a(com.uzmap.pkg.uzcore.a view, String url) {
        if (this.g != null) {
            this.g.a(view.b(), url);
        }

    }

    @InstanceMethod
    public final boolean b(com.uzmap.pkg.uzcore.a view, String url) {
        return this.g != null && this.g.b(view.b(), url);
    }

    @InstanceMethod
    public final boolean a(com.uzmap.pkg.uzcore.a view, UZModuleContext moduleContext) {
        return this.g != null && this.g.a(view.b(), moduleContext);
    }

    @InstanceMethod
    public final void c(com.uzmap.pkg.uzcore.a view, String title) {
        if (this.g != null) {
            this.g.c(view.b(), title);
        }

    }

    @InstanceMethod
    public final void t() {
        this.c = true;
        if (this.f != null) {
            this.a(this.f);
            this.f = null;
        }

        if (this.g != null) {
            this.g.a();
        }

    }

    @InstanceMethod
    public final boolean b(boolean silent) {
        return this.g != null && this.g.a(silent);
    }

    @InstanceMethod
    public final boolean a(com.uzmap.pkg.uzcore.external.bb.b view, int requestedOrientation) {
        return this.g != null && this.g.a(view, requestedOrientation);
    }

    @InstanceMethod
    public final void u() {
        if (this.g != null) {
            this.g.b();
        }

    }

    @InstanceMethod
    public final void b(int orientation) {
        if (this.g != null) {
            this.g.a(orientation);
        }

    }

    @InstanceMethod
    public final void c(boolean fullScreen) {
        if (this.g != null) {
            this.g.b(fullScreen);
        }

    }

    @InstanceMethod
    public final void d(boolean keep) {
        if (this.g != null) {
            this.g.c(keep);
        }

    }

    @InstanceMethod
    public boolean b(String title, String msg, String btnText) {
        return this.g != null && this.g.a(title, msg, btnText);
    }

    @InstanceMethod
    public boolean a(boolean force, v animPair) {
        return this.g != null && this.g.a(force, animPair);
    }

    @InstanceMethod
    public boolean c(int eventType) {
        return this.g != null && this.g.b(eventType);
    }

    @InstanceMethod
    public boolean e(boolean secure) {
        return this.g != null && this.g.f(secure);
    }

    @InstanceMethod
    public boolean a(UZActivityResult callback, Intent intent, int requestCode, boolean needresult) {
        if (this.h != null) {
            return false;
        } else {
            if (callback != null) {
                this.h = callback;
            }

            return this.g != null && this.g.a(intent, requestCode, needresult);
        }
    }

    @InstanceMethod
    public final int f(boolean todip) {
        if (this.g != null) {
            return this.g.d(todip);
        } else {
            return this.e != null ? this.e.getHeight() : 0;
        }
    }

    @InstanceMethod
    public final int g(boolean todip) {
        if (this.g != null) {
            return this.g.e(todip);
        } else {
            return this.e != null ? this.e.getWidth() : 0;
        }
    }

    public interface af {
        void c(WebViewProvider var1, String var2);

        void a();

        void a(WebViewProvider var1, int var2);

        void a(WebViewProvider var1, String var2, Bitmap var3);

        void a(WebViewProvider var1, String var2);

        void a(com.uzmap.pkg.uzcore.a var1, int var2, Object var3);

        void a(String var1);

        boolean b(WebViewProvider var1, String var2);

        boolean a(WebViewProvider var1, UZModuleContext var2);

        boolean a(boolean var1);

        boolean a(com.uzmap.pkg.uzcore.external.bb.b var1, int var2);

        boolean b();

        boolean a(int var1);

        boolean b(boolean var1);

        boolean c(boolean var1);

        boolean a(String var1, String var2, String var3);

        boolean a(boolean var1, v var2);

        boolean b(int var1);

        boolean f(boolean var1);

        boolean a(Intent var1, int var2, boolean var3);

        int d(boolean var1);

        int e(boolean var1);
    }
}
