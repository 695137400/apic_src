package com.uzmap.pkg.uzcore.uzmodule;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.aa.AssetsFileUtil;
import com.uzmap.pkg.uzcore.external.Enslecb;
import com.uzmap.pkg.uzcore.external.UzResourceCache;
import com.uzmap.pkg.uzcore.uzmodule.aa.r;
import com.uzmap.pkg.uzkit.UZUtility;
import com.uzmap.pkg.uzkit.data.UZWidgetInfo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class e implements Cloneable {
    static Hashtable<String, String> a = new Hashtable();
    static Hashtable<String, String> b = new Hashtable();
    static String c = "pageBounce";
    static String d = "appBackground";
    static String e = "windowBackground";
    static String f = "frameBackgroundColor";
    static String g = "frameBackground";
    static String h = "hScrollBarEnabled";
    static String i = "vScrollBarEnabled";
    static String j = "autoLaunch";
    static String k = "autoUpdate";
    static String l = "smartUpdate";
    static String m = "debug";
    static String n = "phonegapSupport";
    static String o = "statusBarAppearance";
    static String p = "userAgent";
    static String q = "loader";
    public String r;
    public String s;
    public String t;
    public String u;
    public String v;
    public String w;
    public String x;
    public String y;
    public String z;
    public String A;
    public String B;
    public String C;
    public String D;
    private String X;
    public boolean E = false;
    public String F;
    public String G;
    public String H;
    public int I;
    public boolean J;
    public boolean K;
    public boolean L;
    public boolean M;
    public boolean N;
    public boolean O;
    public boolean P;
    public boolean Q;
    public boolean R;
    public boolean S;
    public String T;
    public r U;
    public List<String> V;
    public Hashtable<String, com.uzmap.pkg.uzkit.data.b> W;
    private Drawable Y;
    private UZWidgetInfo Z;
    private AppInfo aa;

    static {
        if (com.uzmap.pkg.uzapp.b.o()) {
            b.put("A6965066952332", "62587239-AD3C-8190-47B4-37DE080D7E9D");
        }

    }

    public e() {
        this.I = com.uzmap.pkg.uzcore.external.l.c;
        this.J = true;
        this.K = true;
        this.L = true;
        this.M = true;
        this.N = false;
        this.O = false;
        this.P = false;
        this.Q = false;
    }

    public void a(String name, String value) {
        if (c.equals(name)) {
            this.E = "true".equalsIgnoreCase(value);
        } else if (d.equals(name)) {
            this.F = value;
        } else if (e.equals(name)) {
            this.G = value;
        } else if (f.equals(name)) {
            this.I = UZCoreUtil.parseColor(value);
        } else if (g.equals(name)) {
            this.H = value;
        } else if (h.equals(name)) {
            this.J = "true".equalsIgnoreCase(value);
        } else if (i.equals(name)) {
            this.K = "true".equalsIgnoreCase(value);
        } else if (j.equals(name)) {
            this.L = "true".equalsIgnoreCase(value);
        } else if (k.equals(name)) {
            this.M = "true".equalsIgnoreCase(value);
        } else if (l.equals(name)) {
            this.N = "true".equalsIgnoreCase(value);
        } else if (m.equals(name)) {
            this.P = "true".equalsIgnoreCase(value);
        } else if (n.equals(name)) {
            this.O = "true".equalsIgnoreCase(value);
        } else if (o.equals(name)) {
            this.R = "true".equalsIgnoreCase(value);
        } else if (p.equals(name)) {
            this.T = value;
        }

    }

    public Drawable a() {
        if (this.Y != null) {
            return this.Y;
        } else {
            this.Y = this.f(this.G);
            return this.Y;
        }
    }

    public Drawable b() {
        return this.f(this.F);
    }

    public static String c(String id) {
        if (TextUtils.isEmpty(id)) {
            return null;
        } else {
            String key = b.get(id);
            if (!TextUtils.isEmpty(key)) {
                return key;
            } else if (com.uzmap.pkg.uzcore.d.j) {
                key = Enslecb.xkm(id);
                b.put(id, key);
                return key;
            } else {
                key = com.uzmap.pkg.uzapp.b.i();
                if (TextUtils.isEmpty(key)) {
                    return null;
                } else {
                    key = AssetsFileUtil.b(key, id);
                    if (key != null) {
                        try {
                            StringBuffer buffer = new StringBuffer(key);
                            buffer = buffer.replace(0, 2, "");
                            buffer = buffer.replace(14, 18, "");
                            buffer = buffer.replace(32, 34, "");
                            buffer.insert(8, '-');
                            buffer.insert(13, '-');
                            buffer.insert(18, '-');
                            buffer.insert(23, '-');
                            key = buffer.toString();
                        } catch (Exception var3) {
                            var3.printStackTrace();
                        }
                    }

                    b.put(id, key);
                    return key;
                }
            }
        }
    }

    public static String d(String wId) {
        if (TextUtils.isEmpty(wId)) {
            return "";
        } else {
            String token = a.get(wId);
            if (!TextUtils.isEmpty(token)) {
                return token;
            } else {
                token = wId + "UZ" + UZCoreUtil.getUUID() + "UZ" + c(wId);
                token = UZCoreUtil.toSHA1(token);
                a.put(wId, token);
                return token;
            }
        }
    }

    private Drawable f(String res) {
        Drawable drawable = null;
        if (!TextUtils.isEmpty(res)) {
            char first = res.charAt(0);
            if ('#' != first && 'r' != first && 'R' != first) {
                String url = UZUtility.makeAbsUrl(this.y, res);
                Bitmap bitmap = UzResourceCache.get().getImage(url);
                if (bitmap != null) {
                    drawable = new BitmapDrawable(com.uzmap.pkg.uzcore.b.a().b().getResources(), bitmap);
                }
            } else {
                int color = UZCoreUtil.parseColor(res);
                drawable = new ColorDrawable(color);
            }
        } else {
            drawable = new ColorDrawable(com.uzmap.pkg.uzcore.external.l.c);
        }

        return drawable;
    }

    public void b(String permissionName) {
        if (this.V == null) {
            this.V = new ArrayList();
        }

        this.V.add(permissionName);
    }

    public int c() {
        return this.U == null ? 0 : this.U.a;
    }

    public int d() {
        return this.U == null ? 0 : this.U.b;
    }

    public long e() {
        return this.U == null ? 300L : this.U.c;
    }

    public String f() {
        if (this.X != null) {
            return this.X;
        } else {
            this.X = this.D.replace("file://", "");
            return this.X;
        }
    }

    public String g() {
        if (com.uzmap.pkg.uzapp.b.o()) {
            return "A6965066952332";
        } else {
            return "sdk".equals(com.uzmap.pkg.uzapp.b.r()) ? com.uzmap.pkg.uzapp.b.q() : this.r;
        }
    }

    public void a(String featureName, String paramName, String paramValue) {
        if (featureName != null) {
            if (this.W == null) {
                this.W = new Hashtable();
            }

            com.uzmap.pkg.uzkit.data.b feature = this.W.get(featureName);
            if (feature != null) {
                feature.a(paramName, paramValue);
            } else {
                feature = new com.uzmap.pkg.uzkit.data.b(featureName);
                if (!TextUtils.isEmpty(paramName) && !TextUtils.isEmpty(paramValue)) {
                    feature.a(paramName, paramValue);
                }

                this.W.put(featureName, feature);
            }

        }
    }

    public UZWidgetInfo i() {
        if (this.Z != null) {
            return this.Z;
        } else {
            this.Z = new UZWidgetInfo();
            this.Z.id = this.r;
            this.Z.name = this.t;
            this.Z.version = this.s;
            this.Z.description = this.u;
            this.Z.author = this.v;
            this.Z.authorEmail = this.w;
            this.Z.authorHref = this.x;
            this.Z.access = this.A;
            this.Z.origin = this.B;
            this.Z.iconPath = this.C;
            this.Z.widgetPath = this.D;
            return this.Z;
        }
    }

    public void j() {
        if (!TextUtils.isEmpty(this.T)) {
            String customUserAgent = null;
            if (!this.T.startsWith("widget")) {
                com.uzmap.pkg.uzcore.r.b(this.T);
            } else {
                customUserAgent = UZUtility.makeRealPath(this.T, this.i());

                try {
                    InputStream input = UZUtility.guessInputStream(customUserAgent);
                    String cusUserAgent = UZCoreUtil.readString(input);
                    input.close();
                    if (!TextUtils.isEmpty(cusUserAgent)) {
                        com.uzmap.pkg.uzcore.r.a(cusUserAgent);
                    }
                } catch (Exception var4) {
                    var4.printStackTrace();
                    this.T = null;
                }
            }
        }

    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("widgetInfo: ");
        sb.append("\n");
        sb.append("id: " + this.r);
        sb.append("\n");
        sb.append("name: " + this.t);
        sb.append("\n");
        sb.append("description: " + this.u);
        sb.append("\n");
        sb.append("author: " + this.v);
        sb.append("\n");
        sb.append("authorEmail: " + this.w);
        sb.append("\n");
        sb.append("authorHref: " + this.x);
        sb.append("\n");
        sb.append("content: " + this.z);
        sb.append("\n");
        sb.append("access: " + this.A);
        sb.append("\n");
        sb.append("version: " + this.s);
        sb.append("\n");
        return sb.toString();
    }

    public com.uzmap.pkg.uzkit.data.b a(String featureName) {
        return this.W != null && featureName != null ? this.W.get(featureName) : null;
    }

    public AppInfo h() {
        if (this.aa != null) {
            return this.aa;
        } else {
            String channel = com.uzmap.pkg.uzcore.d.a().a((String) null);
            if (channel != null) {
                this.a("TalkingData", "logEnable", "false");
                this.a("TalkingData", "exceptionReportEnabled", "true");
                this.a("TalkingData", "channel", channel);
            }

            this.aa = new AppInfo(this.W);
            this.aa.appId = this.r;
            this.aa.appName = this.t;
            return this.aa;
        }
    }

    public e e(String url) {
        String newUrl = url;
        String wgt;
        if (!url.startsWith("http")) {
            wgt = "widget/";
            int index = url.indexOf(wgt);
            if (index >= 0) {
                newUrl = this.D + url.substring(index + wgt.length());
            }
        }

        wgt = null;

        e resultInfo;
        try {
            resultInfo = (e) this.clone();
        } catch (CloneNotSupportedException var5) {
            var5.printStackTrace();
            this.z = newUrl;
            return this;
        }

        resultInfo.z = newUrl;
        return resultInfo;
    }
}
