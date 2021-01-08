package com.uzmap.pkg.uzkit.request;

import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.uzmap.pkg.a.b.dd.aa.*;
import com.uzmap.pkg.a.b.dd.f;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpParams implements Params {
    public Object body;
    public String fileStream;
    public List<f> values;
    public List<f> files;

    public boolean addValue(String name, String value) {
        if (TextUtils.isEmpty(name)) {
            return false;
        } else {
            if (this.values == null) {
                this.values = new ArrayList();
            }

            f vPair = new f(name, value);
            this.values.add(vPair);
            return true;
        }
    }

    public boolean addFile(String name, String filePath) {
        if (TextUtils.isEmpty(name)) {
            return false;
        } else {
            if (this.files == null) {
                this.files = new ArrayList();
            }

            f vPair = new f(name, filePath);
            this.files.add(vPair);
            return true;
        }
    }

    public boolean setStream(String fileStreamPath) {
        if (TextUtils.isEmpty(fileStreamPath)) {
            return false;
        } else {
            this.fileStream = fileStreamPath;
            return true;
        }
    }

    public boolean setBody(Object body) {
        if (body == null) {
            return false;
        } else {
            this.body = body;
            return true;
        }
    }

    public a getHttpEntity() {
        a entity = null;
        if (!TextUtils.isEmpty(this.fileStream)) {
            entity = this.createInputStemEntity();
        } else if (this.values != null && this.files == null) {
            entity = this.createFormEntity();
        } else if (this.body != null) {
            entity = this.createStringEntity();
        } else if (this.values != null && this.files != null || this.files != null) {
            entity = this.createMultiEntity();
        }

        return entity;
    }

    private a createFormEntity() {
        a entry = null;
        List values = this.values;

        try {
            entry = new k(values, "UTF-8");
        } catch (UnsupportedEncodingException var4) {
            var4.printStackTrace();
        }

        return entry;
    }

    private a createInputStemEntity() {
        b entry = null;

        try {
            List<f> files = this.files;
            f first = files.get(0);
            String value = first.b();
            if (TextUtils.isEmpty(value)) {
                return entry;
            }

            value = value.replaceFirst("file://", "");
            File file = new File(value);
            if (!file.exists()) {
                return entry;
            }

            FileInputStream instream = new FileInputStream(file);
            entry = new b(instream, file.length());
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return entry;
    }

    private a createMultiEntity() {
        c multiEn = null;

        try {
            multiEn = new c();
            List<f> temps = this.values;
            f pair;
            Iterator var4;
            if (temps != null) {
                var4 = temps.iterator();

                while (var4.hasNext()) {
                    pair = (f) var4.next();
                    d part = new i(pair.a(), pair.b());
                    multiEn.a1(part);
                }
            }

            temps = this.files;
            if (temps != null) {
                var4 = temps.iterator();

                while (var4.hasNext()) {
                    pair = (f) var4.next();
                    String key = pair.a();
                    String value = pair.b();
                    if (!TextUtils.isEmpty(value)) {
                        value = value.replaceFirst("file://", "");
                        File file = new File(value);
                        if (file.exists()) {
                            String mimeType = this.mimeTypeFromUrl(value);
                            d part = new com.uzmap.pkg.a.b.dd.aa.f(key, file, mimeType, null);
                            multiEn.a1(part);
                        }
                    }
                }
            }

            return multiEn;
        } catch (Exception var10) {
            var10.printStackTrace();
            return null;
        }
    }

    private a createStringEntity() {
        j entry = null;

        try {
            entry = new j(this.body.toString(), "UTF-8");
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return entry;
    }

    private String mimeTypeFromUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return null;
        } else {
            String extension = MimeTypeMap.getFileExtensionFromUrl(url);
            extension = extension.toLowerCase();
            String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
            return TextUtils.isEmpty(mimeType) ? null : mimeType;
        }
    }

    public Map<String, String> getAdditionalHeaders() {
        return null;
    }
}
