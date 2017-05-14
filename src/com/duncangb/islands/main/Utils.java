package com.duncangb.islands.main;

public class Utils {
    public static double lerp(double a, double b, double t) {
        return (1-t) * a + t * b;
    }
}
