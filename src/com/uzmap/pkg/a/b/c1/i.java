//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b.c1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class i extends ByteArrayOutputStream {
    private final b a;

    public i(b pool, int size) {
        this.a = pool;
        this.buf = this.a.a(Math.max(size, 256));
    }

    public void close() throws IOException {
        this.a.a(this.buf);
        this.buf = null;
        super.close();
    }

    public void finalize() {
        this.a.a(this.buf);
    }

    private void a(int i) {
        if (this.count + i > this.buf.length) {
            byte[] newbuf = this.a.a((this.count + i) * 2);
            System.arraycopy(this.buf, 0, newbuf, 0, this.count);
            this.a.a(this.buf);
            this.buf = newbuf;
        }
    }

    public synchronized void write(byte[] buffer, int offset, int len) {
        this.a(len);
        super.write(buffer, offset, len);
    }

    public synchronized void write(int oneByte) {
        this.a(1);
        super.write(oneByte);
    }
}
