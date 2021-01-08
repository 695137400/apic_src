//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b.a1;

import android.content.Intent;
import com.uzmap.pkg.a.b.i;
import com.uzmap.pkg.a.b.o;

public class a extends o {
    private static final long serialVersionUID = 3209225894087981655L;
    public Intent b;

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
