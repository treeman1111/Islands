package com.duncangb.islands.tests;

import com.duncangb.islands.terrain.Chunk;
import com.duncangb.islands.terrain.Tile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TileTest {
    @Test
    public void test() {
        Chunk c = new Chunk(0, 0);
        for(int x=0;x < c.CHUNK_WIDTH; x++) {
            for(int y=0; y < c.CHUNK_HEIGHT; y++) {
                System.out.print(c.getTileAtPosition(x, y).getHeight() + ", ");
            }
        }
    }
}