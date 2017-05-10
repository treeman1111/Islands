package com.duncangb.islands.life;

import com.duncangb.islands.terrain.Coordinate;
import com.duncangb.islands.terrain.TileMap;

import java.util.Random;

public class Grass {
    private int x, y, energy, rcrs_count;
    private TileMap map;

    public Grass(int x, int y, TileMap m) {
        this.x = x;
        this.y = y;
        this.rcrs_count = 0;
        this.map = m;
    }

    public Coordinate getPosition() {
        return new Coordinate(x, y);
    }

    public Coordinate tick() {
        if(energy > 5) {
            energy = 1;
            return choose_child_coordinate();
        }

        energy++;
        return null;
    }

    private Coordinate choose_child_coordinate() {
        Random rnd = new Random();
        int vert_pos = rnd.nextInt(3);
        int horz_pos = rnd.nextInt(3);

        if(rcrs_count > 3) return null;

        if(vert_pos == 0) { // left most column
            if(horz_pos == 0) {
                return new Coordinate(this.x-1, this.y-1);
            } else if(horz_pos == 1) {
                return new Coordinate(this.x-1, this.y);
            } else {
                return new Coordinate(this.x-1, this.y+1);
            }
        } else if(vert_pos == 1) { // middle column
            if(horz_pos == 0) {
                return new Coordinate(this.x, this.y-1);
            } else if(horz_pos == 1) {
                rcrs_count++;
                return choose_child_coordinate(); // keep calling until a different tile is chosen.
            } else {
                return new Coordinate(this.x, this.y+1);
            }
        } else {
            if(horz_pos == 0) {
                return new Coordinate(this.x+1, this.y-1);
            } else if(horz_pos == 1) {
                return new Coordinate(this.x+1, this.y);
            } else {
                return new Coordinate(this.x+1, this.y+1);
            }
        }
    }
}
