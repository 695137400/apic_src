package com.uzmap.pkg.uzcore;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.webkit.URLUtil;
import com.uzmap.pkg.openapi.Html5EventListener;
import com.uzmap.pkg.openapi.WebViewProvider;
import com.uzmap.pkg.uzcore.uzmodule.ApiConfig;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;
import com.uzmap.pkg.uzsocket.api.UPnsListener;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class UZAppActivity extends ActivityGroup {
    private boolean a = false;
    private boolean b = false;
    private boolean c = false;
    private boolean d = false;
    private com.uzmap.pkg.uzcore.external.bb.e e;
    private com.uzmap.pkg.uzcore.external.bb.c f;
    private f g;
    private com.uzmap.pkg.uzcore.external.bb.a h;
    private af i;
    private List<Html5EventListener> j = new ArrayList();
    private boolean k = false;
    private boolean l;
    private final Handler m = new Handler();
    private Thread n;
    private final Runnable p = new Runnable() {
        public void run() {
            UZAppActivity.this.removeLaunchView(false, null);
        }
    };
    private final ApplicationProcess.bcl o = new ApplicationProcess.bcl() {
        public void a(boolean success, ApiConfig entity, String msg) {
            if (this.a() != null) {
                entity = entity.e(this.a());
            }

            UZAppActivity.this.initializeEngine(success, entity, msg);
        }
    };
    private Runnable q = new Runnable() {
        public void run() {
            ApiConfig wgtInfo = null;
            if (UZAppActivity.this.g != null) {
                wgtInfo = UZAppActivity.this.g.g();
            }

            ApplicationProcess.initialize().b(wgtInfo);
            com.uzmap.pkg.uzcore.c.a(UZAppActivity.this.q, 180000L);
        }
    };

    protected abstract boolean isFromNativeSDK();

    protected abstract void onProgressChanged(WebViewProvider var1, int var2);

    protected abstract void onPageStarted(WebViewProvider var1, String var2, Bitmap var3);

    protected abstract void onPageFinished(WebViewProvider var1, String var2);

    protected abstract boolean shouldOverrideUrlLoading(WebViewProvider var1, String var2);

    protected abstract void onReceivedTitle(WebViewProvider var1, String var2);

    protected abstract boolean shouldForbiddenAccess(String var1, String var2, String var3);

    protected abstract boolean onHtml5AccessRequest(WebViewProvider var1, UZModuleContext var2);

    // $FF: synthetic method
    static boolean access$9(UZAppActivity var0) {
        return var0.k;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(null);
        if (!com.uzmap.pkg.uzcore.external.l.c(this)) {
            this.l = true;
            this.a = true;
            this.finish();
        } else {
            this.n = Thread.currentThread();
            this.initializeNativeUI();
            ApplicationProcess delegate = ApplicationProcess.initialize();
            delegate.a(this);
            delegate.a(new b(null));
            delegate.a(new c(null));
        }
    }

    private final void initializeNativeUI() {
        com.uzmap.pkg.uzcore.external.l.a(this);
        this.e = new com.uzmap.pkg.uzcore.external.bb.e(this, null);
        this.e.a(new UZAppActivity.d());
        this.setContentView(this.e, com.uzmap.pkg.uzcore.external.l.a(com.uzmap.pkg.uzcore.external.l.d, com.uzmap.pkg.uzcore.external.l.d));
        ApiConfig entity = ApplicationProcess.initialize().f();
        if (entity != null && entity.R) {
            com.uzmap.pkg.uzcore.external.l.b(this);
        }

        if (!this.isFromNativeSDK()) {
            if (com.uzmap.pkg.uzcore.d.a().s) {
                com.uzmap.pkg.uzcore.external.bb.d sheet = new com.uzmap.pkg.uzcore.external.bb.d(this, null);
                sheet.show();
                this.f = sheet.a();
            } else {
                this.f = new com.uzmap.pkg.uzcore.external.bb.c(this, null);
                this.addContentView(this.f, com.uzmap.pkg.uzcore.external.l.a(com.uzmap.pkg.uzcore.external.l.d, com.uzmap.pkg.uzcore.external.l.d));
            }

            com.uzmap.pkg.uzcore.c.a(this.p, 3000L);
        }
    }

    private final void initializeEngine(boolean success, ApiConfig entity, String msg) {
        if (!success) {
            String title = null;
            String error_msg = com.uzmap.pkg.uzcore.d.i;
            if (msg != null) {
                title = "提示";
                error_msg = msg;
            }

            this.forceFinishAppWidthAlert(title, error_msg, null);
        } else {
            if (!entity.L) {
                com.uzmap.pkg.uzcore.c.a(this.p);
            }

            if (entity.R) {
                com.uzmap.pkg.uzcore.external.l.b(this);
            }

            com.uzmap.pkg.uzcore.external.l.a(this.e, entity.b());
            this.g = com.uzmap.pkg.uzcore.f.a(this);
            this.g.a(this.e);
            this.i = new af(this);
            this.g.a(this.i);
            this.g.b(UZCoreUtil.parseAppParam(this.getIntent()));
            this.g.a(entity);
            this.g.h();
            ApplicationProcess.initialize().a(entity);
        }
    }

    private final void unBlockScreen() {
        com.uzmap.pkg.uzcore.c.a(new Runnable() {
            public void run() {
                if (UZAppActivity.this.h != null) {
                    UZAppActivity.this.h.b();
                }

            }
        }, 0L);
    }

    private void removeLaunchView(boolean force, v animPair) {
        if (this.f != null) {
            if (force) {
                this.removeLaunchView(animPair);
            } else {
                if (this.f.a()) {
                    this.removeLaunchView(animPair);
                } else {
                    this.f.a(true);
                }

            }
        }
    }

    private void removeLaunchView(v animPair) {
        if (this.f != null) {
            this.f.a(animPair);
            this.f = null;
            if (this.d) {
                this.blockScreen("正在安全认证");
            }

        }
    }

    private final void blockScreen(String msg) {
        if (this.h == null) {
            this.h = new com.uzmap.pkg.uzcore.external.bb.a(this, this);
        }

        this.h.a(msg);
        com.uzmap.pkg.uzcore.c.a(new Runnable() {
            public void run() {
                UZAppActivity.this.h.a();
            }
        }, 0L);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        this.b = true;
        switch (keyCode) {
            case 4:
            case 82:
                return true;
            default:
                return super.onKeyDown(keyCode, event);
        }
    }

    private final boolean disPatchKeyBack(int keyCode) {
        if (this.g == null) {
            return true;
        } else if (this.i.b()) {
            return true;
        } else if (com.uzmap.pkg.uzcore.f.f()) {
            return true;
        } else if (this.g.o()) {
            this.g.a(keyCode);
            return true;
        } else {
            return this.g.l();
        }
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (!this.b) {
            return true;
        } else {
            this.b = false;
            switch (keyCode) {
                case 4:
                    return this.disPatchKeyBack(keyCode);
                case 82:
                    if (this.g != null) {
                        this.g.a(keyCode);
                    }

                    return true;
                default:
                    return super.onKeyUp(keyCode, event);
            }
        }
    }

    protected void onPause() {
        super.onPause();
        this.k = false;
        if (!this.a) {
            if (this.g != null) {
                this.g.m();
            }

            if (!this.isModuleRunning()) {
                com.uzmap.pkg.uzcore.c.a(this.q);
                ApplicationProcess.initialize().r();
            }

            ApplicationProcess.initialize().c(this);
        }
    }

    protected void onDestroy() {
        this.cleanEngine();
        super.onDestroy();
        if (!this.l && !this.isFromNativeSDK()) {
            Process.killProcess(Process.myPid());
        }

    }

    protected void onNewIntent(Intent intent) {
        if (this.g != null && intent != null) {
            this.handlderIntent(intent, -1);
        }
    }

    public void finish() {
        super.finish();
        if (!this.l) {
            ApplicationProcess.initialize().d(this);
        }

    }

    public final boolean runOnUiThread(Runnable action, long delay) {
        if (Thread.currentThread() != this.n) {
            return this.m.post(action);
        } else if (0L == delay) {
            action.run();
            return true;
        } else {
            return this.m.postDelayed(action, delay);
        }
    }

    public final void registerUPnsListener(UPnsListener listener) {
        ApplicationProcess.initialize().a(listener);
    }

    public final void unRegisterUPnsListener(UPnsListener listener) {
        ApplicationProcess.initialize().b(listener);
    }

    private void setModuleRunning(boolean flag) {
        this.c = flag;
    }

    private boolean isModuleRunning() {
        return this.c;
    }

    private final void forceFinishAppWidthAlert(final String title, final String msg, final String btn) {
        Runnable action = new Runnable() {
            public void run() {
                Builder dia = new Builder(UZAppActivity.this);
                dia.setTitle(!TextUtils.isEmpty(title) ? title : com.uzmap.pkg.uzcore.d.g);
                dia.setMessage(!TextUtils.isEmpty(msg) ? msg : com.uzmap.pkg.uzcore.d.h);
                dia.setCancelable(false);
                dia.setPositiveButton(!TextUtils.isEmpty(btn) ? btn : com.uzmap.pkg.uzcore.d.b, new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        UZAppActivity.this.finishAppImmediately();
                    }
                });
                dia.show();
            }
        };
        com.uzmap.pkg.uzcore.c.a(action, 0L);
    }

    protected void onResume() {
        if (this.g != null) {
            this.g.n();
        }

        super.onResume();
        this.k = true;
        if (!this.isModuleRunning()) {
            ApplicationProcess.initialize().s();
            com.uzmap.pkg.uzcore.c.a(this.q, 180000L);
        }

        this.setModuleRunning(false);
        ApplicationProcess.initialize().b(this);
    }

    private final void finishAppImmediately() {
        this.finish();
    }

    private final void cleanEngine() {
        this.a = true;
        if (this.g != null) {
            this.g.i();
        }

        if (this.e != null) {
            this.e.removeAllViews();
        }

        this.j.clear();
        if (!this.isFromNativeSDK()) {
            ApplicationProcess.initialize().q();
            Runtime.getRuntime().gc();
        }
    }

    private void handlderIntent(Intent intent, int eventType) {
        if (intent == null) {
            intent = this.getIntent();
        }

        if (!UZCoreUtil.isEmptyIntent(intent)) {
            this.g.b(UZCoreUtil.parseAppParam(intent));
            String callingPackage = null;
            ComponentName cname = intent.getComponent();
            if (cname != null) {
                callingPackage = cname.getPackageName();
            }

            callingPackage = callingPackage != null ? callingPackage : "";
            this.g.a(callingPackage, intent);
            if (eventType == 18 && intent.hasExtra("api_arguments")) {
                intent.removeExtra("api_arguments");
            }

            if (eventType == 19 && intent.hasExtra("appParam")) {
                intent.removeExtra("appParam");
            }

        }
    }

    private final void openAssign(final String url) {
        Runnable action = new Runnable() {
            public void run() {
                UZAppActivity.this.g.a(url);
            }
        };
        com.uzmap.pkg.uzcore.c.a(action, 0L);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.g != null) {
            this.g.a(requestCode, resultCode, data);
        }

    }

    public final Bitmap screenSnapshot() {
        return this.g.j();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    protected void onSaveInstanceState(Bundle outState) {
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
    }

    protected final void evaluateScript(String winName, String frameName, String script) {
        if (this.g != null) {
            this.g.a(winName, frameName, script);
        }

    }

    protected final void addH5EventListener(Html5EventListener listener) {
        this.j.add(listener);
    }

    protected final void removeH5EventListener(Html5EventListener listener) {
        if (listener == null) {
            this.j.clear();
        } else {
            this.j.remove(listener);
        }

    }

    protected final void sendEventToH5(String eventName, JSONObject extra) {
        if (this.g != null) {
            int event = com.uzmap.pkg.uzcore.e.a(eventName);
            this.g.a(event, extra);
        }

    }

    private final void finishAppWithConfirm() {
        Builder tDialog = new Builder(this);
        tDialog.setTitle(com.uzmap.pkg.uzcore.d.f);
        tDialog.setNegativeButton(com.uzmap.pkg.uzcore.d.c, null);
        tDialog.setMessage(com.uzmap.pkg.uzcore.d.e);
        tDialog.setPositiveButton(com.uzmap.pkg.uzcore.d.b, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                UZAppActivity.this.finishAppImmediately();
            }
        });
        tDialog.show();
    }

    private class af implements com.uzmap.pkg.uzcore.f.af {
        private com.uzmap.pkg.uzcore.external.bb.b b;
        private com.uzmap.pkg.uzcore.external.e c;
        private Activity d;

        public af(Activity activity) {
            this.d = activity;
        }

        public void a() {
            UZAppActivity.this.removeLaunchView(false, null);
            this.d.getWindow().clearFlags(1048576);
        }

        public boolean a(boolean silent) {
            if (!silent && !UZAppActivity.this.isFromNativeSDK()) {
                UZAppActivity.this.finishAppWithConfirm();
            } else {
                UZAppActivity.this.finishAppImmediately();
            }

            return true;
        }

        public final boolean a(com.uzmap.pkg.uzcore.external.bb.b view, int requestedOrientation) {
            if (this.b != null) {
                this.b();
                return false;
            } else {
                LayoutParams rlp = com.uzmap.pkg.uzcore.external.l.a(com.uzmap.pkg.uzcore.external.l.d, com.uzmap.pkg.uzcore.external.l.d);
                this.d.addContentView(view, rlp);
                this.b = view;
                UZAppActivity.this.e.setVisibility(4);
                return true;
            }
        }

        public final boolean b() {
            if (this.b == null) {
                return false;
            } else {
                UZAppActivity.this.e.setVisibility(0);
                if (this.b != null) {
                    ViewGroup parent = (ViewGroup) this.b.getParent();
                    if (parent != null) {
                        parent.removeView(this.b);
                    }

                    this.b.a();
                }

                this.b = null;
                return true;
            }
        }

        public void a(String error) {
            if (this.c != null && this.c.isShowing()) {
                this.c.a(error);
            } else {
                this.c = new com.uzmap.pkg.uzcore.external.e(this.d, null);
                this.c.a(this.d, error);
                this.c.show();
            }

        }

        public boolean a(int orientation) {
            int curOr = UZAppActivity.this.getRequestedOrientation();
            if (curOr == orientation) {
                return false;
            } else {
                this.d.setRequestedOrientation(orientation);
                UZAppActivity.this.e.a();
                return true;
            }
        }

        public boolean b(boolean fullScreen) {
            Window activityWindow = UZAppActivity.this.getWindow();
            android.view.WindowManager.LayoutParams attrs = activityWindow.getAttributes();
            if (fullScreen) {
                attrs.flags |= 1024;
                activityWindow.setAttributes(attrs);
            } else {
                attrs.flags &= -1025;
                activityWindow.setAttributes(attrs);
            }

            UZAppActivity.this.e.c(fullScreen);
            return false;
        }

        public boolean c(boolean keep) {
            Window activityWindow = UZAppActivity.this.getWindow();
            android.view.WindowManager.LayoutParams attrs = activityWindow.getAttributes();
            if (keep) {
                attrs.flags |= 128;
                activityWindow.setAttributes(attrs);
            } else {
                attrs.flags &= -129;
                activityWindow.setAttributes(attrs);
            }

            return true;
        }

        public int d(boolean todip) {
            return UZAppActivity.this.e.a(todip);
        }

        public int e(boolean todip) {
            return UZAppActivity.this.e.b(todip);
        }

        public boolean a(String title, String msg, String btnText) {
            UZAppActivity.this.forceFinishAppWidthAlert(title, msg, btnText);
            return true;
        }

        public boolean a(boolean force, v animPair) {
            UZAppActivity.this.removeLaunchView(force, animPair);
            return true;
        }

        public boolean b(int eventType) {
            UZAppActivity.this.handlderIntent(null, eventType);
            return true;
        }

        public boolean f(boolean secure) {
            com.uzmap.pkg.uzcore.external.l.a(this.d, secure);
            return true;
        }

        public boolean a(Intent intent, int requestCode, boolean needresult) {
            if (!needresult) {
                UZAppActivity.this.startActivity(intent);
            } else {
                UZAppActivity.this.startActivityForResult(intent, requestCode);
            }

            UZAppActivity.this.setModuleRunning(true);
            return true;
        }

        public void a(WebViewProvider provider, int newProgress) {
            if (UZAppActivity.this.isFromNativeSDK()) {
                UZAppActivity.this.onProgressChanged(provider, newProgress);
            }
        }

        public void a(WebViewProvider provider, String url, Bitmap favicon) {
            if (UZAppActivity.this.isFromNativeSDK()) {
                UZAppActivity.this.onPageStarted(provider, url, favicon);
            }
        }

        public void a(WebViewProvider provider, String url) {
            if (UZAppActivity.this.isFromNativeSDK()) {
                UZAppActivity.this.onPageFinished(provider, url);
            }
        }

        public boolean b(WebViewProvider provider, String url) {
            return !UZAppActivity.this.isFromNativeSDK() ? false : UZAppActivity.this.shouldOverrideUrlLoading(provider, url);
        }

        public void c(WebViewProvider provider, String title) {
            if (UZAppActivity.this.isFromNativeSDK()) {
                UZAppActivity.this.onReceivedTitle(provider, title);
            }
        }

        public void a(com.uzmap.pkg.uzcore.a view, int event, Object extra) {
            if (UZAppActivity.this.isFromNativeSDK()) {
                WebViewProvider provider = view.b();
                Iterator var6 = UZAppActivity.this.j.iterator();

                while (var6.hasNext()) {
                    Html5EventListener listener = (Html5EventListener) var6.next();
                    if (listener.matching(event)) {
                        listener.onReceive(provider, extra);
                    }
                }

            }
        }

        public boolean a(WebViewProvider provider, UZModuleContext moduleContext) {
            return !UZAppActivity.this.isFromNativeSDK() ? false : UZAppActivity.this.onHtml5AccessRequest(provider, moduleContext);
        }
    }

    private class b implements com.uzmap.pkg.uzkit.a.f {
        private b() {
        }

        public void a(boolean success, com.uzmap.pkg.uzkit.a.d lastPackage) {
            UZAppActivity.this.g.a(success, lastPackage);
        }

        public void a() {
            UZAppActivity.this.d = true;
        }

        public void a(boolean success, String assignUrl, String msg) {
            UZAppActivity.this.d = false;
            if (!success) {
                UZAppActivity.this.unBlockScreen();
                UZAppActivity.this.forceFinishAppWidthAlert("出错了", msg, "退出");
            } else {
                UZAppActivity.this.unBlockScreen();
                if (!TextUtils.isEmpty(assignUrl)) {
                    UZAppActivity.this.openAssign(assignUrl);
                }
            }

        }

        public boolean a(boolean silent) {
            if (!silent && !UZAppActivity.this.isFromNativeSDK()) {
                UZAppActivity.this.finishAppWithConfirm();
            } else {
                UZAppActivity.this.finishAppImmediately();
            }

            return true;
        }

        // $FF: synthetic method
        b(UZAppActivity.b var2) {
            this();
        }
    }

    private class c implements UZPlatformBridge.a {
        private c() {
        }

        public void a(boolean connected, String type) {
            if (UZAppActivity.this.g != null) {
                UZAppActivity.this.g.a(connected, type);
            }

        }

        public void a(Intent intent) {
            if (UZAppActivity.this.g != null) {
                UZAppActivity.this.g.a(intent);
            }

        }

        public void b(Intent intent) {
            String reason = intent.getStringExtra("reason");
            if (reason != null) {
                boolean longPress = false;
                if (reason.equals("homekey")) {
                    longPress = false;
                } else if (reason.equals("recentapps")) {
                    longPress = true;
                }

                if (UZAppActivity.this.g != null) {
                    UZAppActivity.this.g.a(longPress);
                }
            }

        }

        public void a(double lat, double log, String widgetId) {
            ApplicationProcess.initialize().a(lat, log, widgetId);
        }

        public void a(String title, String msg, String btnText) {
            UZAppActivity.this.forceFinishAppWidthAlert(title, msg, btnText);
        }

        // $FF: synthetic method
        c(UZAppActivity.c var2) {
            this();
        }
    }

    private class d implements com.uzmap.pkg.uzcore.external.bb.e.a {
        private boolean b = false;

        public d() {
        }

        public void a(int w, int h, int oldw, int oldh) {
            if (!this.b) {
                this.b = true;
                if (UZAppActivity.this.isFromNativeSDK()) {
                    String startUrl = UZAppActivity.this.getIntent().getStringExtra("startUrl");
                    if (URLUtil.isValidUrl(startUrl)) {
                        UZAppActivity.this.o.a(startUrl);
                    }
                }

                ApplicationProcess.initialize().a(UZAppActivity.this.o);
            }
        }
    }
}
