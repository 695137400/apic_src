package com.uzmap.pkg.uzcore.aa;

import android.content.res.AssetFileDescriptor;
import android.os.AsyncTask;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseOutputStream;

import java.io.IOException;

public class d {
    public static ParcelFileDescriptor a(byte[] data) throws IOException {
        ParcelFileDescriptor[] pipe = ParcelFileDescriptor.createPipe();
        ParcelFileDescriptor readSide = pipe[0];
        ParcelFileDescriptor writeSide = pipe[1];
        AutoCloseOutputStream out = new AutoCloseOutputStream(writeSide);
        (new d.a(data, out)).execute((Object[]) null);
        return readSide;
    }

    public static AssetFileDescriptor a(String u) {
        int length = 0;
        byte[] content = e.a().a(u);
        if (content != null) {
            length = content.length;
        } else {
            content = e.a().c(u);
            if (content != null) {
                length = content.length;
            }
        }

        if (length <= 0) {
            return null;
        } else {
            AssetFileDescriptor assetFdes = null;

            try {
                ParcelFileDescriptor fd = a(content);
                if (fd != null) {
                    assetFdes = new AssetFileDescriptor(fd, 0L, length);
                }

                return assetFdes;
            } catch (IOException var5) {
                var5.printStackTrace();
                return null;
            }
        }
    }

    public static AssetFileDescriptor a(String u, String extension) {
        try {
            int length = 0;
            byte[] content = g.a().a(u);
            if (content != null) {
                length = content.length;
            } else {
                content = g.a().c(u);
                if (content != null) {
                    length = content.length;
                }
            }

            if (length <= 0) {
                return null;
            }

            AssetFileDescriptor assetFdes = null;
            ParcelFileDescriptor fd = a(content);
            if (fd != null) {
                assetFdes = new AssetFileDescriptor(fd, 0L, length);
                return assetFdes;
            }
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        return null;
    }

    private static class a extends AsyncTask<Object, Object, Object> {
        byte[] a;
        AutoCloseOutputStream b;

        public a(byte[] in, AutoCloseOutputStream output) {
            this.a = in;
            this.b = output;
        }

        protected Object doInBackground(Object... Params) {
            try {
                this.b.write(this.a);
                this.b.flush();
            } catch (IOException var11) {
                var11.printStackTrace();
            } finally {
                try {
                    this.b.close();
                } catch (IOException var10) {
                }

            }

            return null;
        }

        protected void onPostExecute(Object result) {
        }
    }
}
