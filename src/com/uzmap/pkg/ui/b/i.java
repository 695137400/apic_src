package com.uzmap.pkg.ui.b;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class i {
    public final int a;
    public final byte[] b;
    public final Map<String, String> c;
    public final boolean d;
    public final long e;

    public i(int statusCode, byte[] data, Map<String, String> headers, boolean notModified, long networkTimeMs) {
        this.a = statusCode;
        this.b = data;
        this.c = headers;
        this.d = notModified;
        this.e = networkTimeMs;
    }

    public i(byte[] data, Map<String, String> headers) {
        this(200, data, headers, false, 0L);
    }

    public String a() {
        String parsed;
        try {
            parsed = new String(this.b, com.uzmap.pkg.ui.b.cc.e.a(this.c));
        } catch (UnsupportedEncodingException var3) {
            parsed = new String(this.b);
        }

        return parsed;
    }

    public String b() {
        String ret = "";
        ret = ret + "[statusCode]=" + this.a + "\n";
        String da = this.a();
        ret = ret + "[data]=" + (da.length() > 300 ? da.substring(0, 300) + "..." + da.length() : da) + "\n";
        ret = ret + "[headers]=" + this.c + "\n";
        ret = ret + "[notModified]=" + this.d + "\n";
        ret = ret + "[networkTimeMs]=" + this.e + "\n";
        return ret;
    }
}
