package com.uzmap.pkg.uzkit.fineHttp;

public class i extends Response {
    public i() {
        super(0);
        this.setError("bad request");
    }

    public i(String msg) {
        super(0);
        this.setError(msg);
    }
}
