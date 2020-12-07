package com.vladf.koursw.utils.logger;

import com.vladf.koursw.pc.System;
import com.vladf.koursw.utils.ticker.TickListener;
import com.vladf.koursw.utils.ticker.Ticker;

public class Logger implements TickListener {

    private static String message = "Logger:\nCurrent tick=";

    public static void print(String _s)
    {
        java.lang.System.out.println(message+Ticker.getTick()+'\n' + _s);
    }

    @Override
    public void tickEvent() {
        if(Ticker.getTick()% System.tickIncrement*System.logDelay==0)
        {
            java.lang.System.out.println(message+Ticker.getTick()+'\n'+"Something interesting...");
        }
    }
}
