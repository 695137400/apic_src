package com.uzmap.pkg.uzkit.a.aa;

import android.text.TextUtils;
import com.uzmap.pkg.uzcore.uzmodule.ApiConfig;
import com.uzmap.pkg.uzkit.fineHttp.RequestListener;
import com.uzmap.pkg.uzkit.fineHttp.RequestParam;
import com.uzmap.pkg.uzkit.fineHttp.Response;
import com.uzmap.pkg.uzkit.fineHttp.UZHttpClient;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

class c {
    static int a = 0;
    static SimpleDateFormat b = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final e c;

    public c(e analyticsStorage) {
        this.c = analyticsStorage;
    }

    public final void a(ApiConfig wgtInfo) {
        if (wgtInfo != null) {
            String behavior = this.c.e();
            String eventInfo = this.c.f();
            if (behavior != null || eventInfo != null) {
                com.uzmap.pkg.uzkit.a.c deviceInfo = com.uzmap.pkg.uzkit.a.c.a();
                String widgetId = wgtInfo.g();
                String host = com.uzmap.pkg.uzkit.a.c.b;
                RequestParam argument = new RequestParam();
                argument.setUrl(host);
                argument.setMethod(1);
                argument.setResultDataType(0);
                argument.setEscape(false);
                argument.setInSecure(widgetId);
                argument.setRqValue("systemType", deviceInfo.p);
                argument.setRqValue("appVersion", deviceInfo.k);
                argument.setRqValue("model", deviceInfo.q);
                argument.setRqValue("systemVersion", deviceInfo.o);
                argument.setRqValue("reportTime", this.a(System.currentTimeMillis()));
                argument.setRqValue("behaviorInfo", behavior);
                if (!TextUtils.isEmpty(eventInfo)) {
                    argument.setRqValue("eventInfo", eventInfo);
                }

                UZHttpClient.get().execute(argument, new c.a());
            }
        }
    }

    void a(double lat, double log, String widgetId) {
        String host = com.uzmap.pkg.uzkit.a.c.c;
        RequestParam argument = new RequestParam();
        argument.setUrl(host);
        argument.setMethod(1);
        argument.setEscape(false);
        argument.setResultDataType(0);
        argument.setInSecure(widgetId);
        com.uzmap.pkg.uzkit.a.c deviceInfo = com.uzmap.pkg.uzkit.a.c.a();
        argument.setRqValue("systemType", deviceInfo.p);
        argument.setRqValue("appVersion", deviceInfo.k);
        argument.setRqValue("longitude", "" + log);
        argument.setRqValue("latitude", "" + lat);
        argument.setRqValue("reportTime", "" + System.currentTimeMillis());
        RequestListener callback = new RequestListener() {
            public void onResult(Response result) {
                if (result.success()) {
                    c.this.a("Geo ok! result: " + result);
                } else {
                    c.this.b("Geo faild! result: " + result);
                }

            }
        };
        UZHttpClient.get().execute(argument, callback);
    }

    String a(long time) {
        String result = "";

        try {
            result = b.format(time);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return result;
    }

    private void a(String msg) {
        com.uzmap.pkg.uzkit.a.b.a(msg != null ? msg : "");
    }

    private void b(String msg) {
        com.uzmap.pkg.uzkit.a.b.a(msg != null ? msg : "");
    }

    class a implements RequestListener {
        private final int b;

        public a() {
            ++com.uzmap.pkg.uzkit.a.aa.c.a;
            this.b = com.uzmap.pkg.uzkit.a.aa.c.a;
        }

        public void onResult(Response result) {
            if (result.success()) {
                c.this.a("analysis ok! result: " + result + " , " + this.b);
                JSONObject json = com.uzmap.pkg.uzkit.a.a.a(result.content);
                if (json == null) {
                    return;
                }

                int status = json.optInt("status");
                if (1 == status) {
                    c.this.c.g();
                }
            } else {
                c.this.b("analysis faild! result: " + result + " , " + this.b);
            }

        }
    }
}
