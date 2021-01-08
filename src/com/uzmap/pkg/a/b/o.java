package com.uzmap.pkg.a.b;

public class o extends Exception {
    private static final long serialVersionUID = -8436667986383800680L;
    public final i a;
    private long b;
    private int c = 0;

    public o() {
        this.a = null;
    }

    public o(i response) {
        this.a = response;
    }

    public o(Throwable cause) {
        super(cause);
        this.a = null;
    }

    protected final void a(int type) {
        this.c = type;
    }

    void a(long networkTimeMs) {
        this.b = networkTimeMs;
    }

    public long a() {
        return this.b;
    }

    public int b() {
        return this.c;
    }

    public String c() {
        switch (this.c) {
            case 0:
                return "ERROR_NORMAL";
            case 1:
                return "ERROR_NETWORK";
            case 2:
                return "ERROR_NO_CONNECTION";
            case 3:
                return "ERROR_PARSE";
            case 4:
                return "ERROR_SERVER";
            case 5:
                return "ERROR_TIME_OUT";
            case 6:
                return "ERROR_AUTH_FAIL";
            case 7:
            case 8:
            case 9:
            default:
                return "";
            case 10:
                return "ERROR_CANCELED";
        }
    }
}
