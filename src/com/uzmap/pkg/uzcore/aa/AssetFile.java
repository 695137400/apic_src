package com.uzmap.pkg.uzcore.aa;

import android.content.res.AssetFileDescriptor;
import android.os.AsyncTask;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseOutputStream;

import java.io.IOException;

public class AssetFile {
    public static ParcelFileDescriptor getAssetFile(byte[] data) throws IOException {
        ParcelFileDescriptor[] pipe = ParcelFileDescriptor.createPipe();
        ParcelFileDescriptor readSide = pipe[0];
        ParcelFileDescriptor writeSide = pipe[1];
        AutoCloseOutputStream out = new AutoCloseOutputStream(writeSide);
        (new AutoFile(data, out)).execute((Object[]) null);
        return readSide;
    }

    public static AssetFileDescriptor getAssetFile(String url) {
        int length = 0;
        byte[] content = UrlUtil.getInstance().getUrlBit(url);
        if (content != null) {
            length = content.length;
        } else {
            content = UrlUtil.getInstance().putUrl(url);
            if (content != null) {
                length = content.length;
            }
        }

        if (length <= 0) {
            return null;
        } else {
            AssetFileDescriptor assetFdes = null;

            try {
                ParcelFileDescriptor fd = getAssetFile(content);
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

    private static class AutoFile extends AsyncTask<Object, Object, Object> {
        byte[] bytes;
        AutoCloseOutputStream stream;

        public AutoFile(byte[] in, AutoCloseOutputStream output) {
            this.bytes = in;
            this.stream = output;
        }

        protected Object doInBackground(Object... Params) {
            try {
                this.stream.write(this.bytes);
                this.stream.flush();
            } catch (IOException var11) {
                var11.printStackTrace();
            } finally {
                try {
                    this.stream.close();
                } catch (IOException var10) {
                }

            }

            return null;
        }

        protected void onPostExecute(Object result) {
        }
    }
}
