package com.vladf.koursw.pc;

public class Configuration {

            /*PC Configuration*/
    public static final int coreCount = 2;
    public static int memory = 2048;

            /*OS configurations*/
    public static int minValue = 10;
    public static int initPCount = 2;
    /**
     * Process Remove Multiplier
     */
    public static int PRmMultiplier = 2;
    /**
     * Remove old Processes every 'N' tick
     */
    public static int rmOldPIterator = 2;
    public static int getRandBoolEveryTick=4;

            /*Proc Settings*/
    public static int maxPriority = 32;
    /**
     * ZombieProcess new birstTime += thisBirstTime/'n'
     */
    public static float ZombieDiv = 2;
    public static int minProcName = 2;
    public static int maxProcName = 7;

            /*Ticker settings*/
    public static int tickIncrement= 1;

            /*Logger settings*/
    public static boolean logger = true;
    /**
     * Show message every 'N' ticks
     */
    public static int logDelay = 1;
}
