package com.uzmap.pkg.a.b;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class p {
    public static String a = "volleylx";

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

        for (int i = 2; i < trace.length; ++i) {
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

    static class a2 {
        private final List<a1> a3;
        private boolean b;

        a2(List<a1> a3) {
            this.a3 = a3;
        }

        public synchronized void a(String header) {
            this.b = true;
            long duration = this.a();
            if (duration > 0L) {
                long prevTime = this.a3.get(0).c;
                p.b("(%-4d ms) %s", duration, header);

                long thisTime;
                for (Iterator var7 = a3.iterator(); var7.hasNext(); prevTime = thisTime) {
                    a1 marker = (a1) var7.next();
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
            if (this.a3.size() == 0) {
                return 0L;
            } else {
                long first = this.a3.get(0).c;
                long last = this.a3.get(this.a3.size() - 1).c;
                return last - first;
            }
        }

        private static class a1 {
            public final String a;
            public final long b;
            public final long c;

            private a1(String a, long b, long c) {
                this.a = a;
                this.b = b;
                this.c = c;
            }
        }
    }
}
