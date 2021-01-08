package com.uzmap.pkg.a.b.bb;

import android.text.TextUtils;
import com.uzmap.pkg.uzkit.UZUtility;

import javax.net.ssl.SSLSocketFactory;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.HashMap;

public class b {
    static HashMap<String, c> a = new HashMap();

    public static SSLSocketFactory a(String certPath, String certPsw) {
        boolean hasCert = !TextUtils.isEmpty(certPath) && !TextUtils.isEmpty(certPsw);
        String alias = !hasCert ? "none" : certPath;
        c sslSocket = a.get(alias);
        if (sslSocket != null) {
            return sslSocket;
        } else {
            try {
                if (hasCert) {
                    KeyStore trustStore = b(certPath, certPsw);
                    sslSocket = new c(trustStore, certPsw);
                } else {
                    sslSocket = new c(null, null);
                }

                a.put(alias, sslSocket);
                return sslSocket;
            } catch (Exception var6) {
                var6.printStackTrace();
                return null;
            }
        }
    }

    private static synchronized KeyStore b(String certPath, String certPsw) throws Exception {
        KeyStore trustStore = KeyStore.getInstance("pkcs12");
        InputStream input = null;
        char[] password = null;
        if (!TextUtils.isEmpty(certPath) && !TextUtils.isEmpty(certPsw)) {
            input = UZUtility.guessInputStream(certPath);
            password = certPsw.toCharArray();
        }

        trustStore.load(input, password);
        if (input != null) {
            input.close();
        }

        return trustStore;
    }

    public static SSLSocketFactory a() {
        return a(null, null);
    }
}
