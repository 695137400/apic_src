package com.uzmap.pkg.uzapp;

import com.uzmap.pkg.uzcore.UZCoreUtil;
import compile.Properties;

public class b {
    public static boolean a() {
        return Properties.promotion();
    }

    public static boolean b() {
        return Properties.television();
    }

    public static String c() {
        return Properties.mamHost();
    }

    public static String d() {
        return Properties.mcmHost();
    }

    public static String e() {
        return Properties.msmHost();
    }

    public static String f() {
        return Properties.pushHost();
    }

    public static String g() {
        return Properties.storeHost();
    }

    public static String h() {
        return Properties.analysisHost();
    }

    public static String i() {
        return Properties.widgetKey();
    }

    public static String j() {
        return Properties.cloudKey();
    }

    public static String k() {
        return Properties.sandbox();
    }

    public static boolean l() {
        return Properties.developerMode();
    }

    public static boolean m() {
        return Properties.auth();
    }

    public static boolean n() {
        return Properties.smode();
    }

    public static boolean o() {
        return Properties.loader();
    }

    public static String p() {
        return Properties.loaderVer();
    }

    public static String q() {
        return Properties.appId();
    }

    public static String r() {
        return Properties.descriptor();
    }

    public static long s() {
        return Properties.cloudStamp();
    }

    public static boolean t() {
        if (o() || a()) {
            long st = s();
            long now = System.currentTimeMillis();
            long ex = 2592000000L;
            long pl = now - st;
            return pl > ex;
        }

        return false;
    }

    public static String a(String wId) {
        return com.uzmap.pkg.uzcore.uzmodule.e.d(wId);
    }

    public static String b(String wId) {
        return com.uzmap.pkg.uzcore.uzmodule.e.c(wId);
    }

    public static String c(String wId) {
        return UZCoreUtil.getUUID() + wId;
    }
}
