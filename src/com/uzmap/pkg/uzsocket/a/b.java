package com.uzmap.pkg.uzsocket.a;

import com.uzmap.pkg.uzsocket.d.d;
import com.uzmap.pkg.uzsocket.e.h;
import com.uzmap.pkg.uzsocket.e.i;

import java.net.InetSocketAddress;

public abstract class b implements e {
    public i a(a conn, com.uzmap.pkg.uzsocket.b.a draft, com.uzmap.pkg.uzsocket.e.a request) throws com.uzmap.pkg.uzsocket.c.b {
        return new com.uzmap.pkg.uzsocket.e.e();
    }

    public void a(a conn, com.uzmap.pkg.uzsocket.e.a request, h response) throws com.uzmap.pkg.uzsocket.c.b {
    }

    public void a(a conn, com.uzmap.pkg.uzsocket.e.a request) throws com.uzmap.pkg.uzsocket.c.b {
    }

    public void a(a conn, com.uzmap.pkg.uzsocket.d.d frame) {
    }

    public void b(a conn, com.uzmap.pkg.uzsocket.d.d f) {
        com.uzmap.pkg.uzsocket.d.e resp = new com.uzmap.pkg.uzsocket.d.e(f);
        resp.a(d.aemnu.e);
        conn.a(resp);
    }

    public void c(a conn, com.uzmap.pkg.uzsocket.d.d f) {
    }

    public String a(a conn) throws com.uzmap.pkg.uzsocket.c.b {
        InetSocketAddress adr = conn.a();
        if (adr == null) {
            throw new com.uzmap.pkg.uzsocket.c.d("socket not bound");
        } else {
            StringBuffer sb = new StringBuffer(90);
            sb.append("<cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"");
            sb.append(adr.getPort());
            sb.append("\" /></cross-domain-policy>\u0000");
            return sb.toString();
        }
    }
}
