package com.vladf.koursw.pc;

public class Configuration {

            /*PC Configuration*/
    public static final int coreCount = 2;
    public static int memory = 2048;

            /*OS configurations*/
    public static int maxPriority = 32;
    public static int minValue = 10;
    public static int initPCount = 2;
    public static int PRmMultiplier = 2;
    public static int clearOutdatedTimer = 2;
    public static int getRandBoolEveryTick=4;

            /*Ticker settings*/
    public static int tickIncrement= 1;

            /*Logger settings*/
    public static boolean logger = true;
    public static int logDelay = 1;
}
