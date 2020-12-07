package com.vladf.koursw.utils;

import com.vladf.koursw.pc.Configuration;

import java.util.Random;

public class Randomize {
    public static Random random = new Random();

    public static int getRandInt(int size) {
        return random.nextInt(size);
    }

    public static int getRandInt(int l, int r) {
        return l + random.nextInt(r - l + 1);
    }

    public static String getRandString(int size)
    {
        String qwerty = "qwertyuiopasdfghjklzxcvbnm";
        char[] _tmp = new char[size];
        for(int i = 0; i<_tmp.length-1;i++)
            _tmp[i]=qwerty.charAt(getRandInt(qwerty.length()-1));
        return new String(_tmp);
    }

    public static boolean getRandBool(int chanse)
    {
        return getRandInt(Configuration.minValue)%(chanse/*/100*/) == 0;
    }

    public static boolean getRandBool()
    {
        return getRandInt(Configuration.minValue)%2 == 0;
    }

}
