package com.uzmap.pkg.uzcore.uzmodule;

import android.text.TextUtils;

import java.util.Hashtable;

public class AppInfo {
    public String appId;
    public String appName;
    private final Hashtable<String, com.uzmap.pkg.uzkit.data.b> featureList;

    public AppInfo(Hashtable<String, com.uzmap.pkg.uzkit.data.b> features) {
        this.featureList = features;
    }

    public String getFeatureValue(String featureName, String paramName) {
        if (!TextUtils.isEmpty(featureName) && !TextUtils.isEmpty(paramName)) {
            if (this.featureList != null) {
                com.uzmap.pkg.uzkit.data.b feature = this.featureList.get(featureName);
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
