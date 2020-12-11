package com.vladf.koursw.fx;

import com.vladf.koursw.pc.Scheduler;

public class TLauncher implements Runnable {
    public static Scheduler sc;

    @Override
    public void run() {
        sc = new Scheduler();
        sc.Start();

    }

}
