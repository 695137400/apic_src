//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b.d1.aa;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class g implements h {
    private File a;
    private String b;

    public g(File file) throws FileNotFoundException {
        this.a = file;
        if (file != null) {
            if (!file.isFile()) {
                throw new FileNotFoundException("File is not a normal file.");
            }

            if (!file.canRead()) {
                throw new FileNotFoundException("File is not readable.");
            }

            this.b = file.getName();
        }

    }

    public long a() {
        return this.a != null ? this.a.length() : 0L;
    }

    public String b() {
        return this.b == null ? "noname" : this.b;
    }

    public InputStream c() throws IOException {
        return (InputStream)(this.a != null ? new FileInputStream(this.a) : new ByteArrayInputStream(new byte[0]));
    }
}
