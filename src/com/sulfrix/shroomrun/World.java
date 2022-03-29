package com.sulfrix.shroomrun;

import com.sulfrix.shroomrun.entities.Camera;
import com.sulfrix.shroomrun.lib.BoundingBox;
import com.sulfrix.shroomrun.lib.Display;
import com.sulfrix.shroomrun.lib.Input;
import com.sulfrix.shroomrun.lib.RenderPosType;
import processing.core.PGraphics;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Collections;

public class World {
    public double globalTimescale = 1;
    public double gravity = 1.2;

    public ArrayList<Entity> entities;
    public Camera camera;
    public Input input;

    public int renderedEnts;

    public boolean updateEnabled = true;

    public World() {
        entities = new ArrayList<>();
    }

    public void update(double timescale) {
        if (updateEnabled) {
            // unmodifiable list used in case an entity is deleted in the middle of an update() loop
            for (Entity e : Collections.unmodifiableList(entities)) {
                e.update(timescale * globalTimescale);
            }
        }
    }

    public void draw(double timescale, PGraphics g) {
        renderedEnts = 0;
        DoCameraFocus();
        for (Entity e : entities) {
            if ((EntOnscreen(e) || e.renderingOffscreen) && e.renderingEnabled) {
                DrawEntity(e, timescale * globalTimescale, g);
                renderedEnts++;
            }
        }
    }

    public Entity AddEntity(Entity ent) {
        if (ent.world != null) {
            ent.world.RemoveEntity(ent);
        }
        ent.world = this;
        entities.add(ent);
        return ent;
    }

    public void RemoveEntity(Entity ent) {
        ent.world = null;
        if (entities.contains(ent)) {
            entities.remove(ent);
        }
    }

    public void DrawEntity(Entity entity, double timescale, PGraphics g) {
        g.push();
        RenderOffsets(entity, g);
        entity.draw(timescale, g);
        if (ShroomRun.debugOBB) {
            g.push();
            g.stroke(255);
            g.strokeWeight(1);
            g.fill(0, 0, 0, 40);
            g.rect(0, 0, (float)entity.boundingBox.width, (float)entity.boundingBox.height);
            g.pop();
        }
        g.pop();
    }

    public void RenderOffsets(Entity entity, PGraphics g) {
        if (entity.renderPosType == RenderPosType.WORLD_SPACE) {
            PVector camPos = CameraPos();
            //g.translate(-camPos.x + (g.width / 2) + entity.position.x, -camPos.y + (g.height / 2) + entity.position.y);
            g.translate((float) (-camPos.x*camera.getScale()), (float) (-camPos.y*camera.getScale()));
            g.translate(g.width/2, g.height/2);
            g.scale((float)camera.getScale());
            g.translate(entity.position.x, entity.position.y, entity.ZPos);

            //g.translate(0, 0, entity.ZPos);
        } else {
            g.translate(entity.position.x, entity.position.y);
        }
        if (entity.OBBCenter) {
            g.translate(-(float) entity.boundingBox.width / 2, -(float) entity.boundingBox.height / 2);
        }
    }

    public PVector CameraPos() {
        if (camera != null) {
            return camera.position;
        } else {
            return new PVector(0, 0);
        }
    }

    public void DoCameraFocus() {
        if (camera != null) {
            if (camera.focus != null) {
                camera.position = PVector.add(camera.focus.position, camera.focusOffset);
            }
        }
    }

    public boolean EntOnscreen(Entity ent) {

        if (ent.renderPosType == RenderPosType.SCREEN_SPACE) {
            return true;
        } else {
            var w = (float)(Display.width()*(1/camera.getScale()))+0;
            var h = (float)(Display.height()*(1/camera.getScale()))+0;
            //return ent.touching(new BoundingBox(w, h), PVector.sub(CameraPos(), new PVector(w/2, h/2)));
            return ent.touching(new BoundingBox(w, h), CameraPos());
        }
    }
}
