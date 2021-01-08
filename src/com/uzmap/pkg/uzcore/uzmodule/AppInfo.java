//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzcore.uzmodule;

import android.text.TextUtils;
import com.uzmap.pkg.uzkit.data.b;
import java.util.Hashtable;

public class AppInfo {
    public String appId;
    public String appName;
    private Hashtable<String, b> featureList;

    public AppInfo(Hashtable<String, b> features) {
        this.featureList = features;
    }

    public String getFeatureValue(String featureName, String paramName) {
        if (!TextUtils.isEmpty(featureName) && !TextUtils.isEmpty(paramName)) {
            if (this.featureList != null) {
                b feature = (b)this.featureList.get(featureName);
                if (feature != null) {
                    return feature.a(paramName);
                }
            }

            return null;
        } else {
            return null;
        }
    }

    public String toString() {
        return "appId: " + this.appId + "\n" + "appName: " + this.appName;
    }
}
