package com.uzmap.pkg.a.b.cc;

import java.util.*;

public class b {
    protected static final Comparator<byte[]> a = new Comparator<byte[]>() {
        public int a(byte[] lhs, byte[] rhs) {
            return lhs.length - rhs.length;
        }

        // $FF: synthetic method
        public int compare(byte[] var1, byte[] var2) {
            return this.a(var1, var2);
        }
    };
    private final int e;
    private final List<byte[]> b = new LinkedList();
    private final List<byte[]> c = new ArrayList(64);
    private int d = 0;

    public b(int sizeLimit) {
        this.e = sizeLimit;
    }

    public synchronized byte[] a(int len) {
        for (int i = 0; i < this.c.size(); ++i) {
            byte[] buf = this.c.get(i);
            if (buf.length >= len) {
                this.d -= buf.length;
                this.c.remove(i);
                this.b.remove(buf);
                return buf;
            }
        }

        return new byte[len];
    }

    public synchronized void a(byte[] buf) {
        if (buf != null && buf.length <= this.e) {
            this.b.add(buf);
            int pos = Collections.binarySearch(this.c, buf, a);
            if (pos < 0) {
                pos = -pos - 1;
            }

            this.c.add(pos, buf);
            this.d += buf.length;
            this.a();
        }
    }

    private synchronized void a() {
        while (this.d > this.e) {
            byte[] buf = this.b.remove(0);
            this.c.remove(buf);
            this.d -= buf.length;
        }

    }
}
