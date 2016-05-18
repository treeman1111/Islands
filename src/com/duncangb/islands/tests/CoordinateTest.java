package com.duncangb.islands.tests;

import com.duncangb.islands.terrain.Coordinate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {
    private Coordinate origin;
    private Coordinate other;
    private Coordinate originAgain;

    @Before
    public void setUp() throws Exception {
        origin = new Coordinate(0, 0);
        other = new Coordinate(0, 11);
        originAgain = new Coordinate(0, 0);
    }

    @Test
    public void testGetX() throws Exception {
        assertEquals(origin.getX(), 0);
        assertEquals(other.getX(), 0);
        assertEquals(originAgain.getX(), 0);
    }

    @Test
    public void testGetY() throws Exception {
        assertEquals(origin.getY(), 0);
        assertEquals(other.getY(), 11);
        assertEquals(originAgain.getY(), 0);
    }

    @Test
    public void testEquals() throws Exception {
        assertEquals(origin, originAgain);
        assertNotEquals(origin, other);
        assertNotEquals(origin, "hello");
    }
}