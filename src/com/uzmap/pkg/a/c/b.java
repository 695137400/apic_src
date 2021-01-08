//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.c;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.webkit.ValueCallback;
import android.widget.Toast;
import com.uzmap.pkg.uzcore.f;
import com.uzmap.pkg.uzcore.uzmodule.UZActivityResult;
import java.io.File;

public class b {
    protected ValueCallback<Uri> a;
    protected Uri b;
    protected UZActivityResult c;
    protected boolean d;
    protected boolean e;
    protected Activity f;

    public b(Activity context, UZActivityResult activityResult) {
        this.f = context;
        this.c = activityResult;
    }

    public void a(int resultCode, Intent intent) {
        if (resultCode == 0 && this.e) {
            this.e = false;
        } else {
            Uri result = intent != null && resultCode == -1 ? intent.getData() : null;
            if (result == null && intent == null && resultCode == -1 && this.b != null) {
                result = this.b;
                File cameraFile = new File(this.b.toString().replaceFirst("file://", ""));
                if (cameraFile.exists()) {
                    this.f.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", result));
                }
            }

            this.a.onReceiveValue(result);
            this.d = true;
            this.e = false;
        }
    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        String mediaSourceKey = "capture";
        String mediaSourceValueCamera = "camera";
        String mediaSourceValueFileSystem = "filesystem";
        String mediaSourceValueCamcorder = "camcorder";
        String mediaSourceValueMicrophone = "microphone";
        String mediaSource = "filesystem";
        if (this.a == null) {
            this.a = uploadMsg;
            String[] params = acceptType.split(";");
            String mimeType = params[0];
            if (capture.length() > 0) {
                mediaSource = capture;
            }

            if (capture.equals("filesystem")) {
                String[] var15 = params;
                int var14 = params.length;

                for(int var13 = 0; var13 < var14; ++var13) {
                    String p = var15[var13];
                    String[] keyValue = p.split("=");
                    if (keyValue.length == 2 && "capture".equals(keyValue[0])) {
                        mediaSource = keyValue[1];
                    }
                }
            }

            this.b = null;
            Intent chooser;
            if (mimeType.equals("image/*")) {
                if (mediaSource.equals("camera")) {
                    this.a(this.e());
                } else {
                    chooser = this.a(String.valueOf(this.e()));
                    chooser.putExtra("android.intent.extra.INTENT", this.a("image/*"));
                    this.a(chooser);
                }
            } else if (mimeType.equals("video/*")) {
                if (mediaSource.equals("camcorder")) {
                    this.a(this.b());
                } else {
                    chooser = this.a(String.valueOf(this.b()));
                    chooser.putExtra("android.intent.extra.INTENT", this.a("video/*"));
                    this.a(chooser);
                }
            } else if (mimeType.equals("audio/*")) {
                if (mediaSource.equals("microphone")) {
                    this.a(this.c());
                } else {
                    chooser = this.a(String.valueOf(this.c()));
                    chooser.putExtra("android.intent.extra.INTENT", this.a("audio/*"));
                    this.a(chooser);
                }
            } else {
                this.a(this.d());
            }
        }
    }

    private void a(Intent intent) {
        try {
            f engine = com.uzmap.pkg.uzcore.f.b(this.f);
            if (engine == null) {
                throw new ActivityNotFoundException();
            }

            engine.a(this.c, intent, 100001, true);
        } catch (ActivityNotFoundException var5) {
            try {
                this.e = true;
                f engine = com.uzmap.pkg.uzcore.f.b(this.f);
                if (engine == null) {
                    throw new ActivityNotFoundException();
                }

                engine.a(this.c, this.d(), 100001, true);
            } catch (ActivityNotFoundException var4) {
                Toast.makeText(this.f, "文件上传功能已停用", 1).show();
            }
        }

    }

    private Intent d() {
        Intent i = new Intent("android.intent.action.GET_CONTENT");
        i.addCategory("android.intent.category.OPENABLE");
        i.setType("*/*");
        Intent chooser = this.a(this.e(), this.b(), this.c());
        chooser.putExtra("android.intent.extra.INTENT", i);
        return chooser;
    }

    private Intent a(Intent... intents) {
        Intent chooser = new Intent("android.intent.action.CHOOSER");
        chooser.putExtra("android.intent.extra.INITIAL_INTENTS", intents);
        chooser.putExtra("android.intent.extra.TITLE", "选择要上传的文件");
        return chooser;
    }

    private Intent a(String type) {
        Intent i = new Intent("android.intent.action.GET_CONTENT");
        i.addCategory("android.intent.category.OPENABLE");
        i.setType(type);
        return i;
    }

    private Intent e() {
        Intent cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
        this.b = this.a();
        cameraIntent.putExtra("output", this.b);
        return cameraIntent;
    }

    protected Uri a() {
        File externalDataDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File cameraDataDir = new File(externalDataDir.getAbsolutePath() + File.separator + "browser-photos");
        cameraDataDir.mkdirs();
        String cameraFilePath = cameraDataDir.getAbsolutePath() + File.separator + System.currentTimeMillis() + ".jpg";
        return Uri.fromFile(new File(cameraFilePath));
    }

    protected Intent b() {
        return new Intent("android.media.action.VIDEO_CAPTURE");
    }

    protected Intent c() {
        return new Intent("android.provider.MediaStore.RECORD_SOUND");
    }
}
