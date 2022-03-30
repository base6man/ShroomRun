package com.sulfrix.shroomrun.lib.GlobalManagers;

import java.util.Random;

public class RNG extends GlobalManager {

    public static int seed;
    public static int offset;

    public static int RandomInt(int min, int max) {
        var rand = new Random();
        rand.setSeed((seed+offset)*100);
        offset++;
        var offset = rand.nextInt(min, max+1);
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
