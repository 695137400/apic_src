package com.uzmap.pkg.uzsocket.b;

public class c extends com.uzmap.pkg.uzsocket.b.b {
    public bemnu a(com.uzmap.pkg.uzsocket.e.a handshakedata) throws com.uzmap.pkg.uzsocket.c.d {
        int v = b(handshakedata);
        return v == 13 ? com.uzmap.pkg.uzsocket.b.a.bemnu.a : com.uzmap.pkg.uzsocket.b.a.bemnu.b;
    }

    public com.uzmap.pkg.uzsocket.e.b a(com.uzmap.pkg.uzsocket.e.b request) {
        super.a(request);
        request.a("Sec-WebSocket-Version", "13");
        return request;
    }

    public com.uzmap.pkg.uzsocket.b.a c() {
        return new c();
    }
}
