package com.vladf.koursw;

import com.vladf.koursw.pc.Scheduler;
import com.vladf.koursw.utils.SysImporter;

public class Main {
    public static Scheduler sc;
    public static void main(String[] args) {

       SysImporter.ImportSySetings();
       sc = new Scheduler();
       sc.Start();
    }
}
