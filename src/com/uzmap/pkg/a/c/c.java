//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.c;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient.FileChooserParams;
import android.widget.Toast;
import com.uzmap.pkg.uzcore.f;
import com.uzmap.pkg.uzcore.uzmodule.UZActivityResult;

public class c extends b {
    private ValueCallback<Uri[]> h;
    private FileChooserParams i;

    public c(Activity context, UZActivityResult activityResult) {
        super(context, activityResult);
    }

    public void a(int resultCode, Intent intent) {
        Uri[] uris = this.b(resultCode, intent);
        this.h.onReceiveValue(uris);
        this.d = true;
    }

    public void a(ValueCallback<Uri[]> callback, FileChooserParams fileChooserParams) {
        if (this.h == null) {
            this.h = callback;
            this.i = fileChooserParams;
            Intent[] captureIntents = this.d();

            assert captureIntents != null && captureIntents.length > 0;

            Intent intent = null;
            if (fileChooserParams.isCaptureEnabled() && captureIntents.length == 1) {
                intent = captureIntents[0];
            } else {
                intent = new Intent("android.intent.action.CHOOSER");
                intent.putExtra("android.intent.extra.INITIAL_INTENTS", captureIntents);
                intent.putExtra("android.intent.extra.INTENT", fileChooserParams.createIntent());
            }

            this.a(intent);
        }
    }

    private Uri[] b(int resultCode, Intent intent) {
        if (resultCode == 0) {
            return null;
        } else {
            Uri result = intent != null && resultCode == -1 ? intent.getData() : null;
            if (result == null && intent == null && resultCode == -1 && this.b != null) {
                result = this.b;
            }

            Uri[] uris = null;
            if (result != null) {
                uris = new Uri[]{result};
            }

            return uris;
        }
    }

    private void a(Intent intent) {
        try {
            f engine = f.b(this.f);
            if (engine == null) {
                throw new ActivityNotFoundException();
            }

            engine.a(this.c, intent, 100001, true);
        } catch (ActivityNotFoundException var3) {
            Toast.makeText(this.f, "文件上传功能已停用", 1).show();
        }

    }

    private Intent[] d() {
        String mimeType = "*/*";
        String[] acceptTypes = this.i.getAcceptTypes();
        if (acceptTypes != null && acceptTypes.length > 0) {
            mimeType = acceptTypes[0];
        }

        Intent[] intents;
        if (mimeType.equals("image/*")) {
            intents = new Intent[]{this.a(this.a())};
        } else if (mimeType.equals("video/*")) {
            intents = new Intent[]{this.b()};
        } else if (mimeType.equals("audio/*")) {
            intents = new Intent[]{this.c()};
        } else {
            intents = new Intent[]{this.a(this.a()), this.b(), this.c()};
        }

        return intents;
    }

    private Intent a(Uri contentUri) {
        if (contentUri == null) {
            throw new IllegalArgumentException();
        } else {
            this.b = contentUri;
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.setFlags(3);
            intent.putExtra("output", this.b);
            return intent;
        }
    }
}
