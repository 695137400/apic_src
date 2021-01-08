package com.uzmap.pkg.uzkit.a.aa;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

public class e {
    public static int a;
    static byte[] b;

    static {
        a = VERSION.SDK_INT < 11 ? 0 : 4;
        b = "\n".getBytes();
    }

    private SharedPreferences c;
    private SharedPreferences d;
    private SharedPreferences e;
    private SharedPreferences f;
    private String g;
    private final f h = new f() {
        public long a() {
            return System.currentTimeMillis();
        }
    };

    public void a(Context context) {
        this.c = context.getSharedPreferences("analytics_crash_info", a);
        this.d = context.getSharedPreferences("analytics_view_info", a);
        this.e = context.getSharedPreferences("analytics_run_info", a);
        this.f = context.getSharedPreferences("analytics_event_info", a);
        this.g = context.getFilesDir().getAbsolutePath() + "/" + "analysis/";
    }

    void a(String title, String content) {
        JSONObject json = new JSONObject();

        try {
            String sub = content;
            if (content != null && content.length() >= 3000) {
                sub = content.substring(0, 2990);
            }

            json.put("title", title);
            json.put("content", sub);
            json.put("timestamp", this.h.a());
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        Editor editor = this.c.edit();
        editor.putString("analytics_crash_info", json.toString());
        editor.commit();
    }

    String a() {
        return this.c.getString("analytics_crash_info", "");
    }

    void a(g tracker) {
        String existing = this.d.getString("analytics_view_info", null);
        JSONArray cache = null;

        try {
            if (existing != null) {
                cache = new JSONArray(existing);
            } else {
                cache = new JSONArray();
            }

            String from = tracker.a();
            String name = tracker.a;
            long duration = tracker.f() / 1000L;
            JSONObject json = new JSONObject();
            json.put("from", from);
            json.put("name", name);
            json.put("duration", duration);
            json.put("timestamp", this.h.a());
            cache.put(json);
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        Editor editor = this.d.edit();
        editor.putString("analytics_view_info", cache.toString());
        editor.commit();
    }

    String b() {
        return this.d.getString("analytics_view_info", "");
    }

    void a(b tracker) {
        long gap = tracker.f();
        int duration = (int) (gap / 1000L);
        String id = tracker.a();
        String existing = this.e.getString("analytics_run_info", null);
        JSONArray runInfo = null;

        try {
            if (existing != null) {
                runInfo = new JSONArray(existing);
            } else {
                runInfo = new JSONArray();
            }

            JSONObject json = new JSONObject();
            json.put("id", id);
            json.put("duration", duration);
            json.put("timestamp", this.h.a());
            runInfo.put(json);
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        Editor editor = this.e.edit();
        editor.putString("analytics_run_info", runInfo.toString());
        editor.commit();
    }

    String c() {
        return this.e.getString("analytics_run_info", "");
    }

    void a(String eventName) {
        String existing = this.f.getString("analytics_event_info", null);
        JSONArray cache = null;

        try {
            if (existing != null) {
                cache = new JSONArray(existing);
            } else {
                cache = new JSONArray();
            }

            JSONObject json = new JSONObject();
            json.put("name", eventName);
            json.put("timestamp", this.h.a());
            cache.put(json);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        Editor editor = this.f.edit();
        editor.putString("analytics_event_info", cache.toString());
        editor.commit();
    }

    String d() {
        return this.f.getString("analytics_event_info", "");
    }

    String e() {
        JSONArray viewInfo = new JSONArray();

        try {
            String pInfo = this.b();
            if (!TextUtils.isEmpty(pInfo)) {
                viewInfo = new JSONArray(pInfo);
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        JSONObject exceptionInfo = new JSONObject();

        try {
            String exInfo = this.a();
            if (!TextUtils.isEmpty(exInfo)) {
                exceptionInfo = new JSONObject(exInfo);
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        if (viewInfo.length() == 0 && exceptionInfo.length() == 0) {
            return null;
        } else {
            JSONObject behaviorInfo = new JSONObject();

            try {
                behaviorInfo.put("pageViewInfo", viewInfo);
                behaviorInfo.put("exceptionInfo", exceptionInfo);
            } catch (Exception var5) {
                var5.printStackTrace();
            }

            return behaviorInfo.toString();
        }
    }

    public String f() {
        JSONArray viewInfo = null;

        try {
            String eInfo = this.d();
            if (!TextUtils.isEmpty(eInfo)) {
                viewInfo = new JSONArray(eInfo);
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return viewInfo != null ? viewInfo.toString() : null;
    }

    public void g() {
        Editor editor = this.c.edit();
        editor.clear();
        editor.commit();
        editor = this.f.edit();
        editor.clear();
        editor.commit();
        editor = this.e.edit();
        editor.clear();
        editor.commit();
        editor = this.d.edit();
        editor.clear();
        editor.commit();
    }

    public void h() {
        Editor editor = this.e.edit();
        editor.clear();
        editor.commit();
    }

    public String i() {
        JSONArray runInfo = new JSONArray();

        try {
            String rInfo = this.c();
            if (!TextUtils.isEmpty(rInfo)) {
                runInfo = new JSONArray(rInfo);
            }

            int length = runInfo.length();
            HashMap<String, e.a> analyUnits = new HashMap();

            for (int i = 0; i < length; ++i) {
                JSONObject json = runInfo.optJSONObject(i);
                String id = json.optString("id");
                int duration = json.optInt("duration");
                long timestamp = json.optLong("timestamp");
                e.a mode = analyUnits.get(id);
                if (mode == null) {
                    mode = new e.a();
                }

                mode.a = id;
                mode.b += duration;
                mode.c = timestamp;
                analyUnits.put(id, mode);
            }

            runInfo = new JSONArray();
            e.a m;
            if (analyUnits.isEmpty()) {
                m = new e.a();
                m.a = "" + System.currentTimeMillis();
                m.b = 1;
                m.c = System.currentTimeMillis();
                runInfo.put(m.a());
            } else {
                Iterator var14 = analyUnits.values().iterator();

                while (var14.hasNext()) {
                    m = (e.a) var14.next();
                    runInfo.put(m.a());
                }
            }
        } catch (Exception var12) {
            var12.printStackTrace();
        }

        return runInfo.toString();
    }

    private class a {
        String a;
        int b;
        long c;

        public a() {
        }

        public JSONObject a() {
            JSONObject json = new JSONObject();

            try {
                json.put("id", this.a);
                json.put("duration", this.b);
                json.put("timestamp", this.c);
            } catch (Exception var3) {
                var3.printStackTrace();
            }

            return json;
        }
    }
}
