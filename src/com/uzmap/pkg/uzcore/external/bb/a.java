package com.uzmap.pkg.uzcore.external.bb;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;

public class a extends Dialog {
    private final Activity a;
    private final com.uzmap.pkg.uzcore.external.i b;

    public a(Context context, Object o) {
        super(context, 16974121);
        this.a = (Activity) context;
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
        this.c();
        this.b = new com.uzmap.pkg.uzcore.external.i(this.a, this);
        this.b.b(true);
        this.b.a(true);
        this.b.a();
        LayoutParams lp = new LayoutParams(-1, -1);
        this.setContentView(this.b, lp);
    }

    public void a() {
        super.show();
    }

    public void b() {
        super.dismiss();
    }

    public void a(String msg) {
        this.b.a(msg);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return true;
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return true;
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
