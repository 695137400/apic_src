//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzcore.uzmodule.aa;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.text.TextUtils;
import com.uzmap.pkg.a.b.d.c.a;
import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.external.UzResourceCache;
import com.uzmap.pkg.uzcore.external.g;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;
import com.uzmap.pkg.uzkit.fineHttp.ProgressListener;
import com.uzmap.pkg.uzkit.fineHttp.RequestParam;
import com.uzmap.pkg.uzkit.fineHttp.Response;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import org.json.JSONObject;

public class h extends UZModuleContext implements ProgressListener {
    public String a;
    public int b;
    public boolean c;

    public h(String json, UZWebView webView) {
        super(json, webView);
        this.d();
    }

    private void d() {
        if (!this.empty()) {
            this.a = this.optString("url");
            this.c = this.optBoolean("thumbnail", true);
            String p = this.optString("policy", (String)null);
            if (p == null) {
                this.b = 1;
            } else if (p.equalsIgnoreCase("cache_else_network")) {
                this.b = 1;
            } else if (p.equalsIgnoreCase("no_cache")) {
                this.b = 2;
            } else if (p.equalsIgnoreCase("cache_only")) {
                this.b = 3;
            } else {
                this.b = 1;
            }

        }
    }

    public boolean a() {
        if (this.b == 1 || this.b == 3) {
            a entity = UzResourceCache.get().hasDiskCache(this.a);
            if (entity != null) {
                String local;
                if (this.c) {
                    if (!entity.hasThumbnail()) {
                        String thumb = this.a(entity.c);
                        entity.d = thumb;
                    }

                    local = entity.d;
                } else {
                    local = entity.c;
                }

                JSONObject json = new JSONObject();

                try {
                    json.put("url", local);
                    json.put("status", true);
                } catch (Exception var5) {
                }

                this.success(json, true);
                return true;
            }
        }

        return false;
    }

    public boolean b() {
        return !TextUtils.isEmpty(this.a);
    }

    public RequestParam c() {
        RequestParam param = new RequestParam();
        param.method = 5;
        param.url = this.a;
        param.cache = false;
        param.savePath = UzResourceCache.get().makeDiskFile(this.a);
        return param;
    }

    public void onResult(Response result) {
    }

    public void onProgress(int state, JSONObject result) {
        String finalUrl = null;
        boolean status = true;
        if (1 == state) {
            String local = result.optString("savePath", (String)null);
            String thumb = null;
            finalUrl = local;
            if (this.c) {
                thumb = this.a(local);
                finalUrl = thumb;
            }

            UzResourceCache.get().cacheDisk(this.a, local, thumb);
        } else if (2 == state) {
            finalUrl = this.a;
            status = false;
        }

        if (finalUrl != null) {
            JSONObject json = new JSONObject();

            try {
                json.put("url", finalUrl);
                json.put("status", status);
            } catch (Exception var7) {
            }

            this.success(json, true);
        }
    }

    private String a(String local) {
        Bitmap picture = g.a(local, 1);
        if (picture != null) {
            File thumb = UzResourceCache.transImageThumbPath(new File(local));
            File p = thumb.getParentFile();
            if (p != null && !p.exists()) {
                p.mkdirs();
            }

            try {
                FileOutputStream out = new FileOutputStream(thumb);
                BufferedOutputStream bos = new BufferedOutputStream(out);
                picture.compress(CompressFormat.PNG, 100, bos);
                bos.flush();
                bos.close();
                picture.recycle();
            } catch (Exception var7) {
                var7.printStackTrace();
            }

            return thumb.getAbsolutePath();
        } else {
            return null;
        }
    }
}
