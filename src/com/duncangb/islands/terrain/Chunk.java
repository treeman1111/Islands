package com.duncangb.islands.terrain;

import com.duncangb.islands.main.islandController;

public class Chunk {
    private Tile[][] contents;

    public static final int CHUNK_WIDTH = 32;
    public static final int CHUNK_HEIGHT = 32;

    public Chunk(int i, int j) {
        this.contents = new Tile[CHUNK_WIDTH][CHUNK_HEIGHT];
        int global_x = i * CHUNK_WIDTH;
        int global_y = j * CHUNK_HEIGHT;

        for (int x = 0; x < CHUNK_WIDTH; x++) {
            for (int y = 0; y < CHUNK_HEIGHT; y++) {
                float nx = (global_x + x * 1.0f) / CHUNK_WIDTH - 0.5f;
                float ny = (global_y + y * 1.0f) / CHUNK_HEIGHT - 0.5f;

                double raw_perlin = (Perlin.noise(nx, ny, 0) +
                        0.5 * Perlin.noise(nx * 2, ny * 2, 0) +
                        0.25 * Perlin.noise(nx * 4, ny * 4, 0) +
                        0.125 * Perlin.noise(nx * 8, ny * 8, 0));

                byte scaled = (byte) (-255 * Math.abs(raw_perlin) - 1);

                this.contents[x][y] = new Tile(scaled);
            }
        }
    }

    public Tile getTileAtPosition(int tile_x, int tile_y) {
        return this.contents[tile_x][tile_y];
    }

    public void setTileAtPosition(int tile_x, int tile_y, Tile tile) {
        this.contents[tile_x][tile_y] = tile;
    }
}
