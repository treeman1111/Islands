package com.duncangb.islands.terrain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* Taken from http://mrl.nyu.edu/~perlin/noise/ and modified. */

public class Perlin {
    /* This seems equivalent to a seed, superficially. */
    static final int p[] = new int[512],
            permutation[] = newPermutation();

    static {
        for (int i=0; i < 256 ; i++)
            p[256+i] = p[i] = permutation[i];
    }

    static public double noise(double x, double y, double z) {
        int X = (int)Math.floor(x) & 255, Y = (int)Math.floor(y) & 255, Z = (int)Math.floor(z) & 255;

        x -= Math.floor(x);
        y -= Math.floor(y);
        z -= Math.floor(z);

        double u = fade(x), v = fade(y), w = fade(z);

        int A = p[X] + Y,
                AA = p[A] + Z,
                AB = p[A + 1] + Z,
                B = p[X + 1] + Y,
                BA = p[B] + Z, BB = p[B + 1] + Z;

        return lerp(w, lerp(v, lerp(u, grad(p[AA], x  , y  , z   ),  // AND ADD
                grad(p[BA], x-1, y  , z   )), // BLENDED
                lerp(u, grad(p[AB], x  , y-1, z   ),  // RESULTS
                        grad(p[BB], x-1, y-1, z   ))),// FROM  8
                lerp(v, lerp(u, grad(p[AA + 1], x  , y  , z-1 ),  // CORNERS
                        grad(p[BA+1], x-1, y  , z-1 )), // OF CUBE
                        lerp(u, grad(p[AB + 1], x  , y-1, z-1 ),
                                grad(p[BB + 1], x-1, y-1, z-1 ))));
    }

    private static double fade(double t) { return t * t * t * (t * (t * 6 - 15) + 10); }

    private static double lerp(double t, double a, double b) { return a + t * (b - a); }

    private static double grad(int hash, double x, double y, double z) {
        int h = hash & 15;                      // CONVERT LO 4 BITS OF HASH CODE
        double u = h < 8 ? x : y,                 // INTO 12 GRADIENT DIRECTIONS.
                v = h < 4 ? y : h==12 || h==14 ? x : z;
        return ((h&1) == 0 ? u : -u) + ((h&2) == 0 ? v : -v);
    }

    private static int[] newPermutation() {
        List<Integer> list = new ArrayList<>(256);
        for (int i = 0; i < 256; i++) {
            list.add(i);
        }
        Collections.shuffle(list);

        int[] result = new int[256];
        for (int i = 0; i < 256; i++) {
            result[i] = list.get(i);
        }

        return result;
    }
}
