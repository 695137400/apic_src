package com.uzmap.pkg.uzcore.external;

public class j implements Runnable {
    public void run() {
        throw new RuntimeException("Runtime Shutting down VM");
    }
}
