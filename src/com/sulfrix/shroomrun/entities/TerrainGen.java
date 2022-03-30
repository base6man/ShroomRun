package com.sulfrix.shroomrun.entities;

import com.sulfrix.shroomrun.Entity;
import com.sulfrix.shroomrun.lib.BoundingBox;
import com.sulfrix.shroomrun.lib.GlobalManagers.RNG;
import processing.core.PGraphics;
import processing.core.PVector;

public class TerrainGen extends Entity {

    public Entity trackedEntity;
    public int offset;
    public int genY = 9;
    public int genX;

    public TerrainGen() {
        super(new PVector(0, 0), BoundingBox.zero());

    }

    @Override
    public void update(double timescale) {
        var camera = world.camera;
        if (trackedEntity != null) {
            if (position.x < camera.position.x+camera.getBB().width) {
                generate();
            }
        }
    }

    public void generate() {
        var genWidth = RNG.RandomInt(4, 10, offset);
        offset++;
        var genGap = RNG.RandomInt(0, 2, offset)*2;
        offset++;
        if (genGap <= 6) {
            genY -= RNG.RandomInt(-1, 2, offset);
        }
        offset++;
        generateBlock(genWidth, 10, genX, genY);
        genX += genWidth + genGap;
        position.x = genX*30;
    }

    public void generateBlock(int width, int height, int baseX, int baseY) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                var newTile = new Tile(new PVector((baseX+x)*30, (baseY+y)*30), "rock.png");
                world.AddEntity(newTile);
            }
        }
    }

    @Override
    public void draw(double timescale, PGraphics g) {

    }
}
