package com.uzmap.pkg.uzkit.fineHttp;

import android.text.TextUtils;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

class o extends u<String> {
    private URL a;
    private InputStream b;
    private HttpURLConnection c;
    private OutputStream m;
    private final boolean n;
    private final JSONObject o;
    private String p;
    private long q;

    public o(RequestParam args) {
        super(args);
        this.n = args.report;
        this.o = new JSONObject();
    }

    public String a() {
        this.l();
        return this.b();
    }

    public String b() {
        String result = "";
        if (this.e) {
            return result;
        } else {
            int statusCode = 0;
            if (TextUtils.isEmpty(this.g.url)) {
                this.a(statusCode, "download url is empty!");
                return result;
            } else {
                String sp = this.g.savePath;
                if (!TextUtils.isEmpty(sp)) {
                    File filePath = new File(sp);
                    if (filePath.exists()) {
                        if (this.g.cache) {
                            this.a((int) filePath.length(), null, UZCoreUtil.getMimeType(sp));
                            return result;
                        }

                        filePath.delete();
                    }

                    this.p = sp + ".tmp";
                } else {
                    this.p = this.g.defaultSavePath + this.g.makeTmpFileName();
                }

                String onceurl;
                if (this.i && this.h != null) {
                    onceurl = this.h;
                } else {
                    onceurl = this.g.url;
                }

                onceurl = this.b(onceurl);
                File oldtmp = new File(this.p);
                int range = 0;
                if (oldtmp.exists() && this.g.allowResume) {
                    range = (int) oldtmp.length();
                }

                boolean https = false;

                try {
                    this.a = new URL(onceurl);
                    this.c = (HttpURLConnection) this.a.openConnection();
                    this.c.setRequestMethod("GET");
                    String cookie = null;
                    cookie = UZCoreUtil.getCookie(onceurl);
                    if (cookie != null) {
                        this.c.setRequestProperty("Cookie", cookie);
                    }

                    if (this.g.allowResume) {
                        this.c.setRequestProperty("Range", "bytes=" + range + "-");
                    }

                    this.g();
                    this.c.setUseCaches(false);
                    this.c.setReadTimeout(this.g.timeout);
                    this.c.setConnectTimeout(this.g.timeout);
                    this.c.setInstanceFollowRedirects(false);
                    this.c.connect();
                    statusCode = this.c.getResponseCode();
                    Map<String, List<String>> headers = this.c.getHeaderFields();
                    this.a(onceurl, headers);
                    String var18;
                    switch (statusCode) {
                        case 200:
                        case 206:
                            String contentType = this.c.getHeaderField("Content-Type");
                            String extension = "." + UZCoreUtil.getExtension(contentType);
                            String newSavePath = "";
                            if (this.g.hasSavePath()) {
                                newSavePath = this.p.replace(".tmp", "");
                            } else {
                                newSavePath = this.p.replace(".tmp", extension);
                            }

                            this.g.savePath = newSavePath;
                            File filePath = new File(newSavePath);
                            if (filePath.exists() && this.g.cache) {
                                this.a((int) filePath.length(), null, contentType);
                                var18 = result;
                                return var18;
                            }

                            filePath = new File(this.p);
                            File parent = filePath.getParentFile();
                            if (parent != null && !parent.exists()) {
                                parent.mkdirs();
                            }

                            if (!this.g.allowResume && filePath.exists()) {
                                filePath.delete();
                            }

                            int cLength = this.a(filePath, contentType, range);
                            if (cLength >= 0) {
                                var18 = result;
                                return var18;
                            }

                            result = "io error!";
                            break;
                        case 301:
                        case 302:
                        case 307:
                            List<String> urls = headers.get("Location");
                            if (urls != null && urls.size() > 0) {
                                this.h = urls.get(0);
                                this.i = true;
                                this.b();
                                var18 = result;
                                return var18;
                            }
                            break;
                        case 401:
                            result = "unauthorized";
                            break;
                        default:
                            result = "error:" + statusCode;
                    }
                } catch (Exception var21) {
                    var21.printStackTrace();
                    if (var21 instanceof IOException && https) {
                        result = "unauthorized!";
                    } else {
                        result = "net work error or timeout!";
                    }
                } finally {
                    this.d();
                }

                this.a(statusCode, result);
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
            try {
                if (this.b != null) {
                    this.b.close();
                }

                if (this.c != null) {
                    this.c.disconnect();
                }
            } catch (Exception var3) {
                var3.printStackTrace();
            }

            this.c = null;
            this.a = null;

            try {
                if (this.m != null) {
                    this.m.close();
                }
            } catch (Exception var2) {
                var2.printStackTrace();
            }

            this.m = null;
            if (!this.g.allowResume) {
                File tmp = new File(this.p);
                if (tmp.exists()) {
                    tmp.delete();
                }
            }

        }
    }

    private int a(File path, String contentType, int finishSize) throws Exception {
        int contentlength = -1;
        this.b = this.c.getInputStream();
        if (this.b == null) {
            return contentlength;
        } else {
            Map<String, List<String>> head = this.c.getHeaderFields();
            contentlength = this.c.getContentLength();
            if (contentlength > Integer.MAX_VALUE) {
                throw new Exception("HTTP entity too large to be buffered in memory");
            } else {
                if (contentlength <= 0) {
                    contentlength = 0;
                }

                contentlength += finishSize;
                String contentEncoding = this.c.getContentEncoding();
                if (contentEncoding != null && "gzip".equalsIgnoreCase(contentEncoding)) {
                    this.b = new GZIPInputStream(this.b, 4096);
                }

                if (this.m == null) {
                    this.m = new FileOutputStream(path, this.g.allowResume);
                }

                try {
                    byte[] buffer = new byte[this.a(contentlength)];
                    int downSize = finishSize;
                    boolean var9 = false;

                    while (!this.e) {
                        int read = this.b.read(buffer);
                        if (read == -1) {
                            break;
                        }

                        downSize += read;
                        this.m.write(buffer, 0, read);
                        long now = System.currentTimeMillis();
                        long step = now - this.q;
                        if (this.n && step > 400L) {
                            this.q = now;
                            String percent = "0";
                            if (contentlength > 0) {
                                percent = UZCoreUtil.formatNumber((double) downSize / (double) contentlength * 100.0D);
                            }

                            this.b(contentlength, percent);
                        }
                    }

                    this.a(contentlength, path, contentType);
                } catch (Exception var15) {
                    var15.printStackTrace();
                    this.a(-1, "io error!");
                }

                return contentlength;
            }
        }
    }

    private void a(int code, String des) {
        try {
            this.o.put("fileSize", 0);
            this.o.put("percent", 0);
            this.o.put("progress", 0);
            this.o.put("state", 2);
            this.o.put("msg", des);
            this.o.put("statusCode", code);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        this.a(2, this.o);
    }

    private void a(int fileSize, File saveFile, String contentType) {
        try {
            this.o.put("fileSize", fileSize);
            this.o.put("percent", 100);
            this.o.put("progress", 100);
            this.o.put("state", 1);
            String savePath = this.g.savePath;
            if (saveFile != null) {
                saveFile.renameTo(new File(savePath));
            }

            this.o.put("savePath", savePath);
            this.o.put("contentType", contentType);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        this.a(1, this.o);
    }

    private void b(int fileSize, String persent) {
        try {
            this.o.put("fileSize", fileSize);
            this.o.put("percent", persent);
            this.o.put("progress", persent);
            this.o.put("state", 0);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        this.a(0, this.o);
    }

    private int a(int contentLength) {
        int bulength = 2048;
        if (contentLength <= 0) {
            bulength = 3072;
        } else if (contentLength >= 1048576) {
            bulength = 5120;
        } else if (contentLength >= 512000) {
            bulength = 4096;
        }

        return bulength;
    }

    private void g() {
        if (this.c != null) {
            if (this.g.heads != null) {
                this.k.putAll(this.g.heads);
            }

            Iterator var2 = this.k.keySet().iterator();

            while (var2.hasNext()) {
                String key = (String) var2.next();
                String value = this.k.get(key);
                this.c.setRequestProperty(key, value);
            }
        }

    }

    private void a(String url, Map<String, List<String>> headers) {
        if (headers != null) {
            List<String> setCookies = headers.get("Set-Cookie");
            String v;
            Iterator var10;
            if (setCookies != null && setCookies.size() > 0) {
                var10 = setCookies.iterator();

                while (var10.hasNext()) {
                    v = (String) var10.next();
                    UZCoreUtil.setCookie(url, v);
                }

            } else {
                setCookies = headers.get("set-cookie");
                if (setCookies != null && setCookies.size() > 0) {
                    var10 = setCookies.iterator();

                    while (var10.hasNext()) {
                        v = (String) var10.next();
                        UZCoreUtil.setCookie(url, v);
                    }

                } else {
                    List<String> Cookie = headers.get("Cookie");
                    Iterator var11;
                    if (Cookie != null && Cookie.size() > 0) {
                        var11 = Cookie.iterator();

                        while (var11.hasNext()) {
                            v = (String) var11.next();
                            UZCoreUtil.setCookie(url, v);
                        }

                    } else {
                        Cookie = headers.get("cookie");
                        if (Cookie != null && Cookie.size() > 0) {
                            var11 = Cookie.iterator();

                            while (var11.hasNext()) {
                                v = (String) var11.next();
                                UZCoreUtil.setCookie(url, v);
                            }

                        } else {
                            List<String> Cookie2 = headers.get("Cookie2");
                            Iterator var7;
                            if (Cookie2 != null && Cookie2.size() > 0) {
                                var7 = Cookie2.iterator();

                                while (var7.hasNext()) {
                                    v = (String) var7.next();
                                    UZCoreUtil.setCookie(url, v);
                                }

                            } else {
                                Cookie2 = headers.get("cookie2");
                                if (Cookie2 != null) {
                                    var7 = Cookie2.iterator();

                                    while (var7.hasNext()) {
                                        v = (String) var7.next();
                                        UZCoreUtil.setCookie(url, v);
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
    }

    // $FF: synthetic method
    public String e() {
        return this.a();
    }

    // $FF: synthetic method
    public String f() {
        return this.b();
    }
}
