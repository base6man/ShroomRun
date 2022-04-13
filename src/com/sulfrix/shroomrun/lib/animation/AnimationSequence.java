package com.sulfrix.shroomrun.lib.animation;

import processing.core.PImage;

public class AnimationSequence {
    public String name;
    protected AnimatedSprite owner;
    protected PImage[] frames;
    public float rate;
    protected float timer;

    protected AnimationSequence(String name, float rate, PImage[] frames) {
        this.name = name;
        this.frames = frames;
        this.rate = rate;
    }

    public static AnimationSequence addToSprite(AnimatedSprite sprite, String name, float rate, PImage[] frames) {
        var out = new AnimationSequence(name, rate, frames);
        out.owner = sprite;
        return out;
    }

    public AnimatedSprite getOwner() {
        return owner;
    }

    public void progress(float time) {
        timer += time*rate;
    }

    public PImage getCurrent() {
        return frames[(int)timer%frames.length];
    }

    public float getTimer() {
        return timer;
    }

    public void resetTimer() {
        timer = 0;
    }

    public void setTimer(float value) {
        timer = value;
    }
}
