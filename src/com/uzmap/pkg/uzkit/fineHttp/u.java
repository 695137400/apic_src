package com.uzmap.pkg.uzkit.fineHttp;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.uzmap.pkg.ui.b.dd.c;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.external.UzResourceCache;
import com.uzmap.pkg.uzkit.UZUtility;
import org.apache.http.Header;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.zip.GZIPInputStream;

public abstract class u<T> implements Runnable {
    protected static Charset d = StandardCharsets.UTF_8;
    protected boolean e;
    protected RequestListener f;
    protected RequestParam g;
    protected String h;
    protected boolean i;
    protected boolean j;
    protected Hashtable<String, String> k;
    protected long l = 0L;

    public u(RequestParam argument) {
        if (argument == null) {
            throw new RuntimeException("ajax param can not be null");
        } else {
            this.g = argument;
            this.j = argument.needEscape;
            this.j = false;
            this.b();
        }
    }

    public abstract void c();

    public abstract T e();

    protected abstract T f();

    public void run() {
        if (!this.e) {
            this.l();
            this.f();
        }
    }

    public Object a_() {
        return this.g.getTag();
    }

    public void a(RequestListener listener) {
        this.f = listener;
    }

    protected void l() {
        this.l = SystemClock.uptimeMillis();
    }

    protected void a(Response result) {
        if (!this.e) {
            if (!result.success()) {
                if (this.m()) {
                    result.setTimeout(true);
                } else if (this.g.cache) {
                    String cache = this.a(this.g.url);
                    if (cache != null) {
                        result.transCache(cache);
                    }
                }
            }

            if (this.f != null) {
                this.f.onResult(result);
            }

            this.f = null;
        }
    }

    protected boolean m() {
        long now = SystemClock.uptimeMillis();
        long step = now - this.l;
        Log.d("timeout", "isTimeout: " + step + " , " + this.g.timeout);
        return step > (long) this.g.timeout;
    }

    protected void a(int state, JSONObject result) {
        if (this.f != null && this.f instanceof ProgressListener) {
            ((ProgressListener) this.f).onProgress(state, result);
        }

        if (state == 2 || state == 1) {
            this.f = null;
        }

    }

    protected byte[] a(InputStream inputStream, int contentLength, boolean gzip) throws Exception {
        if (inputStream == null) {
            return new byte[0];
        } else if (contentLength > Integer.MAX_VALUE) {
            throw new Exception("HTTP entity too large to be buffered in memory");
        } else {
            if (contentLength <= 0) {
                contentLength = 4096;
            }

            contentLength = (int) ((float) contentLength * 1.3F);
            if (gzip) {
                inputStream = new GZIPInputStream(inputStream, 2048);
                gzip = true;
            }

            ByteArrayBuffer buffer = new ByteArrayBuffer(8192);
            int lenth;
            int bl;
            if (gzip) {
                lenth = 0;

                int j;
                while (lenth != -1) {
                    byte[] buf = new byte[2048];

                    try {
                        lenth = inputStream.read(buf, 0, buf.length);
                        if (lenth != -1) {
                            buffer.append(buf, 0, lenth);
                        }
                    } catch (EOFException var11) {
                        j = buf.length;

                        for (int k = 0; k < j; ++k) {
                            int surpl = buf[k];
                            if (surpl != 0) {
                                buffer.append(surpl);
                            }
                        }

                        lenth = -1;
                    }
                }

                if (this.j) {
                    bl = buffer.length();
                    ByteArrayBuffer temBuffer = new ByteArrayBuffer((int) ((double) bl * 1.4D));

                    for (j = 0; j < bl; ++j) {
                        int cc = buffer.byteAt(j);
                        if (cc == 34 || cc == 39 || cc == 92 || cc == 10 || cc == 13 || cc == 38) {
                            temBuffer.append(92);
                        }

                        temBuffer.append(cc);
                    }

                    buffer = temBuffer;
                }
            } else if (!this.j) {
                byte[] buf = new byte[2048];

                while (true) {
                    bl = inputStream.read(buf);
                    if (-1 == bl) {
                        break;
                    }

                    buffer.append(buf, 0, bl);
                }
            } else {
                for (; (lenth = inputStream.read()) != -1; buffer.append(lenth)) {
                    if (lenth == 34 || lenth == 39 || lenth == 92 || lenth == 10 || lenth == 13 || lenth == 38) {
                        buffer.append(92);
                    }
                }
            }

            return buffer.toByteArray();
        }
    }

    protected JSONObject a(Header[] allHeaders) {
        JSONObject headers = new JSONObject();
        if (allHeaders != null) {
            Header[] var6 = allHeaders;
            int var5 = allHeaders.length;

            for (int var4 = 0; var4 < var5; ++var4) {
                Header header = var6[var4];

                try {
                    String key = header.getName();
                    String value = header.getValue();
                    if (!TextUtils.isEmpty(key)) {
                        headers.put(key, value);
                    }
                } catch (JSONException var9) {
                    var9.printStackTrace();
                }
            }
        }

        return headers;
    }

    protected boolean a(byte[] buffer, Charset set) {
        if (buffer != null && buffer.length >= 4 && d.equals(set)) {
            return -17 == buffer[0] && -69 == buffer[1] && -65 == buffer[2];
        } else {
            return false;
        }
    }

    protected void a(String url, String data) {
        String local = UzResourceCache.get().makeDiskFile(url);
        File text = new File(local);
        File p = text.getParentFile();
        if (p != null && !p.exists()) {
            p.mkdirs();
        }

        try {
            if (!text.exists()) {
                text.createNewFile();
            }

            FileWriter fwriter = new FileWriter(text, false);
            BufferedWriter bwriter = new BufferedWriter(fwriter);
            bwriter.write(data);
            bwriter.flush();
            bwriter.close();
            UzResourceCache.get().cacheDisk(url, local, null);
        } catch (Exception var8) {
            var8.printStackTrace();
        }

    }

    protected String a(String url) {
        String resut = null;
        c.a1 entity = UzResourceCache.get().hasDiskCache(url);
        if (entity != null) {
            String local = entity.c;
            InputStream input = null;

            try {
                input = UZUtility.guessInputStream(local);
                resut = UZCoreUtil.readString(input, null);
                input.close();
            } catch (Exception var7) {
                var7.printStackTrace();
            }
        }

        return resut;
    }

    protected String b(String uri) {
        int index = uri.indexOf(63);
        if (index > 0) {
            String host = uri.substring(0, index);
            String query = uri.substring(index + 1);
            StringBuffer sb = new StringBuffer();
            StringTokenizer tokenizer = new StringTokenizer(query, "&");

            while (tokenizer.hasMoreElements()) {
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
                        sb.append(URLEncoder.encode(v));
                    }

                    if (tokenizer.hasMoreElements()) {
                        sb.append('&');
                    }
                }
            }

            host = host + "?" + sb.toString();
            return host;
        } else {
            return uri;
        }
    }

    protected String c(String url) {
        if (TextUtils.isEmpty(url)) {
            return null;
        } else {
            String extension = MimeTypeMap.getFileExtensionFromUrl(url);
            extension = extension.toLowerCase();
            String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
            return TextUtils.isEmpty(mimeType) ? null : mimeType;
        }
    }

    private void b() {
        this.k = new Hashtable(5);
        this.k.put("Accept", "*/*");
        this.k.put("Charset", "UTF-8");
        this.k.put("User-Agent", UZUtility.getDefaultUseragent());
        this.k.put("Connection", "Keep-Alive");
        if (this.g.needGzip()) {
            this.k.put("Accept-Encoding", "gzip");
        }

    }
}
