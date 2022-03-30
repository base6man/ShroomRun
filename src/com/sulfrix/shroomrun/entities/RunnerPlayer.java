package com.sulfrix.shroomrun.entities;

import com.sulfrix.shroomrun.lib.BoundingBox;
import com.sulfrix.shroomrun.lib.Input;
import processing.core.PGraphics;
import processing.core.PVector;

public class RunnerPlayer extends PhysicsEntity {

    public float jumpTime;
    public boolean hasJumped = true;

    public RunnerPlayer(PVector pos) {
        super(pos, new BoundingBox(30, 30));
        renderingEnabled = true;
        collisionEnabled = true;
    }

    @Override
    public void update(double timescale) {
        MoveForward(timescale);
        var willJump = world.input.KeyPressed(32);
        if (collisionSides[2]) {
            jumpTime = 8;
            hasJumped = false;
        } else {
            if (!willJump) {
                jumpTime = 0;
            }
        }
        if (jumpTime > 0 && willJump) {
            velocity.y = -9;
            jumpTime -= timescale;
            hasJumped = true;
        } else {
            if (velocity.y > 0 || (jumpTime <= 0 && hasJumped)) {
                gravityMult = 1.5;
            } else {
                gravityMult = 1;
            }
        }
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
            velocity.x -= ((velocity.x - 10) / 15)*timescale;
        }
    }
}
