package com.uzmap.pkg.uzsocket.b;

import com.uzmap.pkg.uzsocket.e.h;
import com.uzmap.pkg.uzsocket.e.i;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public abstract class a {
    public static final byte[] c = com.uzmap.pkg.uzsocket.h.b.a("<policy-file-request/>\u0000");
    public static int a = 1000;
    public static int b = 64;
    protected com.uzmap.pkg.uzsocket.a.a.b d = null;
    protected com.uzmap.pkg.uzsocket.d.d.aemnu e = null;

    public static ByteBuffer a(ByteBuffer buf) {
        ByteBuffer sbuf = ByteBuffer.allocate(buf.remaining());
        byte cur = 48;

        byte prev;
        do {
            if (!buf.hasRemaining()) {
                buf.position(buf.position() - sbuf.position());
                return null;
            }

            prev = cur;
            cur = buf.get();
            sbuf.put(cur);
        } while (prev != 13 || cur != 10);

        sbuf.limit(sbuf.position() - 2);
        sbuf.position(0);
        return sbuf;
    }

    public static String b(ByteBuffer buf) {
        ByteBuffer b = a(buf);
        return b == null ? null : com.uzmap.pkg.uzsocket.h.b.a(b.array(), 0, b.limit());
    }

    public static com.uzmap.pkg.uzsocket.e.c a(ByteBuffer buf, com.uzmap.pkg.uzsocket.a.a.b role) throws com.uzmap.pkg.uzsocket.c.d, com.uzmap.pkg.uzsocket.c.a {
        String line = b(buf);
        if (line == null) {
            throw new com.uzmap.pkg.uzsocket.c.a(buf.capacity() + 128);
        } else {
            String[] firstLineTokens = line.split(" ", 3);
            if (firstLineTokens.length != 3) {
                throw new com.uzmap.pkg.uzsocket.c.d();
            } else {
                Object handshake;
                if (role == com.uzmap.pkg.uzsocket.a.a.b.a) {
                    handshake = new com.uzmap.pkg.uzsocket.e.e();
                    i serverhandshake = (i) handshake;
                    serverhandshake.a(Short.parseShort(firstLineTokens[1]));
                    serverhandshake.a(firstLineTokens[2]);
                } else {
                    com.uzmap.pkg.uzsocket.e.b clienthandshake = new com.uzmap.pkg.uzsocket.e.d();
                    clienthandshake.a(firstLineTokens[1]);
                    handshake = clienthandshake;
                }

                for (line = b(buf); line != null && line.length() > 0; line = b(buf)) {
                    String[] pair = line.split(":", 2);
                    if (pair.length != 2) {
                        throw new com.uzmap.pkg.uzsocket.c.d("not an http header");
                    }

                    ((com.uzmap.pkg.uzsocket.e.c) handshake).a(pair[0], pair[1].replaceFirst("^ +", ""));
                }

                if (line == null) {
                    throw new com.uzmap.pkg.uzsocket.c.a();
                } else {
                    return (com.uzmap.pkg.uzsocket.e.c) handshake;
                }
            }
        }
    }

    public abstract bemnu a(com.uzmap.pkg.uzsocket.e.a var1, h var2) throws com.uzmap.pkg.uzsocket.c.d;

    public abstract bemnu a(com.uzmap.pkg.uzsocket.e.a var1) throws com.uzmap.pkg.uzsocket.c.d;

    protected boolean a(com.uzmap.pkg.uzsocket.e.f handshakedata) {
        return handshakedata.b("Upgrade").equalsIgnoreCase("websocket") && handshakedata.b("Connection").toLowerCase(Locale.ENGLISH).contains("upgrade");
    }

    public abstract ByteBuffer a(com.uzmap.pkg.uzsocket.d.d var1);

    public abstract List<com.uzmap.pkg.uzsocket.d.d> a(String var1, boolean var2);

    public abstract void a();

    public List<ByteBuffer> a(com.uzmap.pkg.uzsocket.e.f handshakedata, com.uzmap.pkg.uzsocket.a.a.b ownrole) {
        return this.a(handshakedata, ownrole, true);
    }

    public List<ByteBuffer> a(com.uzmap.pkg.uzsocket.e.f handshakedata, com.uzmap.pkg.uzsocket.a.a.b ownrole, boolean withcontent) {
        StringBuilder bui = new StringBuilder(100);
        if (handshakedata instanceof com.uzmap.pkg.uzsocket.e.a) {
            bui.append("GET ");
            bui.append(((com.uzmap.pkg.uzsocket.e.a) handshakedata).a());
            bui.append(" HTTP/1.1");
        } else {
            if (!(handshakedata instanceof h)) {
                throw new RuntimeException("unknow role");
            }

            bui.append("HTTP/1.1 101 " + ((h) handshakedata).a());
        }

        bui.append("\r\n");
        Iterator it = handshakedata.b();

        while (it.hasNext()) {
            String fieldname = (String) it.next();
            String fieldvalue = handshakedata.b(fieldname);
            bui.append(fieldname);
            bui.append(": ");
            bui.append(fieldvalue);
            bui.append("\r\n");
        }

        bui.append("\r\n");
        byte[] httpheader = com.uzmap.pkg.uzsocket.h.b.b(bui.toString());
        byte[] content = withcontent ? handshakedata.c() : null;
        ByteBuffer bytebuffer = ByteBuffer.allocate((content == null ? 0 : content.length) + httpheader.length);
        bytebuffer.put(httpheader);
        if (content != null) {
            bytebuffer.put(content);
        }

        bytebuffer.flip();
        return Collections.singletonList(bytebuffer);
    }

    public abstract com.uzmap.pkg.uzsocket.e.b a(com.uzmap.pkg.uzsocket.e.b var1) throws com.uzmap.pkg.uzsocket.c.d;

    public abstract com.uzmap.pkg.uzsocket.e.c a(com.uzmap.pkg.uzsocket.e.a var1, i var2) throws com.uzmap.pkg.uzsocket.c.d;

    public abstract List<com.uzmap.pkg.uzsocket.d.d> c(ByteBuffer var1) throws com.uzmap.pkg.uzsocket.c.b;

    public abstract aemnu b();

    public abstract com.uzmap.pkg.uzsocket.b.a c();

    public com.uzmap.pkg.uzsocket.e.f d(ByteBuffer buf) throws com.uzmap.pkg.uzsocket.c.d {
        return a(buf, this.d);
    }

    public int a(int bytecount) throws com.uzmap.pkg.uzsocket.c.b {
        if (bytecount < 0) {
            throw new com.uzmap.pkg.uzsocket.c.b(1002, "Negative count");
        } else {
            return bytecount;
        }
    }

    public void a(com.uzmap.pkg.uzsocket.a.a.b role) {
        this.d = role;
    }

    public enum aemnu {
        a,
        b,
        c
    }

    public enum bemnu {
        a,
        b
    }
}
