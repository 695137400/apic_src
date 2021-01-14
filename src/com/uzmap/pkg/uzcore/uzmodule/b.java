package com.uzmap.pkg.uzcore.uzmodule;

import android.webkit.WebView;
import com.uzmap.pkg.uzcore.ApplicationProcess;
import com.uzmap.pkg.uzcore.external.l;
import com.uzmap.pkg.uzcore.uzmodule.internalmodule.UZCoreModule;
import com.uzmap.pkg.uzcore.uzmodule.internalmodule.UZSynModule;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

public final class b {
    private UZCoreModule a;
    private UZSynModule b;
    private Hashtable<String, UZModule> c = new Hashtable();
    private Hashtable<String, ModuleMethod> d;

    public void a(com.uzmap.pkg.uzcore.a bridge, boolean r, String d) {
        this.a = new UZCoreModule(bridge);
        if (r) {
            this.a.setGp(d);
        }

        String moduleName = this.a.getModuleName();
        bridge.addJavascriptInterface(this.a, moduleName);
        this.c.put(moduleName, this.a);
        this.b = new UZSynModule(bridge);
        moduleName = this.b.getModuleName();
        bridge.addJavascriptInterface(this.b, moduleName);
        this.c.put(moduleName, this.b);
        ApplicationProcess app = ApplicationProcess.initialize();
        PluginModule moduleParser = app.e();
        if (moduleParser != null) {
            this.d = moduleParser.a();
            Collection<ModuleMethod> values = this.d.values();
            Iterator var9 = values.iterator();

            while (var9.hasNext()) {
                ModuleMethod entity = (ModuleMethod) var9.next();
                UZModule module = entity.a(bridge);
                if (module != null) {
                    moduleName = entity.name;
                    this.c.put(moduleName, module);
                }
            }

        }
    }

    public Object a(String JSclassName, String JSmethod, UZModuleContext mcontext) {
        if (this.d == null) {
            return null;
        } else {
            UZModule module = this.c.get(JSclassName);
            ModuleMethod entity = this.d.get(JSclassName);
            Object result = null;
            if (entity != null && module != null) {
                Method javaMethod = entity.a(JSmethod);
                if (javaMethod != null) {
                    try {
                        if (mcontext != null) {
                            result = javaMethod.invoke(module, mcontext);
                        } else {
                            result = javaMethod.invoke(module);
                        }
                    } catch (Exception var9) {
                        var9.printStackTrace();
                    }
                }
            }

            return result;
        }
    }

    public void a() {
        Collection<UZModule> vaules = this.c.values();
        Iterator var3 = vaules.iterator();

        while (var3.hasNext()) {
            UZModule module = (UZModule) var3.next();
            module.onClean();
        }

    }

    public void a(WebView view) {
        Collection<UZModule> vaules = this.c.values();
        Iterator var4 = vaules.iterator();

        while (var4.hasNext()) {
            UZModule module = (UZModule) var4.next();
            module.onClean();
            module.destroy();
        }

        if (l.SDK_INT >= 11) {
            String name = this.a.getModuleName();
            view.removeJavascriptInterface(name);
            name = this.b.getModuleName();
            view.removeJavascriptInterface(name);
        }

        this.c.clear();
        this.a = null;
        this.b = null;
    }
}
