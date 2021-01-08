package com.uzmap.pkg.uzsocket.h;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.*;

public class b {
    public static CodingErrorAction a;

    static {
        a = CodingErrorAction.REPORT;
    }

    public static byte[] a(String s) {
        return s.getBytes(StandardCharsets.UTF_8);
    }

    public static byte[] b(String s) {
        return s.getBytes(StandardCharsets.US_ASCII);
    }

    public static String a(byte[] bytes, int offset, int length) {
        return new String(bytes, offset, length, StandardCharsets.US_ASCII);
    }

    public static String a(ByteBuffer bytes) throws com.uzmap.pkg.uzsocket.c.b {
        CharsetDecoder decode = StandardCharsets.UTF_8.newDecoder();
        decode.onMalformedInput(a);
        decode.onUnmappableCharacter(a);

        try {
            bytes.mark();
            String s = decode.decode(bytes).toString();
            bytes.reset();
            return s;
        } catch (CharacterCodingException var4) {
            throw new com.uzmap.pkg.uzsocket.c.b(1007, var4);
        }
    }
}
