package com.uzmap.pkg.uzcore.uzmodule;

import com.uzmap.pkg.uzcore.UZWebView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

public final class c {
    private Hashtable<String, com.uzmap.pkg.uzcore.uzmodule.a> a = new Hashtable();
    private List<Class<?>> b = new ArrayList();
    private static Hashtable<String, c.a> c = new Hashtable();

    static {
        c.a module = null;
        module = new c.a("mam", "com.uzmap.pkg.uzmodules.uzmam.UzMAM");
        module.a();
        c.put(module.a, module);
        module = new c.a("msm", "com.uzmap.pkg.uzmodules.uzmsm.UzMSM");
        module.a();
        c.put(module.a, module);
        module = new c.a("mcm", "com.uzmap.pkg.uzmodules.uzmcm.UzMCM");
        module.a();
        c.put(module.a, module);
        module = new c.a("push", "com.uzmap.pkg.uzmodules.uzpush.UPush");
        module.a();
        c.put(module.a, module);
        module = new c.a("baiduMap", "com.uzmap.pkg.uzmodules.uzBaiduMap.UzBaiduMap");
        module.a();
        c.put(module.a, module);
        module = new c.a("baiduLocation", "com.uzmap.pkg.uzmodules.uzBaiduLocation.UzBaiduLocation");
        module.a();
        c.put(module.a, module);
    }

    public c(String moduleJson) {
        this.b(moduleJson);
    }

    public Hashtable<String, com.uzmap.pkg.uzcore.uzmodule.a> a() {
        return this.a;
    }

    public static boolean a(String module) {
        c.a contain = c.get(module);
        return contain != null && contain.c;
    }

    public static boolean c() {
        return a("mam");
    }

    public static boolean d() {
        return a("msm");
    }

    public static boolean e() {
        return a("push");
    }

    public static boolean f() {
        return a("baiduLocation");
    }

    public static boolean g() {
        return a("baiduMap");
    }

    public List<ApplicationDelegate> b() {
        List<ApplicationDelegate> list = new ArrayList();
        Iterator var3 = this.b.iterator();

        while (var3.hasNext()) {
            Class<?> clazz = (Class) var3.next();
            ApplicationDelegate intance = null;

            try {
                intance = (ApplicationDelegate) clazz.newInstance();
            } catch (Exception var6) {
            }

            if (intance != null) {
                list.add(intance);
            }
        }

        return list;
    }

    private void b(String moduleJson) {
        JSONArray moduleList = null;

        try {
            JSONObject json = new JSONObject(moduleJson);
            moduleList = json.optJSONArray("modules");
        } catch (Exception var21) {
            var21.printStackTrace();
        }

        if (moduleList != null) {
            int length = moduleList.length();
            Class<?> delegateClass = ApplicationDelegate.class;

            for (int i = 0; i < length; ++i) {
                JSONObject moduleitem = moduleList.optJSONObject(i);
                if (moduleitem != null) {
                    String moduleName = moduleitem.optString("name");
                    String className = moduleitem.optString("class");
                    Class target = null;

                    try {
                        target = Class.forName(className);
                    } catch (Exception var20) {
                        var20.printStackTrace();
                    }

                    if (target != null) {
                        if (delegateClass.equals(target.getSuperclass())) {
                            this.b.add(target);
                        } else {
                            Class[] paramTypes = new Class[]{UZWebView.class};
                            Constructor construct = null;

                            try {
                                construct = target.getConstructor(paramTypes);
                            } catch (Exception var19) {
                                var19.printStackTrace();
                            }

                            if (construct != null) {
                                com.uzmap.pkg.uzcore.uzmodule.a entity = new com.uzmap.pkg.uzcore.uzmodule.a(construct);
                                entity.a = moduleName;
                                Method[] methods = target.getDeclaredMethods();
                                if (methods != null) {
                                    Method[] var17 = methods;
                                    int var16 = methods.length;

                                    for (int var15 = 0; var15 < var16; ++var15) {
                                        Method method = var17[var15];
                                        String name = method.getName();
                                        if (name.startsWith("jsmethod_")) {
                                            name = name.substring(9);
                                            entity.a(name, method);
                                        } else if (name.startsWith("jsget_")) {
                                            name = name.substring(6);
                                            entity.b(name, method);
                                        }
                                    }
                                }

                                this.a.put(moduleName, entity);
                            }
                        }
                    }
                }
            }

            StringBuffer moduleScript = new StringBuffer("");
            Collection<com.uzmap.pkg.uzcore.uzmodule.a> values = this.a.values();
            Iterator var26 = values.iterator();

            while (var26.hasNext()) {
                com.uzmap.pkg.uzcore.uzmodule.a entity = (com.uzmap.pkg.uzcore.uzmodule.a) var26.next();
                moduleScript.append(entity.a());
            }

            com.uzmap.pkg.uzcore.aa.a.a(moduleScript.toString());
        }
    }

    static class a {
        public String a;
        public String b;
        public boolean c = true;

        public a(String name, String clazz) {
            this.a = name;
            this.b = clazz;
        }

        public void a() {
            try {
                Class.forName(this.b);
            } catch (Exception var2) {
                this.c = false;
            }

        }
    }
}
