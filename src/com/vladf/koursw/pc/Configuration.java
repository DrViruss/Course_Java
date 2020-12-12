package com.vladf.koursw.pc;

public class Configuration {

            /*PC Configuration*/
    public static int coreCount = 2;
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



            /*Process Settings*/
    public static int maxPriority = 32;
    /**
     * ZombieProcess new birstTime += thisBirstTime/'n'
     */
    public static float ZombieDiv = 2f;
    public static int minProcName = 2;
    public static int maxProcName = 7;
    public static int minTickWork =3;
    public static int maxTickWork =20;
    public static int minMemsize =4;
    public static int maxMemsize =300;


            /*Ticker settings*/
    public static int tickIncrement= 1;


            /*Logger settings*/
    public static boolean logger = false;
    /**
     * Show message every 'N' ticks
     */
    public static int logDelay = 1;
}
