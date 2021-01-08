package com.uzmap.pkg.uzsocket.e;

public class d extends g implements b {
    private String a = "*";

    public void a(String resourceDescriptor) throws IllegalArgumentException {
        if (resourceDescriptor == null) {
            throw new IllegalArgumentException("http resource descriptor must not be null");
        } else {
            this.a = resourceDescriptor;
        }
    }

    public String a() {
        return this.a;
    }
}
