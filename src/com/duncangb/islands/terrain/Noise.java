package com.duncangb.islands.terrain;

import com.flowpowered.noise.module.combiner.Select;
import com.flowpowered.noise.module.modifier.ScaleBias;
import com.flowpowered.noise.module.source.*;

import java.util.Random;

public class Noise {
    private static final ScaleBias terrain = new ScaleBias();
    private static double terrain_min = 1e4, terrain_max = -1e4;

    static {
        Billow unscaled_sea_floor = new Billow();
        ScaleBias scaled_sea_floor = new ScaleBias();
        RidgedMulti island = new RidgedMulti();
        Perlin perlin = new Perlin();
        Select unscaled = new Select();

        int seed = (int) (Math.random() * 10000);

        scaled_sea_floor.setSourceModule(0, unscaled_sea_floor);
        scaled_sea_floor.setScale(1/8.0);
        scaled_sea_floor.setBias(-2);

        island.setFrequency(0.7);

        perlin.setFrequency(0.5);
        perlin.setPersistence(0.5);
        perlin.setSeed(seed);

        unscaled.setSourceModule(0, scaled_sea_floor);
        unscaled.setSourceModule(1, island);
        unscaled.setControlModule(perlin);
        unscaled.setBounds(100, 1.0275);
        unscaled.setEdgeFalloff(0.2); // smaller values make the change from island to sea floor more rapid

        terrain.setSourceModule(0, unscaled);
        terrain.setBias(0.5);

        approximate_max_and_min();
    }

    public static double getTerrain(double x, double y) {
        return terrain.getValue(x, y, 0);
    }

    public static double getTerrainMax() {
        return terrain_max;
    }

    public static double getTerrainMin() {
        return terrain_min;
    }

    // shit way of finding (approximate) max and min values from lib...
    private static void approximate_max_and_min() {
        Random rnd = new Random();
        double val;
        for(int i = 0; i < 1000; i++) {
            val = terrain.getValue(rnd.doubles(1, -1e6, 1e6+1).findAny().getAsDouble(), 0, 0);
            if(val < terrain_min) {
                terrain_min = val;
            }

            if(val > terrain_max) {
                terrain_max = val;
            }
        }
    }
}
