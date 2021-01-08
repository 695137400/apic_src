package com.uzmap.pkg.uzsocket.g;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class b extends Handler {
    private final c a;

    public b(c service) {
        super(Looper.getMainLooper());
        this.a = service;
    }

    public void handleMessage(Message msg) {
        switch (msg.what) {
            case 0:
                this.a.d();
                this.a();
                break;
            case 1:
                this.a.e();
        }

    }

    public void a() {
        this.sendEmptyMessageDelayed(0, 60000L);
    }

    public void b() {
        this.removeMessages(0);
    }

    public boolean a(int delaySec) {
        long delay = (long) delaySec * 1000L;
        return this.sendEmptyMessageDelayed(1, delay);
    }

    public void c() {
        this.removeMessages(1);
    }

    public void d() {
        this.removeMessages(1);
        this.removeMessages(0);
    }

    public void a(Runnable action) {
        this.post(action);
    }
}
