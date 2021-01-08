//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b.d1;

import com.uzmap.pkg.a.b.d.a.a;
import java.io.IOException;
import java.io.InputStream;

public class h extends a {
    private InputStream d;

    public h() {
    }

    public void a(InputStream inputStream) {
        this.d = inputStream;
    }

    public InputStream a() throws IOException {
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
