package com.uzmap.pkg.uzkit.fineHttp;

public abstract class b extends a {
    private final String i;
    private final String j;
    private final String k;
    private final String l;

    public b(String name, String contentType, String charSet, String transferEncoding) {
        if (name == null) {
            throw new IllegalArgumentException("Name must not be null");
        } else {
            this.i = name;
            this.j = contentType;
            this.k = charSet;
            this.l = transferEncoding;
        }
    }

    public String a() {
        return this.i;
    }

    public String b() {
        return this.j;
    }

    public String c() {
        return this.k;
    }

    public String d() {
        return this.l;
    }
}
