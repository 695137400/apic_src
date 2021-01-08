//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b.d1;

import com.uzmap.pkg.a.b.c;
import com.uzmap.pkg.uzapp.e;
import java.util.Map;

public class i implements c {
    private e a = e.a();

    public i() {
    }

    public void a(String url, Map<String, String> headers) {
        if (headers != null) {
            String cookie = (String)headers.get("Set-Cookie");
            if (cookie == null) {
                cookie = (String)headers.get("set-cookie");
            }

            if (cookie != null) {
                this.a.a(url, cookie);
            }

        }
    }

    public String a(String url) {
        return this.a.a(url);
    }
}
