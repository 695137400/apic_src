package com.uzmap.pkg.uzcore;

import android.app.Activity;
import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.uzmap.pkg.ui.intent.c;

public class q extends p {
    q(Activity context, Object o) {
        super(context, o);
    }

    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        if (this.d != null) {
            filePathCallback.onReceiveValue(null);
            return false;
        } else {
            this.d = new c(this.a, this);
            ((com.uzmap.pkg.ui.intent.c) this.d).av(filePathCallback, fileChooserParams);
            return true;
        }
    }
}
