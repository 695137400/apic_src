package com.uzmap.pkg.uzkit.request;

import android.os.SystemClock;
import android.text.TextUtils;
import com.uzmap.pkg.ui.b.aa.b;
import com.uzmap.pkg.ui.b.aa.f;
import com.uzmap.pkg.ui.b.i;
import com.uzmap.pkg.ui.b.j;
import com.uzmap.pkg.ui.b.l;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class HttpDownload extends Request {
    private String c;
    private String d;
    private String e;
    private String f;
    private long g;
    private long h;
    private boolean i;
    private boolean aBoolean;

    public HttpDownload(String url) {
        super(0, url);
    }

    public void setSavePath(String path) {
        this.c = path;
    }

    public String getSavePath() {
        return this.c;
    }

    public String getContentType() {
        return this.f;
    }

    public long getFileSize() {
        return this.g;
    }

    public void setDefaultSavePath(String path) {
        this.d = path;
    }

    public void setAllowResume(boolean flag) {
        this.i = flag;
    }

    private void setHasCached() {
        this.aBoolean = true;
    }

    public boolean hasCached() {
        return this.aBoolean;
    }

    public void onPreExecute() {
        String sp = this.c;
        File oldtmp;
        if (!TextUtils.isEmpty(sp)) {
            oldtmp = new File(sp);
            if (oldtmp.exists()) {
                if (this.shouldCache()) {
                    this.setHasCached();
                    this.setSavePath(oldtmp.getAbsolutePath());
                } else {
                    oldtmp.delete();
                }
            }

            this.e = sp + ".tmp";
        } else {
            this.e = this.d + this.makeTmpFileName();
        }

        if (this.i) {
            oldtmp = new File(this.e);
            if (oldtmp.exists()) {
                this.g = oldtmp.length();
                this.addHeader("Range", "bytes=" + this.g + "-");
            }
        }

        this.addHeader("Accept-Encoding", "identity");
    }

    public aaemnu getPriority() {
        return j.aaemnu.a;
    }

    protected void deliverResponse(i response) {
        com.uzmap.pkg.ui.b.p.a("deliverResponse", response);
        if (this.a != null) {
            HttpResult result = new HttpResult(response.a);
            result.headers = response.c;
            result.data = response.a();
            result.contentType = this.f;
            result.savePath = this.c;
            result.fileSize = this.g;
            this.a.onFinish(result);
        }

        if (this.b != null) {
            this.b.a(response);
        }

    }

    protected l<i> parseNetworkResponse(i response) {
        return !this.isCanceled() ? com.uzmap.pkg.ui.b.l.a(response, null) : com.uzmap.pkg.ui.b.l.a(new b());
    }

    public boolean handleResponse(com.uzmap.pkg.ui.b.dd.aa.a entity) throws IOException, f {
        File localPath = this.prepare(entity);
        if (localPath != null && this.hasCached()) {
            entity.b();
            return true;
        } else {
            long readLength = this.streamWrite(entity, this.g);
            if (readLength < 0L) {
                throw new IOException("local file IO error");
            } else {
                return true;
            }
        }
    }

    private File prepare(com.uzmap.pkg.ui.b.dd.aa.a entity) {
        String contentType = entity.c();
        String extension = "." + com.uzmap.pkg.ui.b.e.d(contentType);
        String newSavePath = "";
        if (!TextUtils.isEmpty(this.c)) {
            newSavePath = this.e.replace(".tmp", "");
        } else {
            newSavePath = this.e.replace(".tmp", extension);
        }

        this.setSavePath(newSavePath);
        File filePath = new File(newSavePath);
        if (filePath.exists() && this.shouldCache()) {
            this.setHasCached();
            return filePath;
        } else {
            filePath = new File(this.e);
            File parent = filePath.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            if (!this.i && filePath.exists()) {
                filePath.delete();
            }

            return null;
        }
    }

    private long streamWrite(com.uzmap.pkg.ui.b.dd.aa.a entity, long finishSize) throws IllegalStateException, IOException {
        if (this.hasCached()) {
            return 0L;
        } else {
            long contentlength = -1L;
            InputStream inputStream = entity.a1();
            if (inputStream == null) {
                throw new IOException("HTTP entity is empty");
            } else {
                contentlength = entity.f();
                if (contentlength > 2147483647L) {
                    throw new IOException("HTTP entity too large to be buffered in memory");
                } else {
                    if (contentlength <= 0L) {
                        contentlength = 0L;
                    }

                    contentlength += finishSize;
                    String contentEncoding = entity.d();
                    if (contentEncoding != null && "gzip".equalsIgnoreCase(contentEncoding)) {
                        inputStream = new GZIPInputStream(inputStream, 4096);
                    }

                    File localPath = new File(this.e);
                    FileOutputStream outputStream = new FileOutputStream(localPath, this.i);

                    try {
                        byte[] buffer = new byte[this.makeBufferLength(contentlength)];
                        long downSize = finishSize;
                        boolean var13 = false;

                        label120:
                        while (true) {
                            long now;
                            long step;
                            do {
                                if (this.isCanceled()) {
                                    break label120;
                                }

                                int read = inputStream.read(buffer);
                                if (read == -1) {
                                    break label120;
                                }

                                downSize += read;
                                outputStream.write(buffer, 0, read);
                                now = SystemClock.elapsedRealtime();
                                step = now - this.h;
                            } while (step <= 300L && downSize != contentlength);

                            this.h = now;
                            this.deliverProgress(contentlength, downSize);
                        }

                        outputStream.flush();
                        this.finished(contentlength, localPath, entity.c());
                    } finally {
                        outputStream.close();

                        try {
                            entity.b();
                        } catch (Exception var23) {
                        }

                    }

                    return contentlength;
                }
            }
        }
    }

    private void finished(long fileSize, File saveFile, String contentType) {
        try {
            File savePath = new File(this.c);
            if (saveFile != null) {
                saveFile.renameTo(savePath);
            }

            this.g = fileSize;
            this.f = contentType;
            this.c = savePath.getAbsolutePath();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    private int makeBufferLength(long contentLength) {
        int bulength = 2048;
        if (contentLength <= 0L) {
            bulength = 3072;
        } else if (contentLength >= 1048576L) {
            bulength = 5120;
        } else if (contentLength >= 512000L) {
            bulength = 8192;
        }

        return bulength;
    }

    private String makeTmpFileName() {
        return com.uzmap.pkg.ui.b.e.e(this.getUrl()) + ".tmp";
    }
}
