//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b.c1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class b {
    private List<byte[]> b = new LinkedList();
    private List<byte[]> c = new ArrayList(64);
    private int d = 0;
    private final int e;
    protected static final Comparator<byte[]> a = new Comparator<byte[]>() {
        @Override
        public int compare(byte[] o1, byte[] o2) {
            return o1.length - o2.length;
        }
    };

    public b(int sizeLimit) {
        this.e = sizeLimit;
    }

    public synchronized byte[] a(int len) {
        for(int i = 0; i < this.c.size(); ++i) {
            byte[] buf = (byte[])this.c.get(i);
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
        while(this.d > this.e) {
            byte[] buf = (byte[])this.b.remove(0);
            this.c.remove(buf);
            this.d -= buf.length;
        }

    }
}
