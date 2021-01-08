package com.uzmap.pkg.uzsocket.e;

import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;

public class g implements c {
    private byte[] a;
    private final TreeMap<String, String> b;

    public g() {
        this.b = new TreeMap(String.CASE_INSENSITIVE_ORDER);
    }

    public Iterator<String> b() {
        return Collections.unmodifiableSet(this.b.keySet()).iterator();
    }

    public String b(String name) {
        String s = this.b.get(name);
        return s == null ? "" : s;
    }

    public byte[] c() {
        return this.a;
    }

    public void a(byte[] content) {
        this.a = content;
    }

    public void a(String name, String value) {
        this.b.put(name, value);
    }

    public boolean c(String name) {
        return this.b.containsKey(name);
    }
}
