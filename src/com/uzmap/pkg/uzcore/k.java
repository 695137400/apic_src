package com.uzmap.pkg.uzcore;

import android.text.TextUtils;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Stack;

public class k {
    private final Stack<i> a = new Stack();
    private final Hashtable<String, i> b = new Hashtable();

    public void a(i widget) {
        String name = widget.c();
        this.b.put(name, widget);
        this.a.push(widget);
    }

    public i a(String name) {
        return !TextUtils.isEmpty(name) ? this.b.get(name) : null;
    }

    public i b(i widget) {
        int location = this.a.indexOf(widget);
        int retIndex = location - 1;
        return retIndex >= 0 ? this.a.get(retIndex) : null;
    }

    public void c(i widget) {
        String name = widget.c();
        this.b.remove(name);
        this.a.remove(widget);
    }

    public void a(com.uzmap.pkg.uzcore.uzmodule.aa.d event) {
        Iterator var3 = this.a.iterator();

        while (var3.hasNext()) {
            i wgt = (i) var3.next();
            wgt.a(event);
        }

    }

    public void a() {
        Iterator var2 = this.a.iterator();

        while (var2.hasNext()) {
            i widget = (i) var2.next();
            widget.p();
        }

        this.a.clear();
        this.b.clear();
    }
}
