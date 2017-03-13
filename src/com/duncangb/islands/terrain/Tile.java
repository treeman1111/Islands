package com.duncangb.islands.terrain;

import javafx.scene.paint.Color;

public class Tile {
    private byte height;

    public Tile(byte height) {
        setHeight(height);
    }

    public boolean isOcean() {
        return (this.height < 0);
    }

    public byte getHeight() {
        return this.height;
    }

    public void setHeight(byte height) {
        this.height = height;
    }

    // come up with a gradient function for this
    public Color getColor() {
        if (getHeight() < 0 && getHeight() > -20) {
            return Color.rgb(16, 127, 201);
        } else if (getHeight() <= -20 && getHeight() > -40) {
            return Color.rgb(14, 78, 173);
        } else if (getHeight() <= -40 && getHeight() > -60) {
            return Color.rgb(11, 16, 140);
        } else if (getHeight() <= -60 && getHeight() > -80) {
            return Color.rgb(12, 15, 102);
        } else if (getHeight() <= -80 && getHeight() > -100){
            return Color.rgb(7, 9, 61);
        } else if (getHeight() == 0) {
            return Color.rgb(253, 230, 189);
        } else {
            return Color.BLACK;
        }
    }
}
