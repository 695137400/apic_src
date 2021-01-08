package com.uzmap.pkg.uzsocket.a;

import com.uzmap.pkg.uzsocket.e.h;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.NotYetConnectedException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;

public abstract class c extends com.uzmap.pkg.uzsocket.a.b implements com.uzmap.pkg.uzsocket.a.a, Runnable {
    protected URI a;
    private final d c;
    private Socket d;
    private InputStream e;
    private OutputStream f;
    private final Proxy g;
    private Thread h;
    private final com.uzmap.pkg.uzsocket.b.a i;
    private final Map<String, String> j;
    private final CountDownLatch k;
    private final CountDownLatch l;
    private int m;

    public c(URI serverUri, com.uzmap.pkg.uzsocket.b.a draft) {
        this(serverUri, draft, null, 0);
    }

    public c(URI serverUri, com.uzmap.pkg.uzsocket.b.a protocolDraft, Map<String, String> httpHeaders, int cTimeout) {
        this.g = Proxy.NO_PROXY;
        this.k = new CountDownLatch(1);
        this.l = new CountDownLatch(1);
        this.m = 0;
        if (serverUri == null) {
            throw new IllegalArgumentException();
        } else if (protocolDraft == null) {
            throw new IllegalArgumentException("null as draft is permitted for `WebSocketServer` only!");
        } else {
            this.a = serverUri;
            this.i = protocolDraft;
            this.j = httpHeaders;
            this.m = cTimeout;
            if (this.m <= 0) {
                this.m = 5000;
            }

            this.c = new d(this, protocolDraft);
        }
    }

    public URI b() {
        return this.a;
    }

    public void c() {
        if (this.h != null) {
            throw new IllegalStateException("WebSocketClient objects are not reuseable");
        } else {
            this.h = new Thread(this);
            this.h.start();
        }
    }

    public void d() {
        if (this.h != null) {
            this.c.a(1000);
        }

    }

    public void a(String text) throws NotYetConnectedException {
        this.c.a(text);
    }

    public void run() {
        Thread.currentThread().setName("WebsocketReadThread");

        int readBytes;
        try {
            if (this.d == null) {
                this.d = new Socket(this.g);
            } else if (this.d.isClosed()) {
                throw new IOException();
            }

            if (!this.d.isBound()) {
                String host = this.a.getHost();
                readBytes = this.g();
                this.d.connect(new InetSocketAddress(host, readBytes), this.m);
            }

            this.e = this.d.getInputStream();
            this.f = this.d.getOutputStream();
            this.h();
        } catch (Exception var7) {
            this.a(this.c, var7);
            this.c.b(-1, var7.getMessage());
            return;
        }

        this.h = new Thread(new c.a(null));
        this.h.start();
        byte[] rawbuffer = new byte[com.uzmap.pkg.uzsocket.a.d.a];

        try {
            ByteBuffer wrap;
            for (; !this.f() && (readBytes = this.e.read(rawbuffer)) != -1; this.c.a(wrap)) {
                wrap = ByteBuffer.wrap(rawbuffer, 0, readBytes);
                if (com.uzmap.pkg.uzsocket.g.f.a) {
                    String wp = new String(wrap.array(), 0, wrap.limit(), StandardCharsets.UTF_8);
                    com.uzmap.pkg.uzsocket.g.f.a("-------- Websoket Engine Read --------\nlength: " + readBytes + " , \ncontent: " + wp);
                }
            }

            this.c.b();
        } catch (IOException var5) {
            this.c.b();
        } catch (RuntimeException var6) {
            this.a(var6);
            this.c.b(1006, var6.getMessage());
        }

        assert this.d.isClosed();

    }

    private int g() {
        int port = this.a.getPort();
        if (port == -1) {
            String scheme = this.a.getScheme();
            if (scheme.equals("wss")) {
                return 443;
            } else if (scheme.equals("ws")) {
                return 80;
            } else {
                throw new RuntimeException("unkonow scheme" + scheme);
            }
        } else {
            return port;
        }
    }

    private void h() throws com.uzmap.pkg.uzsocket.c.d {
        String part1 = this.a.getPath();
        String part2 = this.a.getQuery();
        String path;
        if (part1 != null && part1.length() != 0) {
            path = part1;
        } else {
            path = "/";
        }

        if (part2 != null) {
            path = path + "?" + part2;
        }

        int port = this.g();
        String host = this.a.getHost() + (port != 80 ? ":" + port : "");
        com.uzmap.pkg.uzsocket.e.d handshake = new com.uzmap.pkg.uzsocket.e.d();
        handshake.a(path);
        handshake.a("Host", host);
        if (this.j != null) {
            Iterator var8 = this.j.entrySet().iterator();

            while (var8.hasNext()) {
                Entry<String, String> kv = (Entry) var8.next();
                handshake.a(kv.getKey(), kv.getValue());
            }
        }

        this.c.a(handshake);
    }

    public final void a(com.uzmap.pkg.uzsocket.a.a conn, String message) {
        this.b(message);
    }

    public final void a(com.uzmap.pkg.uzsocket.a.a conn, ByteBuffer blob) {
        this.a(blob);
    }

    public void a(com.uzmap.pkg.uzsocket.a.a conn, com.uzmap.pkg.uzsocket.d.d frame) {
        this.b(frame);
    }

    public final void a(com.uzmap.pkg.uzsocket.a.a conn, com.uzmap.pkg.uzsocket.e.f handshake) {
        this.k.countDown();
        this.a((h) handshake);
    }

    public final void a(com.uzmap.pkg.uzsocket.a.a conn, int code, String reason, boolean remote) {
        this.k.countDown();
        this.l.countDown();
        if (this.h != null) {
            this.h.interrupt();
        }

        try {
            if (this.d != null) {
                this.d.close();
            }
        } catch (IOException var6) {
            this.a(this, var6);
        }

        this.b(code, reason, remote);
    }

    public final void a(com.uzmap.pkg.uzsocket.a.a conn, Exception ex) {
        this.a(ex);
    }

    public final void b(com.uzmap.pkg.uzsocket.a.a conn) {
    }

    public void a(com.uzmap.pkg.uzsocket.a.a conn, int code, String reason) {
        this.a(code, reason);
    }

    public void b(com.uzmap.pkg.uzsocket.a.a conn, int code, String reason, boolean remote) {
        this.a(code, reason, remote);
    }

    public void a(int code, String reason) {
    }

    public void a(int code, String reason, boolean remote) {
    }

    public InetSocketAddress c(com.uzmap.pkg.uzsocket.a.a conn) {
        return this.d != null ? (InetSocketAddress) this.d.getLocalSocketAddress() : null;
    }

    public abstract void a(h var1);

    public abstract void b(String var1);

    public abstract void b(int var1, String var2, boolean var3);

    public abstract void a(Exception var1);

    public void a(ByteBuffer bytes) {
    }

    public void b(com.uzmap.pkg.uzsocket.d.d frame) {
    }

    public boolean e() {
        return this.c.c();
    }

    public boolean f() {
        boolean closed = this.c.f();
        return closed;
    }

    public void a(com.uzmap.pkg.uzsocket.d.d framedata) {
        this.c.a(framedata);
    }

    public InetSocketAddress a() {
        return this.c.a();
    }

    private class a implements Runnable {
        private a() {
        }

        // $FF: synthetic method
        a(c.a var2) {
            this();
        }

        public void run() {
            Thread.currentThread().setName("WebsocketWriteThread");

            try {
                while (!Thread.interrupted()) {
                    ByteBuffer buffer = c.this.c.e.take();
                    c.this.f.write(buffer.array(), 0, buffer.limit());
                    c.this.f.flush();
                }
            } catch (IOException var2) {
                c.this.c.b();
            } catch (InterruptedException var3) {
            }

        }
    }
}
