package com.sulfrix.shroomrun;

import com.sulfrix.shroomrun.entities.Camera;
import com.sulfrix.shroomrun.entities.Tile;
import com.sulfrix.shroomrun.lib.*;
import com.sulfrix.shroomrun.scenarios.FrameTestScenario;
import com.sulfrix.shroomrun.scenarios.MainScenario;
import com.sulfrix.shroomrun.scenarios.MenuScenario;
import com.sulfrix.shroomrun.scenarios.TestScenario;
import processing.core.*;
import processing.event.KeyEvent;
import processing.opengl.PGraphicsOpenGL;

public class ShroomRun extends PApplet {

    // this is shit, remember to remove
    public static boolean debugOBB = true;

    // slightly less shit
    public static boolean debugText = true;

    public Scenario currentScenario;
    public Input input = new Input();

    public Camera testCam;

    public void settings() {
        size(480, 360, P3D);
        noSmooth();
    }

    public void setup() {
        surface.setResizable(true);
        if (g instanceof PGraphicsOpenGL) {
            PGraphicsOpenGL ogl = ((PGraphicsOpenGL) g);
            ogl.textureSampling(3);
        }
        frameRate(300);

        FontManager.init(this);
        Display.init(this);
        AssetCache.init(this);
        TimeManager.init(this);
        RNG.init(this);
        setCurrentScenario(new MainScenario());
    }

    public void draw() {
        background(176, 252, 255);
        ortho();
        input.update(this);
        currentScenario.update(TimeManager.deltaTime);
        currentScenario.draw(TimeManager.deltaTime, g);
        if (debugText) {
            g.push();
            g.fill(0);
            var s = 20;
            g.textSize(s);
            FontManager.quickUse(g, "Arial", 20);
            g.text(currentScenario.world.entities.size() + " Entities (" + currentScenario.world.renderedEnts + " Rendered)", 0, 1*s);
            g.text(Math.ceil(1000 / TimeManager.avgFrameTime) + " FPS", 0, 2*s);
            g.text("Cam Pos: " + currentScenario.world.camera.position, 0, 3*s);
            g.text("Optimal Zoom: " + Display.getOptimalScale(480, 360), 0, 4*s);
            g.text("Key: " + keyCode, 0, 5*s);
            g.text("Window Size: [" + width + ", " + height + "]", 0, 6*s);
            g.pop();
        }
        TimeManager.sync();
    }

    public void setCurrentScenario(Scenario scenario) {
        if (currentScenario != null) {
            currentScenario.unlinkInput();
        }
        currentScenario = scenario;
        currentScenario.linkInput(input);
    }

    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        input.PressKey(event.getKeyCode());
    }

    public void keyReleased(KeyEvent event) {
        super.keyReleased(event);
        input.ReleaseKey(event.getKeyCode());
    }

}
