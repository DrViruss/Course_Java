package com.vladf.koursw.utils.ticker;

import com.vladf.koursw.pc.Configuration;

import java.util.ArrayList;
import java.util.TimerTask;

public class Ticker  extends TimerTask {
    ArrayList<TickListener> listenersList = new ArrayList<>();

    private static int tick;
    public static int getTick() {
        return tick;
    }
    public static void TickUP(int increment){
        tick+=increment;
    }


    @Override
    public void run() {
        while (true)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TickUP(Configuration.tickIncrement);

            for(TickListener listener : listenersList)
                listener.tickEvent();
        }
    }

    public void addListener(TickListener listener)
    {
        listenersList.add(listener);
    }
}
