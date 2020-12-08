package com.vladf.koursw.utils.logger;

import com.vladf.koursw.pc.Configuration;
import com.vladf.koursw.utils.ticker.TickListener;
import com.vladf.koursw.utils.ticker.Ticker;

public class Logger implements TickListener {

    private static final String info = "Logger:\nCurrent tick=";
    private static String msg="";

    public static void print(String _s)
    {
        msg+=_s+"\n";
    }

    @Override
    public void tickEvent() {
        if(Ticker.getTick()% Configuration.tickIncrement* Configuration.logDelay==0)
        {
            java.lang.System.out.println(info +Ticker.getTick()+'\n'+msg);
            msg = "";
        }
    }
}
