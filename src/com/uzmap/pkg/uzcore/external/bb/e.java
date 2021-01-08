package com.uzmap.pkg.uzcore.external.bb;

import android.content.Context;
import android.widget.FrameLayout;

public class e extends FrameLayout {
    private final float a;
    private int b = -1;
    private int c = -1;
    private e.a d;

    public e(Context context, Object o) {
        super(context);
        this.a = this.getResources().getDisplayMetrics().density;
    }

    public void a(e.a listener) {
        this.d = listener;
    }

    public int a(boolean todip) {
        int height;
        if (this.b > 0) {
            height = this.b;
        } else {
            int top = this.getTop();
            int bottom = this.getBottom();
            height = bottom - top;
            float scale = 1.0F;
            height = (int) ((float) height * 1.0F + 0.5F);
            this.b = height;
        }

        return todip ? (int) ((float) height / this.a + 0.5F) : height;
    }

    public int b(boolean todip) {
        int width;
        if (this.c > 0) {
            width = this.c;
        } else {
            width = this.getRight() - this.getLeft();
            float scale = 1.0F;
            width = (int) ((float) width * 1.0F + 0.5F);
            this.c = width;
        }

        return todip ? (int) ((float) width / this.a + 0.5F) : width;
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (this.d != null) {
            this.d.a(w, h, oldw, oldh);
        }

    }

    public void c(boolean fullscreen) {
        if (fullscreen) {
            this.b = com.uzmap.pkg.uzcore.d.a().k;
        } else {
            this.b = -1;
        }

    }

    public void a() {
        this.b = -1;
        this.c = -1;
    }

    public interface a {
        void a(int var1, int var2, int var3, int var4);
    }
}
