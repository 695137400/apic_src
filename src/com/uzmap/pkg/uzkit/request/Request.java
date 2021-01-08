//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzkit.request;

import com.uzmap.pkg.a.b.d.d;
import java.util.Map;

public class Request extends d {
    public Request(int method, String url) {
        super(method, url);
    }

    public void addHeader(String key, String value) {
        super.addHeader(key, value);
    }

    public void addHeader(Map<String, String> headers) {
        super.addHeader(headers);
    }

    public void setTimeout(int time) {
        super.setTimeout(time);
    }

    public void setCertificate(String certPath, String certPsw) {
        super.setCertificate(certPath, certPsw);
    }

    public void setCharset(String charset) {
        super.setCharset(charset);
    }

    public void setNeedReport(boolean flag) {
        super.setNeedReport(flag);
    }

    public void setCallback(RequestCallback callback) {
        super.setCallback(callback);
    }
}
