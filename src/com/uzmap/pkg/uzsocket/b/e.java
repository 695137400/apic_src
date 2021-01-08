package com.uzmap.pkg.uzsocket.b;

import com.uzmap.pkg.uzsocket.e.h;
import com.uzmap.pkg.uzsocket.e.i;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class e extends d {
    private static final byte[] j = new byte[]{-1, 0};
    private final Random k = new Random();
    private final boolean i = false;

    public static byte[] a(String key1, String key2, byte[] key3) throws com.uzmap.pkg.uzsocket.c.d {
        byte[] part1 = a(key1);
        byte[] part2 = a(key2);
        byte[] challenge = new byte[]{part1[0], part1[1], part1[2], part1[3], part2[0], part2[1], part2[2], part2[3], key3[0], key3[1], key3[2], key3[3], key3[4], key3[5], key3[6], key3[7]};

        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException var8) {
            throw new RuntimeException(var8);
        }

        return md5.digest(challenge);
    }

    private static String e() {
        Random r = new Random();
        long maxNumber = 4294967295L;
        long spaces = r.nextInt(12) + 1;
        int max = (new Long(maxNumber / spaces)).intValue();
        max = Math.abs(max);
        int number = r.nextInt(max) + 1;
        long product = (long) number * spaces;
        String key = Long.toString(product);
        int numChars = r.nextInt(12) + 1;

        int i;
        int position;
        for (i = 0; i < numChars; ++i) {
            position = r.nextInt(key.length());
            position = Math.abs(position);
            char randChar = (char) (r.nextInt(95) + 33);
            if (randChar >= '0' && randChar <= '9') {
                randChar = (char) (randChar - 15);
            }

            key = (new StringBuilder(key)).insert(position, randChar).toString();
        }

        for (i = 0; (long) i < spaces; ++i) {
            position = r.nextInt(key.length() - 1) + 1;
            position = Math.abs(position);
            key = (new StringBuilder(key)).insert(position, " ").toString();
        }

        return key;
    }

    private static byte[] a(String key) throws com.uzmap.pkg.uzsocket.c.d {
        try {
            long keyNumber = Long.parseLong(key.replaceAll("[^0-9]", ""));
            long keySpace = key.split(" ").length - 1;
            if (keySpace == 0L) {
                throw new com.uzmap.pkg.uzsocket.c.d("invalid Sec-WebSocket-Key (/key2/)");
            } else {
                long part = new Long(keyNumber / keySpace);
                return new byte[]{(byte) ((int) (part >> 24)), (byte) ((int) (part << 8 >> 24)), (byte) ((int) (part << 16 >> 24)), (byte) ((int) (part << 24 >> 24))};
            }
        } catch (NumberFormatException var7) {
            throw new com.uzmap.pkg.uzsocket.c.d("invalid Sec-WebSocket-Key (/key1/ or /key2/)");
        }
    }

    public bemnu a(com.uzmap.pkg.uzsocket.e.a request, h response) {
        if (this.i) {
            return com.uzmap.pkg.uzsocket.b.a.bemnu.b;
        } else {
            try {
                if (response.b("Sec-WebSocket-Origin").equals(request.b("Origin")) && this.a(response)) {
                    byte[] content = response.c();
                    if (content != null && content.length != 0) {
                        return Arrays.equals(content, a(request.b("Sec-WebSocket-Key1"), request.b("Sec-WebSocket-Key2"), request.c())) ? com.uzmap.pkg.uzsocket.b.a.bemnu.a : com.uzmap.pkg.uzsocket.b.a.bemnu.b;
                    } else {
                        throw new com.uzmap.pkg.uzsocket.c.a();
                    }
                } else {
                    return com.uzmap.pkg.uzsocket.b.a.bemnu.b;
                }
            } catch (com.uzmap.pkg.uzsocket.c.d var4) {
                throw new RuntimeException("bad handshakerequest", var4);
            }
        }
    }

    public bemnu a(com.uzmap.pkg.uzsocket.e.a handshakedata) {
        return handshakedata.b("Upgrade").equals("WebSocket") && handshakedata.b("Connection").contains("Upgrade") && handshakedata.b("Sec-WebSocket-Key1").length() > 0 && !handshakedata.b("Sec-WebSocket-Key2").isEmpty() && handshakedata.c("Origin") ? com.uzmap.pkg.uzsocket.b.a.bemnu.a : com.uzmap.pkg.uzsocket.b.a.bemnu.b;
    }

    public com.uzmap.pkg.uzsocket.e.b a(com.uzmap.pkg.uzsocket.e.b request) {
        request.a("Upgrade", "WebSocket");
        request.a("Connection", "Upgrade");
        request.a("Sec-WebSocket-Key1", e());
        request.a("Sec-WebSocket-Key2", e());
        if (!request.c("Origin")) {
            request.a("Origin", "random" + this.k.nextInt());
        }

        byte[] key3 = new byte[8];
        this.k.nextBytes(key3);
        request.a(key3);
        return request;
    }

    public com.uzmap.pkg.uzsocket.e.c a(com.uzmap.pkg.uzsocket.e.a request, i response) throws com.uzmap.pkg.uzsocket.c.d {
        response.a("WebSocket Protocol Handshake");
        response.a("Upgrade", "WebSocket");
        response.a("Connection", request.b("Connection"));
        response.a("Sec-WebSocket-Origin", request.b("Origin"));
        String location = "ws://" + request.b("Host") + request.a();
        response.a("Sec-WebSocket-Location", location);
        String key1 = request.b("Sec-WebSocket-Key1");
        String key2 = request.b("Sec-WebSocket-Key2");
        byte[] key3 = request.c();
        if (key1 != null && key2 != null && key3 != null && key3.length == 8) {
            response.a(a(key1, key2, key3));
            return response;
        } else {
            throw new com.uzmap.pkg.uzsocket.c.d("Bad keys");
        }
    }

    public com.uzmap.pkg.uzsocket.e.f d(ByteBuffer buf) throws com.uzmap.pkg.uzsocket.c.d {
        com.uzmap.pkg.uzsocket.e.c bui = a(buf, this.d);
        if ((bui.c("Sec-WebSocket-Key1") || this.d == com.uzmap.pkg.uzsocket.a.a.b.a) && !bui.c("Sec-WebSocket-Version")) {
            byte[] key3 = new byte[this.d == com.uzmap.pkg.uzsocket.a.a.b.b ? 8 : 16];

            try {
                buf.get(key3);
            } catch (BufferUnderflowException var5) {
                throw new com.uzmap.pkg.uzsocket.c.a(buf.capacity() + 16);
            }

            bui.a(key3);
        }

        return bui;
    }

    public List<com.uzmap.pkg.uzsocket.d.d> c(ByteBuffer buffer) throws com.uzmap.pkg.uzsocket.c.b {
        buffer.mark();
        List<com.uzmap.pkg.uzsocket.d.d> frames = super.e(buffer);
        if (frames == null) {
            buffer.reset();
            frames = this.g;
            this.f = true;
            if (this.h == null) {
                this.h = ByteBuffer.allocate(2);
                if (buffer.remaining() > this.h.remaining()) {
                    throw new com.uzmap.pkg.uzsocket.c.c();
                } else {
                    this.h.put(buffer);
                    if (!this.h.hasRemaining()) {
                        if (Arrays.equals(this.h.array(), j)) {
                            frames.add(new com.uzmap.pkg.uzsocket.d.b(1000));
                            return frames;
                        } else {
                            throw new com.uzmap.pkg.uzsocket.c.c();
                        }
                    } else {
                        this.g = new LinkedList();
                        return frames;
                    }
                }
            } else {
                throw new com.uzmap.pkg.uzsocket.c.c();
            }
        } else {
            return frames;
        }
    }

    public ByteBuffer a(com.uzmap.pkg.uzsocket.d.d framedata) {
        return framedata.f() == com.uzmap.pkg.uzsocket.d.d.aemnu.f ? ByteBuffer.wrap(j) : super.a(framedata);
    }

    public aemnu b() {
        return com.uzmap.pkg.uzsocket.b.a.aemnu.b;
    }

    public com.uzmap.pkg.uzsocket.b.a c() {
        return new e();
    }
}
