package com.uzmap.pkg.ui.b.dd;

import java.util.Map;

public class i implements com.uzmap.pkg.ui.b.c {
    private final com.uzmap.pkg.uzapp.e a = com.uzmap.pkg.uzapp.e.a();

    public void a(String url, Map<String, String> headers) {
        if (headers != null) {
            String cookie = headers.get("Set-Cookie");
            if (cookie == null) {
                cookie = headers.get("set-cookie");
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
