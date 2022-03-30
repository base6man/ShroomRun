package com.sulfrix.shroomrun.entities;

import com.sulfrix.shroomrun.lib.BoundingBox;
import processing.core.PGraphics;
import processing.core.PVector;

public class RunnerPlayer extends PhysicsEntity {

    public RunnerPlayer(PVector pos) {
        super(pos, new BoundingBox(30, 30));
        renderingEnabled = true;
        collisionEnabled = true;
    }

    @Override
    public void update(double timescale) {
        MoveForward(timescale);
        super.update(timescale);
    }

    @Override
    public void draw(double timescale, PGraphics g) {
        g.fill(0);
        g.noStroke();
        g.rect(0, 0, 30, 30);
    }

    void MoveForward(double timescale) {
        if (velocity.x < 10) {
            velocity.x += 1 * timescale;
        } else {
            velocity.x -= ((velocity.x - 10) / 10)*timescale;
        }
    }
}
