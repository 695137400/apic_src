//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class p {
    public static String a = "volleylx";

    public p() {
    }

    public static void a(String format, Object... args) {
    }

    public static void b(String format, Object... args) {
    }

    public static void c(String format, Object... args) {
    }

    public static void a(Throwable tr, String format, Object... args) {
    }

    private static String d(String format, Object... args) {
        String msg = args == null ? format : String.format(Locale.US, format, args);
        StackTraceElement[] trace = (new Throwable()).fillInStackTrace().getStackTrace();
        String caller = "<unknown>";

        for(int i = 2; i < trace.length; ++i) {
            Class<?> clazz = trace[i].getClass();
            if (!clazz.equals(p.class)) {
                String callingClass = trace[i].getClassName();
                callingClass = callingClass.substring(callingClass.lastIndexOf(46) + 1);
                callingClass = callingClass.substring(callingClass.lastIndexOf(36) + 1);
                caller = callingClass + "." + trace[i].getMethodName();
                break;
            }
        }

        return String.format(Locale.US, "[%d] %s: %s", Thread.currentThread().getId(), caller, msg);
    }

    public static void a(String msg) {
    }

    public static void b(String msg) {
    }

    public static void c(String msg) {
    }

    public static void a(String tag, Object o) {
    }

    static class aa {
        private final List<p.aa.aaa> a = new ArrayList<>();
        private boolean b;

        public synchronized void a(String header) {
            this.b = true;
            long duration = this.a();
            if (duration > 0L) {
                long prevTime = ((p.aa.aaa)this.a.get(0)).c;
                p.b("(%-4d ms) %s", duration, header);

                long thisTime;
                for(Iterator var7 = this.a.iterator(); var7.hasNext(); prevTime = thisTime) {
                    p.aa.aaa marker = (p.aa.aaa)var7.next();
                    thisTime = marker.c;
                    p.b("(+%-4d) [%2d] %s", thisTime - prevTime, marker.b, marker.a);
                }

            }
        }

        protected void finalize() throws Throwable {
            if (!this.b) {
                this.a("Request on the loose");
                p.c("Marker log finalized without finish() - uncaught exit point for request");
            }

        }

        private long a() {
            if (this.a.size() == 0) {
                return 0L;
            } else {
                long first = ((p.aa.aaa)this.a.get(0)).c;
                long last = ((p.aa.aaa)this.a.get(this.a.size() - 1)).c;
                return last - first;
            }
        }

        private static class aaa {
            public final String a;
            public final long b;
            public final long c;

            private aaa(String a, long b, long c) {
                this.a = a;
                this.b = b;
                this.c = c;
            }
        }
    }
}
