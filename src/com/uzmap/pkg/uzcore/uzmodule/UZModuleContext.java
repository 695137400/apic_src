//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzcore.uzmodule;

import android.content.Context;
import android.text.TextUtils;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.a;
import org.json.JSONArray;
import org.json.JSONObject;

public class UZModuleContext {
    private static String a;
    private static String b;
    private int c;
    private int d = -1;
    private a e;
    private JSONObject f;

    static {
        a = com.uzmap.pkg.uzcore.aa.a.a + "$cb";
        b = a + ".on";
    }

    public UZModuleContext() {
    }

    public UZModuleContext(UZWebView webView) {
        this.e = (a)webView;
        if (this.e != null) {
            this.c = this.e.A();
        }

    }

    public UZModuleContext(String json, UZWebView webView) {
        this.e = (a)webView;
        if (this.e != null) {
            this.c = this.e.A();
        }

        this.parse(json);
    }

    private void parse(String jsonParam) {
        if (!TextUtils.isEmpty(jsonParam)) {
            try {
                JSONObject json = new JSONObject(jsonParam);
                this.d = json.optInt("cbId", -1);
                this.f = json.optJSONObject("arg");
            } catch (Exception var3) {
                var3.printStackTrace();
            }

        }
    }

    public final void success(String result, boolean transToJson, boolean deleteJsFunction) {
        if (this.valid()) {
            String script = null;
            if (transToJson) {
                String ret = "{}";
                if (!TextUtils.isEmpty(result)) {
                    ret = UZCoreUtil.transcoding(result);
                }

                ret = "JSON.parse('" + ret + "')";
                script = b + "('" + this.d + "'," + ret + ", ''," + deleteJsFunction + ");";
            } else {
                if (TextUtils.isEmpty(result)) {
                    result = "";
                }

                result = UZCoreUtil.transcoding(result);
                script = b + "('" + this.d + "','" + result + "', ''," + deleteJsFunction + ");";
            }

            this.e.e(script);
        }
    }

    public final void success(JSONObject result, boolean deleteJsFunction) {
        if (this.valid()) {
            String script = null;
            String ret = "{}";
            if (result != null) {
                ret = UZCoreUtil.transcoding(result.toString());
            }

            ret = "JSON.parse('" + ret + "')";
            script = b + "('" + this.d + "'," + ret + ", ''," + deleteJsFunction + ");";
            this.e.e(script);
        }
    }

    public final void error(JSONObject result, JSONObject error, boolean deleteJsFunction) {
        if (this.valid()) {
            String script = null;
            String ret = "''";
            String err = "{}";
            if (error != null) {
                err = UZCoreUtil.transcoding(error.toString());
            }

            err = "JSON.parse('" + err + "')";
            if (result != null) {
                ret = "JSON.parse('" + result.toString() + "')";
            }

            script = b + "('" + this.d + "'," + ret + "," + err + "," + deleteJsFunction + ");";
            this.e.e(script);
        }
    }

    public final void interrupt() {
        if (this.valid()) {
            String script = a + ".gc('" + this.d + "');";
            this.e.e(script);
        }
    }

    private boolean valid() {
        return this.e != null && !this.e.H() && this.d > 0;
    }

    public Context getContext() {
        return this.e.getContext();
    }

    public int getVid() {
        return this.c;
    }

    public final boolean empty() {
        return this.f == null;
    }

    public final int optInt(String key) {
        return this.f != null ? this.f.optInt(key) : 0;
    }

    public final int optInt(String key, int fallback) {
        return this.f != null ? this.f.optInt(key, fallback) : fallback;
    }

    public final long optLong(String key) {
        return this.f != null ? this.f.optLong(key) : 0L;
    }

    public final long optLong(String key, long fallback) {
        return this.f != null ? this.f.optLong(key, fallback) : fallback;
    }

    public final String optString(String key) {
        return this.f != null ? this.f.optString(key) : "";
    }

    public final String optString(String key, String fallback) {
        return this.f != null ? this.f.optString(key, fallback) : fallback;
    }

    public final boolean optBoolean(String key) {
        return this.f != null ? this.f.optBoolean(key) : false;
    }

    public final boolean optBoolean(String key, boolean fallback) {
        return this.f != null ? this.f.optBoolean(key, fallback) : fallback;
    }

    public final double optDouble(String key) {
        return this.f != null ? this.f.optDouble(key) : 0.0D / 0.0;
    }

    public final double optDouble(String key, double fallback) {
        return this.f != null ? this.f.optDouble(key, fallback) : fallback;
    }

    public final JSONObject optJSONObject(String key) {
        return this.f != null ? this.f.optJSONObject(key) : null;
    }

    public final JSONArray optJSONArray(String key) {
        return this.f != null ? this.f.optJSONArray(key) : null;
    }

    public final Object optObject(String key) {
        return this.f != null ? this.f.opt(key) : null;
    }

    public JSONObject get() {
        return this.f;
    }

    public boolean isNull(String name) {
        return this.f != null ? this.f.isNull(name) : true;
    }

    protected final String transcode(String input) {
        char[] c = input.toCharArray();

        for(int i = 0; i < c.length; ++i) {
            if (c[i] == 12288) {
                c[i] = ' ';
            } else if (c[i] > '\uff00' && c[i] < '｟') {
                c[i] -= 'ﻠ';
            }
        }

        return new String(c);
    }

    public String toString() {
        return this.f != null ? this.f.toString() : super.toString();
    }
}
