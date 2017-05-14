package com.duncangb.islands.terrain;

public class Chunk {
    private Tile[][] contents;
    private int global_x, global_y;

    public static final int CHUNK_WIDTH = 32;
    public static final int CHUNK_HEIGHT = 32;

    public Chunk(int i, int j) {
        this.contents = new Tile[CHUNK_WIDTH][CHUNK_HEIGHT];
        this.global_x = i;
        this.global_y = j;
    }

    public Tile getTileAtPosition(int tile_x, int tile_y) {
        return this.contents[tile_x][tile_y];
    }

    public void setTileAtPosition(int tile_x, int tile_y, Tile tile) {
        this.contents[tile_x][tile_y] = tile;
    }

    public int getGlobalX() {
        return this.global_x;
    }

    public int getGlobalY() {
        return this.global_y;
    }
}
