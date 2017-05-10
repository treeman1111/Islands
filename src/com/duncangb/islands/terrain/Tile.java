package com.duncangb.islands.terrain;

import javafx.scene.paint.Color;

import static com.duncangb.islands.terrain.TerrainConstants.*;

public class Tile {
    private int height;
    private double moisture;

    private static final Color DEEPEST_WATER = Color.DARKBLUE;
    private static final Color SHALLOW_WATER = Color.LIGHTSEAGREEN;

    private static final Color BEACH_COLOR = Color.BEIGE;
    private static final Color PEAK_COLOR = Color.PURPLE;

    public Tile(int height) {
        this.height = height;
        this.moisture = 1;
    }

    public void setMoisture(double m) {
        this.moisture = m;
    }

    public boolean isOcean() {
        return (this.height < SEA_LEVEL);
    }

    public int getHeight() {
        return this.height;
    }

    public double getMoisture() {
        return this.moisture;
    }

    public Color getColor() {
        if (this.height < SEA_LEVEL) {
            return SHALLOW_WATER.interpolate(DEEPEST_WATER, this.height / (-1.0 * SCALING_FACTOR));
        } else {
            //return BEACH_COLOR.interpolate(PEAK_COLOR, this.height / (SCALING_FACTOR * 1.0));
            return Color.RED.interpolate(Color.GREEN, moisture);
        }
    }
}
