//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b.b1;

import com.uzmap.pkg.a.b.p;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class a implements HostnameVerifier {
    public static a a = new a();

    public a() {
    }

    public boolean verify(String hostname, SSLSession session) {
        p.a("HostnameVerifier verify ----------- " + hostname);
        return true;
    }
}
