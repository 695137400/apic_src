package com.uzmap.pkg.uzkit.fineHttp;

import android.text.TextUtils;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Iterator;

public class r extends u<String> {
    protected HttpClient a;
    protected InputStream b;
    protected HttpRequestBase c;

    public r(RequestParam argument) {
        super(argument);
    }

    public String b() {
        this.l();
        return this.a();
    }

    protected String a() {
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
                try {
                    this.c = new HttpHead(curUrl);
                } catch (Exception var15) {
                    this.a(new i("url is invalid"));
                    return result;
                }

                String cookie = UZCoreUtil.getCookie(curUrl);
                if (cookie != null) {
                    this.c.addHeader("Cookie", cookie);
                }

                this.g();
                int responseCode = 0;
                JSONObject headers = null;

                try {
                    HttpResponse response = this.a.execute(this.c);
                    responseCode = response.getStatusLine().getStatusCode();
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
                            headers = this.a(response.getAllHeaders());
                            break;
                        case 301:
                        case 302:
                        case 307:
                            Header location = response.getFirstHeader("Location");
                            String reUrl = location.getValue();
                            if (reUrl == null || reUrl.length() <= 0) {
                                break;
                            }

                            this.h = reUrl;
                            this.i = true;
                            result = this.a();
                            String var11 = result;
                            return var11;
                        default:
                            String erBody = this.a(response.getEntity());
                            if (!TextUtils.isEmpty(erBody)) {
                                result = erBody;
                            } else {
                                result = "网络无法连接，请检查网络配置";
                            }
                    }
                } catch (Exception var16) {
                    var16.printStackTrace();
                    result = "网络无法连接，请检查网络配置";
                } finally {
                    this.d();
                }

                Response response = new Response(responseCode);
                response.setHeaders(headers);
                if (response.success()) {
                    response.setContent(headers.toString());
                } else {
                    response.setError(result);
                }

                this.a(response);
                return result;
            }
        }
    }

    public void c() {
        this.e = true;
        this.d();
    }

    public void d() {
        if (this.a != null) {
            if (this.b != null) {
                try {
                    this.b.close();
                } catch (Exception var3) {
                }
            }

            if (this.c != null) {
                try {
                    this.c.abort();
                } catch (Exception var2) {
                }
            }

        }
    }

    protected String a(HttpEntity httpEntity) throws Exception {
        String result = "";
        if (httpEntity == null) {
            return result;
        } else {
            Charset set = this.b(httpEntity);
            int contentLength = (int) httpEntity.getContentLength();
            Header contentEncoding = httpEntity.getContentEncoding();
            boolean gzip = false;
            if (contentEncoding != null) {
                gzip = "gzip".equalsIgnoreCase(contentEncoding.getValue());
            }

            this.b = httpEntity.getContent();
            byte[] content = this.a(this.b, contentLength, gzip);
            boolean containsBom = this.a(content, set);
            if (!containsBom) {
                result = new String(content, set);
            } else {
                result = new String(content, 3, content.length - 3, set);
            }

            return result;
        }
    }

    protected void a(String url, HttpResponse response) {
        if (response != null) {
            Header[] setCookie = response.getHeaders("Set-Cookie");
            int var6;
            Header cokie;
            int var13;
            Header[] var15;
            String str;
            if (setCookie != null && setCookie.length > 0) {
                var15 = setCookie;
                var6 = setCookie.length;

                for (var13 = 0; var13 < var6; ++var13) {
                    cokie = var15[var13];
                    str = cokie.getValue();
                    UZCoreUtil.setCookie(url, str);
                }

            } else {
                setCookie = response.getHeaders("set-cookie");
                if (setCookie != null && setCookie.length > 0) {
                    var15 = setCookie;
                    var6 = setCookie.length;

                    for (var13 = 0; var13 < var6; ++var13) {
                        cokie = var15[var13];
                        str = cokie.getValue();
                        UZCoreUtil.setCookie(url, str);
                    }

                } else {
                    Header[] cookie = response.getHeaders("Cookie");
                    int var7;
                    Header[] var8;
                    if (cookie != null && cookie.length > 0) {
                        var8 = cookie;
                        var7 = cookie.length;

                        for (var6 = 0; var6 < var7; ++var6) {
                            cokie = var8[var6];
                            str = cokie.getValue();
                            UZCoreUtil.setCookie(url, str);
                        }
                    } else {
                        cookie = response.getHeaders("cookie");
                        if (cookie != null && cookie.length > 0) {
                            var8 = cookie;
                            var7 = cookie.length;

                            for (var6 = 0; var6 < var7; ++var6) {
                                cokie = var8[var6];
                                str = cokie.getValue();
                                UZCoreUtil.setCookie(url, str);
                            }

                            return;
                        }
                    }

                    Header[] cookie2 = response.getHeaders("Cookie2");
                    if (cookie2 != null && cookie2.length > 0) {
                        Header[] var18 = cookie2;
                        int var16 = cookie2.length;

                        for (var7 = 0; var7 < var16; ++var7) {
                            cokie = var18[var7];
                            str = cokie.getValue();
                            UZCoreUtil.setCookie(url, str);
                        }

                    }
                }
            }
        }
    }

    protected Charset b(HttpEntity entity) {
        Charset set = d;
        String setStr = "";

        try {
            setStr = EntityUtils.getContentCharSet(entity);
        } catch (Exception var6) {
        }

        if (TextUtils.isEmpty(setStr)) {
            setStr = this.g.charset;
        }

        if (!TextUtils.isEmpty(setStr)) {
            try {
                set = Charset.forName(setStr);
            } catch (Exception var5) {
            }
        }

        return set;
    }

    protected void g() {
        if (this.c != null) {
            if (this.g.heads != null) {
                this.k.putAll(this.g.heads);
            }

            Iterator var2 = this.k.keySet().iterator();

            while (var2.hasNext()) {
                String key = (String) var2.next();
                String value = this.k.get(key);
                this.c.addHeader(key, value);
            }
        }

    }

    // $FF: synthetic method
    public String e() {
        return this.b();
    }

    // $FF: synthetic method
    protected String f() {
        return this.a();
    }
}
