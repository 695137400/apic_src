package com.uzmap.pkg.a.b;

public class d implements n {
    private int a;
    private int b;
    private final int c;
    private final float d;

    public d() {
        this(10000, 0, 1.0F);
    }

    public d(int initialTimeoutMs, int maxNumRetries, float backoffMultiplier) {
        this.a = initialTimeoutMs;
        this.c = maxNumRetries;
        this.d = backoffMultiplier;
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public void a(o error) throws o {
        ++this.b;
        this.a = (int) ((float) this.a + (float) this.a * this.d);
        if (!this.c()) {
            throw error;
        }
    }

    protected boolean c() {
        return this.b <= this.c;
    }
}
