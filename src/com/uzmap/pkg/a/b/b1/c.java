//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b.b1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class c extends SSLSocketFactory {
    private SSLContext a = SSLContext.getInstance("TLS");

    public c(KeyStore ksP12, String keyPass) throws Exception {
        KeyManagerFactory kFactory = KeyManagerFactory.getInstance("X509");
        char[] password = null;
        if (keyPass != null) {
            password = keyPass.toCharArray();
        }

        kFactory.init(ksP12, password);
        KeyManager[] kManagers = kFactory.getKeyManagers();
        TrustManager trustMgr = new d(ksP12);
        TrustManager[] tManagers = new TrustManager[]{trustMgr};
        SecureRandom random = new SecureRandom();
        this.a.init(kManagers, tManagers, random);
    }

    public String[] getDefaultCipherSuites() {
        SSLSocketFactory socketfact = this.a.getSocketFactory();
        return socketfact.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        SSLSocketFactory socketfact = this.a.getSocketFactory();
        return socketfact.getSupportedCipherSuites();
    }

    public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException {
        SSLSocketFactory socketfact = this.a.getSocketFactory();
        Socket result = socketfact.createSocket(socket, host, port, autoClose);
        return result;
    }

    public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
        SSLSocketFactory socketfact = this.a.getSocketFactory();
        return socketfact.createSocket(host, port);
    }

    public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException, UnknownHostException {
        SSLSocketFactory socketfact = this.a.getSocketFactory();
        return socketfact.createSocket(host, port, localHost, localPort);
    }

    public Socket createSocket(InetAddress host, int port) throws IOException {
        SSLSocketFactory socketfact = this.a.getSocketFactory();
        return socketfact.createSocket(host, port);
    }

    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
        SSLSocketFactory socketfact = this.a.getSocketFactory();
        return socketfact.createSocket(address, port, localAddress, localPort);
    }
}
