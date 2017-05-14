package com.duncangb.islands.terrain;

public class Tile {
    private double height, moisture, temperature;

    public Tile(double height) {
        this.height = height;
    }

    public void setMoisture(double m) {
        this.moisture = m;
    }

    public void setTemperature(double t) {
        this.temperature = t;
    }

    public boolean isOcean() {
        return (this.height < 0);
    }

    public double getHeight() {
        return this.height;
    }

    public double getMoisture() {
        return this.moisture;
    }

    public double getTemperature() {
        return this.temperature;
    }
}
