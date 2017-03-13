package com.duncangb.islands.terrain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Perlin {
    static final int p[] = new int[512],
            permutation[] = newPermutation();

    static {
        for (int i=0; i < 256 ; i++)
            p[256+i] = p[i] = permutation[i];
    }

    static public double noise(double x, double y) {
        int X = (int) Math.floor(x) & 0xff;
        int Y = (int) Math.floor(y) & 0xff;

        x -= (int) Math.floor(x);
        y -= (int) Math.floor(y);

        double u = fade(x);
        double v = fade(y); // as x and y get closer to next unit cell, fade approaches 1.

        int a = (p[X] + Y) & 0xff;
        int b = (p[X+1] + Y) & 0xff; // [0, 255] -- assigning the pseudorandom gradient to the gridpoints

        return lerp(v,
                lerp(u, grad(p[a], x, y), grad(p[b], x-1, y)),
                lerp(u, grad(p[a+1], x, y-1), grad(p[b+1], x-1, y-1))
        ); // returns [-1, 1]
    }

    private static double fade(double t) {
        return t * t * (3 - 2 * t);
    }

    private static double lerp(double t, double a, double b) {
        return a + t * (b - a);
    }

    private static double grad(int hash, double x, double y) {
        return ((hash & 0x1) == 0 ? x : -x) + ((hash & 0x2) == 0 ? y : -y);
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
