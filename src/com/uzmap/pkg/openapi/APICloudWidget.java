package com.uzmap.pkg.openapi;

import android.content.Context;
import android.widget.FrameLayout;
import com.uzmap.pkg.uzcore.aa.j;
import com.uzmap.pkg.uzcore.external.l;
import com.uzmap.pkg.uzcore.i;
import com.uzmap.pkg.uzcore.uzmodule.e;

final class APICloudWidget extends FrameLayout {
    private i a;

    APICloudWidget(Context context) {
        super(context);
    }

    void initialize(Context context, e wgtInfo) {
        j.a(wgtInfo);
        this.a = new i(context, wgtInfo);
        this.a.a();
        LayoutParams parm = l.d(l.d, l.d);
        this.a.setLayoutParams(parm);
        this.a.b(false);
        this.a.b(1);
        this.addView(this.a);
        this.a.d();
    }

    public void sendEvent() {
        if (this.a != null) {
            ;
        }
    }

    public void addEventListener() {
        if (this.a != null) {
            ;
        }
    }

    public void execScript(String winName, String frameName, String script) {
        if (this.a != null) {
            ;
        }
    }

    public void loadUrl(String url) {
        if (this.a != null) {
            ;
        }
    }

    public void destroy() {
        if (this.a != null) {
            this.removeView(this.a);
            this.a.p();
            this.a = null;
        }
    }
}
