package com.uzmap.pkg.uzsocket.b;

import com.uzmap.pkg.uzsocket.e.h;
import com.uzmap.pkg.uzsocket.e.i;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class b extends com.uzmap.pkg.uzsocket.b.a {
    private final Random i = new Random();
    private ByteBuffer g;
    private final com.uzmap.pkg.uzsocket.d.d h = null;

    public static int b(com.uzmap.pkg.uzsocket.e.f handshakedata) {
        String vers = handshakedata.b("Sec-WebSocket-Version");
        if (vers.length() > 0) {
            try {
                int v = new Integer(vers.trim());
                return v;
            } catch (NumberFormatException var4) {
                return -1;
            }
        } else {
            return -1;
        }
    }

    public bemnu a(com.uzmap.pkg.uzsocket.e.a request, h response) throws com.uzmap.pkg.uzsocket.c.d {
        if (request.c("Sec-WebSocket-Key") && response.c("Sec-WebSocket-Accept")) {
            String seckey_answere = response.b("Sec-WebSocket-Accept");
            String seckey_challenge = request.b("Sec-WebSocket-Key");
            seckey_challenge = this.a(seckey_challenge);
            return seckey_challenge.equals(seckey_answere) ? com.uzmap.pkg.uzsocket.b.a.bemnu.a : com.uzmap.pkg.uzsocket.b.a.bemnu.b;
        } else {
            return com.uzmap.pkg.uzsocket.b.a.bemnu.b;
        }
    }

    public bemnu a(com.uzmap.pkg.uzsocket.e.a handshakedata) throws com.uzmap.pkg.uzsocket.c.d {
        int v = b(handshakedata);
        if (v != 7 && v != 8) {
            return com.uzmap.pkg.uzsocket.b.a.bemnu.b;
        } else {
            return this.a((com.uzmap.pkg.uzsocket.e.f) handshakedata) ? com.uzmap.pkg.uzsocket.b.a.bemnu.a : com.uzmap.pkg.uzsocket.b.a.bemnu.b;
        }
    }

    public ByteBuffer a(com.uzmap.pkg.uzsocket.d.d framedata) {
        ByteBuffer mes = framedata.c();
        boolean mask = this.d == com.uzmap.pkg.uzsocket.a.a.b.a;
        int sizebytes = mes.remaining() <= 125 ? 1 : (mes.remaining() <= 65535 ? 2 : 8);
        ByteBuffer buf = ByteBuffer.allocate(1 + (sizebytes > 1 ? sizebytes + 1 : sizebytes) + (mask ? 4 : 0) + mes.remaining());
        byte optcode = this.a(framedata.f());
        byte one = (byte) (framedata.d() ? -128 : 0);
        one |= optcode;
        buf.put(one);
        byte[] payloadlengthbytes = this.a(mes.remaining(), sizebytes);

        assert payloadlengthbytes.length == sizebytes;

        if (sizebytes == 1) {
            buf.put((byte) (payloadlengthbytes[0] | (mask ? -128 : 0)));
        } else if (sizebytes == 2) {
            buf.put((byte) (126 | (mask ? -128 : 0)));
            buf.put(payloadlengthbytes);
        } else {
            if (sizebytes != 8) {
                throw new RuntimeException("Size representation not supported/specified");
            }

            buf.put((byte) (127 | (mask ? -128 : 0)));
            buf.put(payloadlengthbytes);
        }

        if (mask) {
            ByteBuffer maskkey = ByteBuffer.allocate(4);
            maskkey.putInt(this.i.nextInt());
            buf.put(maskkey.array());

            for (int i = 0; mes.hasRemaining(); ++i) {
                buf.put((byte) (mes.get() ^ maskkey.get(i % 4)));
            }
        } else {
            buf.put(mes);
        }

        assert buf.remaining() == 0 : buf.remaining();

        buf.flip();
        return buf;
    }

    public List<com.uzmap.pkg.uzsocket.d.d> a(String text, boolean mask) {
        com.uzmap.pkg.uzsocket.d.e curframe = new com.uzmap.pkg.uzsocket.d.e();

        try {
            curframe.a(ByteBuffer.wrap(com.uzmap.pkg.uzsocket.h.b.a(text)));
        } catch (com.uzmap.pkg.uzsocket.c.b var5) {
            throw new com.uzmap.pkg.uzsocket.c.f(var5);
        }

        curframe.a(true);
        curframe.a(com.uzmap.pkg.uzsocket.d.d.aemnu.b);
        curframe.b(mask);
        return Collections.singletonList(curframe);
    }

    private byte a(com.uzmap.pkg.uzsocket.d.d.aemnu opcode) {
        if (opcode == com.uzmap.pkg.uzsocket.d.d.aemnu.a) {
            return 0;
        } else if (opcode == com.uzmap.pkg.uzsocket.d.d.aemnu.b) {
            return 1;
        } else if (opcode == com.uzmap.pkg.uzsocket.d.d.aemnu.c) {
            return 2;
        } else if (opcode == com.uzmap.pkg.uzsocket.d.d.aemnu.f) {
            return 8;
        } else if (opcode == com.uzmap.pkg.uzsocket.d.d.aemnu.d) {
            return 9;
        } else if (opcode == com.uzmap.pkg.uzsocket.d.d.aemnu.e) {
            return 10;
        } else {
            throw new RuntimeException("Don't know how to handle " + opcode.toString());
        }
    }

    private String a(String in) {
        String seckey = in.trim();
        String acc = seckey + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";

        MessageDigest sh1;
        try {
            sh1 = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException var6) {
            throw new RuntimeException(var6);
        }

        return com.uzmap.pkg.uzsocket.h.a.a(sh1.digest(acc.getBytes()));
    }

    public com.uzmap.pkg.uzsocket.e.b a(com.uzmap.pkg.uzsocket.e.b request) {
        request.a("Upgrade", "websocket");
        request.a("Connection", "Upgrade");
        request.a("Sec-WebSocket-Version", "8");
        byte[] random = new byte[16];
        this.i.nextBytes(random);
        request.a("Sec-WebSocket-Key", com.uzmap.pkg.uzsocket.h.a.a(random));
        return request;
    }

    public com.uzmap.pkg.uzsocket.e.c a(com.uzmap.pkg.uzsocket.e.a request, i response) throws com.uzmap.pkg.uzsocket.c.d {
        response.a("Upgrade", "websocket");
        response.a("Connection", request.b("Connection"));
        response.a("Switching Protocols");
        String seckey = request.b("Sec-WebSocket-Key");
        if (seckey == null) {
            throw new com.uzmap.pkg.uzsocket.c.d("missing Sec-WebSocket-Key");
        } else {
            response.a("Sec-WebSocket-Accept", this.a(seckey));
            return response;
        }
    }

    private byte[] a(long val, int bytecount) {
        byte[] buffer = new byte[bytecount];
        int highest = 8 * bytecount - 8;

        for (int i = 0; i < bytecount; ++i) {
            buffer[i] = (byte) ((int) (val >>> highest - 8 * i));
        }

        return buffer;
    }

    private com.uzmap.pkg.uzsocket.d.d.aemnu a(byte opcode) throws com.uzmap.pkg.uzsocket.c.c {
        switch (opcode) {
            case 0:
                return com.uzmap.pkg.uzsocket.d.d.aemnu.a;
            case 1:
                return com.uzmap.pkg.uzsocket.d.d.aemnu.b;
            case 2:
                return com.uzmap.pkg.uzsocket.d.d.aemnu.c;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            default:
                throw new com.uzmap.pkg.uzsocket.c.c("unknow optcode " + (short) opcode);
            case 8:
                return com.uzmap.pkg.uzsocket.d.d.aemnu.f;
            case 9:
                return com.uzmap.pkg.uzsocket.d.d.aemnu.d;
            case 10:
                return com.uzmap.pkg.uzsocket.d.d.aemnu.e;
        }
    }

    public List<com.uzmap.pkg.uzsocket.d.d> c(ByteBuffer buffer) throws com.uzmap.pkg.uzsocket.c.b {
        List<com.uzmap.pkg.uzsocket.d.d> frames = new LinkedList();
        com.uzmap.pkg.uzsocket.d.d cur;
        int pref;
        if (this.g != null) {
            try {
                buffer.mark();
                int available_next_byte_count = buffer.remaining();
                pref = this.g.remaining();
                if (pref > available_next_byte_count) {
                    this.g.put(buffer.array(), buffer.position(), available_next_byte_count);
                    buffer.position(buffer.position() + available_next_byte_count);
                    return Collections.emptyList();
                }

                this.g.put(buffer.array(), buffer.position(), pref);
                buffer.position(buffer.position() + pref);
                cur = this.e((ByteBuffer) this.g.duplicate().position(0));
                frames.add(cur);
                this.g = null;
            } catch (com.uzmap.pkg.uzsocket.b.b.a var7) {
                pref = this.g.limit();
                ByteBuffer extendedframe = ByteBuffer.allocate(this.a(var7.a()));

                assert extendedframe.limit() > this.g.limit();

                this.g.rewind();
                extendedframe.put(this.g);
                this.g = extendedframe;
                return this.c(buffer);
            }
        }

        while (buffer.hasRemaining()) {
            buffer.mark();

            try {
                cur = this.e(buffer);
                frames.add(cur);
            } catch (com.uzmap.pkg.uzsocket.b.b.a var8) {
                buffer.reset();
                pref = var8.a();
                this.g = ByteBuffer.allocate(this.a(pref));
                this.g.put(buffer);
                break;
            }
        }

        return frames;
    }

    public com.uzmap.pkg.uzsocket.d.d e(ByteBuffer buffer) throws com.uzmap.pkg.uzsocket.b.b.a, com.uzmap.pkg.uzsocket.c.b {
        int maxpacketsize = buffer.remaining();
        int realpacketsize = 2;
        if (maxpacketsize < realpacketsize) {
            throw new com.uzmap.pkg.uzsocket.b.b.a(realpacketsize);
        } else {
            byte b1 = buffer.get();
            boolean FIN = b1 >> 8 != 0;
            byte rsv = (byte) ((b1 & 127) >> 4);
            if (rsv != 0) {
                throw new com.uzmap.pkg.uzsocket.c.c("bad rsv " + rsv);
            } else {
                byte b2 = buffer.get();
                boolean MASK = (b2 & -128) != 0;
                int payloadlength = (byte) (b2 & 127);
                com.uzmap.pkg.uzsocket.d.d.aemnu optcode = this.a((byte) (b1 & 15));
                if (!FIN && (optcode == com.uzmap.pkg.uzsocket.d.d.aemnu.d || optcode == com.uzmap.pkg.uzsocket.d.d.aemnu.e || optcode == com.uzmap.pkg.uzsocket.d.d.aemnu.f)) {
                    throw new com.uzmap.pkg.uzsocket.c.c("control frames may no be fragmented");
                } else {
                    if (payloadlength < 0 || payloadlength > 125) {
                        if (optcode == com.uzmap.pkg.uzsocket.d.d.aemnu.d || optcode == com.uzmap.pkg.uzsocket.d.d.aemnu.e || optcode == com.uzmap.pkg.uzsocket.d.d.aemnu.f) {
                            throw new com.uzmap.pkg.uzsocket.c.c("more than 125 octets");
                        }

                        byte[] bytes;
                        if (payloadlength == 126) {
                            realpacketsize += 2;
                            if (maxpacketsize < realpacketsize) {
                                throw new com.uzmap.pkg.uzsocket.b.b.a(realpacketsize);
                            }

                            bytes = new byte[]{0, buffer.get(), buffer.get()};
                            payloadlength = (new BigInteger(bytes)).intValue();
                        } else {
                            realpacketsize += 8;
                            if (maxpacketsize < realpacketsize) {
                                throw new com.uzmap.pkg.uzsocket.b.b.a(realpacketsize);
                            }

                            bytes = new byte[8];

                            for (int i = 0; i < 8; ++i) {
                                bytes[i] = buffer.get();
                            }

                            long length = (new BigInteger(bytes)).longValue();
                            if (length > 2147483647L) {
                                throw new com.uzmap.pkg.uzsocket.c.e("Payloadsize is to big...");
                            }

                            payloadlength = (int) length;
                        }
                    }

                    realpacketsize += MASK ? 4 : 0;
                    realpacketsize += payloadlength;
                    if (maxpacketsize < realpacketsize) {
                        throw new com.uzmap.pkg.uzsocket.b.b.a(realpacketsize);
                    } else {
                        ByteBuffer payload = ByteBuffer.allocate(this.a(payloadlength));
                        if (MASK) {
                            byte[] maskskey = new byte[4];
                            buffer.get(maskskey);

                            for (int i = 0; i < payloadlength; ++i) {
                                payload.put((byte) (buffer.get() ^ maskskey[i % 4]));
                            }
                        } else {
                            payload.put(buffer.array(), buffer.position(), payload.limit());
                            buffer.position(buffer.position() + payload.limit());
                        }

                        Object frame;
                        if (optcode == com.uzmap.pkg.uzsocket.d.d.aemnu.f) {
                            frame = new com.uzmap.pkg.uzsocket.d.b();
                        } else {
                            frame = new com.uzmap.pkg.uzsocket.d.e();
                            ((com.uzmap.pkg.uzsocket.d.c) frame).a(FIN);
                            ((com.uzmap.pkg.uzsocket.d.c) frame).a(optcode);
                        }

                        payload.flip();
                        ((com.uzmap.pkg.uzsocket.d.c) frame).a(payload);
                        return (com.uzmap.pkg.uzsocket.d.d) frame;
                    }
                }
            }
        }
    }

    public void a() {
        this.g = null;
    }

    public com.uzmap.pkg.uzsocket.b.a c() {
        return new com.uzmap.pkg.uzsocket.b.b();
    }

    public aemnu b() {
        return com.uzmap.pkg.uzsocket.b.a.aemnu.c;
    }

    private class a extends Throwable {
        private static final long serialVersionUID = 7330519489840500997L;
        private final int b;

        public a(int preferedsize) {
            this.b = preferedsize;
        }

        public int a() {
            return this.b;
        }
    }
}
