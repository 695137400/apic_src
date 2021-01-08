package com.uzmap.pkg.uzkit.a.aa;

import android.os.SystemClock;

public class b extends i {
    private long d;
    private long e;
    private String f;

    public b() {
        super("app");
        this.h();
    }

    public String a() {
        return this.f;
    }

    public synchronized void b() {
        this.a("onResume");
        this.d = SystemClock.elapsedRealtime();
        if (this.d - this.e > 30000L) {
            this.h();
            this.b("app");
        }

        this.a((Object) "start");
    }

    public synchronized void c() {
        this.e = SystemClock.elapsedRealtime();
        this.a((Object) "pause");
        this.a("onPause");
    }

    public synchronized void d() {
        this.a((Object) "finish");
        this.a("onFinish");
    }

    private void h() {
        this.f = Long.toString(System.currentTimeMillis());
    }

    public void a(String logtitle) {
    }
}
