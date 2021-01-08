package com.uzmap.pkg.uzkit.b;

import android.text.TextUtils;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzkit.UZUtility;
import com.uzmap.pkg.uzkit.fineHttp.RequestParam;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import java.io.InputStream;
import java.security.KeyStore;
import java.util.HashMap;

public class b {
    public static HashMap<String, KeyStore> a = new HashMap();

    public static HttpClient a(RequestParam param) {
        try {
            KeyStore trustStore = a(param.capath, param.capsw);
            SSLSocketFactory socketFact = new a(trustStore, param.capsw);
            socketFact.setHostnameVerifier(new c());
            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            registry.register(new Scheme("https", socketFact, 443));
            HttpParams params = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(params, param.timeout);
            HttpConnectionParams.setSoTimeout(params, param.timeout);
            HttpConnectionParams.setSocketBufferSize(params, 8192);
            HttpClientParams.setRedirecting(params, false);
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, "UTF-8");
            ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);
            DefaultHttpClient client = new DefaultHttpClient(ccm, params);
            client.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
            return client;
        } catch (Exception var7) {
            var7.printStackTrace();
            return null;
        }
    }

    public static HttpClient b(RequestParam param) {
        int so_timeout = param.timeout;
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setStaleCheckingEnabled(params, false);
        HttpConnectionParams.setConnectionTimeout(params, so_timeout);
        HttpConnectionParams.setSoTimeout(params, so_timeout);
        HttpConnectionParams.setSocketBufferSize(params, 8192);
        HttpClientParams.setRedirecting(params, false);
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, "UTF-8");
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        ClientConnectionManager manager = new ThreadSafeClientConnManager(params, schemeRegistry);
        DefaultHttpClient client = new DefaultHttpClient(manager, params);
        client.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
        return client;
    }

    public static synchronized KeyStore a(String certPath, String certPsw) throws Exception {
        KeyStore trustStore;
        if (TextUtils.isEmpty(certPath)) {
            trustStore = KeyStore.getInstance("pkcs12");
            trustStore.load(null, null);
            return trustStore;
        } else {
            String alias = UZCoreUtil.toMD5(certPath);
            trustStore = a.get(alias);
            if (trustStore != null) {
                return trustStore;
            } else {
                InputStream input = UZUtility.guessInputStream(certPath);
                trustStore = KeyStore.getInstance("pkcs12");
                if (input != null) {
                    trustStore.load(input, certPsw.toCharArray());
                    input.close();
                    a.put(alias, trustStore);
                } else {
                    trustStore.load(null, null);
                }

                return trustStore;
            }
        }
    }
}
