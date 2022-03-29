package com.sulfrix.shroomrun.entities;

import com.sulfrix.shroomrun.Entity;
import com.sulfrix.shroomrun.lib.BoundingBox;
import com.sulfrix.shroomrun.lib.Display;
import processing.core.PGraphics;
import processing.core.PVector;

public class Camera extends Entity {

    public Entity focus;
    public PVector focusOffset;
    public double zoom = 1;
    public boolean useOptimalScale = true;

    public Camera(PVector pos) {
        super(pos, BoundingBox.zero());
    }

    public Camera() {
        super();
    }

    @Override
    public void update(double timescale) {

    }

    @Override
    public void draw(double timescale, PGraphics g) {

    }

    public double getScale() {
        if (useOptimalScale) {
            return Display.getOptimalScale(480, 360)*zoom;
        } else {
            return zoom;
        }
    }
}
