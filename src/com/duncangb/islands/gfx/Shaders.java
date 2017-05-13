package com.duncangb.islands.gfx;

import com.duncangb.islands.terrain.Noise;
import com.duncangb.islands.terrain.Tile;
import javafx.scene.paint.Color;

public class Shaders {
    public static Color shade_elevation(Tile t) {
        if(t == null) return Color.WHITE;

        if(t.getHeight() < 0) {
            return Color.LIGHTBLUE.interpolate(Color.DARKBLUE, t.getHeight() / Noise.getTerrainMin());
        } else {
            return Color.GREEN.interpolate(Color.BROWN, t.getHeight() / Noise.getTerrainMax());
        }
    }
}
