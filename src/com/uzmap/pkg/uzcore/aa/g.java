package com.uzmap.pkg.uzcore.aa;

import android.support.v4.util.LruCache;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzkit.UZUtility;

import java.io.IOException;
import java.io.InputStream;

public class g extends LruCache<String, f> {
    private static g a;

    public g() throws IOException {
        super(3145728);
    }

    public static g a() {
        if (a == null) {
            try {
                a = new g();
            } catch (IOException var1) {
                var1.printStackTrace();
            }
        }

        return a;
    }

    public synchronized byte[] a(String url) {
        url = this.d(url);
        f value = this.get(url);
        return value != null ? value.a : null;
    }

    public synchronized void a(String url, byte[] bt) {
        url = this.d(url);
        f value = new f(bt);
        this.put(url, value);
    }

    protected f b(String key) {
        return null;
    }

    protected void a(boolean evicted, String key, f oldValue, f newValue) {
    }

    protected int a(String key, f value) {
        return value == null ? 0 : value.a();
    }

    private String d(String url) {
        String result = "";

        try {
            result = Integer.toHexString(url.hashCode());
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return result;
    }

    public byte[] c(String url) {
        InputStream input = null;

        try {
            byte[] data = null;
            input = UZUtility.guessInputStream(url);
            if (input != null) {
                data = UZCoreUtil.readByte(input);
            }

            if (data != null) {
                this.a(url, data);
            }

            byte[] var5 = data;
            return var5;
        } catch (IOException var13) {
            var13.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException var12) {
                    var12.printStackTrace();
                }
            }

        }

        return null;
    }

    // $FF: synthetic method
    protected int sizeOf(String var1, Object var2) {
        return this.a(var1, (f) var2);
    }

    // $FF: synthetic method
    protected void entryRemoved(boolean var1, String var2, Object var3, Object var4) {
        this.a(var1, var2, (f) var3, (f) var4);
    }

    // $FF: synthetic method
    protected f create(String var1) {
        return this.b(var1);
    }
}
