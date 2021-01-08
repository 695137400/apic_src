package com.uzmap.pkg.uzkit.data;

import java.io.Serializable;

public class UZWidgetInfo implements Serializable {
    private static final long serialVersionUID = -8549686409475454058L;
    public String id;
    public String version;
    public String name;
    public String description;
    public String author;
    public String authorEmail;
    public String authorHref;
    public String base;
    public String access;
    public String origin;
    public String iconPath;
    public String widgetPath;
    private String trimPath;

    public String widgetPath() {
        if (this.trimPath != null) {
            return this.trimPath;
        } else {
            String path = this.widgetPath;
            int index = path.lastIndexOf(47);
            if (index > 0) {
                this.trimPath = path.substring(0, index);
            } else {
                this.trimPath = path;
            }

            return this.trimPath;
        }
    }
}
