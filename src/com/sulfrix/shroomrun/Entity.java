package com.sulfrix.shroomrun;

import com.sulfrix.shroomrun.lib.BoundingBox;
import com.sulfrix.shroomrun.lib.RenderPosType;
import processing.core.*;

public abstract class Entity {
    public PVector position;
    public double parallax = 0;
    public PVector velocity = new PVector(0, 0);
    public float ZPos = 0;
    public BoundingBox boundingBox;
    public RenderPosType renderPosType = RenderPosType.WORLD_SPACE;

    public World world;
    // focused entities receive player input from World
    public boolean isFocused;

    public boolean OBBCenter = true;
    public boolean collisionEnabled = false;
    public boolean renderingEnabled = false;
    public boolean updateEnabled = true;
    public boolean renderingOffscreen = false;
    public boolean updateOffscreen = true;

    public Entity(PVector pos, BoundingBox bb) {
        position = pos;
        boundingBox = bb;
    }

    public Entity() {
        position = new PVector(0, 0);
        boundingBox = new BoundingBox(0, 0);
    }

    public static boolean touching(Entity e1, Entity e2) {
        if ((!e1.collisionEnabled || !e2.collisionEnabled) || (e1 == e2)) {
            return false;
        } else {
            return BoundingBox.touching(e1.boundingBox, e1.position, e2.boundingBox, e2.position);
        }
    }

    public abstract void update(double timescale);

    public abstract void draw(double timescale, PGraphics g);

    public boolean touching(Entity e2) {
        return Entity.touching(this, e2);
    }

    public boolean touching(BoundingBox bb, PVector pos) {
        return bb.touching(pos, boundingBox, position);
    }

    public boolean touching(PVector thisPos, Entity e2) {
        if (!collisionEnabled || !e2.collisionEnabled) {
            return false;
        } else {
            return BoundingBox.touching(boundingBox, thisPos, e2.boundingBox, e2.position);
        }
    }
}
