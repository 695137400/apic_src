package com.uzmap.pkg.uzkit.fineHttp;

import android.text.TextUtils;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class s extends r implements j {
    private final JSONObject m = new JSONObject();
    private long n;

    public s(RequestParam argument) {
        super(argument);
    }

    public String a() {
        String result = "";
        if (this.e) {
            return result;
        } else if (this.g.url == null) {
            this.a(new i("url is invalid"));
            return result;
        } else {
            String curUrl;
            if (this.i && this.h != null) {
                curUrl = this.h;
            } else {
                curUrl = this.g.url;
            }

            curUrl = this.b(curUrl);
            if (curUrl.startsWith("https")) {
                this.a = com.uzmap.pkg.uzkit.b.b.a(this.g);
            } else {
                this.a = com.uzmap.pkg.uzkit.b.b.b(this.g);
            }

            if (this.a == null) {
                this.a(new i("client is empty!"));
                return result;
            } else {
                HttpEntity entity = null;
                if (this.g.onlyStream()) {
                    entity = this.i();
                } else if (this.g.onlyValue()) {
                    entity = this.h();
                } else if (this.g.onlyBody()) {
                    entity = this.k();
                } else if (this.g.multi()) {
                    entity = this.j();
                }

                try {
                    this.c = new HttpPost(curUrl);
                } catch (Exception var18) {
                    this.a(new i("url is invalid"));
                    return result;
                }

                if (entity != null) {
                    ((HttpPost) this.c).setEntity(entity);
                }

                String cookie = UZCoreUtil.getCookie(curUrl);
                if (cookie != null) {
                    this.c.addHeader("Cookie", cookie);
                }

                this.g();
                int responseCode = 0;
                boolean success = true;
                JSONObject headers = null;

                try {
                    HttpResponse response = this.a.execute(this.c);
                    responseCode = response.getStatusLine().getStatusCode();
                    headers = this.a(response.getAllHeaders());
                    this.a(curUrl, response);
                    switch (responseCode) {
                        case 200:
                        case 201:
                        case 202:
                        case 203:
                        case 204:
                        case 205:
                        case 206:
                        case 207:
                            HttpEntity httpEntity = response.getEntity();
                            result = this.a(httpEntity);
                            httpEntity.consumeContent();
                            break;
                        case 301:
                        case 302:
                        case 307:
                            Header location = response.getFirstHeader("Location");
                            String reUrl = location.getValue();
                            if (reUrl != null && reUrl.length() > 0) {
                                this.h = reUrl;
                                this.i = true;
                                result = this.a();
                                String var14 = result;
                                return var14;
                            }
                            break;
                        default:
                            success = false;
                            String erBody = this.a(response.getEntity());
                            if (!TextUtils.isEmpty(erBody)) {
                                result = erBody;
                            } else {
                                result = "网络无法连接，请检查网络配置";
                            }
                    }
                } catch (Exception var19) {
                    var19.printStackTrace();
                    result = "网络无法连接，请检查网络配置";
                    success = false;
                } finally {
                    this.d();
                }

                if (this.g.report) {
                    this.a(responseCode, result, success);
                    return result;
                } else {
                    Response response = new Response(responseCode);
                    response.setHeaders(headers);
                    if (response.success()) {
                        response.setContent(result);
                    } else {
                        response.setError(result);
                    }

                    this.a(response);
                    return result;
                }
            }
        }
    }

    protected HttpEntity h() {
        HttpEntity entry = null;
        List<g> values = this.g.values;
        List<BasicNameValuePair> formData = new ArrayList();
        Iterator var5 = values.iterator();

        while (var5.hasNext()) {
            g pair = (g) var5.next();
            formData.add(new BasicNameValuePair(pair.a(), pair.b()));
        }

        try {
            entry = new UrlEncodedFormEntity(formData, "UTF-8");
        } catch (UnsupportedEncodingException var6) {
            var6.printStackTrace();
        }

        return entry;
    }

    protected HttpEntity i() {
        InputStreamEntity entry = null;

        try {
            List<g> files = this.g.files;
            g first = files.get(0);
            String value = first.b();
            if (TextUtils.isEmpty(value)) {
                return entry;
            }

            value = value.replaceFirst("file://", "");
            File file = new File(value);
            if (!file.exists()) {
                return entry;
            }

            FileInputStream instream = new FileInputStream(file);
            entry = new InputStreamEntity(instream, file.length());
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return entry;
    }

    protected HttpEntity j() {
        h multiEn = null;

        try {
            multiEn = new h();
            multiEn.a(this);
            List<g> temps = this.g.values;
            g pair;
            Iterator var4;
            if (temps != null) {
                var4 = temps.iterator();

                while (var4.hasNext()) {
                    pair = (g) var4.next();
                    a body = new f(pair.a(), pair.b());
                    multiEn.a(body);
                }
            }

            temps = this.g.files;
            if (temps != null) {
                var4 = temps.iterator();

                while (var4.hasNext()) {
                    pair = (g) var4.next();
                    String key = pair.a();
                    String value = pair.b();
                    if (!TextUtils.isEmpty(value)) {
                        value = value.replaceFirst("file://", "");
                        File file = new File(value);
                        if (file.exists()) {
                            String mimeType = this.c(value);
                            a body = new c(key, file, mimeType, null);
                            multiEn.a(body);
                        }
                    }
                }
            }

            return multiEn;
        } catch (Exception var10) {
            var10.printStackTrace();
            return null;
        }
    }

    protected HttpEntity k() {
        StringEntity entry = null;

        try {
            entry = new StringEntity(this.g.body.toString(), "UTF-8");
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return entry;
    }

    public void a(double newProgress) {
        if (this.g.report) {
            long now = System.currentTimeMillis();
            long step = now - this.n;
            if (step <= 400L) {
                return;
            }

            this.n = now;

            try {
                this.m.put("progress", newProgress);
                this.m.put("status", 0);
                this.m.put("state", 0);
                this.m.put("body", "");
            } catch (Exception var8) {
                var8.printStackTrace();
            }

            this.a(0, this.m);
        }

    }

    private void a(int statusCode, String body, boolean success) {
        if (success) {
            try {
                this.m.put("progress", 100);
                this.m.put("status", 0);
                this.m.put("state", 0);
                this.m.put("body", "");
            } catch (Exception var8) {
                var8.printStackTrace();
            }

            this.a(0, this.m);
        }

        Object b = body;

        try {
            b = new JSONObject(body);
        } catch (Exception var7) {
        }

        try {
            int status = success ? 1 : 2;
            this.m.put("progress", 100);
            this.m.put("status", status);
            this.m.put("state", status);
            this.m.put("body", b);
            this.m.put("msg", b);
            this.m.put("statusCode", statusCode);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        this.a(1, this.m);
    }
}
