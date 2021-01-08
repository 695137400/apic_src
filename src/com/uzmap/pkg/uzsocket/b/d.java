package com.uzmap.pkg.uzsocket.b;

import com.uzmap.pkg.uzsocket.e.h;
import com.uzmap.pkg.uzsocket.e.i;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class d extends com.uzmap.pkg.uzsocket.b.a {
    private final Random i = new Random();
    protected boolean f = false;
    protected List<com.uzmap.pkg.uzsocket.d.d> g = new LinkedList();
    protected ByteBuffer h;

    public bemnu a(com.uzmap.pkg.uzsocket.e.a request, h response) {
        return request.b("WebSocket-Origin").equals(response.b("Origin")) && this.a(response) ? com.uzmap.pkg.uzsocket.b.a.bemnu.a : com.uzmap.pkg.uzsocket.b.a.bemnu.b;
    }

    public bemnu a(com.uzmap.pkg.uzsocket.e.a handshakedata) {
        return handshakedata.c("Origin") && this.a((com.uzmap.pkg.uzsocket.e.f) handshakedata) ? com.uzmap.pkg.uzsocket.b.a.bemnu.a : com.uzmap.pkg.uzsocket.b.a.bemnu.b;
    }

    public ByteBuffer a(com.uzmap.pkg.uzsocket.d.d framedata) {
        if (framedata.f() != com.uzmap.pkg.uzsocket.d.d.aemnu.b) {
            throw new RuntimeException("only text frames supported");
        } else {
            ByteBuffer pay = framedata.c();
            ByteBuffer b = ByteBuffer.allocate(pay.remaining() + 2);
            b.put((byte) 0);
            pay.mark();
            b.put(pay);
            pay.reset();
            b.put((byte) -1);
            b.flip();
            return b;
        }
    }

    public List<com.uzmap.pkg.uzsocket.d.d> a(String text, boolean mask) {
        com.uzmap.pkg.uzsocket.d.e frame = new com.uzmap.pkg.uzsocket.d.e();

        try {
            frame.a(ByteBuffer.wrap(com.uzmap.pkg.uzsocket.h.b.a(text)));
        } catch (com.uzmap.pkg.uzsocket.c.b var5) {
            throw new com.uzmap.pkg.uzsocket.c.f(var5);
        }

        frame.a(true);
        frame.a(com.uzmap.pkg.uzsocket.d.d.aemnu.b);
        frame.b(mask);
        return Collections.singletonList(frame);
    }

    public com.uzmap.pkg.uzsocket.e.b a(com.uzmap.pkg.uzsocket.e.b request) throws com.uzmap.pkg.uzsocket.c.d {
        request.a("Upgrade", "WebSocket");
        request.a("Connection", "Upgrade");
        if (!request.c("Origin")) {
            request.a("Origin", "random" + this.i.nextInt());
        }

        return request;
    }

    public com.uzmap.pkg.uzsocket.e.c a(com.uzmap.pkg.uzsocket.e.a request, i response) throws com.uzmap.pkg.uzsocket.c.d {
        response.a("Web Socket Protocol Handshake");
        response.a("Upgrade", "WebSocket");
        response.a("Connection", request.b("Connection"));
        response.a("WebSocket-Origin", request.b("Origin"));
        String location = "ws://" + request.b("Host") + request.a();
        response.a("WebSocket-Location", location);
        return response;
    }

    protected List<com.uzmap.pkg.uzsocket.d.d> e(ByteBuffer buffer) throws com.uzmap.pkg.uzsocket.c.b {
        while (buffer.hasRemaining()) {
            byte newestByte = buffer.get();
            if (newestByte == 0) {
                if (this.f) {
                    throw new com.uzmap.pkg.uzsocket.c.c("unexpected START_OF_FRAME");
                }

                this.f = true;
            } else if (newestByte == -1) {
                if (!this.f) {
                    throw new com.uzmap.pkg.uzsocket.c.c("unexpected END_OF_FRAME");
                }

                if (this.h != null) {
                    this.h.flip();
                    com.uzmap.pkg.uzsocket.d.e curframe = new com.uzmap.pkg.uzsocket.d.e();
                    curframe.a(this.h);
                    curframe.a(true);
                    curframe.a(com.uzmap.pkg.uzsocket.d.d.aemnu.b);
                    this.g.add(curframe);
                    this.h = null;
                    buffer.mark();
                }

                this.f = false;
            } else {
                if (!this.f) {
                    return null;
                }

                if (this.h == null) {
                    this.h = this.d();
                } else if (!this.h.hasRemaining()) {
                    this.h = this.f(this.h);
                }

                this.h.put(newestByte);
            }
        }

        List<com.uzmap.pkg.uzsocket.d.d> frames = this.g;
        this.g = new LinkedList();
        return frames;
    }

    public List<com.uzmap.pkg.uzsocket.d.d> c(ByteBuffer buffer) throws com.uzmap.pkg.uzsocket.c.b {
        List<com.uzmap.pkg.uzsocket.d.d> frames = this.e(buffer);
        if (frames == null) {
            throw new com.uzmap.pkg.uzsocket.c.b(1002);
        } else {
            return frames;
        }
    }

    public void a() {
        this.f = false;
        this.h = null;
    }

    public aemnu b() {
        return com.uzmap.pkg.uzsocket.b.a.aemnu.a;
    }

    public ByteBuffer d() {
        return ByteBuffer.allocate(b);
    }

    public ByteBuffer f(ByteBuffer full) throws com.uzmap.pkg.uzsocket.c.b {
        full.flip();
        ByteBuffer newbuffer = ByteBuffer.allocate(this.a(full.capacity() * 2));
        newbuffer.put(full);
        return newbuffer;
    }

    public com.uzmap.pkg.uzsocket.b.a c() {
        return new d();
    }
}
