package com.uzmap.pkg.uzkit.fineHttp;

import android.text.TextUtils;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.json.JSONObject;

final class t extends s {
    public t(RequestParam argument) {
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
                    this.c = new HttpPut(curUrl);
                } catch (Exception var17) {
                    this.a(new i("url is invalid"));
                    return result;
                }

                if (entity != null) {
                    ((HttpPut) this.c).setEntity(entity);
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
                            if (reUrl == null || reUrl.length() <= 0) {
                                break;
                            }

                            this.h = reUrl;
                            this.i = true;
                            result = this.a();
                            String var13 = result;
                            return var13;
                        default:
                            String erBody = this.a(response.getEntity());
                            if (!TextUtils.isEmpty(erBody)) {
                                result = erBody;
                            } else {
                                result = "网络无法连接，请检查网络配置";
                            }
                    }
                } catch (Exception var18) {
                    var18.printStackTrace();
                    result = "网络无法连接，请检查网络配置";
                } finally {
                    this.d();
                }

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
