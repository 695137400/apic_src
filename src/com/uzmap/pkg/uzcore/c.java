package com.uzmap.pkg.uzcore;

import android.os.Handler;
import android.os.Looper;

public class c {
    private static Handler a;
    private static long b;

    public static void a(Runnable action) {
        b();
        a.removeCallbacks(action);
    }

    public static boolean b(Runnable action) {
        return a(action, 0L);
    }

    public static boolean a(Runnable action, long delayMillis) {
        b();
        if (delayMillis != 0L) {
            return a.postDelayed(action, delayMillis);
        } else if (c()) {
            action.run();
            return true;
        } else {
            return a.post(action);
        }
    }

    public static void a() {
        if (a != null) {
            a.removeMessages(0);
        }

        a = null;
        b = -1L;
    }

    private static void b() {
        if (a == null) {
            a = new Handler(Looper.getMainLooper());
            b = Looper.getMainLooper().getThread().getId();
        }

    }

    private static boolean c() {
        return Thread.currentThread().getId() == b;
    }
}
