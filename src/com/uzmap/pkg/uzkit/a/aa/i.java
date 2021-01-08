package com.uzmap.pkg.uzkit.a.aa;

import android.os.SystemClock;

import java.util.ArrayList;

class i {
    protected String a;
    ArrayList<Long> b;
    ArrayList<Object> c;

    public i(String label) {
        this.b(label);
    }

    public void b(String label) {
        this.a = label;
        this.e();
    }

    public void e() {
        if (this.b == null) {
            this.b = new ArrayList();
            this.c = new ArrayList();
        } else {
            this.b.clear();
            this.c.clear();
        }

        this.a(null);
    }

    public void a(Object splitLabel) {
        long now = SystemClock.elapsedRealtime();
        this.b.add(now);
        this.c.add(splitLabel);
    }

    public long f() {
        long first = this.b.get(0);
        long visabletime = 0L;

        for (int i = 1; i < this.b.size(); ++i) {
            long now = this.b.get(i);
            Object splitLabel = this.c.get(i);
            long prev = this.b.get(i - 1);
            long timegap = now - prev;
            if (!"start".equals(splitLabel)) {
                visabletime += timegap;
            }
        }

        return visabletime;
    }

    public long g() {
        long first = this.b.get(0);
        long visabletime = 0L;

        for (int i = 1; i < this.b.size(); ++i) {
            long now = this.b.get(i);
            Object splitLabel = this.c.get(i);
            long prev = this.b.get(i - 1);
            long timegap = now - prev;
            if ("start".equals(splitLabel)) {
                visabletime += timegap;
            }
        }

        return visabletime;
    }
}
