package com.vladf.koursw.process;

import com.vladf.koursw.pc.System;
import com.vladf.koursw.utils.ticker.Ticker;
import com.vladf.koursw.utils.Randomize;

import java.util.Comparator;

public class Process {
    private int id;        //after create
    private String name;    //rand
    private int priority;  //rand + on work
    private Status status;    //rand + on work
    private int tickWorks;       //rand
    private int memory;     //rand
    private int timeIn;     //after create
    private int bursTime;   //on work

    public Process(int id) {
        this.id = id;
        this.name = "P_"+this.id+"_"+ Randomize.getRandString(Randomize.getRandInt(2,7));
        this.memory = Randomize.getRandInt(System.minValue, System.memory/2);
        this.priority= Randomize.getRandInt(System.maxPriority);
        this.tickWorks = Randomize.getRandInt(System.minValue,System.minValue*10);
        this.timeIn = Ticker.getTick();
        this.bursTime=0;
        this.status = Status.Ready;
    }
    //________Setters________\\

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setBursTime(int bursTime) {
        this.bursTime = bursTime;
    }

    //________Getters________\\

    public int getTickWorks() {
        return tickWorks;
    }

    public int getMemory() {
        return memory;
    }

    public int getTimeIn() {
        return timeIn;
    }

    public int getBursTime() {
        return bursTime;
    }


    public static Comparator<Process> byTime = Comparator.comparingInt(o -> o.tickWorks);

    //________toString________\\

    @Override
    public String toString() {
        return "Process" +
                "\tid=" + id +
                "\tname=" + name +
                "\tpriority=" + priority +
                "\tstate=" + status +
                "\ttick=" + tickWorks +
                "\tmemory=" + memory +
                "\ttimeIn=" + timeIn +
                "\tbursTime=" + bursTime+'\n';
    }

}
