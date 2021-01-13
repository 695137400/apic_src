package com.uzmap.pkg.ui.b.bb;

import com.uzmap.pkg.ui.b.p;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class a implements HostnameVerifier {
    public static a a = new a();

    public boolean verify(String hostname, SSLSession session) {
        p.a("HostnameVerifier verify ----------- " + hostname);
        return true;
    }
}
