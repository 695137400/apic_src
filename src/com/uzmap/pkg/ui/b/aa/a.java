package com.uzmap.pkg.ui.b.aa;

import android.content.Intent;
import com.uzmap.pkg.ui.b.i;
import com.uzmap.pkg.ui.b.o;

public class a extends o {
    private static final long serialVersionUID = 3209225894087981655L;
    private Intent b;

    public a() {
        this.a(6);
    }

    public a(i response) {
        super(response);
        this.a(6);
    }

    public String getMessage() {
        return this.b != null ? "User needs to (re)enter credentials." : super.getMessage();
    }
}
