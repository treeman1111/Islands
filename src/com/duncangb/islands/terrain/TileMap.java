package com.duncangb.islands.terrain;

import java.util.HashMap;
import java.util.Map;

public class TileMap {
    private Map<Coordinate, Chunk> map;
    private int chunks_wide;
    private int chunks_tall;

    public TileMap(int chunks_wide, int chunks_tall) {
        if (chunks_wide < 2 || chunks_tall < 2) {
            throw new IllegalArgumentException("Map must be at least two chunks in either direction.");
        }

        this.chunks_wide = chunks_wide;
        this.chunks_tall = chunks_tall;
        map = new HashMap<>(chunks_wide * chunks_tall); // More efficient than growing each time a new chunk is added?
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

    private Tile getTileAtPosition(int chunk_x, int chunk_y, int tile_x, int tile_y) {
        if(!checkTileBounds(chunk_x, chunk_y, tile_x, tile_y)) return null;

        if (map.containsKey(new Coordinate(chunk_x, chunk_y))) {
            return map.get(new Coordinate(chunk_x, chunk_y)).getTileAtPosition(tile_x, tile_y);
        } else {
            Chunk temp = new Chunk(chunk_x, chunk_y);
            map.put(new Coordinate(chunk_x, chunk_y), temp);
            return temp.getTileAtPosition(tile_x, tile_y);
        }
    }

    public Chunk getChunkAtPosition(int chunk_x, int chunk_y) {
        if(!checkTileBounds(chunk_x, chunk_y, 0, 0)) return null;
        return map.get(new Coordinate(chunk_x, chunk_y));
    }

    public void setTileAtPosition(int x, int y, Tile tile) {
        int chunk_x = x / Chunk.CHUNK_WIDTH;
        int chunk_y = y / Chunk.CHUNK_HEIGHT;

        int tile_x = x - (chunk_x * Chunk.CHUNK_WIDTH);
        int tile_y = y - (chunk_y * Chunk.CHUNK_HEIGHT);

        setTileAtPosition(chunk_x, chunk_y, tile_x, tile_y, tile);
    }

    private void setTileAtPosition(int chunk_x, int chunk_y, int tile_x, int tile_y, Tile tile) {
        if(!checkTileBounds(chunk_x, chunk_y, tile_x, tile_y)) return;
        if (tile == null) return;

        if (map.containsKey(new Coordinate(chunk_x, chunk_y))) {
            map.get(new Coordinate(chunk_x, chunk_y)).setTileAtPosition(tile_x, tile_y, tile);
        } else {
            Chunk temp =  new Chunk(chunk_x, chunk_y);
            map.put(new Coordinate(chunk_x, chunk_y), temp);
            temp.setTileAtPosition(tile_x, tile_y, tile);
        }

    }

    private boolean checkTileBounds(int chunk_x, int chunk_y, int tile_x, int tile_y) {
        if (chunk_x < 0 || chunk_x >= chunks_wide || chunk_y < 0 || chunk_y >= chunks_tall) {
            return false;
        } else if (tile_x < 0 || tile_x >= Chunk.CHUNK_WIDTH || tile_y < 0 || tile_y >= Chunk.CHUNK_HEIGHT) {
            return false;
        }

        return true;
    }
}
