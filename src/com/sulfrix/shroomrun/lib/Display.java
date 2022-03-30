package com.sulfrix.shroomrun.lib;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Display extends GlobalManager {
    public static double getOptimalScale(int targetWidth, int targetHeight) {
        double tW = targetWidth;
        double tH = targetHeight;
        double oW = owner.width;
        double oH = owner.height;
        double ratio1 = tW/tH;
        double ratio2 = oW/oH;
        if (ratio1 > ratio2) {
            return oW/tW;
        } else {
            return oH/tH;
        }
    }

    public static double width() {
        return owner.width;
    }

    public static double height() {
        return owner.height;
    }
}
