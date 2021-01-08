package com.uzmap.pkg.uzkit.b;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.params.HttpParams;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.SecureRandom;

public class a extends SSLSocketFactory {
    private final SSLContext a = SSLContext.getInstance("TLS");

    public a(KeyStore ksP12, String keyPass) throws Exception {
        super(ksP12);
        KeyManagerFactory kMgrFty = null;
        TrustManager[] trustMgrs = null;
        KeyManager[] keyMgrs = null;
        TrustManager TrustMgr = null;
        TrustMgr = new d(ksP12);
        kMgrFty = KeyManagerFactory.getInstance("X509");
        char[] password = null;
        if (keyPass != null) {
            password = keyPass.toCharArray();
        }

        kMgrFty.init(ksP12, password);
        keyMgrs = kMgrFty.getKeyManagers();
        trustMgrs = new TrustManager[]{TrustMgr};
        SecureRandom secureRandom = new SecureRandom();
        this.a.init(keyMgrs, trustMgrs, secureRandom);
    }

    public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException {
        javax.net.ssl.SSLSocketFactory socketfty = this.a.getSocketFactory();
        Socket rsocket = socketfty.createSocket(socket, host, port, autoClose);
        return rsocket;
    }

    public Socket connectSocket(Socket sock, String host, int port, InetAddress localAddress, int localPort, HttpParams params) throws IOException {
        return super.connectSocket(sock, host, port, localAddress, localPort, params);
    }

    public Socket createSocket() throws IOException {
        javax.net.ssl.SSLSocketFactory socketfty = this.a.getSocketFactory();
        Socket socket = socketfty.createSocket();
        return socket;
    }
}
