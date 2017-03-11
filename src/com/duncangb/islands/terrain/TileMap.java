package com.duncangb.islands.terrain;

import java.util.HashMap;
import java.util.Map;

public class TileMap {
    private Map<Coordinate, Chunk> map;
    private int chunks_wide;
    private int chunks_tall;

    private static final short INIT_MAP_SIZE = 100;

    public TileMap(int chunks_wide, int chunks_tall) {
        if (chunks_wide < 2 || chunks_tall < 2) {
            throw new IllegalArgumentException("Map must be at least two chunks in either direction.");
        }

        this.chunks_wide = chunks_wide;
        this.chunks_tall = chunks_tall;
        map = new HashMap<>(INIT_MAP_SIZE); // More efficient than growing each time a new chunk is added?
    }

    public int getWidthInTiles() {
        return chunks_wide * Chunk.CHUNK_WIDTH;
    }

    public int getHeightInTiles() {
        return chunks_tall * Chunk.CHUNK_HEIGHT;
    }

    public int getWidthInChunks() {
        return chunks_tall;
    }

    public int getHeightInChunks() {
        return chunks_wide;
    }

    public Tile getTileAtPosition(int x, int y) {
        int chunk_x = x / Chunk.CHUNK_WIDTH;
        int chunk_y = y / Chunk.CHUNK_HEIGHT;

        int tile_x = x - (chunk_x * Chunk.CHUNK_WIDTH);
        int tile_y = y - (chunk_y * Chunk.CHUNK_HEIGHT);

        return getTileAtPosition(chunk_x, chunk_y, tile_x, tile_y);
    }

    public Tile getTileAtPosition(int chunk_x, int chunk_y, int tile_x, int tile_y) {
        checkTileBounds(chunk_x, chunk_y, tile_x, tile_y);

        if (map.containsKey(new Coordinate(chunk_x, chunk_y))) {
            return map.get(new Coordinate(chunk_x, chunk_y)).getTileAtPosition(tile_x, tile_y);
        } else {
            Chunk temp = new Chunk(chunk_x, chunk_y);

            map.put(new Coordinate(chunk_x, chunk_y), temp);
            return temp.getTileAtPosition(tile_x, tile_y);
        }
    }

    public void setTileAtPosition(int x, int y, Tile tile) {
        int chunk_x = x / Chunk.CHUNK_WIDTH;
        int chunk_y = y / Chunk.CHUNK_HEIGHT;

        int tile_x = x - (chunk_x * Chunk.CHUNK_WIDTH);
        int tile_y = y - (chunk_y * Chunk.CHUNK_HEIGHT);

        setTileAtPosition(chunk_x, chunk_y, tile_x, tile_y, tile);
    }

    public void setTileAtPosition(int chunk_x, int chunk_y, int tile_x, int tile_y, Tile tile) {
        checkTileBounds(chunk_x, chunk_y, tile_x, tile_y);

        if (tile == null) {
            throw new IllegalArgumentException("Bitch, plz.");
        }

        if (map.containsKey(new Coordinate(chunk_x, chunk_y))) {
            map.get(new Coordinate(chunk_x, chunk_y)).setTileAtPosition(tile_x, tile_y, tile);
        } else {
            Chunk temp =  new Chunk(chunk_x, chunk_y);
            map.put(new Coordinate(chunk_x, chunk_y), temp);
            temp.setTileAtPosition(tile_x, tile_y, tile);
        }

    }

    private void checkTileBounds(int chunk_x, int chunk_y, int tile_x, int tile_y) {
        if (chunk_x < 0 || chunk_x >= chunks_wide || chunk_y < 0 || chunk_y >= chunks_tall) {
            throw new IllegalArgumentException("Chunk value should be between 0 and " + (chunks_wide - 1));
        }

        if (tile_x < 0 || tile_x >= Chunk.CHUNK_WIDTH || tile_y < 0 || tile_y >= Chunk.CHUNK_HEIGHT) {
            throw new IllegalArgumentException("Tile value should be between 0 and " + (Chunk.CHUNK_HEIGHT - 1));
        }
    }
}
