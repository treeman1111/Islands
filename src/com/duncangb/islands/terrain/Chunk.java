package com.duncangb.islands.terrain;

import java.util.Random;

import static com.duncangb.islands.terrain.TerrainConstants.SCALING_FACTOR;
import static com.duncangb.islands.terrain.TerrainConstants.SHIFT_FACTOR;

public class Chunk {
    private Tile[][] contents;

    public static final int CHUNK_WIDTH = 32;
    public static final int CHUNK_HEIGHT = 32;

    public Chunk(int i, int j) {
        this.contents = new Tile[CHUNK_WIDTH][CHUNK_HEIGHT];
        int global_x = i * CHUNK_WIDTH, global_y = j * CHUNK_HEIGHT;

        for (int x = 0; x < CHUNK_WIDTH; x++) {
            for (int y = 0; y < CHUNK_HEIGHT; y++) {
                double nx = (global_x + x * 1.0f) / CHUNK_WIDTH - 0.5f,
                        ny = (global_y + y * 1.0f) / CHUNK_HEIGHT - 0.5f;

                double raw_perlin = Perlin.octaved_perlin(nx, ny, 4, 0.5, 1, 128);
                Tile gnu = new Tile((int) (SCALING_FACTOR * raw_perlin) + SHIFT_FACTOR);

                gnu.setMoisture((SCALING_FACTOR + SHIFT_FACTOR - gnu.getHeight()) / (1.0 * (SCALING_FACTOR + SHIFT_FACTOR)));
                this.contents[x][y] = gnu;
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
