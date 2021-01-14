//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzcore;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.uzmap.pkg.uzcore.external.l;
import com.uzmap.pkg.uzcore.uzmodule.aa.f;
import com.uzmap.pkg.uzcore.uzmodule.aa.g;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class h extends ViewPager {
    private final Context a;
    private g b;
    private final m c;
    private String d;
    private boolean e = true;
    private boolean f = true;
    private final List<com.uzmap.pkg.uzcore.g> g;
    private h.a h;

    public h(Context context, m hybridWindow) {
        super(context);
        this.a = context;
        this.c = hybridWindow;
        this.g = new ArrayList();
        this.setAnimationCacheEnabled(false);
        this.setAlwaysDrawnWithCacheEnabled(false);
        this.setFocusable(false);
        if (l.SDK_INT >= 9) {
            this.setOverScrollMode(2);
        }

        if (com.uzmap.pkg.uzcore.d.v) {
            l.a(this, 1);
        }

    }

    public void a(g moduleArgs) {
        this.b = moduleArgs;
        this.h = new h.a();
        this.setAdapter(this.h);
        this.setOnPageChangeListener(this.h);
        List<f> frames = moduleArgs.w;
        int size = frames != null ? frames.size() : 0;
        if (size != 0) {
            int limit = moduleArgs.s;
            this.f = limit != 0;
            this.setOffscreenPageLimit(limit);
            Iterator var6 = frames.iterator();

            while (var6.hasNext()) {
                f context = (f) var6.next();
                com.uzmap.pkg.uzcore.g child = this.a(context);
                this.g.add(child);
            }

            this.h.notifyDataSetChanged();
            if (moduleArgs.r != 0) {
                this.a(moduleArgs.r, false, false);
            } else {
                this.postDelayed(new Runnable() {
                    public void run() {
                        h.this.h.onPageSelected(0);
                    }
                }, 50L);
            }

        }
    }

    private com.uzmap.pkg.uzcore.g a(f moduleArgs) {
        com.uzmap.pkg.uzcore.a frame = new com.uzmap.pkg.uzcore.a(2, false, this.a, this.c);
        frame.d(moduleArgs.y);
        frame.i(moduleArgs.F);
        frame.setHorizontalScrollBarEnabled(moduleArgs.J);
        frame.setVerticalScrollBarEnabled(moduleArgs.I);
        frame.a(moduleArgs.H);
        android.widget.RelativeLayout.LayoutParams rlp = l.b(l.d, l.d);
        com.uzmap.pkg.uzcore.g parent = new com.uzmap.pkg.uzcore.g(this.a, null);
        parent.setLayoutParams(rlp);
        parent.a(frame);
        parent.a(moduleArgs.B);
        if (moduleArgs.h()) {
            l.a(parent, moduleArgs.a(this.c.l()));
        }

        frame.a();
        if (moduleArgs.E) {
            frame.a(moduleArgs.E);
        }

        return parent;
    }

    public void a() {
        this.h.notifyDataSetChanged();
    }

    public void a(boolean flag) {
        if (this.e != flag) {
            this.e = flag;
        }

    }

    private String a(int position) {
        return this.b.w.get(position).z;
    }

    public void a(int index, boolean smoothScroll, boolean reload) {
        int size = this.g.size();
        if (index < size && index >= 0) {
            com.uzmap.pkg.uzcore.g child = this.g.get(index);
            if (index != this.getCurrentItem()) {
                this.setCurrentItem(index, smoothScroll);
            }

            if (reload && child.c()) {
                child.b();
            }

        }
    }

    public void a(String name) {
        this.d = name;
    }

    public com.uzmap.pkg.uzcore.a b(String name) {
        if (TextUtils.isEmpty(name)) {
            return null;
        } else {
            Iterator var3 = this.g.iterator();

            while (var3.hasNext()) {
                com.uzmap.pkg.uzcore.g child = (com.uzmap.pkg.uzcore.g) var3.next();
                com.uzmap.pkg.uzcore.a view = child.a();
                if (name.equals(view.B())) {
                    return view;
                }
            }

            return null;
        }
    }

    public void b() {
        Iterator var2 = this.g.iterator();

        while (var2.hasNext()) {
            com.uzmap.pkg.uzcore.g hybridView = (com.uzmap.pkg.uzcore.g) var2.next();
            com.uzmap.pkg.uzcore.a child = hybridView.a();
            hybridView.setVisibility(8);
            this.removeView(hybridView);
            child.clearCache(false);
            child.destroy();
        }

        this.g.clear();
        this.b = null;
    }

    public void c() {
        Iterator var2 = this.g.iterator();

        while (var2.hasNext()) {
            com.uzmap.pkg.uzcore.g hybridView = (com.uzmap.pkg.uzcore.g) var2.next();
            com.uzmap.pkg.uzcore.a child = hybridView.a();
            child.stopLoading();
        }

    }

    public boolean onInterceptTouchEvent(MotionEvent e) {
        if (!this.e) {
            return false;
        } else {
            this.b(true);
            return super.onInterceptTouchEvent(e);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        return this.e && super.onTouchEvent(event);
    }

    public void a(int event, JSONObject jsonValue, String value) {
        Iterator var5 = this.g.iterator();

        while (var5.hasNext()) {
            com.uzmap.pkg.uzcore.g frame = (com.uzmap.pkg.uzcore.g) var5.next();
            frame.a().a(event, jsonValue, value);
        }

    }

    public void c(String script) {
        Iterator var3 = this.g.iterator();

        while (var3.hasNext()) {
            com.uzmap.pkg.uzcore.g frame = (com.uzmap.pkg.uzcore.g) var3.next();
            frame.a().e(script);
        }

    }

    private void b(boolean disallowIntercept) {
        ViewParent parent = this.getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(disallowIntercept);
        }

    }

    public String toString() {
        return "group[" + this.d + "]" + "@" + Integer.toHexString(this.hashCode());
    }

    class a extends PagerAdapter implements OnPageChangeListener {
        JSONObject a = new JSONObject();

        public a() {
        }

        public int getCount() {
            return h.this.g.size();
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
        }

        public boolean isViewFromObject(View child, Object obj) {
            return child == obj;
        }

        public Object instantiateItem(ViewGroup container, int position) {
            com.uzmap.pkg.uzcore.g child = h.this.g.get(position);
            if (child.getParent() != null) {
                return child;
            } else {
                container.addView(child);
                if (h.this.f) {
                    child.a(h.this.a(position));
                }

                return child;
            }
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (positionOffsetPixels == 0) {
                h.this.b(false);
            }

        }

        public void onPageScrollStateChanged(int state) {
        }

        public void onPageSelected(int position) {
            if (!h.this.g.isEmpty()) {
                long delay = h.this.e ? 350 : (l.SDK_INT <= 16 ? 70 : 50);
                if (!h.this.f) {
                    com.uzmap.pkg.uzcore.g child = h.this.g.get(position);
                    if (!child.c()) {
                        child.a(h.this.a(position));
                        delay = 300L;
                    }
                }

                if (h.this.b != null) {
                    this.a(position, delay);
                }

            }
        }

        private void a(final int position, long delay) {
            Runnable action = new Runnable() {
                public void run() {
                    if (!h.this.g.isEmpty()) {
                        com.uzmap.pkg.uzcore.g frame = h.this.g.get(position);
                        com.uzmap.pkg.uzcore.a child = frame.a();
                        int tempy = child.getScrollY();
                        child.scrollTo(0, tempy + 1);
                        String name = child.B();
                        a.this.a(name, position);
                        h.this.b.success(a.this.a, false);
                        child.scrollTo(0, tempy);
                        UZCoreUtil.hideSoftKeyboard(h.this.a, child);
                        if (!h.this.e) {
                            h.this.requestLayout();
                        }

                    }
                }
            };
            h.this.postDelayed(action, delay);
        }

        private void a(String name, int index) {
            try {
                this.a.put("name", name);
                this.a.put("index", index);
            } catch (Exception var4) {
            }

        }
    }
}
