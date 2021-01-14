package com.uzmap.pkg.uzkit.fineHttp;

import android.text.TextUtils;
import com.uzmap.pkg.uzapp.PropertiesUtil;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.uzmodule.internalmodule.UZConstant;
import com.uzmap.pkg.uzkit.UZUtility;
import com.uzmap.pkg.uzkit.data.UZWidgetInfo;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class RequestParam {
    public static final String API_APP_ID = "X-APICloud-AppId";
    public static final String API_APP_KEY = "X-APICloud-AppKey";
    public static final String API_APP_UUID = "X-APICloud-UUID";
    public static final String API_APP_PLATFORM = "X-APICloud-Platform";
    public static final int METHOD_GET = 0;
    public static final int METHOD_POST = 1;
    public static final int METHOD_HEAD = 2;
    public static final int METHOD_PUT = 3;
    public static final int METHOD_DELETE = 4;
    public static final int METHOD_DOWNLOAD = 5;
    public static final int DATA_TYPE_JSON = 0;
    public static final int DATA_TYPE_TEXT = 1;
    public static final int DEFAULT_TIMEOUT = 30000;
    public static final int MIN_PROGRESS_TIME = 400;
    private String tag;
    public String url;
    public int method;
    public boolean cache;
    public boolean allowResume;
    public boolean needEscape;
    public boolean report;
    public boolean needErrorInfo;
    public boolean returnAll;
    public int timeout;
    public int dataType;
    public String savePath;
    public String defaultSavePath;
    public String charset;
    public String capath;
    public String capsw;
    public Hashtable<String, String> heads;
    public Object body;
    public String stream;
    public List<g> values;
    public List<g> files;

    public RequestParam() {
        this.method = 0;
        this.dataType = 0;
        this.cache = true;
        this.needEscape = true;
        this.detmineTimeout(0);
    }

    public RequestParam(JSONObject agument) {
        this.parse(agument);
    }

    private void parse(JSONObject json) {
        if (json != null) {
            this.url = json.optString("url");
            this.cache = json.optBoolean("cache", true);
            this.needEscape = json.optBoolean("escape", true);
            this.allowResume = json.optBoolean("allowResume");
            this.returnAll = json.optBoolean("returnAll");
            this.report = json.optBoolean("report");
            this.detmineTimeout(json.optInt("timeout"));
            this.method = UZConstant.mapInt(json.optString("method"), 0);
            this.dataType = UZConstant.mapInt(json.optString("dataType"), 0);
            this.savePath = json.optString("savePath");
            this.charset = json.optString("charset");
            this.tag = json.optString("tag", null);
            this.transHeads(json.optJSONObject("headers"));
            JSONObject data = json.optJSONObject("data");
            if (data != null) {
                this.body = data.opt("body");
                this.stream = data.optString("stream");
                this.transValues(data.optJSONObject("values"));
                this.transFiles(data.optJSONObject("files"));
            }

            JSONObject keyStore = json.optJSONObject("certificate");
            if (keyStore != null) {
                this.capath = keyStore.optString("path");
                this.capsw = keyStore.optString("password");
            }

        }
    }

    public void setTag(String inTag) {
        if (TextUtils.isEmpty(this.tag)) {
            this.tag = inTag;
        }
    }

    public void setEscape(boolean need) {
        this.needEscape = need;
    }

    public void setNeedErrorInfo(boolean need) {
        this.needErrorInfo = need;
    }

    public void setDefaultSavePath(String path) {
        this.defaultSavePath = path;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAllowResume(boolean flag) {
        this.allowResume = flag;
    }

    public void setMethod(int meth) {
        this.method = meth;
    }

    public void setCacheable(boolean flag) {
        this.cache = flag;
    }

    public void setTimeout(int time) {
        this.detmineTimeout(time);
    }

    public void setResultDataType(int type) {
        this.dataType = type;
    }

    public void setWillReportProgress(boolean flag) {
        this.report = flag;
    }

    public void setFileSavePath(String path) {
        this.savePath = path;
    }

    public void setRqBody(Object bdy) {
        this.body = bdy;
    }

    public void setRqFile(String key, String value) {
        if (this.files == null) {
            this.files = new ArrayList();
        }

        g object = new g(key, value);
        this.files.add(object);
    }

    public void setRqValue(String key, String value) {
        if (this.values == null) {
            this.values = new ArrayList();
        }

        g object = new g(key, value);
        this.values.add(object);
    }

    public void setRqHeads(Map<String, String> headers) {
        if (headers != null) {
            if (this.heads == null) {
                this.heads = new Hashtable();
            }

            this.heads.putAll(headers);
        }
    }

    public void setRqHeads(String key, String value) {
        if (this.heads == null) {
            this.heads = new Hashtable();
        }

        if (!TextUtils.isEmpty(key)) {
            value = value != null ? value : "";
            this.heads.put(key, value);
        }
    }

    public void makeRealUrl(UZWidgetInfo winfo) {
        if (this.files != null) {
            List<g> newFiles = new ArrayList(this.files.size());
            Iterator var4 = this.files.iterator();

            while (var4.hasNext()) {
                g pair = (g) var4.next();
                String key = pair.a();
                String value = pair.b();
                value = UZUtility.makeRealPath(value, winfo);
                g newPair = new g(key, value);
                newFiles.add(newPair);
            }

            this.files = newFiles;
        }

        if (this.savePath != null) {
            this.savePath = UZUtility.makeRealPath(this.savePath, winfo);
        }

        if (this.capath != null) {
            this.capath = UZUtility.makeRealPath(this.capath, winfo);
        }

    }

    public String getTag() {
        if (this.tag != null) {
            return this.tag;
        } else {
            this.tag = UZCoreUtil.random(this.url);
            return this.tag;
        }
    }

    public String makeTmpFileName() {
        return this.getTag() + ".tmp";
    }

    public boolean hasSavePath() {
        return !TextUtils.isEmpty(this.savePath);
    }

    public boolean needToJson() {
        return this.dataType == 0;
    }

    public boolean needGzip() {
        return this.method != 5;
    }

    public boolean onlyStream() {
        return !TextUtils.isEmpty(this.stream);
    }

    public boolean onlyBody() {
        return this.body != null;
    }

    public boolean onlyValue() {
        return this.values != null && this.files == null;
    }

    public boolean multi() {
        return this.values != null && this.files != null || this.files != null;
    }

    private void transValues(JSONObject data) {
        if (data != null && data.length() != 0) {
            Iterator<String> keys = data.keys();
            this.values = new ArrayList(data.length());

            while (keys.hasNext()) {
                String key = keys.next();
                Object val = data.opt(key);
                g kvp = new g(key, val);
                this.values.add(kvp);
            }

        }
    }

    private void transFiles(JSONObject data) {
        if (data != null && data.length() != 0) {
            Iterator<String> keys = data.keys();
            this.files = new ArrayList(data.length());

            while (true) {
                while (true) {
                    String key;
                    Object val;
                    do {
                        if (!keys.hasNext()) {
                            return;
                        }

                        key = keys.next();
                        val = data.opt(key);
                    } while (val == null);

                    if (val instanceof JSONArray) {
                        JSONArray array = (JSONArray) val;
                        int l = array.length();
                        String akey = key;

                        for (int i = 0; i < l; ++i) {
                            Object item = array.opt(i);
                            if (item != null) {
                                g kvp = new g(akey, item);
                                this.files.add(kvp);
                            }
                        }
                    } else {
                        g kvp = new g(key, val);
                        this.files.add(kvp);
                    }
                }
            }
        }
    }

    private void transHeads(JSONObject data) {
        if (data != null && data.length() != 0) {
            if (this.heads == null) {
                this.heads = new Hashtable(data.length());
            }

            Iterator keys = data.keys();

            while (keys.hasNext()) {
                String key = (String) keys.next();
                String value = data.optString(key);
                if (!TextUtils.isEmpty(key)) {
                    value = value != null ? value : "";
                    this.heads.put(key, value);
                }
            }

        }
    }

    public void setInSecure(String widgetId) {
        this.setRqHeads("X-APICloud-AppId", widgetId);
        this.setRqHeads("X-APICloud-AppKey", PropertiesUtil.a(widgetId));
        this.setRqHeads("X-APICloud-UUID", UZCoreUtil.getUUID());
        this.setRqHeads("X-APICloud-Platform", "0");
    }

    public long length() {
        long l = 0L;
        g kvp;
        Iterator var4;
        if (this.values != null) {
            for (var4 = this.values.iterator(); var4.hasNext(); l += kvp.c()) {
                kvp = (g) var4.next();
            }
        }

        if (this.heads != null) {
            String key;
            for (var4 = this.heads.values().iterator(); var4.hasNext(); l += key.length()) {
                key = (String) var4.next();
            }

            for (var4 = this.heads.keySet().iterator(); var4.hasNext(); l += key.length()) {
                key = (String) var4.next();
            }
        }

        return l;
    }

    private void detmineTimeout(int time) {
        if (time <= 0) {
            this.timeout = 30000;
        } else {
            this.timeout = time * 1000;
        }

    }

    public int timeout() {
        return this.timeout;
    }

    public String toString() {
        String sp = '@' + Integer.toHexString(this.hashCode());
        return "{\ntag=" + this.tag + ",\n" + "url=" + this.url + ",\n" + "method=" + this.method + ",\n" + "cache=" + this.cache + ",\n" + "allowResume=" + this.allowResume + ",\n" + "needEscape=" + this.needEscape + ",\n" + "report=" + this.report + ",\n" + "needErrorInfo=" + this.needErrorInfo + ",\n" + "returnAll=" + this.returnAll + ",\n" + "timeout=" + this.timeout + ",\n" + "dataType=" + this.dataType + ",\n" + "savePath=" + this.savePath + ",\n" + "defaultSavePath=" + this.defaultSavePath + ",\n" + "body=" + this.body + ",\n" + "stream=" + this.stream + ",\n" + "charset=" + this.charset + ",\n" + "capath=" + this.capath + ",\n" + "capsw=" + this.capsw + ",\n" + "heads=" + this.heads + ",\n" + "values=" + this.values + ",\n" + "files=" + this.files + ",\n" + "[" + sp + "]" + "\n}";
    }
}
