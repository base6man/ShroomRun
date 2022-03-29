package com.sulfrix.shroomrun.scenarios;

import com.sulfrix.shroomrun.Scenario;
import com.sulfrix.shroomrun.entities.Camera;
import com.sulfrix.shroomrun.entities.ui.TextEntity;
import processing.core.PGraphics;
import processing.core.PVector;

public class MenuScenario extends Scenario {

    // I haven't tested screen space rendering until now, when I'm making this scenario. Wish me luck.

    @Override
    public void init() {
        Camera camera = new Camera();
        world.AddEntity(camera);
        world.camera = camera;
        world.AddEntity(new TextEntity(new PVector(), "Your balls can't help you now.", 10f, 0, 0));
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
