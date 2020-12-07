package com.vladf.koursw.pc;

import com.vladf.koursw.pc.cpu.CPU;
import com.vladf.koursw.pc.memory.MemScheduler;
import com.vladf.koursw.pc.memory.MemoryBlock;
import com.vladf.koursw.process.Process;
import com.vladf.koursw.process.Queue;
import com.vladf.koursw.utils.Randomize;
import com.vladf.koursw.utils.logger.Logger;
import com.vladf.koursw.utils.ticker.Ticker;
import com.vladf.koursw.utils.ticker.TickListener;

import java.util.ArrayList;

public class Scheduler implements TickListener {
    static ArrayList<Process> doneProcesses = new ArrayList<>();
    Queue queue;
    CPU cpu;
    MemScheduler memScheduler;
    Ticker ticker;

    public Scheduler() {
        this.queue = new Queue();
        this.cpu = new CPU(System.coreCount);
        this.memScheduler = new MemScheduler();
        this.ticker = new Ticker();

        this.ticker.addListener(this);
        this.ticker.addListener(cpu);
    }

    public void Start()
    {
        preLaunchInit();
        ticker.run();
    }

    private void preLaunchInit()
    {
                    /*Some MB*/
        MemScheduler.add(new MemoryBlock(0,100,null));

                    /*Some Processes*/
        this.queue.Add(System.initPCount);
    }

    private void addJob()
    {
        this.queue.Add(Randomize.getRandInt(System.minValue));
    }


    @Override
    public String toString() {
        return "Scheduler{\n"+cpu+'\n'+memScheduler+'\n'+queue+"\nDone:"+doneProcesses+"\n}";
    }

    public static void PDone(Process process)
    {
        doneProcesses.add(process);
    }





    @Override
    public void tickEvent() {
        /*Remove outdated processes*/
        if(Ticker.getTick()%2==0)
        queue.cancelOutdated();


                /*CPUs will roll!*/
        for (int i=0; i<System.coreCount;i++) {
            int _tmpInt = cpu.getFreeCore();
            if (_tmpInt >= 0) {
                cpu.setCoreJob(_tmpInt, queue.getNextProcess());
            }
        }
                    /*Lazy Memory fix*/
        if(Randomize.getRandBool(4))
            addJob();

        Logger.print(toString());

    }
}