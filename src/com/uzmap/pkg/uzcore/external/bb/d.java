package com.uzmap.pkg.uzcore.external.bb;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import com.uzmap.pkg.uzcore.external.l;

public class d extends Dialog {
    private final Activity a;
    private final c b;

    public d(Context context, Object o) {
        super(context, 16974121);
        this.a = (Activity) context;
        this.c();
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
        this.b = new c(this.a, this);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(this.b, l.a(l.d, l.d));
    }

    public c a() {
        return this.b;
    }

    void b() {
        int flags = 1024;
        flags = flags | 2048;
        this.getWindow().clearFlags(flags);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return this.a.onKeyDown(keyCode, event);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return this.a.onKeyUp(keyCode, event);
    }

    private void c() {
        Window w = this.getWindow();
        w.setDimAmount(0.0F);
        w.setBackgroundDrawable(new ColorDrawable(0));
        int flags = w.getAttributes().flags;
        flags |= 67108864;
        flags |= 1024;
        flags |= 2048;
        w.addFlags(flags);
    }
}
