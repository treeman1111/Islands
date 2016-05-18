package com.duncangb.islands.terrain;

public interface ITileContainer {
    void setTileAtPosition(int tile_x, int tile_y, ITile tile);
    ITile getTileAtPosition(int tile_x, int tile_y);
}
