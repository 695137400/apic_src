//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzcore;

import android.content.Context;
import android.os.SystemClock;
import com.uzmap.pkg.uzcore.external.l;
import com.uzmap.pkg.uzcore.external.b.k;

public abstract class UZWebView extends k {
    private long a = SystemClock.uptimeMillis();
    private int b;

    public UZWebView(Context context) {
        super(context, (Object)null);
        throw new RuntimeException("can not access!");
    }

    public UZWebView(int type, boolean a, Context context, m inWin) {
        super(context, type == 0, a);
        if (type != 0) {
            j(true);
        }

        this.b = L();
        if (d.A && type != 0 && K() >= 4) {
            l.a(this, 1);
        }

    }

    protected int z() {
        return (int)((SystemClock.uptimeMillis() - this.a) / 1000L);
    }

    public int A() {
        return this.b;
    }
}
