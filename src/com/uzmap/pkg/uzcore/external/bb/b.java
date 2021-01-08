package com.uzmap.pkg.uzcore.external.bb;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class b extends FrameLayout {
    private b.a a;

    public b(Context ctx, Object o) {
        super(ctx);
        this.setBackgroundColor(-16777216);
        this.setClickable(true);
    }

    public boolean onTouchEvent(MotionEvent evt) {
        return true;
    }

    public void a(b.a listener) {
        this.a = listener;
    }

    public void a() {
        if (this.a != null) {
            this.a.a();
        }

        this.a = null;
    }

    public interface a {
        void a();
    }
}
