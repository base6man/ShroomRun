package com.sulfrix.shroomrun.entities.ui;

import com.sulfrix.shroomrun.Entity;
import com.sulfrix.shroomrun.entities.RunnerPlayer;
import com.sulfrix.shroomrun.lib.BoundingBox;
import com.sulfrix.shroomrun.lib.GlobalManagers.Display;
import com.sulfrix.shroomrun.lib.GlobalManagers.FontManager;
import com.sulfrix.shroomrun.lib.RenderPosType;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PVector;
import processing.opengl.PGraphics2D;

public class HUDEntity extends Entity {

    PGraphics hg;

    PApplet app;

    RunnerPlayer player;

    public HUDEntity() {
        super(new PVector(0, 0), BoundingBox.zero());
        app = Display.getApplet();
        hg = app.createGraphics(480, 360, PConstants.P2D);
        renderingEnabled = true;
        renderPosType = RenderPosType.SCREEN_SPACE;
        OBBCenter = false;
        hg.beginDraw();
        hg.background(255);
        hg.endDraw();
    }

    @Override
    public void update(double timescale) {

    }

    @Override
    public void draw(double timescale, PGraphics g) {
        float scale = (float)Display.getOptimalScale(480, 460);
        hg.setSize((int)(240*scale), (int)(160*scale));
        hg.beginDraw();
        hg.background(0, 0);
        DrawHealthBar(hg);
        hg.endDraw();
        g.image(hg, 0, 0, hg.width, hg.height);
        /*g.push();
        g.texture(hg);
        g.rect(0, 0, g.width/2, g.height/2);
        g.pop();*/
    }

    public void DrawHealthBar(PGraphics g) {
        if (player == null) {
            player = findPlayer();
        }
        g.push();
        float scale = (float)Display.getOptimalScale(480, 460);
        g.scale(scale);
        g.translate(15, 10);
        var hpTextSize = 17;
        g.textFont(FontManager.useFont("sourcesanspro.ttf", hpTextSize*scale));
        g.textSize(hpTextSize);
        g.noStroke();
        g.fill(0);
        g.textAlign(PConstants.LEFT, PConstants.TOP);
        g.text("HEALTH", 0, 0);
        g.push();
        var hpBarThick = 8;
        var hpBarWide = 200;
        g.translate(hpBarThick/2, hpTextSize+10);
        g.stroke(0);
        g.strokeWeight(hpBarThick);
        g.line(0, 0, hpBarWide, 0);
        g.stroke(30, 255, 60);
        g.line(0, 0, hpBarWide*(player.health/100f), 0);
        g.pop();
        g.pop();
    }

    public RunnerPlayer findPlayer() {
        for (Entity e : world.entities) {
            if (e instanceof RunnerPlayer) {
                return (RunnerPlayer) e;
            }
        }
        return null;
    }
}
