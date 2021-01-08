package com.uzmap.pkg.uzkit.fineHttp;

import com.uzmap.pkg.uzcore.annotation.UzOpenAPI;
import org.json.JSONObject;

public interface ProgressListener extends RequestListener {
    @UzOpenAPI
    void onProgress(int var1, JSONObject var2);
}
