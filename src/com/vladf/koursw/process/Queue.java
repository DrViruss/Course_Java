package com.vladf.koursw.process;

import com.vladf.koursw.pc.Configuration;
import com.vladf.koursw.pc.memory.MemScheduler;
import com.vladf.koursw.utils.ticker.Ticker;

import java.util.ArrayList;

public class Queue {
    private ArrayList<Process> queue;
    private ArrayList<Process> rejectedQueue;
    private int PID;

    public Queue() {
        this.queue = new ArrayList<>();
        this.rejectedQueue = new ArrayList<>();
        this.PID=1;
    }
    public void Add(final int PCount)
    {
        for (int i = 0; i < PCount; i++) {
            Process p = new Process(this.PID);
            this.PID++;
            if(MemScheduler.fillMB(p)) {
                this.queue.add(p);
            }
            else {
                p.setStatus(Status.Canceled);
                rejectedQueue.add(p);
            }
        }
    }

    public void Remove(Process process)
    {
        queue.remove(process);
        MemScheduler.releaseMB(process);
    }

    public void cancelOutdated()
    {
            for (int i=queue.size()-1; i>=0;i--)
                if (Ticker.getTick() >= queue.get(i).getTimeIn() * Configuration.PRmMultiplier)
                    cancelProcess(queue.get(i));
    }

    private void cancelProcess(Process process)
    {
        Remove(process);
        process.setStatus(Status.Canceled);
        rejectedQueue.add(process);
    }


    public int getPID() {
        return PID;
    }
    public Process getNextProcess() {
        queue.sort(Process.byTime);
        if(queue.size()!=0) {
            Process _tmp = queue.get(0);
            this.Remove(_tmp);
            _tmp.setStatus(Status.Running);
            return _tmp;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Queue{\n" + queue +"}\nRejectedQueue{\n"+rejectedQueue+'}';
    }
}
