package com.duncangb.islands.terrain;

import com.duncangb.islands.main.islandController;

import java.io.*;

public class Chunk implements IChunk {
    private ITile[][] contents;
    private String data_location;

    public static final int CHUNK_WIDTH = 32;
    public static final int CHUNK_HEIGHT = 32;

    public Chunk(int i, int j) throws IOException {
        this.contents = new ITile[CHUNK_WIDTH][CHUNK_HEIGHT];
        this.data_location = islandController.CHUNK_DIRECTORY + "\\CHUNK-" + i + "-" + j;

        if (new File(data_location).exists()) {
            FileInputStream reader = new FileInputStream(data_location);

            int x_cursor = 0, y_cursor = 0, read;
            while ((read = reader.read()) != -1) {
                contents[x_cursor][y_cursor] = new Tile((byte) read);

                if (y_cursor == CHUNK_HEIGHT - 1) {
                    y_cursor = 0;
                    x_cursor++;
                } else {
                    y_cursor++;
                }
            }

            reader.close();
        } else {
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
    }

    @Override
    public ITile getTileAtPosition(int tile_x, int tile_y) {
        return this.contents[tile_x][tile_y];
    }

    @Override
    public void setTileAtPosition(int tile_x, int tile_y, ITile tile) {
        this.contents[tile_x][tile_y] = tile;
    }

    @Override
    public void unload() throws IOException {
        FileOutputStream writer = new FileOutputStream(data_location);

        for (int x = 0; x < CHUNK_WIDTH; x++) {
            for (int y = 0; y < CHUNK_HEIGHT; y++) {
                writer.write(contents[x][y].getHeight());
            }
        }

        writer.close();
    }
}
