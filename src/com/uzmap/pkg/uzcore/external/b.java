package com.uzmap.pkg.uzcore.external;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

import java.util.Hashtable;

public class b implements OnGlobalLayoutListener {
    private static final Hashtable<String, b> b = new Hashtable();
    protected Activity a;
    private View c;
    private int d;
    private LayoutParams e;
    private int f;

    private b(Activity activity) {
        this.a = activity;
        FrameLayout content = (FrameLayout) activity.findViewById(16908290);
        if (content != null && content.getChildCount() != 0) {
            this.c = content.getChildAt(0);

            try {
                this.e = (LayoutParams) this.c.getLayoutParams();
                this.f = this.e.height;
            } catch (Exception var4) {
                var4.printStackTrace();
                return;
            }

            this.c.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }
    }

    public static void a(Activity activity) {
        String tag = String.valueOf(activity.hashCode());
        if (!b.contains(tag)) {
            b.put(tag, new b(activity));
        }
    }

    public void onGlobalLayout() {
        this.a();
    }

    private void a() {
        int curUsableHeight = this.b();
        if (this.d != curUsableHeight) {
            int visualHeight = this.c.getRootView().getHeight();
            int diffHeight = visualHeight - curUsableHeight;
            if (diffHeight > visualHeight / 4) {
                this.e.height = visualHeight - diffHeight;
            } else {
                this.e.height = this.f;
            }

            this.c.requestLayout();
            this.d = curUsableHeight;
        }
    }

    private int b() {
        Rect r = new Rect();
        this.c.getWindowVisibleDisplayFrame(r);
        return r.bottom;
    }
}
