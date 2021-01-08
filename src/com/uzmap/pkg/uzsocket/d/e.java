package com.uzmap.pkg.uzsocket.d;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class e implements c {
    protected static byte[] b = new byte[0];
    protected boolean c;
    protected aemnu d;
    protected boolean e;
    private ByteBuffer a;

    public e() {
    }

    public e(aemnu op) {
        this.d = op;
        this.a = ByteBuffer.wrap(b);
    }

    public e(d f) {
        this.c = f.d();
        this.d = f.f();
        this.a = f.c();
        this.e = f.e();
    }

    public boolean d() {
        return this.c;
    }

    public aemnu f() {
        return this.d;
    }

    public boolean e() {
        return this.e;
    }

    public ByteBuffer c() {
        return this.a;
    }

    public void a(boolean fin) {
        this.c = fin;
    }

    public void a(aemnu optcode) {
        this.d = optcode;
    }

    public void a(ByteBuffer payload) throws com.uzmap.pkg.uzsocket.c.b {
        this.a = payload;
    }

    public void b(boolean transferemasked) {
        this.e = transferemasked;
    }

    public String toString() {
        return "Framedata{ optcode:" + this.f() + ", fin:" + this.d() + ", payloadlength:[pos:" + this.a.position() + ", len:" + this.a.remaining() + "], payload:" + Arrays.toString(com.uzmap.pkg.uzsocket.h.b.a(new String(this.a.array()))) + "}";
    }
}
