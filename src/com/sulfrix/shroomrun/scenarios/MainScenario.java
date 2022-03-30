package com.sulfrix.shroomrun.scenarios;

import com.sulfrix.shroomrun.Entity;
import com.sulfrix.shroomrun.Scenario;
import com.sulfrix.shroomrun.entities.Camera;
import com.sulfrix.shroomrun.entities.PhysicsTest;
import com.sulfrix.shroomrun.entities.RunnerPlayer;
import com.sulfrix.shroomrun.entities.TerrainGen;
import processing.core.PGraphics;
import processing.core.PVector;

public class MainScenario extends Scenario {
    public Camera camera;
    public Entity player;
    public TerrainGen terrainGen;

    @Override
    public void init() {
        camera = new Camera();

        world.AddEntity(camera);
        world.camera = camera;
        player = new RunnerPlayer(new PVector(0, -30));
        world.AddEntity(player);
        camera.focus = player;
        camera.focusOffset = new PVector(0, -30);
        terrainGen = new TerrainGen();
        world.AddEntity(terrainGen);
        terrainGen.trackedEntity = player;
        terrainGen.generate();
    }

    @Override
    public void update(double timescale) {
        super.update(timescale);
    }

    @Override
    public void draw(double timescale, PGraphics g) {
        super.draw(timescale, g);
    }
}
