package com.uzmap.pkg.uzsocket.g;

import android.content.Context;
import android.util.Log;
import com.uzmap.pkg.uzapp.UZFileSystem;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzkit.fineHttp.RequestListener;
import com.uzmap.pkg.uzkit.fineHttp.RequestParam;
import com.uzmap.pkg.uzkit.fineHttp.Response;
import com.uzmap.pkg.uzkit.fineHttp.UZHttpClient;
import org.json.JSONObject;

import java.io.File;

public class f {
    public static final boolean a;

    static {
        a = UZCoreUtil.a;
    }

    private boolean b;

    public static void a(String msg) {
        if (a) {
            Log.d("ldx", msg != null ? msg : "");
        }
    }

    public static void b(String msg) {
        if (a) {
            Log.e("ldx", msg != null ? msg : "");
        }
    }

    public void a(String widgetId, final f.a callback) {
        String deviceToken = com.uzmap.pkg.uzapp.b.c(widgetId);
        String systemType = "android";
        String host = com.uzmap.pkg.uzapp.b.f() + "/BindPush";
        RequestParam argument = new RequestParam();
        argument.setUrl(host);
        argument.setMethod(1);
        argument.setResultDataType(0);
        argument.setEscape(false);
        argument.setInSecure(widgetId);
        argument.setRqValue("deviceToken", deviceToken);
        argument.setRqValue("systemType", systemType);
        argument.setRqValue("deviceInfo", (new JSONObject()).toString());
        argument.setTag("bind");
        RequestListener httpBindback = new RequestListener() {
            public void onResult(Response result) {
                f.this.b = false;
                if (callback != null) {
                    callback.a(result.statusCode, result.content);
                }

            }
        };
        this.b = true;
        UZHttpClient.get().execute(argument, httpBindback);
    }

    public boolean a() {
        return this.b;
    }

    public void a(final Context context, final String widgetId) {
        Thread clear = new Thread("#UPns clear data") {
            public void run() {
                String wgtroot = UZFileSystem.get().getWidgetRootPath(widgetId);
                UZCoreUtil.logd("UPns Service Clear Data: " + wgtroot);
                File wgtcache = new File(wgtroot);
                if (wgtcache.exists()) {
                    UZCoreUtil.delete(wgtcache);
                }

                File boxcache = context.getCacheDir();
                if (boxcache != null) {
                    UZCoreUtil.delete(boxcache);
                }

                File extcache = context.getExternalCacheDir();
                if (extcache != null) {
                    UZCoreUtil.delete(extcache);
                }

            }
        };
        clear.start();
    }

    public interface a {
        void a(int var1, String var2);
    }
}
