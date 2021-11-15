package com.sulfrix.shroomrun.lib;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.HashMap;

public class AssetCache {
    static HashMap<String, PImage> images = new HashMap<String, PImage>();
    static PApplet owner;

    private AssetCache() {

    }

    public static void init(PApplet ownerApplet) {
        owner = ownerApplet;
    }

    public static PImage getImage(String name) {
        if (images.containsKey(name)) {
            return images.get(name);
        } else {
            PImage load = owner.loadImage(name);
            images.put(name, load);
            return load;
        }
    }
}
