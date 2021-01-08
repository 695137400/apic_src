package com.uzmap.pkg.uzcore;

import android.content.Intent;
import android.text.TextUtils;

import java.util.*;

public class l {
    private final Stack<x> a = new Stack();
    private final WeakHashMap<String, x> b = new WeakHashMap();

    public void a(x win) {
        String name = win.m();
        this.b.put(name, win);
        this.a.push(win);
    }

    public x a(String name) {
        if (!TextUtils.isEmpty(name)) {
            x result = null;
            x slid = this.b.get("slidLayout");
            if (slid != null) {
                result = ((o) slid).a(name);
            }

            return result != null ? result : this.b.get(name);
        } else {
            return null;
        }
    }

    public x b(x win) {
        String name = win.m();
        if (!TextUtils.isEmpty(name)) {
            x slid = this.b.get("slidLayout");
            if (slid != null && ((o) slid).a(name) != null) {
                return slid;
            }
        }

        return win;
    }

    public x c(x win) {
        int location = this.a.indexOf(win);
        int retIndex = location - 1;
        return retIndex >= 0 ? this.a.get(retIndex) : null;
    }

    public void d(x win) {
        String name = win.m();
        this.b.remove(name);
        this.a.removeElement(win);
    }

    public List<x> e(x win) {
        int index = this.a.indexOf(win);
        if (index >= 0) {
            int start = index + 1;
            int end = this.a.size();
            List<x> sublist = this.a.subList(start, end);
            return new ArrayList(sublist);
        } else {
            return null;
        }
    }

    protected void a() {
        Iterator var2 = this.a.iterator();

        while (var2.hasNext()) {
            x win = (x) var2.next();
            win.c();
        }

    }

    protected void b() {
        Iterator var2 = this.a.iterator();

        while (var2.hasNext()) {
            x win = (x) var2.next();
            win.d();
        }

    }

    protected void a(String callingPackage, Intent intent) {
        Iterator var4 = this.a.iterator();

        while (var4.hasNext()) {
            x win = (x) var4.next();
            win.a(callingPackage, intent);
        }

    }

    protected void a(boolean connected, String type) {
        Iterator var4 = this.a.iterator();

        while (var4.hasNext()) {
            x win = (x) var4.next();
            win.a(connected, type);
        }

    }

    protected final void a(Intent intent) {
        Iterator var3 = this.a.iterator();

        while (var3.hasNext()) {
            x win = (x) var3.next();
            win.a(intent);
        }

    }

    protected final void a(boolean longPress) {
        Iterator var3 = this.a.iterator();

        while (var3.hasNext()) {
            x win = (x) var3.next();
            win.b(longPress);
        }

    }

    public void a(boolean success, com.uzmap.pkg.uzkit.a.d lastPackage) {
        Iterator var4 = this.a.iterator();

        while (var4.hasNext()) {
            x win = (x) var4.next();
            win.a(success, lastPackage);
        }

    }

    protected void a(com.uzmap.pkg.uzcore.uzmodule.aa.d event) {
        Iterator var3 = this.a.iterator();

        while (var3.hasNext()) {
            x win = (x) var3.next();
            win.a(event);
        }

    }

    protected void c() {
        Iterator var2 = this.a.iterator();

        while (var2.hasNext()) {
            x win = (x) var2.next();
            win.w();
        }

        this.a.clear();
        this.b.clear();
    }
}
