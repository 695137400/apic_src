package com.uzmap.pkg.uzsocket.g;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class g {
    private Handler a = null;
    private final Runnable b = new g.a();
    private WakeLock c;

    public g() {
        this.a = new Handler(Looper.getMainLooper());
    }

    public void a(Context context) {
        PowerManager pm = (PowerManager) context.getSystemService("power");
        this.c = pm.newWakeLock(1, "APICloud-UPNS");
        this.c.setReferenceCounted(false);
    }

    public boolean a() {
        return this.c != null && this.c.isHeld();
    }

    public void b() {
        try {
            this.a.removeCallbacks(this.b);
            this.c.acquire();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public void a(long delayMillis) {
        this.b();
        this.a.postDelayed(this.b, delayMillis);
    }

    public void c() {
        this.a.removeCallbacks(this.b);
        if (this.a()) {
            try {
                this.c.release();
            } catch (Exception var2) {
                var2.printStackTrace();
            }

        }
    }

    protected void finalize() {
        this.c();
    }

    class a implements Runnable {
        public void run() {
            g.this.c();
        }
    }
}
