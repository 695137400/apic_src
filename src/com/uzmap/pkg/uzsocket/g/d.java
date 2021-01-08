package com.uzmap.pkg.uzsocket.g;

import com.uzmap.pkg.uzsocket.e.h;
import org.json.JSONObject;

import java.net.URI;
import java.nio.ByteBuffer;

public class d extends com.uzmap.pkg.uzsocket.a.c {
    private e c;

    public d(URI serverURI, com.uzmap.pkg.uzsocket.b.f draft) {
        super(serverURI, draft);
    }

    public void a(e listener) {
        this.c = listener;
    }

    public void g() {
        this.c = null;
    }

    public void a(h handshakedata) {
        com.uzmap.pkg.uzsocket.g.f.a(" @@@@@@@@@@@@@ UPnsSocketClient ------ onOpen");
        if (this.c != null) {
            this.c.a();
        }

    }

    public void b(int code, String reason, boolean remote) {
        com.uzmap.pkg.uzsocket.g.f.a(" @@@@@@@@@@@@@ UPnsSocketClient onClose");
        if (this.c != null) {
            this.c.a(code, reason, remote);
        }

    }

    public void a(Exception ex) {
        com.uzmap.pkg.uzsocket.g.f.a(" @@@@@@@@@@@@@ UPnsSocketClient ------ onError\n" + ex);
        if (this.c != null) {
            this.c.a(ex);
        }

    }

    public void b(String message) {
        com.uzmap.pkg.uzsocket.g.f.a(" @@@@@@@@@@@@@ UPnsSocketClient ------ onMessage: " + message);
        this.c(message);
    }

    public void a(ByteBuffer bytes) {
        super.a(bytes);
    }

    public void b(com.uzmap.pkg.uzsocket.d.d frame) {
        super.b(frame);
    }

    public void b(com.uzmap.pkg.uzsocket.a.a conn, com.uzmap.pkg.uzsocket.d.d f) {
        super.b(conn, f);
    }

    public void c(com.uzmap.pkg.uzsocket.a.a conn, com.uzmap.pkg.uzsocket.d.d f) {
        super.c(conn, f);
    }

    public void a(com.uzmap.pkg.uzsocket.a.a conn, com.uzmap.pkg.uzsocket.d.d frame) {
        com.uzmap.pkg.uzsocket.d.c builder = (com.uzmap.pkg.uzsocket.d.c) frame;
        builder.b(true);
        this.a(frame);
    }

    private void c(String message) {
        com.uzmap.pkg.uzsocket.f.g route = new com.uzmap.pkg.uzsocket.f.g(message);
        if (route.a()) {
            switch (route.e) {
                case 0:
                    this.a(route.b());
                    break;
                case 1:
                    this.a(route.c());
                    break;
                default:
                    this.b(route.d());
            }
        }

    }

    private void a(com.uzmap.pkg.uzsocket.f.a message) {
        switch (message.b) {
            case 1:
                this.a((com.uzmap.pkg.uzsocket.f.e) message);
                break;
            case 2:
                this.a((com.uzmap.pkg.uzsocket.f.d) message);
                break;
            case 101:
            case 102:
            case 103:
            case 107:
                this.a((com.uzmap.pkg.uzsocket.f.c) message);
        }

    }

    private void b(com.uzmap.pkg.uzsocket.f.a message) {
        this.c(message);
    }

    private void a(com.uzmap.pkg.uzsocket.f.e message) {
        this.d(message.a());
        if (this.c != null) {
            this.c.a(message);
        }

    }

    private void a(com.uzmap.pkg.uzsocket.f.d message) {
        this.d(message.a());
        if (this.c != null) {
            this.c.a(message);
        }

    }

    private void a(com.uzmap.pkg.uzsocket.f.c message) {
        if (this.c != null) {
            this.c.a(message);
        }

    }

    private void c(com.uzmap.pkg.uzsocket.f.a message) {
        if (this.c != null) {
            this.c.a(message);
        }

    }

    private void a(com.uzmap.pkg.uzsocket.f.b message) {
        this.d(message.a());
    }

    private void d(String text) {
        this.a(text);
    }

    public void h() {
        JSONObject request = new JSONObject();

        try {
            request.put("j", 3);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        this.a(request.toString());
    }

    public String toString() {
        return this.b() != null ? this.b().toString() : super.toString();
    }
}
