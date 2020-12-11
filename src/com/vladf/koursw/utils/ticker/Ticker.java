package com.vladf.koursw.utils.ticker;

import com.vladf.koursw.fx.Main;
import com.vladf.koursw.pc.Configuration;

import java.util.ArrayList;
import com.vladf.koursw.process.Queue;
import java.util.TimerTask;

public class Ticker  extends TimerTask {
    ArrayList<TickListener> listenersList = new ArrayList<>();

    private static int tick;
    public static int getTick() {
        return tick;
    }
    public static void TickUP(){
        tick++;
    }
//    Queue q;
//    /////////////////////////
//
//
//    public Queue getQ() {
//        return q;
//    }
//
//    public void setQ(Queue q) {
//        this.q = q;
//    }
//
//    ////////////////////
    public static void clearTime(){tick =0;}

    @Override
    public void run() {
        while (true)
        {
            try {
                Thread.sleep(1000,Configuration.tickIncrement);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TickUP();

            for(TickListener listener : listenersList)
                listener.tickEvent();

        }
    }

    public void addListener(TickListener listener)
    {
        listenersList.add(listener);
    }
}
