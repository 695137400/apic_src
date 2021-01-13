package com.uzmap.pkg.uzkit.request;

import com.uzmap.pkg.ui.b.dd.aa.a;

import java.util.Map;

interface Params {
    a getHttpEntity();

    Map<String, String> getAdditionalHeaders();
}
