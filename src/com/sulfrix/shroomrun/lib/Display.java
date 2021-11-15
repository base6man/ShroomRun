package com.sulfrix.shroomrun.lib;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Display {

    private Display() {}

    static PApplet owner;

    public static double getOptimalScale(int targetWidth, int targetHeight) {
        double targetAxis = Math.min(targetWidth, targetHeight);
        double currentAxis = Math.min(owner.width, owner.height);

        return currentAxis / targetAxis;
    }

    public static void init(PApplet ownerApplet) {
        owner = ownerApplet;
    }

    public static double width() {
        return owner.width;
    }

    public static double height() {
        return owner.height;
    }
}
