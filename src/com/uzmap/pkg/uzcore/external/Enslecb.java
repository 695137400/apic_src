//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzcore.external;

public class Enslecb {
    static {
        System.loadLibrary("sec");
    }

    private Enslecb() {
    }

    public static synchronized String xoc(Object o) {
        return oc(o);
    }

    public static synchronized String xog() {
        return og();
    }

    public static synchronized byte[] xoh(byte[] b, String i) {
        return b == null ? null : oh(b, i);
    }

    public static synchronized byte[] xohs(byte[] s, String i) {
        return s == null ? new byte[0] : ohs(s, i);
    }

    public static synchronized byte[] xrc(byte[] b, String i) {
        return b == null ? null : rc(b, i);
    }

    public static synchronized String xen(String s) {
        s = s != null ? s : "";
        return bs(s, 0);
    }

    public static synchronized String xben(byte[] b) {
        return b == null ? "" : bbs(b, 0);
    }

    public static synchronized String xde(String s) {
        s = s != null ? s : "";
        return bs(s, 1);
    }

    public static synchronized String xbde(byte[] b) {
        return b == null ? "" : bbs(b, 1);
    }

    public static synchronized String xmd(String s, int h) {
        s = s != null ? s : "";
        return md(s, h);
    }

    public static synchronized String xbmd(byte[] b, int h) {
        return b == null ? "" : bmd(b, h);
    }

    public static synchronized boolean xsm(Object o) {
        return sm(o);
    }

    public static synchronized String xpm(Object o) {
        return pm(o);
    }

    public static synchronized String xkm(Object o) {
        return km(o);
    }

    private static native String oc(Object var0);

    private static native byte[] oh(byte[] var0, String var1);

    private static native byte[] ohs(byte[] var0, String var1);

    private static native byte[] rc(byte[] var0, String var1);

    private static native String og();

    private static native String bs(String var0, int var1);

    private static native String bbs(byte[] var0, int var1);

    private static native String md(String var0, int var1);

    private static native String bmd(byte[] var0, int var1);

    private static native String pm(Object var0);

    private static native String km(Object var0);

    private static native boolean sm(Object var0);
}
