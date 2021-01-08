package com.uzmap.pkg.uzkit.fineHttp;

import java.io.*;

public class d implements e {
    private final File a;
    private String b;

    public d(File file) throws FileNotFoundException {
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
        return this.a != null ? new FileInputStream(this.a) : new ByteArrayInputStream(new byte[0]);
    }
}
