package com.uzmap.pkg.uzcore.external.bb;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.external.l;

import java.lang.reflect.Method;
import java.util.Map;

public abstract class k extends WebView implements OnLongClickListener {
    private static int h = 0;
    private static int i = 0;
    private static int j = -1;
    protected String d;
    protected boolean e = false;
    private boolean a = false;
    private boolean b = false;
    private boolean c = false;
    private boolean f = false;
    private boolean g;

    public k(Context context, Object o) {
        super(context);
        throw new RuntimeException("can not access!");
    }

    public k(Context context, boolean a, boolean al) {
        super(context);
        ++j;
        ++h;
        this.a(context, a && !al);
    }

    protected static void j(boolean plus) {
        if (plus) {
            ++i;
        } else {
            --i;
        }

    }

    protected static int K() {
        return i;
    }

    protected static int L() {
        return j;
    }

    protected abstract boolean a(int var1);

    protected abstract void b(View var1, boolean var2);

    private final void a(Context context, boolean a) {
        this.setOnLongClickListener(this);
        this.setVerticalScrollbarOverlay(true);
        this.setHorizontalScrollbarOverlay(true);
        if (l.SDK_INT >= 9) {
            this.setOverScrollMode(2);
        }

        l.b(this);
        this.e = com.uzmap.pkg.uzcore.d.v;
        this.a(a);
    }

    public final String B() {
        return this.d;
    }

    public final void d(String name) {
        this.d = name;
    }

    public final void h(boolean flag) {
        this.g = flag;
    }

    public final boolean C() {
        return this.g;
    }

    public void i(boolean allowEdit) {
        this.c = allowEdit;
        if (this.c) {
            this.setOnLongClickListener(null);
        } else {
            this.setOnLongClickListener(this);
        }

    }

    public final int D() {
        View p = (View) this.getParent();
        return p != null ? p.getLeft() : this.getLeft();
    }

    public final int E() {
        View p = (View) this.getParent();
        return p != null ? p.getTop() : this.getTop();
    }

    public final int F() {
        View p = (View) this.getParent();
        return p != null ? p.getRight() : this.getRight();
    }

    public final int G() {
        View p = (View) this.getParent();
        return p != null ? p.getBottom() : this.getBottom();
    }

    public final boolean H() {
        return this.a;
    }

    public final void I() {
        this.a = true;
        --h;
    }

    public final void J() {
        if (!this.H()) {
            if (l.SDK_INT >= 11) {
                super.onPause();
            } else {
                try {
                    Class[] nullParm = new Class[0];
                    Method pause = WebView.class.getDeclaredMethod("onPause", nullParm);
                    pause.setAccessible(true);
                    pause.invoke(this, (Object[]) null);
                } catch (Exception var3) {
                }
            }

        }
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        if (!this.e) {
            return super.dispatchKeyEvent(event);
        } else {
            int action = event.getAction();
            int keyCode = event.getKeyCode();
            if (action == 0) {
                if (keyCode != 4 && keyCode != 82) {
                    this.f = true;
                    return true;
                } else {
                    return super.onKeyDown(keyCode, event);
                }
            } else if (action == 1) {
                if (keyCode != 4 && keyCode != 82) {
                    boolean handled = false;
                    if (this.f) {
                        if (keyCode == 66) {
                            keyCode = 23;
                        }

                        handled = this.a(keyCode);
                    }

                    this.f = false;
                    return handled || super.onKeyDown(keyCode, event);
                } else {
                    return super.onKeyDown(keyCode, event);
                }
            } else {
                return super.dispatchKeyEvent(event);
            }
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!this.e) {
            return super.onKeyDown(keyCode, event);
        } else if ((keyCode != 4 || this.C()) && keyCode != 82) {
            this.f = true;
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (!this.e) {
            return super.onKeyDown(keyCode, event);
        } else if ((keyCode != 4 || this.C()) && keyCode != 82) {
            boolean handled = false;
            if (this.f) {
                handled = this.a(keyCode);
            }

            this.f = false;
            return handled || super.onKeyDown(keyCode, event);
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    protected void onDraw(Canvas c) {
        super.onDraw(c);
        if (!this.b) {
            this.b = true;
            final View root = this.getRootView();
            if (root.getBackground() != null) {
                this.post(new Runnable() {
                    public void run() {
                        l.a(root, null);
                    }
                });
            }
        }

    }

    public final boolean onLongClick(View v) {
        return true;
    }

    public final void pauseTimers() {
        if (!this.H()) {
            try {
                super.pauseTimers();
            } catch (Exception var2) {
            }

        }
    }

    public final void resumeTimers() {
        if (!this.H()) {
            try {
                super.resumeTimers();
            } catch (Exception var2) {
            }

        }
    }

    public final void clearCache(boolean includeDiskFiles) {
        if (!this.H()) {
            try {
                super.clearCache(includeDiskFiles);
            } catch (Exception var3) {
            }

        }
    }

    public final void loadUrl(String url) {
        if (!this.H()) {
            try {
                super.loadUrl(url);
            } catch (Exception var3) {
            }

        }
    }

    public final void loadUrl(String url, Map<String, String> extraHeaders) {
        if (!this.H()) {
            try {
                super.loadUrl(url, extraHeaders);
            } catch (Exception var4) {
            }

        }
    }

    public final void loadData(String data, String mimeType, String encoding) {
        if (!this.H()) {
            try {
                super.loadData(data, mimeType, encoding);
            } catch (Exception var5) {
            }

        }
    }

    public final void e(String script) {
        if (this.getContext() != null) {
            k.a evaluate = new k.a(script);
            com.uzmap.pkg.uzcore.c.a(evaluate, 0L);
        }
    }

    public String getOriginalUrl() {
        String url = super.getOriginalUrl();
        return url != null ? url : "";
    }

    public final void goBack() {
        if (!this.H()) {
            super.goBack();
        }
    }

    public final void goForward() {
        if (!this.H()) {
            super.goForward();
        }
    }

    public final void stopLoading() {
        if (!this.H()) {
            super.stopLoading();
            this.J();
        }
    }

    public String toString() {
        String s = super.toString();
        return this.d + "-" + s;
    }

    protected void finalize() throws Throwable {
        super.finalize();
    }

    protected Parcelable onSaveInstanceState() {
        UZCoreUtil.logd("Webview onSaveInstanceState");
        return null;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        UZCoreUtil.logd("Webview onRestoreInstanceState");
    }

    public final void saveWebArchive(String filename) {
    }

    public boolean post(Runnable action) {
        if (l.SDK_INT >= 16) {
            this.postOnAnimation(action);
            return true;
        } else {
            return super.post(action);
        }
    }

    public boolean postDelayed(Runnable action, long delayMillis) {
        if (l.SDK_INT >= 16) {
            this.postOnAnimationDelayed(action, delayMillis);
            return true;
        } else {
            return super.postDelayed(action, delayMillis);
        }
    }

    public final void saveWebArchive(String basename, boolean autoname, ValueCallback<String> callback) {
    }

    protected void a(WebChromeClient client) {
        this.setWebChromeClient(client);
    }

    protected void a(WebViewClient client) {
        this.setWebViewClient(client);
    }

    private void a(boolean a) {
        this.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (k.this.e) {
                    k.this.b(v, hasFocus);
                }

            }
        });
        if (a && !com.uzmap.pkg.uzcore.d.z) {
            l.a(this, 1);
        }

    }

    private class a implements Runnable {
        String a;

        a(String js) {
            this.a = js;
        }

        public void run() {
            if (l.SDK_INT >= 19) {
                k.this.evaluateJavascript(this.a, null);
            } else {
                k.this.loadUrl("javascript:" + this.a);
            }

        }
    }
}
