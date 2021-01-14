package com.uzmap.pkg.uzcore.aa;

import android.net.Uri;
import com.uzmap.pkg.uzapp.UZFileSystem;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;

public class AssetsUtil {
    public static String a = "setWebContentsDebuggingEnabled";
    public static String b = "getFactory";
    public static String c = "text/javascript";
    public static String d = "text/html";
    public static String e = "text/css";
    private static String j = ".ups/";
    private static String k = "contents";
    private static String l = "content://";
    private static String m;
    private static String n;
    private static String o;
    private static String p;
    public static String f;
    public static String g;
    private static String q;
    private static String r;
    private static String s;
    private static String t;
    protected static SimpleDateFormat h;
    protected static Hashtable<String, String> i;

    static {
        m = com.uzmap.pkg.uzcore.external.l.a >= 11 ? k : l + com.uzmap.pkg.uzcore.d.a().b() + j;
        n = "file";
        o = "file://";
        p = com.uzmap.pkg.uzcore.external.l.a >= 11 ? n : o;
        f = "momenc";
        g = "UTF-8";
        q = "widget/res/key.xml";
        r = "uzmap/framework";
        s = "uzmap/module.json";
        t = "uzmap/atfrmk";
        i = new Hashtable();
        StringTokenizer st = new StringTokenizer("css\t\ttext/css htm\t\ttext/html html\t\ttext/html xhtml\ttext/html js\t\ttext/javascript jpg\t\timage/jpeg jpeg\t\timage/jpeg png\t\timage/png gif\t\timage/gif xml\t\ttext/xml svg\t\timage/svg+xml txt\t\ttext/plain asc\t\ttext/plain mp3\t\taudio/mpeg m3u\t\taudio/mpeg-url mp4\t\tvideo/mp4 qdv\t\tvideo/mp4 ogv\t\tvideo/ogg flv\t\tvideo/x-flv mov\t\tvideo/quicktime swf\t\tapplication/x-shockwave-flash pdf\t\tapplication/pdf doc\t\tapplication/msword ogg\t\tapplication/x-ogg zip\t\tapplication/octet-stream exe\t\tapplication/octet-stream class\tapplication/octet-stream ");

        while (st.hasMoreTokens()) {
            i.put(st.nextToken(), st.nextToken());
        }

        h = new SimpleDateFormat("E, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        h.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    public static final String a() {
        return m;
    }

    public static final String a(String extension) {
        return i.get(extension);
    }

    public static final String b() {
        return q;
    }

    public static final String c() {
        return s;
    }

    public static final String d() {
        return r;
    }

    public static final String e() {
        return t;
    }

    public static final String b(String url) {
        String c = a();
        return url.replaceFirst(p, c);
    }

    public static final String getFinalDir(String url) {
        if (!com.uzmap.pkg.uzcore.n.b()) {
            return url;
        } else {
            String finalDir;
            if (com.uzmap.pkg.uzcore.external.l.a > 10) {
                finalDir = UZFileSystem.get().getExtStorageDir();
                int index = url.indexOf(finalDir);
                if (index > 0) {
                    url = a() + "://" + url.substring(index);
                } else {
                    int indx = url.indexOf("/storage/");
                    if (indx > 0) {
                        url = a() + "://" + url.substring(indx);
                    }
                }
            }

            finalDir = url.replaceFirst(a(), p);
            return finalDir;
        }
    }

    public static final String getFinalDir(Uri u) {
        return getFinalDir(u.toString());
    }

    public static final String f() {
        return p;
    }
}
