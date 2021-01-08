package com.uzmap.pkg.uzcore.external;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.webkit.ValueCallback;
import android.widget.Toast;
import com.uzmap.pkg.uzkit.UZUtility;
import com.uzmap.pkg.uzkit.fineHttp.ProgressListener;
import com.uzmap.pkg.uzkit.fineHttp.RequestParam;
import com.uzmap.pkg.uzkit.fineHttp.Response;
import com.uzmap.pkg.uzkit.fineHttp.UZHttpClient;
import org.json.JSONObject;

import java.io.File;

public class c extends ProgressDialog implements ProgressListener {
    private String a;
    private String b;
    private RequestParam c;
    private ValueCallback<Object> d;
    private final Handler e = new Handler();

    public c(Context context, Object o) {
        super(context);
        this.a();
    }

    private void a() {
        this.setTitle("下载附件");
        this.setProgress(0);
        this.setCancelable(false);
        this.setProgressStyle(1);
        this.setMax(100);
        this.setButton(-1, "取消", new OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                c.this.c();
            }
        });
    }

    public void a(String url) {
        this.a = url;
    }

    public void b(String mimetype) {
        this.b = mimetype;
    }

    public void a(ValueCallback<Object> callback) {
        this.d = callback;
    }

    public void show() {
        super.show();
        this.b();
    }

    private void b() {
        if (TextUtils.isEmpty(this.a)) {
            throw new RuntimeException("download url is null");
        } else {
            this.c = new RequestParam();
            this.c.setUrl(this.a);
            this.c.setMethod(5);
            String defaultPath = UZUtility.getExternaDownloadDir();
            this.c.setDefaultSavePath(defaultPath);
            this.c.setWillReportProgress(true);
            this.c.setCacheable(false);
            UZHttpClient.get().execute(this.c, this);
        }
    }

    private void c() {
        UZHttpClient.get().cancelDownload(this.c.getTag());
        this.dismiss();
        this.d();
    }

    private void d() {
        if (this.d != null) {
            this.d.onReceiveValue(null);
        }

    }

    public void onResult(Response result) {
    }

    public void onProgress(final int state, final JSONObject result) {
        Runnable action = new Runnable() {
            public void run() {
                String savePath;
                if (state == 0) {
                    savePath = result.optString("percent");
                    float p = Float.parseFloat(savePath);
                    c.this.setProgress((int) p);
                } else if (1 == state) {
                    c.this.dismiss();
                    savePath = result.optString("savePath");
                    c.this.a(savePath, c.this.b != null ? c.this.b : result.optString("contentType"));
                    c.this.d();
                } else if (2 == state) {
                    c.this.dismiss();
                    Toast.makeText(c.this.getContext(), "下载失败", 0).show();
                    c.this.d();
                }

            }
        };
        this.e.post(action);
    }

    private void a(String filePath, String mimetype) {
        Intent installIntent = new Intent("android.intent.action.VIEW");
        String filename = filePath.contains("file://") ? filePath : "file://" + filePath;
        Uri path = Uri.parse(filename);
        if (path.getScheme() == null) {
            path = Uri.fromFile(new File(filename));
        }

        installIntent.setDataAndType(path, mimetype);
        installIntent.setFlags(268435456);

        try {
            this.getContext().startActivity(installIntent);
        } catch (Exception var7) {
            var7.printStackTrace();
            Toast.makeText(this.getContext(), "未找到可执行的应用", 0).show();
        }

    }
}
