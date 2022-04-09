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
        var h = 25;
        if (offset == 0) {
            generateBlock(24, h, 0, genY, 0);
            genX += 24;
        }
        var genWidth = RNG.RandomInt(2, 12, offset);
        offset++;
        var genGap = RNG.RandomInt(0, 2, offset)*2;
        offset++;
        if (genGap <= 6) {
            genY -= RNG.RandomInt(-1, 2, offset);
        }
        offset++;
        var hazards = RNG.RandomInt(0, 2, offset);
        if (genWidth <= 4) {
            hazards = 0;
        }
        offset++;
        generateBlock(genWidth, h, genX, genY, hazards);
        genX += genWidth + genGap;
        position.x = genX*30;
    }

    public void generateBlock(int width, int height, int baseX, int baseY, int hazards) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                var tex = "dirt.png";
                if (y == 0) {
                    tex = "grass.png";
                }
                if (y > 4) {
                    tex = "rock.png";
                }
                var newTile = new Tile(new PVector((baseX+x)*30, (baseY+y)*30), tex);
                newTile.ZPos = -2;
                world.AddEntity(newTile);
            }
        }
        for (int h = 0; h < hazards; h++) {
            var hazardX = RNG.RandomInt(1, width-1, offset);
            var hazard = new Hazard(new PVector((baseX + hazardX)*30, (baseY-1)*30), "spikes.png");
            world.AddEntity(hazard);
        }
    }

    @Override
    public void draw(double timescale, PGraphics g) {

    }
}
