//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b.d1;

import android.text.TextUtils;
import com.uzmap.pkg.a.b.i;
import com.uzmap.pkg.a.b.j;
import com.uzmap.pkg.a.b.l;
import com.uzmap.pkg.a.b.o;
import com.uzmap.pkg.a.b.p;
import com.uzmap.pkg.a.b.l.a;
import com.uzmap.pkg.a.b.l.b;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzkit.request.HttpResult;
import com.uzmap.pkg.uzkit.request.RequestCallback;
import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.Map;
import javax.net.ssl.SSLSocketFactory;

public abstract class d extends j<i> implements a {
    protected RequestCallback a;
    protected b<i> b;
    private String c;
    private String d;
    private String e;
    private boolean f;
    private boolean gg;
    private boolean h;
    private Hashtable<String, String> i;
    private g j;

    public d(int method, String url) {
        super(method, url);
        this.setErrorListener(this);
        this.initDefault();
        this.j = new g();
        this.setRetryPolicy(this.j);
    }

    protected l<i> parseNetworkResponse(i response) {
        p.a(" >>>>>> parseNetworkResponse", (Object)null);
        return l.a(response, com.uzmap.pkg.a.b.c1.e.a(response));
    }

    protected void deliverResponse(i response) {
        p.a("deliverResponse", response);
        if (this.a != null) {
            HttpResult result = new HttpResult(response.a);
            result.headers = response.c;
            result.data = response.a();
            this.a.onFinish(result);
        }

        if (this.b != null) {
            this.b.a(response);
        }

    }

    protected void deliverProgress(long contentlength, long finishlength) {
        if (this.needReport() && this.a != null) {
            double percent = 0.0D;
            if (contentlength > 0L) {
                float pro = (float)finishlength / (float)contentlength * 100.0F;
                if (pro > 99.99F && pro < 100.0F) {
                    return;
                }

                BigDecimal b = new BigDecimal((double)pro);
                percent = b.setScale(2, 4).doubleValue();
            }

            p.b("percent: " + finishlength + "," + contentlength + "," + percent);
            this.a.onProgress(contentlength, percent);
        }

    }

    public void onErrorResponse(o error) {
        p.a("onErrorResponse", error);
        if (this.shouldCache()) {
            com.uzmap.pkg.a.b.a.a entry = this.getCacheEntry();
            if (entry != null) {
                p.c("Request Error, Return will from Cache <<<< ");
                i response = new i(304, entry.a, entry.g, true, error.a());
                this.deliverResponse(response);
                return;
            }
        }

        if (this.a != null) {
            int errorType = error.b();
            String des = error.c();
            int statusCode = 0;
            Map<String, String> headers = null;
            if (error.a != null) {
                statusCode = error.a.a;
                des = error.a.a();
                headers = error.a.c;
            }

            HttpResult result = new HttpResult(statusCode);
            result.data = des;
            result.headers = (Map)(headers != null ? headers : new Hashtable());
            result.setErrorType(errorType);
            this.a.onFinish(result);
        }

        if (this.b != null) {
            this.b.a(error.a);
        }

    }

    public Map<String, String> getHeaders() throws com.uzmap.pkg.a.b.a1.a {
        return this.i;
    }

    public void cancel() {
        super.cancel();
    }

    public final SSLSocketFactory getSslSocketFactory() {
        return !TextUtils.isEmpty(this.d) && !TextUtils.isEmpty(this.e) ? com.uzmap.pkg.a.b.b1.b.a(this.d, this.e) : com.uzmap.pkg.a.b.b1.b.a();
    }

    public void addHeader(String key, String value) {
        if (!TextUtils.isEmpty(key)) {
            this.i.put(key, value);
        }

    }

    public void addHeader(Map<String, String> headers) {
        if (headers != null) {
            this.i.putAll(headers);
        }

    }

    public void setTimeout(int time) {
        this.j.a(time);
    }

    public void setCertificate(String certPath, String certPsw) {
        this.d = certPath;
        this.e = certPsw;
    }

    public void setCharset(String charset) {
        this.c = charset;
    }

    public String getCharset() {
        return this.c;
    }

    public void setNeedEscape(boolean flag) {
        this.f = flag;
    }

    public boolean needEscape() {
        return this.f;
    }

    public void setNeedErrorInfo(boolean flag) {
        this.gg = flag;
    }

    public boolean needErrorInfo() {
        return this.gg;
    }

    public void setNeedReport(boolean flag) {
        this.h = flag;
    }

    public boolean needReport() {
        return this.h;
    }

    public void setSyncListener(b<i> listener) {
        this.b = listener;
    }

    public void setCallback(RequestCallback callback) {
        this.a = callback;
    }

    private void initDefault() {
        this.setDeliverInThread(true);
        this.i = new Hashtable();
        this.i.put("Accept", "*/*");
        this.i.put("Charset", "UTF-8");
        this.i.put("User-Agent", UZCoreUtil.getDefaultUserAgent());
        this.i.put("Accept-Encoding", "gzip, deflate");
        this.i.put("Connection", "Keep-Alive");
    }
}
