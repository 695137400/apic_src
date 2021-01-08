package com.uzmap.pkg.a.b.dd;

import java.io.IOException;
import java.io.InputStream;

public class h extends com.uzmap.pkg.a.b.dd.aa.a {
    private InputStream d;

    public void a1(InputStream inputStream) {
        this.d = inputStream;
    }

    public InputStream a1() throws IOException {
        return this.d;
    }

    public void b() {
        if (this.d != null) {
            try {
                this.d.close();
            } catch (IOException var2) {
                var2.printStackTrace();
            }
        }

    }
}
