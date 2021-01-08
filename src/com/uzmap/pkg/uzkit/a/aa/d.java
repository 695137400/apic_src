package com.uzmap.pkg.uzkit.a.aa;

import android.os.Process;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class d extends Thread {
    private final BlockingQueue<h> c = new PriorityBlockingQueue();
    private final boolean a = false;
    private boolean b = false;

    public d() {
    }

    public synchronized void a() {
        this.b = true;
        this.start();
    }

    public void run() {
        Process.setThreadPriority(10);
        this.setName("##Thread-" + Thread.currentThread().getId() + "####");

        while (true) {
            h queue;
            while (true) {
                try {
                    queue = this.c.take();
                    break;
                } catch (InterruptedException var4) {
                    if (this.a) {
                        return;
                    }
                }
            }

            try {
                queue.run();
            } catch (Exception var3) {
                var3.printStackTrace();
                queue.a();
            }
        }
    }

    public final void a(h action) {
        this.c.add(action);
        if (!this.b) {
            this.a();
        }

    }
}
