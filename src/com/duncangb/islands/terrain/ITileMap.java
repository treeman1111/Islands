package com.duncangb.islands.terrain;

public interface ITileMap extends ITileContainer {
    int getWidthInTiles();
    int getHeightInTiles();
    int getWidthInChunks();
    int getHeightInChunks();
    ITile getTileAtPosition(int chunk_x, int chunk_y, int tile_x, int tile_y);
    void setTileAtPosition(int chunk_x, int chunk_y, int tile_x, int tile_y, ITile tile);
}
