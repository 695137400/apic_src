//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.uzmap.pkg.a.b.d.f;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.ref.SoftReference;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TimeZone;

public class e {
    private static final char[] b = "0123456789ABCDEF".toCharArray();
    private static final String[] c = new String[]{"EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy"};
    static String a = null;

    private static String b(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];

        for(int j = 0; j < bytes.length; ++j) {
            int v = bytes[j] & 255;
            hexChars[j * 2] = b[v >>> 4];
            hexChars[j * 2 + 1] = b[v & 15];
        }

        return new String(hexChars);
    }

    public static String a(String text) {
        String hash = null;

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] bytes = text.getBytes("UTF-8");
            digest.update(bytes, 0, bytes.length);
            hash = b(digest.digest());
        } catch (NoSuchAlgorithmException var4) {
            var4.printStackTrace();
        } catch (UnsupportedEncodingException var5) {
            var5.printStackTrace();
        }

        return hash;
    }

    public static String a(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss", Locale.US);
        return df.format(date);
    }

    public static Date b(String dateValue) {
        String[] localDateFormats = c;
        Date localStartDate = new Date();
        String v = dateValue;
        if (dateValue.length() > 1 && dateValue.startsWith("'") && dateValue.endsWith("'")) {
            v = dateValue.substring(1, dateValue.length() - 1);
        }

        String[] var7 = localDateFormats;
        int var6 = localDateFormats.length;

        for(int var5 = 0; var5 < var6; ++var5) {
            String dateFormat = var7[var5];
            SimpleDateFormat dateParser = e.aa.a(dateFormat);
            dateParser.set2DigitYearStart(localStartDate);
            ParsePosition pos = new ParsePosition(0);
            Date result = dateParser.parse(v, pos);
            if (pos.getIndex() != 0) {
                return result;
            }
        }

        return null;
    }

    public static byte[] a(String data, String charset) {
        if (data == null) {
            throw new IllegalArgumentException("data may not be null");
        } else if (charset != null && charset.length() != 0) {
            try {
                return data.getBytes(charset);
            } catch (UnsupportedEncodingException var3) {
                return data.getBytes();
            }
        } else {
            throw new IllegalArgumentException("charset may not be null or empty");
        }
    }

    public static byte[] c(String data) {
        if (data == null) {
            throw new IllegalArgumentException("Parameter may not be null");
        } else {
            try {
                return data.getBytes("US-ASCII");
            } catch (UnsupportedEncodingException var2) {
                throw new Error("HttpClient requires ASCII support");
            }
        }
    }

    public static String a(byte[] data, int offset, int length) {
        if (data == null) {
            throw new IllegalArgumentException("Parameter may not be null");
        } else {
            try {
                return new String(data, offset, length, "US-ASCII");
            } catch (UnsupportedEncodingException var4) {
                throw new Error("HttpClient requires ASCII support");
            }
        }
    }

    public static String a(byte[] data) {
        if (data == null) {
            throw new IllegalArgumentException("Parameter may not be null");
        } else {
            return a(data, 0, data.length);
        }
    }

    public static String a(List<? extends f> parameters, String encoding) {
        StringBuilder result = new StringBuilder();
        Iterator var4 = parameters.iterator();

        while(var4.hasNext()) {
            f parameter = (f)var4.next();
            String encodedName = b(parameter.a(), encoding);
            String value = parameter.b();
            String encodedValue = value != null ? b(value, encoding) : "";
            if (result.length() > 0) {
                result.append("&");
            }

            result.append(encodedName);
            result.append("=");
            result.append(encodedValue);
        }

        return result.toString();
    }

    private static String b(String content, String encoding) {
        try {
            return URLEncoder.encode(content, encoding != null ? encoding : "ISO-8859-1");
        } catch (UnsupportedEncodingException var3) {
            throw new IllegalArgumentException(var3);
        }
    }

    public static String d(String mimeType) {
        if (TextUtils.isEmpty(mimeType)) {
            return "file";
        } else {
            String extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType);
            return extension != null ? extension : "file";
        }
    }

    public static String e(String random) {
        String result = "";

        try {
            result = Integer.toHexString(random.hashCode());
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return result;
    }

    public static String f(String uri) {
        int index = uri.indexOf(63);
        if (index <= 0) {
            return uri;
        } else {
            String host = uri.substring(0, index);
            String query = uri.substring(index + 1);
            StringBuffer sb = new StringBuffer();
            StringTokenizer tokenizer = new StringTokenizer(query, "&");

            while(tokenizer.hasMoreElements()) {
                String valuePair = tokenizer.nextToken();
                if (valuePair.length() > 0) {
                    int assignmentIndex = valuePair.indexOf(61);
                    if (assignmentIndex < 0) {
                        sb.append(valuePair);
                    } else {
                        String k = valuePair.substring(0, assignmentIndex);
                        sb.append(k);
                        sb.append('=');
                        String v = valuePair.substring(assignmentIndex + 1);

                        try {
                            sb.append(URLEncoder.encode(v, "UTF-8"));
                        } catch (UnsupportedEncodingException var11) {
                            var11.printStackTrace();
                        }
                    }

                    if (tokenizer.hasMoreElements()) {
                        sb.append('&');
                    }
                }
            }

            host = host + "?" + sb.toString();
            return host;
        }
    }

    public static final String a(Context context) {
        if (a != null) {
            return a;
        } else {
            File cacheDir = context.getExternalCacheDir();
            a = cacheDir != null ? cacheDir.toString() + File.separator : null;
            return a;
        }
    }

    static final class aa {
        private static final ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> a = new ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>>() {
            protected SoftReference<Map<String, SimpleDateFormat>> a() {
                return new SoftReference(new HashMap());
            }
        };

        public static SimpleDateFormat a(String pattern) {
            SoftReference<Map<String, SimpleDateFormat>> ref = (SoftReference)a.get();
            Map<String, SimpleDateFormat> formats = (Map)ref.get();
            if (formats == null) {
                formats = new HashMap();
                a.set(new SoftReference(formats));
            }

            SimpleDateFormat format = (SimpleDateFormat)((Map)formats).get(pattern);
            if (format == null) {
                format = new SimpleDateFormat(pattern, Locale.US);
                format.setTimeZone(TimeZone.getTimeZone("GMT"));
                ((Map)formats).put(pattern, format);
            }

            return format;
        }
    }
}
