package com.sulfrix.shroomrun.lib;

import processing.core.PApplet;

import java.util.HashMap;

public class Input {
    public int mouseX = 0;
    public int mouseY = 0;
    public int pmouseX = 0;
    public int pmouseY = 0;
    public boolean mousePressed = false;

    public HashMap<Integer, Boolean> keys = new HashMap<>();

    public void update(PApplet applet) {
        mouseX = applet.mouseX;
        mouseY = applet.mouseY;
        pmouseX = applet.pmouseX;
        pmouseY = applet.pmouseY;
        mousePressed = applet.mousePressed;
    }

    public void PressKey(int key) {
        keys.put(key, true);
    }

    public void ReleaseKey(int key) {
        keys.put(key, false);
    }

    public boolean KeyPressed(int key) {
        if (!keys.containsKey(key)) {
            return false;
        } else {
            return keys.get(key);
        }
    }
}
