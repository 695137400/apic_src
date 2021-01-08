//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzcore.aa;

import android.text.TextUtils;
import android.util.Xml;
import java.io.StringReader;
import java.util.Hashtable;
import org.xmlpull.v1.XmlPullParser;

public class h {
    private Hashtable<String, String> a = new Hashtable();

    public h() {
    }

    public String a(String key) {
        return (String)this.a.get(key);
    }

    public void a(byte[] content) {
        if (content != null && content.length != 0) {
            this.b(content);
        }
    }

    private void b(byte[] content) {
        try {
            content = j.b(content);
            XmlPullParser parser = Xml.newPullParser();
            String de = new String(content);
            StringReader input = new StringReader(de);
            parser.setInput(input);
            boolean needContinue = true;

            do {
                int tokenType = parser.next();
                switch(tokenType) {
                    case 1:
                        needContinue = false;
                        break;
                    case 2:
                        String localName = parser.getName().toLowerCase();
                        if (!"security".equals(localName) && "item".equals(localName)) {
                            String name = parser.getAttributeValue((String)null, "name");
                            String value = parser.getAttributeValue((String)null, "value");
                            if (!TextUtils.isEmpty(name)) {
                                this.a.put(name, value);
                            }
                        }
                }
            } while(needContinue);
        } catch (Exception var10) {
        }

    }
}
