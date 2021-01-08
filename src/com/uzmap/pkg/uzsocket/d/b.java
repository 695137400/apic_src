package com.uzmap.pkg.uzsocket.d;

import java.nio.ByteBuffer;

public class b extends e implements com.uzmap.pkg.uzsocket.d.a {
    static final ByteBuffer a = ByteBuffer.allocate(0);
    private int f;
    private String g;

    public b() {
        this.a(true);
    }

    public b(int code) throws com.uzmap.pkg.uzsocket.c.b {
        this.a(true);
        this.a(code, "");
    }

    public b(int code, String m) throws com.uzmap.pkg.uzsocket.c.b {
        this.a(true);
        this.a(code, m);
    }

    private void a(int code, String m) throws com.uzmap.pkg.uzsocket.c.b {
        if (m == null) {
            m = "";
        }

        if (code == 1015) {
            code = 1005;
            m = "";
        }

        if (code == 1005) {
            if (m.length() > 0) {
                throw new com.uzmap.pkg.uzsocket.c.b(1002, "A close frame must have a closecode if it has a reason");
            }
        } else {
            byte[] by = com.uzmap.pkg.uzsocket.h.b.a(m);
            ByteBuffer buf = ByteBuffer.allocate(4);
            buf.putInt(code);
            buf.position(2);
            ByteBuffer pay = ByteBuffer.allocate(2 + by.length);
            pay.put(buf);
            pay.put(by);
            pay.rewind();
            this.a(pay);
        }
    }

    private void g() throws com.uzmap.pkg.uzsocket.c.c {
        this.f = 1005;
        ByteBuffer payload = super.c();
        payload.mark();
        if (payload.remaining() >= 2) {
            ByteBuffer bb = ByteBuffer.allocate(4);
            bb.position(2);
            bb.putShort(payload.getShort());
            bb.position(0);
            this.f = bb.getInt();
            if (this.f == 1006 || this.f == 1015 || this.f == 1005 || this.f > 4999 || this.f < 1000 || this.f == 1004) {
                throw new com.uzmap.pkg.uzsocket.c.c("closecode must not be sent over the wire: " + this.f);
            }
        }

        payload.reset();
    }

    public int a() {
        return this.f;
    }

    private void h() throws com.uzmap.pkg.uzsocket.c.b {
        if (this.f == 1005) {
            this.g = com.uzmap.pkg.uzsocket.h.b.a(super.c());
        } else {
            ByteBuffer b = super.c();
            int mark = b.position();

            try {
                b.position(b.position() + 2);
                this.g = com.uzmap.pkg.uzsocket.h.b.a(b);
            } catch (IllegalArgumentException var7) {
                throw new com.uzmap.pkg.uzsocket.c.c(var7);
            } finally {
                b.position(mark);
            }
        }

    }

    public String b() {
        return this.g;
    }

    public String toString() {
        return super.toString() + "code: " + this.f;
    }

    public void a(ByteBuffer payload) throws com.uzmap.pkg.uzsocket.c.b {
        super.a(payload);
        this.g();
        this.h();
    }

    public ByteBuffer c() {
        return this.f == 1005 ? a : super.c();
    }
}
