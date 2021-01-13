package com.uzmap.pkg.uzcore.aa;

import android.text.TextUtils;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;

import java.io.StringReader;
import java.util.Hashtable;

public class h {
    private Hashtable<String, String> a = new Hashtable();

    public String a(String key) {
        return this.a.get(key);
    }

    public void a(byte[] content) {
        if (content != null && content.length != 0) {
            this.b(content);
        }
    }

    private void b(byte[] content) {
        try {
            content = AssetsFileUtil.b(content);
            XmlPullParser parser = Xml.newPullParser();
            String de = new String(content);
            StringReader input = new StringReader(de);
            parser.setInput(input);
            boolean needContinue = true;

            do {
                int tokenType = parser.next();
                switch (tokenType) {
                    case 1:
                        needContinue = false;
                        break;
                    case 2:
                        String localName = parser.getName().toLowerCase();
                        if (!"security".equals(localName) && "item".equals(localName)) {
                            String name = parser.getAttributeValue(null, "name");
                            String value = parser.getAttributeValue(null, "value");
                            if (!TextUtils.isEmpty(name)) {
                                this.a.put(name, value);
                            }
                        }
                }
            } while (needContinue);
        } catch (Exception var10) {
        }

    }
}
