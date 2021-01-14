package com.uzmap.pkg.uzkit.a.aa;

import android.content.Context;
import android.text.TextUtils;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.uzmodule.ApiConfig;

import java.util.Hashtable;

public final class a {
    static boolean a = false;
    private static a h;
    private b b;
    private d c;
    private e d;
    private c e;
    private boolean f;
    private Hashtable<String, i> g;

    private a() {
    }

    public static final synchronized a a(Context context) {
        if (h == null) {
            h = new a();
            h.b(context);
        }

        return h;
    }

    public static final a a() {
        if (h == null) {
            throw new IllegalAccessError("Analysis is not createInstance");
        } else {
            return h;
        }
    }

    private void b(Context context) {
        this.g = new Hashtable();
        this.b = new b();
        this.c = new d();
        this.d = new e();
        this.d.a(context);
        this.e = new c(this.d);
    }

    public final void b() {
        this.b.b();
        this.a(false);
    }

    public final void a(ApiConfig wgtInfo) {
        this.b.c();
        this.b(false);
        this.b(wgtInfo);
        this.a(true);
    }

    public final void c() {
        this.b.d();
        this.b(true);
    }

    public final void a(String label) {
        if (a) {
            if (!TextUtils.isEmpty(label)) {
                i logger = this.g.get(label);
                if (logger == null) {
                    g pagelogger = new g(label);
                    this.g.put(label, pagelogger);
                    logger = pagelogger;
                }

                logger.a("start");
            }
        }
    }

    public final void b(String label) {
        if (a) {
            if (!TextUtils.isEmpty(label)) {
                i logger = this.g.get(label);
                if (logger != null) {
                    logger.a("pause");
                }

            }
        }
    }

    public final void c(String label) {
        if (a) {
            if (!TextUtils.isEmpty(label)) {
                i logger = this.g.remove(label);
                if (logger != null) {
                    logger.a("finish");
                    this.a(logger);
                }
            }
        }
    }

    public final void d(final String eventName) {
        if (a) {
            h action = new h() {
                public void run() {
                    if (!TextUtils.isEmpty(eventName)) {
                        a.this.d.a(eventName);
                    }
                }
            };
            this.c.a(action);
        }
    }

    public final synchronized void a(double lat, double log, String widgetId) {
        if (!this.e() && !TextUtils.isEmpty(widgetId) && 0.0D != lat && 0.0D != log) {
            this.e.a(lat, log, widgetId);
        }
    }

    public final synchronized void a(String title, String content) {
        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(content)) {
            this.d.a(title, content);
        }
    }

    public final synchronized void b(ApiConfig wgtInfo) {
        if (!com.uzmap.pkg.uzcore.b.a().o() && com.uzmap.pkg.uzcore.b.a().h()) {
            if (!this.e() && this.f()) {
                this.e.a(wgtInfo);
            }
        }
    }

    public final String d() {
        return this.d.i();
    }

    public final void a(int infoType) {
        switch (infoType) {
            case 0:
                this.d.h();
                break;
            case 1:
                this.d.g();
        }

    }

    private final boolean e() {
        return this.f;
    }

    private void a(boolean flag) {
        this.f = flag;
    }

    private void a(final i logger) {
        com.uzmap.pkg.uzkit.a.b.a("analysisPageImmediately ----------------------------------");
        h action = new h() {
            public void run() {
                a.this.d.a((g) logger);
            }
        };
        this.c.a(action);
    }

    private void b(boolean immediately) {
        com.uzmap.pkg.uzkit.a.b.a("analysisAppImmediately ----------------------------------");
        if (!immediately && a) {
            h action = new h() {
                public void run() {
                    a.this.d.a(a.this.b);
                }
            };
            this.c.a(action);
        } else {
            this.d.a(this.b);
        }
    }

    private boolean f() {
        return 2 == UZCoreUtil.getConnectedType();
    }
}
