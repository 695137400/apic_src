package com.uzmap.pkg.uzcore.uzmodule;

import com.uzmap.pkg.uzcore.aa.JSCore;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public final class ModuleMethod {
    public String name;
    public Constructor<?> b;
    public Hashtable<String, Method> c;
    public Hashtable<String, Method> d;

    public ModuleMethod(Constructor<?> constructor) {
        this.b = constructor;
    }

    public Method a(String methodName) {
        Method result = this.c != null ? this.c.get(methodName) : null;
        if (result == null) {
            result = this.d != null ? this.d.get(methodName) : null;
        }

        return result;
    }

    public void a(String name, Method method) {
        if (this.c == null) {
            this.c = new Hashtable();
        }

        this.c.put(name, method);
    }

    public void b(String name, Method method) {
        if (this.d == null) {
            this.d = new Hashtable();
        }

        this.d.put(name, method);
    }

    public UZModule a(Object... args) {
        UZModule module = null;

        try {
            module = (UZModule) this.b.newInstance(args);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return module;
    }

    public String a() {
        String object = JSCore.a + "$md['" + this.name + "']={";
        StringBuffer buffer = new StringBuffer(object);
        Set methods;
        String method;
        Iterator var5;
        String script;
        //  jsmethod_
        if (this.c != null) {
            methods = this.c.keySet();
            var5 = methods.iterator();

            while (var5.hasNext()) {
                method = (String) var5.next();
                script = method + ":function(){" + JSCore.a + ".E('" + this.name + "','" + method + "'," + JSCore.a + "$e(arguments));},";
                buffer.append(script);
            }
        }
// jsget_
        if (this.d != null) {
            methods = this.d.keySet();
            var5 = methods.iterator();

            while (var5.hasNext()) {
                method = (String) var5.next();
                script = method + ":" + JSCore.a + ".ES('" + this.name + "','" + method + "'),";
                buffer.append(script);
            }
        }

        buffer.append("};");
        return buffer.toString();
    }
}
