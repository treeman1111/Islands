package com.duncangb.islands.terrain;


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

                Tile t = new Tile(Noise.getTerrain(nx,ny));

                System.out.println(Noise.getTerrain(nx, ny));
                this.contents[x][y] = t;
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
