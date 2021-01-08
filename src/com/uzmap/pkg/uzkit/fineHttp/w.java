package com.uzmap.pkg.uzkit.fineHttp;

import org.json.JSONException;
import org.json.JSONTokener;

import java.util.HashMap;

public class w extends JSONTokener {
    public static final HashMap<String, Object> a = new HashMap(8);

    static {
        a.put("amp", v.b);
        a.put("apos", v.c);
        a.put("gt", v.f);
        a.put("lt", v.g);
        a.put("quot", v.i);
    }

    public w(String s) {
        super(s);
    }

    public String a() throws JSONException {
        StringBuffer sb = new StringBuffer();

        int i;
        do {
            char c = this.next();
            if (c == 0) {
                throw this.syntaxError("Unclosed CDATA");
            }

            sb.append(c);
            i = sb.length() - 3;
        } while (i < 0 || sb.charAt(i) != ']' || sb.charAt(i + 1) != ']' || sb.charAt(i + 2) != '>');

        sb.setLength(i);
        return sb.toString();
    }

    public Object b() throws JSONException {
        char c;
        do {
            c = this.next();
        } while (Character.isWhitespace(c));

        if (c == 0) {
            return null;
        } else if (c == '<') {
            return v.g;
        } else {
            StringBuffer sb;
            for (sb = new StringBuffer(); c != '<' && c != 0; c = this.next()) {
                if (c == '&') {
                    sb.append(this.a(c));
                } else {
                    sb.append(c);
                }
            }

            this.back();
            return sb.toString().trim();
        }
    }

    public Object a(char ampersand) throws JSONException {
        StringBuffer sb = new StringBuffer();

        while (true) {
            char c = this.next();
            if (!Character.isLetterOrDigit(c) && c != '#') {
                if (c == ';') {
                    String string = sb.toString();
                    Object object = a.get(string);
                    return object != null ? object : ampersand + string + ";";
                }

                throw this.syntaxError("Missing ';' in XML entity: &" + sb);
            }

            sb.append(Character.toLowerCase(c));
        }
    }

    public Object c() throws JSONException {
        char c;
        do {
            c = this.next();
        } while (Character.isWhitespace(c));

        switch (c) {
            case '\u0000':
                throw this.syntaxError("Misshaped meta tag");
            case '!':
                return v.d;
            case '"':
            case '\'':
                char q = c;

                do {
                    c = this.next();
                    if (c == 0) {
                        throw this.syntaxError("Unterminated string");
                    }
                } while (c != q);

                return Boolean.TRUE;
            case '/':
                return v.j;
            case '<':
                return v.g;
            case '=':
                return v.e;
            case '>':
                return v.f;
            case '?':
                return v.h;
            default:
                while (true) {
                    c = this.next();
                    if (Character.isWhitespace(c)) {
                        return Boolean.TRUE;
                    }

                    switch (c) {
                        case '\u0000':
                        case '!':
                        case '"':
                        case '\'':
                        case '/':
                        case '<':
                        case '=':
                        case '>':
                        case '?':
                            this.back();
                            return Boolean.TRUE;
                    }
                }
        }
    }

    public Object d() throws JSONException {
        char c;
        do {
            c = this.next();
        } while (Character.isWhitespace(c));

        StringBuffer sb;
        switch (c) {
            case '\u0000':
                throw this.syntaxError("Misshaped element");
            case '!':
                return v.d;
            case '"':
            case '\'':
                char q = c;
                sb = new StringBuffer();

                while (true) {
                    c = this.next();
                    if (c == 0) {
                        throw this.syntaxError("Unterminated string");
                    }

                    if (c == q) {
                        return sb.toString();
                    }

                    if (c == '&') {
                        sb.append(this.a(c));
                    } else {
                        sb.append(c);
                    }
                }
            case '/':
                return v.j;
            case '<':
                throw this.syntaxError("Misplaced '<'");
            case '=':
                return v.e;
            case '>':
                return v.f;
            case '?':
                return v.h;
            default:
                sb = new StringBuffer();

                while (true) {
                    sb.append(c);
                    c = this.next();
                    if (Character.isWhitespace(c)) {
                        return sb.toString();
                    }

                    switch (c) {
                        case '\u0000':
                            return sb.toString();
                        case '!':
                        case '/':
                        case '=':
                        case '>':
                        case '?':
                        case '[':
                        case ']':
                            this.back();
                            return sb.toString();
                        case '"':
                        case '\'':
                        case '<':
                            throw this.syntaxError("Bad character in a name");
                    }
                }
        }
    }

    public boolean a(String to) throws JSONException {
        int offset = 0;
        int length = to.length();
        char[] circle = new char[length];

        char c;
        int i;
        for (i = 0; i < length; ++i) {
            c = this.next();
            if (c == 0) {
                return false;
            }

            circle[i] = c;
        }

        while (true) {
            int j = offset;
            boolean b = true;

            for (i = 0; i < length; ++i) {
                if (circle[j] != to.charAt(i)) {
                    b = false;
                    break;
                }

                ++j;
                if (j >= length) {
                    j -= length;
                }
            }

            if (b) {
                return true;
            }

            c = this.next();
            if (c == 0) {
                return false;
            }

            circle[offset] = c;
            ++offset;
            if (offset >= length) {
                offset -= length;
            }
        }
    }
}
