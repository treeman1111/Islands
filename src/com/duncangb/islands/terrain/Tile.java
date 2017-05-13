package com.duncangb.islands.terrain;

public class Tile {
    private double height, moisture;

    public Tile(double height) {
        this.height = height;
    }

    public void setMoisture(double m) {
        this.moisture = m;
    }

    public boolean isOcean() {
        return (this.height < 1);
    }

    public double getHeight() {
        return this.height;
    }

    public double getMoisture() {
        return this.moisture;
    }
}
