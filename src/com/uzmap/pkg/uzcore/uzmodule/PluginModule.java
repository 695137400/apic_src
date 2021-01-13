package com.uzmap.pkg.uzcore.uzmodule;

import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.aa.JSCore;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

public final class PluginModule {
    private Hashtable<String, ModuleMethod> a = new Hashtable();
    private List<Class<?>> moduleClassList = new ArrayList();
    private static Hashtable<String, Module> modules = new Hashtable();

    static {
        Module module = new Module("mam", "com.uzmap.pkg.uzmodules.uzmam.UzMAM");
        module.LoadClass();
        modules.put(module.name, module);
        module = new Module("msm", "com.uzmap.pkg.uzmodules.uzmsm.UzMSM");
        module.LoadClass();
        modules.put(module.name, module);
        module = new Module("mcm", "com.uzmap.pkg.uzmodules.uzmcm.UzMCM");
        module.LoadClass();
        modules.put(module.name, module);
        module = new Module("push", "com.uzmap.pkg.uzmodules.uzpush.UPush");
        module.LoadClass();
        modules.put(module.name, module);
        module = new Module("baiduMap", "com.uzmap.pkg.uzmodules.uzBaiduMap.UzBaiduMap");
        module.LoadClass();
        modules.put(module.name, module);
        module = new Module("baiduLocation", "com.uzmap.pkg.uzmodules.uzBaiduLocation.UzBaiduLocation");
        module.LoadClass();
        modules.put(module.name, module);
    }

    public PluginModule(String moduleJson) {
        this.b(moduleJson);
    }

    public Hashtable<String, ModuleMethod> a() {
        return this.a;
    }

    public static boolean a(String module) {
        Module contain = modules.get(module);
        return contain != null && contain.falg;
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
        Iterator var3 = this.moduleClassList.iterator();

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
                            this.moduleClassList.add(target);
                        } else {
                            Class[] paramTypes = new Class[]{UZWebView.class};
                            Constructor construct = null;

                            try {
                                construct = target.getConstructor(paramTypes);
                            } catch (Exception var19) {
                                var19.printStackTrace();
                            }

                            if (construct != null) {
                                ModuleMethod entity = new ModuleMethod(construct);
                                entity.name = moduleName;
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
            Collection<ModuleMethod> values = this.a.values();
            Iterator var26 = values.iterator();

            while (var26.hasNext()) {
                ModuleMethod entity = (ModuleMethod) var26.next();
                moduleScript.append(entity.a());
            }

            JSCore.a(moduleScript.toString());
        }
    }

    static class Module {
        public String name;
        public String clazz;
        public boolean falg = true;

        public Module(String name, String clazz) {
            this.name = name;
            this.clazz = clazz;
        }

        public void LoadClass() {
            try {
                Class.forName(this.clazz);
            } catch (Exception var2) {
                this.falg = false;
            }

        }
    }
}
