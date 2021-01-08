package com.uzmap.pkg.uzkit.a;

import org.json.JSONObject;

public final class a {
    public static JSONObject a(String json) {
        if (json == null) {
            return null;
        } else {
            try {
                return new JSONObject(json);
            } catch (Exception var2) {
                var2.printStackTrace();
                return null;
            }
        }
    }
}
