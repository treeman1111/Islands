package com.duncangb.islands.terrain;

import javafx.scene.paint.Color;

public class Tile {
    private double height;
    private double winter_moisture, summer_moisture;

    private static final Color DEEPEST_WATER = Color.DARKBLUE;
    private static final Color SHALLOW_WATER = Color.LIGHTSEAGREEN;

    public Tile(double height) {
        this.height = height;
    }

    public void setWinterMoisture(double d) {
        this.winter_moisture = d;
    }

    public void setSummerMoisture(double d) {
        this.summer_moisture = d;
    }

    public boolean isOcean() {
        return (this.height < 1);
    }

    public double getHeight() {
        return this.height;
    }

    public double getMoisture(int day) {
        return lerp(winter_moisture, summer_moisture, day / 365.0);
    }

    private double lerp(double a, double b, double f)
    {
        return (a * (1.0 - f)) + (b * f);
    }
}
