package com.sulfrix.shroomrun.lib;

import processing.core.PApplet;

public class TimeManager {

    public static int lastSync = 0;
    public static PApplet owner;
    /**
     * DeltaTime is not in seconds but is aligned based on a 30hz refresh rate.
     * e.g. running at 60hz gives a 0.5 deltatime
     */
    public static double deltaTime;
    public static double frameTime;

    private TimeManager() {

    }

    public static void init(PApplet app) {
        owner = app;
    }

    /**
     * MUST be run after every draw() at the topmost level, or timing WILL break!
     */
    public static void sync() {
        deltaTime = ((owner.millis() - lastSync)) / 32.0;
        frameTime = ((owner.millis() - lastSync));
        lastSync = owner.millis();
    }

}
