package com.vladf.koursw;

import com.vladf.koursw.pc.Scheduler;
import com.vladf.koursw.utils.SysImporter;
import com.vladf.koursw.utils.ticker.Ticker;

public class KMain implements Runnable{
    public static Scheduler sc;
    public static void main(String[] args) {

       SysImporter.ImportSySetings();
//       sc = new Scheduler(new Ticker());
       sc.Start();
    }

    @Override
    public void run() {

    }
}
