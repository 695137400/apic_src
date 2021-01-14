package com.uzmap.pkg.uzcore.aa;

import android.text.TextUtils;
import android.util.Base64;
import android.webkit.MimeTypeMap;
import com.uzmap.pkg.uzapp.PropertiesUtil;
import com.uzmap.pkg.uzcore.external.Enslecb;
import com.uzmap.pkg.uzcore.n;
import com.uzmap.pkg.uzcore.uzmodule.ApiConfig;

import java.util.ArrayList;
import java.util.List;

public class AssetsFileUtil {
    static String a = Enslecb.xoc(new Object()) + b();
    static final int[] b = new int[]{239, 157, 102, 150, 29, 86, 207, 230, 165, 46, 102, 181, 75, 90, 17, 62, 153, 44, 78, 204};
    static String c = null;
    static final List<String> fileTypes = new ArrayList();

    static {
        fileTypes.add("js");
        fileTypes.add("css");
        fileTypes.add("html");
    }

    private static String c(String res, String aKey) {
        if (!TextUtils.isEmpty(res) && !TextUtils.isEmpty(aKey)) {
            int[] iS = new int[256];
            byte[] iK = new byte[256];

            int j;
            for (j = 0; j < 256; iS[j] = j++) {
            }


            for (short i = 0; i < 256; ++i) {
                iK[i] = (byte) aKey.charAt(i % aKey.length());
            }

            j = 0;

            int i;
            for (i = 0; i < 255; ++i) {
                j = (j + iS[i] + iK[i]) % 256;
                int temp = iS[i];
                iS[i] = iS[j];
                iS[j] = temp;
            }

            i = 0;
            j = 0;
            char[] iInputChar = res.toCharArray();
            char[] iOutputChar = new char[iInputChar.length];

            for (short x = 0; x < iInputChar.length; ++x) {
                i = (i + 1) % 256;
                j = (j + iS[i]) % 256;
                int temp = iS[i];
                iS[i] = iS[j];
                iS[j] = temp;
                int t = (iS[i] + iS[j] % 256) % 256;
                int iY = iS[t];
                char iCY = (char) iY;
                iOutputChar[x] = (char) (iInputChar[x] ^ iCY);
            }

            return new String(iOutputChar);
        } else {
            return null;
        }
    }

    public static String a(String encryptedValue, String ekey) {
        return c(encryptedValue, ekey);
    }

    public static byte[] a(String encodeValue) {
        return TextUtils.isEmpty(encodeValue) ? null : Base64.decode(encodeValue, 0);
    }

    public static String b(String encodeValue) {
        return TextUtils.isEmpty(encodeValue) ? null : new String(a(encodeValue));
    }

    public static String b(String widgetKey, String widgetId) {
        String bValue = b(widgetKey);
        return bValue == null ? null : a(bValue, widgetId);
    }

    public static byte[] a(byte[] src) {
        return a(src, Enslecb.xog());
    }

    public static byte[] b(byte[] src) {
        return com.uzmap.pkg.uzcore.d.j ? Enslecb.xohs(src, null) : a(src, a);
    }

    public static byte[] a(byte[] src, String key) {
        return a(src, src.length, key);
    }

    public static byte[] a(byte[] buffer, int length, String key) {
        int[] res = new int[length];
        int[] out = new int[length];

        for (int i = 0; i < length; ++i) {
            res[i] = buffer[i];
        }

        a(res, length, out, key);
        byte[] outData = new byte[length];

        for (int k = 0; k < length; ++k) {
            outData[k] = (byte) out[k];
        }

        return outData;
    }

    private static int a(int[] in, int length, int[] out, String key) {
        int[] ns = new int[b.length];
        int index = 0;
        a(ns, key);
        int j = 0;

        for (int i = 0; index < length; ++index) {
            i = (i + 1) % b.length;
            j = (j + ns[i]) % b.length;
            a(ns, i, j);
            int k = (ns[i] + ns[j] % b.length) % b.length;
            out[index] = in[index] ^ ns[k];
        }

        return length;
    }

    private static void a(int[] ns, String key) {
        int[] st = new int[b.length];
        a(ns);
        b(st, key);
        a(ns, st);
    }

    private static void a(int[] ns) {
        System.arraycopy(b, 0, ns, 0, b.length);
    }

    private static void b(int[] st, String key) {
        int length = key.length();
        byte[] keys = key.getBytes();

        for (int i = 0; i < b.length; ++i) {
            int k = i % length;
            st[i] = keys[k];
        }

    }

    private static void a(int[] ns, int i, int j) {
        int temp = ns[i];
        ns[i] = ns[j];
        ns[j] = temp;
    }

    private static void a(int[] ns, int[] st) {
        int j = 0;

        for (int i = 0; i < b.length; ++i) {
            j = (j + ns[i] + st[i]) % b.length;
            a(ns, i, j);
        }

    }

    private static String b() {
        if (c != null) {
            return c;
        } else {
            String key = n.a();
            if (10 == key.length()) {
                c = key;
                return c;
            } else {
                String[] sp = new String[10];

                for (int i = 0; i < 10; ++i) {
                    sp[i] = key.substring(i * 2, i * 2 + 2);
                }

                StringBuffer sb = new StringBuffer();
                sb.append(sp[0]);
                sb.append(sp[2]);
                sb.append(sp[4]);
                sb.append(sp[6]);
                sb.append(sp[8]);
                c = sb.toString();
                return c;
            }
        }
    }

    public static String c(String url) {
        String result = null;
        if (url.startsWith("file")) {
            result = AssetsUtil.b(url);
        } else {
            result = url;
        }

        return result;
    }

    public static void a(ApiConfig info) {
        if (info != null && info.z != null && info.Q) {
            String data = null;

            try {
                data = c(info.z);
            } catch (Exception var3) {
                var3.printStackTrace();
            }

            if (data != null) {
                info.z = data;
            }

        }
    }

    public static String getFileExtension(String url) {
        String cleanUrl = url;
        int index = url.indexOf(63);
        if (index > 0) {
            cleanUrl = url.substring(0, index);
        } else {
            index = url.indexOf(35);
            if (index > 0) {
                cleanUrl = url.substring(0, index);
            }
        }

        String extension = MimeTypeMap.getFileExtensionFromUrl(cleanUrl);
        extension = extension.toLowerCase();
        return extension;
    }

    public static boolean checkFileType(String extension) {
        return fileTypes.contains(extension);
    }

    public static boolean a() {
        if (!PropertiesUtil.n()) {
            return false;
        } else {
            boolean code = true;
            boolean dable = com.uzmap.pkg.uzcore.d.a().d();
            return !code || dable;
        }
    }
}
