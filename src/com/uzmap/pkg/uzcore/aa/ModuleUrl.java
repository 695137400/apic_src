package com.uzmap.pkg.uzcore.aa;

public class ModuleUrl {
    public byte[] urlBit;

    public ModuleUrl(byte[] buffer) {
        this.urlBit = buffer;
    }

    public String toString() {
        return this.urlBit != null ? new String(this.urlBit) : null;
    }
}
