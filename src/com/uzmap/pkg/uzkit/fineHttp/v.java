package com.uzmap.pkg.uzkit.fineHttp;

import org.json.JSONException;
import org.json.JSONObject;

public class v {
    public static final Character b = new Character('&');
    public static final Character c = new Character('\'');
    public static final Character d = new Character('!');
    public static final Character e = new Character('=');
    public static final Character f = new Character('>');
    public static final Character g = new Character('<');
    public static final Character h = new Character('?');
    public static final Character i = new Character('"');
    public static final Character j = new Character('/');
    public static String a = "plainText";

    public static void a() {
        a = "plainText";
    }

    private static boolean a(w x, JSONObject context, String name) throws JSONException {
        JSONObject jsonobject = null;
        Object token = x.d();
        String string;
        if (token == d) {
            char c = x.next();
            if (c == '-') {
                if (x.next() == '-') {
                    x.skipPast("-->");
                    return false;
                }

                x.back();
            } else if (c == '[') {
                token = x.d();
                if (token.equals("CDATA") && x.next() == '[') {
                    string = x.a();
                    if (string.length() > 0) {
                        context.accumulate(a, string);
                    }

                    return false;
                }

                throw x.syntaxError("Expected 'CDATA['");
            }

            int i = 1;

            do {
                token = x.c();
                if (token == null) {
                    throw x.syntaxError("Missing '>' after '<!'.");
                }

                if (token == g) {
                    ++i;
                } else if (token == f) {
                    --i;
                }
            } while (i > 0);

            return false;
        } else if (token == h) {
            x.skipPast("?>");
            return false;
        } else if (token == j) {
            token = x.d();
            if (name == null) {
                throw x.syntaxError("Mismatched close tag " + token);
            } else if (!token.equals(name)) {
                throw x.syntaxError("Mismatched " + name + " and " + token);
            } else if (x.d() != f) {
                throw x.syntaxError("Misshaped close tag");
            } else {
                return true;
            }
        } else if (token instanceof Character) {
            throw x.syntaxError("Misshaped tag");
        } else {
            String tagName = (String) token;
            token = null;
            jsonobject = new JSONObject();

            while (true) {
                if (token == null) {
                    token = x.d();
                }

                if (!(token instanceof String)) {
                    if (token == j) {
                        if (x.d() != f) {
                            throw x.syntaxError("Misshaped tag");
                        }

                        if (jsonobject.length() > 0) {
                            context.accumulate(tagName, jsonobject);
                        } else {
                            context.accumulate(tagName, "");
                        }

                        return false;
                    }

                    if (token != f) {
                        throw x.syntaxError("Misshaped tag");
                    }

                    while (true) {
                        token = x.b();
                        if (token == null) {
                            if (tagName != null) {
                                throw x.syntaxError("Unclosed tag " + tagName);
                            }

                            return false;
                        }

                        if (token instanceof String) {
                            string = (String) token;
                            if (string.length() > 0) {
                                jsonobject.accumulate(a, a(string));
                            }
                        } else if (token == g && a(x, jsonobject, tagName)) {
                            if (jsonobject.length() == 0) {
                                context.accumulate(tagName, "");
                            } else if (jsonobject.length() == 1 && jsonobject.opt(a) != null) {
                                context.accumulate(tagName, jsonobject.opt(a));
                            } else {
                                context.accumulate(tagName, jsonobject);
                            }

                            return false;
                        }
                    }
                }

                string = (String) token;
                token = x.d();
                if (token == e) {
                    token = x.d();
                    if (!(token instanceof String)) {
                        throw x.syntaxError("Missing value");
                    }

                    jsonobject.accumulate(string, a((String) token));
                    token = null;
                } else {
                    jsonobject.accumulate(string, "");
                }
            }
        }
    }

    public static Object a(String string) {
        if (string.equals("")) {
            return string;
        } else if (string.equalsIgnoreCase("true")) {
            return Boolean.TRUE;
        } else if (string.equalsIgnoreCase("false")) {
            return Boolean.FALSE;
        } else if (string.equalsIgnoreCase("null")) {
            return JSONObject.NULL;
        } else if (string.equals("0")) {
            return new Integer(0);
        } else {
            try {
                char initial = string.charAt(0);
                boolean negative = false;
                if (initial == '-') {
                    initial = string.charAt(1);
                    negative = true;
                }

                if (initial == '0' && string.charAt(negative ? 2 : 1) == '0') {
                    return string;
                }

                if (initial >= '0' && initial <= '9') {
                    if (string.indexOf(46) >= 0) {
                        return Double.valueOf(string);
                    }

                    if (string.indexOf(101) < 0 && string.indexOf(69) < 0) {
                        Long myLong = new Long(string);
                        if (myLong == (long) myLong.intValue()) {
                            return new Integer(myLong.intValue());
                        }

                        return myLong;
                    }
                }
            } catch (Exception var4) {
            }

            return string;
        }
    }

    public static JSONObject b(String string) throws JSONException {
        JSONObject jo = new JSONObject();
        w x = new w(string);

        while (x.more() && x.a("<")) {
            a(x, jo, null);
        }

        return jo;
    }
}
