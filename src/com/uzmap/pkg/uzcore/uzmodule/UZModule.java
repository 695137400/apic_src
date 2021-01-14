package com.uzmap.pkg.uzcore.uzmodule;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;
import com.uzmap.pkg.uzcore.*;
import com.uzmap.pkg.uzcore.uzmodule.aa.o;
import com.uzmap.pkg.uzcore.uzmodule.aa.r;
import com.uzmap.pkg.uzkit.UZUtility;
import com.uzmap.pkg.uzkit.data.ApiConfigUtil;
import com.uzmap.pkg.uzkit.data.UZWidgetInfo;

public abstract class UZModule implements UZActivityResult {
    private String mModuleName;
    protected com.uzmap.pkg.uzcore.a mWebView;
    protected UZAppActivity mContext;
    protected f mEngine;

    public UZModule(UZWebView webView) {
        this.mWebView = (com.uzmap.pkg.uzcore.a) webView;
        this.mContext = (UZAppActivity) webView.getContext();
        this.mEngine = f.b(this.mContext);
    }

    public final UZAppActivity getContext() {
        return this.mContext;
    }

    public final UZWidgetInfo getWidgetInfo() {
        if (!this.valid()) {
            return null;
        } else {
            ApiConfig entity = this.mWebView.u();
            return entity != null ? entity.i() : null;
        }
    }

    public String getFeatureValue(String featureName, String paramName) {
        if (!this.valid()) {
            return null;
        } else {
            ApiConfig info = this.mWebView.u();
            if (info != null) {
                com.uzmap.pkg.uzkit.data.b feature = info.a(featureName);
                if (feature != null) {
                    return feature.a(paramName);
                }
            }

            return null;
        }
    }

    public String getSecureValue(String key) {
        return ApplicationProcess.initialize().g().a(key);
    }

    public final boolean runOnUiThread(Runnable action) {
        return this.runOnUiThreadDelay(action, 0);
    }

    public final boolean runOnUiThreadDelay(Runnable action, int delayMillis) {
        return !this.valid() ? false : this.mWebView.a(action, delayMillis);
    }

    public final void startActivityForResult(Intent intent, int requestCode) {
        if (this.valid()) {
            this.startActivityForResult(this, intent, requestCode);
        }
    }

    public final void startActivityForResult(UZActivityResult callback, Intent intent, int requestCode) {
        if (this.valid()) {
            this.mEngine.a(callback, intent, requestCode, true);
        }
    }

    public final void startActivity(Intent intent) {
        if (this.valid()) {
            this.mEngine.a(null, intent, 0, false);
        }
    }

    public final void finishApplication() {
        Runnable action = new Runnable() {
            public void run() {
                UZModule.this.mEngine.b(true);
            }
        };
        this.runOnUiThread(action);
    }

    public final void insertViewToCurWindow(View child, LayoutParams parms) {
        this.insertViewToCurWindow(child, parms, null);
    }

    public final void insertViewToCurWindow(View child, LayoutParams parms, String frameName) {
        this.insertViewToCurWindow(child, parms, frameName, true);
    }

    public final void insertViewToCurWindow(View child, LayoutParams parms, String frameName, boolean fixed) {
        this.insertViewToCurWindow(child, parms, frameName, fixed, false);
    }

    public final void insertViewToCurWindow(final View child, final LayoutParams parms, final String frameName, final boolean fixed, final boolean needVerScroll) {
        if (this.valid()) {
            Runnable action = new Runnable() {
                public void run() {
                    UZModule.this.mWebView.a(child, parms, frameName, fixed, needVerScroll);
                }
            };
            this.runOnUiThread(action);
        }
    }

    public final void removeViewFromCurWindow(final View child) {
        Runnable action = new Runnable() {
            public void run() {
                if (UZModule.this.valid()) {
                    UZModule.this.mWebView.a(child);
                }
            }
        };
        this.runOnUiThread(action);
    }

    protected final void openWidgetWidthInfo(final ApiConfig wgtInfo) {
        Runnable action = new Runnable() {
            public void run() {
                if (UZModule.this.valid()) {
                    UZModule.this.mEngine.b(wgtInfo);
                }
            }
        };
        this.runOnUiThread(action);
    }

    public final boolean openWidgetById(String widgetId) {
        if (!this.valid()) {
            return false;
        } else {
            ApiConfig entity = ApiConfigUtil.getConfig(widgetId, false);
            if (entity != null) {
                this.openWidgetWidthInfo(entity);
                return true;
            } else {
                return false;
            }
        }
    }

    public final void closeWidgetWidthInfo(final r args) {
        Runnable action = new Runnable() {
            public void run() {
                if (UZModule.this.valid()) {
                    UZModule.this.mEngine.a(args);
                }
            }
        };
        this.runOnUiThread(action);
    }

    public final void execScript(String winName, String frameName, String script) {
        if (this.valid()) {
            m curWind = this.mWebView.n();
            if (curWind != null) {
                o moduleArgs = new o(null, this.mWebView);
                moduleArgs.a = winName;
                moduleArgs.b = frameName;
                moduleArgs.c = script;
                curWind.a(this.mWebView, moduleArgs);
            }
        }
    }

    public final String makeAbsUrl(String link) {
        return UZUtility.makeAbsUrl(this.mWebView.q(), link);
    }

    public final String makeRealPath(String path) {
        return UZUtility.makeRealPath(path, this.getWidgetInfo());
    }

    public final String getModuleName() {
        return this.mModuleName;
    }

    public final void setModuleName(String name) {
        this.mModuleName = name;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    protected void onClean() {
    }

    public final void destroy() {
        this.mContext = null;
        this.mWebView = null;
    }

    protected boolean valid() {
        return this.mContext != null && this.mWebView != null;
    }
}
