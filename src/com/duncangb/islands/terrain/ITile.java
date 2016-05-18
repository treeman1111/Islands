package com.duncangb.islands.terrain;

import javafx.scene.paint.Color;

public interface ITile {
    boolean isOcean();
    byte getHeight();
    void setHeight(byte height);
    Color getColor();
}
