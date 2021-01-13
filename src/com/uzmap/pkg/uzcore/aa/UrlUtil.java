package com.uzmap.pkg.uzcore.aa;

import android.support.v4.util.LruCache;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzkit.UZUtility;

import java.io.IOException;
import java.io.InputStream;

public class UrlUtil extends LruCache<String, ModuleUrl> {
    private static UrlUtil urlUtil;

    public UrlUtil() throws IOException {
        super(3145728);
    }

    public static UrlUtil getInstance() {
        if (urlUtil == null) {
            try {
                urlUtil = new UrlUtil();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return urlUtil;
    }

    public synchronized byte[] getUrlBit(String url) {
        url = this.urlToBit(url);
        ModuleUrl value = this.get(url);
        return value != null ? value.urlBit : null;
    }

    public synchronized void putUrlBit(String url, byte[] bt) {
        url = this.urlToBit(url);
        ModuleUrl value = new ModuleUrl(bt);
        this.put(url, value);
    }

    private String urlToBit(String url) {
        String result = "";

        try {
            result = Integer.toHexString(url.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public byte[] putUrl(String url) {
        InputStream input = null;

        try {
            byte[] data = null;
            input = UZUtility.guessInputStream(url);
            if (input != null) {
                data = UZCoreUtil.readByte(input);
            }

            if (data != null) {
                this.putUrlBit(url, data);
            }

            byte[] var5 = data;
            return var5;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return null;
    }
}
