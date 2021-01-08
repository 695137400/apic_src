package com.uzmap.pkg.uzcore;

import android.app.Activity;
import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebView;

public class q extends p {
    q(Activity context, Object o) {
        super(context, o);
    }

    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        if (this.d != null) {
            filePathCallback.onReceiveValue(null);
            return false;
        } else {
            this.d = new com.uzmap.pkg.a.c.c(this.a, this);
            ((com.uzmap.pkg.a.c.c) this.d).av(filePathCallback, fileChooserParams);
            return true;
        }
    }
}
