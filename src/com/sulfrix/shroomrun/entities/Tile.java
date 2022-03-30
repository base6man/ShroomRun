package com.sulfrix.shroomrun.entities;

import com.sulfrix.shroomrun.Entity;
import com.sulfrix.shroomrun.lib.AssetCache;
import com.sulfrix.shroomrun.lib.BoundingBox;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

public class Tile extends Entity {

    public String texture;
    PImage texImg;

    public Tile(PVector pos, String tex) {
        super(pos, new BoundingBox(30, 30));
        renderingEnabled = true;
        renderingOffscreen = false;
        updateEnabled = false;
        collisionEnabled = true;
        texture = tex;
        texImg = AssetCache.getImage(texture);
    }

    @Override
    public void update(double timescale) {
    }

    @Override
    public void draw(double timescale, PGraphics g) {
        g.image(texImg, 0, 0, 30, 30);
        /*if (world.input.mousePressed) {
            g.image(texImg, 0, 0, 30, 200);
        }*/
    }
}
