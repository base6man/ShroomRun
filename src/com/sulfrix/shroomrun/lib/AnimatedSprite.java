package com.sulfrix.shroomrun.lib;

import com.sulfrix.shroomrun.lib.GlobalManagers.AssetCache;
import processing.core.PGraphics;
import processing.core.PImage;

public class AnimatedSprite {
    public PImage[] frames;
    public int width;
    public int height;

    public int currentFrame;

    public AnimatedSprite(int width, int height, PImage img) {
        if (img.height == height && img.width % width == 0) {
            int frameCount = img.width/width;
            frames = new PImage[frameCount];
            for (int i = 0; i < frameCount; i++) {
                frames[i] = img.get(i*width, 0, width, height);
            }
        }
    }

    public AnimatedSprite(int width, int height, String tex) {
        this(width, height, AssetCache.getImage(tex));
    }

    public void draw(PGraphics g, float x, float y) {
        g.image(frames[currentFrame % frames.length], x, y);
    }
}
