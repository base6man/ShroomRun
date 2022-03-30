package com.sulfrix.shroomrun.lib;

import processing.core.PApplet;

import java.util.Random;

public class RNG extends GlobalManager {

    public static int seed;
    public static int offset;

    public static int RandomInt(int min, int max) {
        var rand = new Random();
        rand.setSeed((seed+offset)*9999);
        offset++;
        var offset = rand.nextInt(min, max+1);
        System.out.println(min + " - " + max + ": " + offset);
        return offset;
    }

    public static int RandomInt(int min, int max, int useOffset) {
        var tempOffset = offset;
        offset = useOffset;
        var output = RandomInt(min, max);
        offset = tempOffset;
        return output;
    }

    public static void SetSeed(int seedSet) {
        seed = seedSet;
        offset = 0;
    }
}
