package com.duncangb.islands.terrain;

import com.duncangb.islands.main.Utils;

import static com.duncangb.islands.terrain.Chunk.CHUNK_HEIGHT;
import static com.duncangb.islands.terrain.Chunk.CHUNK_WIDTH;

public class ChunkGenThread extends Thread {
    private Chunk c;

    public ChunkGenThread(Chunk c) {
        this.c = c;
    }

    @Override
    public void run() {
        int global_x = c.getGlobalX() * CHUNK_WIDTH, global_y = c.getGlobalY() * CHUNK_HEIGHT;
//        System.out.println("Generating (" + c.getGlobalX() + "," + c.getGlobalY() + ")");
        double nx, ny;

        for(int x = 0; x < CHUNK_WIDTH; x++) {
            for(int y = 0; y < CHUNK_HEIGHT; y++) {
                nx = (global_x + x * 1.0f) / CHUNK_WIDTH - 0.5f;
                ny = (global_y + y * 1.0f) / CHUNK_HEIGHT - 0.5f;

                Tile t = new Tile(Noise.getTerrain(nx,ny));
                t.setTemperature(calculate_surface_temp(t));
                c.setTileAtPosition(x, y, t);
            }
        }
    }

    @Override
    public State getState() {
        if(this.c.getTileAtPosition(CHUNK_WIDTH - 1, CHUNK_HEIGHT - 1) == null) return State.RUNNABLE;
        return State.TERMINATED;
    }

    private double calculate_surface_temp(Tile t) {
        if(t.getHeight() <= 0) return 1; // max temp at sea level
        return Utils.lerp(0.95, 0, t.getHeight() / Noise.getTerrainMax());
    }
}
