//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzkit.analysis;

import com.uzmap.pkg.uzkit.a.a.a;

public class UZAnalysis {
    private static UZAnalysis a = new UZAnalysis();

    public UZAnalysis() {
    }

    public static UZAnalysis get() {
        return a;
    }

    public void sendEventInfo(String name) {
        com.uzmap.pkg.uzkit.a.a.a.a().d(name);
    }
}
