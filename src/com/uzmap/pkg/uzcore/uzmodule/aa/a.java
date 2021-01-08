package com.uzmap.pkg.uzcore.uzmodule.aa;

import android.text.TextUtils;
import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;
import com.uzmap.pkg.uzkit.fineHttp.ProgressListener;
import com.uzmap.pkg.uzkit.fineHttp.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a extends UZModuleContext implements ProgressListener {
    public a(String json, UZWebView webView) {
        super(json, webView);
    }

    public void onProgress(int state, JSONObject result) {
        if (state == 0) {
            this.success(result, false);
        } else {
            this.success(result, true);
        }

    }

    public void onResult(Response result) {
        if (!result.success()) {
            Object body = result.error;

            try {
                body = new JSONObject(result.error);
            } catch (Exception var8) {
            }

            JSONObject json = new JSONObject();

            try {
                json.put("msg", result.error);
                json.put("body", body);
                json.put("statusCode", result.statusCode);
                json.put("code", result.errorCode);
            } catch (JSONException var7) {
                var7.printStackTrace();
            }

            this.error(null, json, true);
        } else {
            boolean needToJson = this.a();
            boolean validFormat = true;
            Object content = result.content;
            if (needToJson) {
                try {
                    content = new JSONObject(result.content);
                } catch (Exception var12) {
                    validFormat = false;
                }

                if (!validFormat) {
                    try {
                        content = new JSONArray(result.content);
                        validFormat = true;
                    } catch (Exception var11) {
                        validFormat = false;
                    }
                }
            }

            JSONObject json;
            if (!validFormat) {
                json = new JSONObject();

                try {
                    json.put("msg", "服务器返回数据格式错误");
                    json.put("body", result.content);
                    json.put("statusCode", result.statusCode);
                    json.put("code", 3);
                } catch (JSONException var9) {
                    var9.printStackTrace();
                }

                this.error(null, json, true);
            } else {
                if (!this.b()) {
                    this.success(content.toString(), needToJson, true);
                } else {
                    json = new JSONObject();

                    try {
                        json.put("body", content);
                        json.put("headers", result.headers);
                        json.put("statusCode", result.statusCode);
                    } catch (JSONException var10) {
                        var10.printStackTrace();
                    }

                    this.success(json, true);
                }

            }
        }
    }

    private boolean a() {
        String dataType = this.optString("dataType");
        return TextUtils.isEmpty(dataType) ? true : "json".equalsIgnoreCase(dataType);
    }

    private boolean b() {
        return this.optBoolean("returnAll");
    }
}
