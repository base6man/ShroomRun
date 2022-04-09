package com.sulfrix.shroomrun.entities;

import com.sulfrix.shroomrun.Entity;
import com.sulfrix.shroomrun.lib.BoundingBox;
import com.sulfrix.shroomrun.lib.GlobalManagers.Display;
import com.sulfrix.shroomrun.lib.GlobalManagers.RNG;
import com.sulfrix.shroomrun.lib.RenderPosType;
import processing.core.PGraphics;
import processing.core.PVector;

public class Background extends Entity {

    public float genPos = 0;
    public float depth = 8;

    public Background(float depth) {
        super(new PVector(0, 0), BoundingBox.zero());
        updateOffscreen = true;
        updateEnabled = true;
        this.depth = depth;
    }

    public Background() {
        this(8);
    }

    @Override
    public void update(double timescale) {
        var camera = world.camera;
        if (camera.position.x + camera.getBB().width/2 > (genPos-BackgroundTile.tileWidth)*depth) {
            genTile();
        }
    }

    public void genTile() {
        System.out.println("Background tile generated: " + genPos);
        world.AddEntity(new BackgroundTile(new PVector(genPos*depth, (depth-2)*-100), depth, new PVector(genPos, 0)));
        genPos+=BackgroundTile.tileWidth;
    }

    @Override
    public void draw(double timescale, PGraphics g) {

    }
}
