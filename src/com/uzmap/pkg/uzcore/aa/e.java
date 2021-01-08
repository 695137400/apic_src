//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzcore.aa;

import android.support.v4.util.LruCache;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzkit.UZUtility;
import java.io.IOException;
import java.io.InputStream;

public class e extends LruCache<String, f> {
    private static e a;

    public e() throws IOException {
        super(2097152);
    }

    public static e a() {
        if (a == null) {
            try {
                a = new e();
            } catch (IOException var1) {
                var1.printStackTrace();
            }
        }

        return a;
    }

    public synchronized byte[] a(String url) {
        url = this.e(url);
        f value = (f)this.get(url);
        return value != null ? value.a : null;
    }

    public synchronized void a(String url, byte[] bt) {
        url = this.e(url);
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

    private String e(String url) {
        String result = "";

        try {
            result = Integer.toHexString(url.hashCode());
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return result;
    }

    public byte[] c(String url) {
        byte[] data = this.d(url);
        if (data != null && data.length > 0) {
            data = j.b(data);
            this.a(url, data);
            return data;
        } else {
            return null;
        }
    }

    public byte[] d(String url) {
        InputStream input = null;

        try {
            byte[] data = null;
            input = UZUtility.guessInputStream(url);
            if (input != null) {
                data = UZCoreUtil.readByte(input);
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
}
