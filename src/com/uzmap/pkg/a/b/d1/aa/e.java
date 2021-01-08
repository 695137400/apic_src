//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b.d1.aa;

public abstract class e extends d {
    private String i;
    private String j;
    private String k;
    private String l;

    public e(String name, String contentType, String charSet, String transferEncoding) {
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
