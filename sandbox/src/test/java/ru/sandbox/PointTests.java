package ru.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void intPointDistanceTest(){
        Point point1 = new Point(1, 2);
        Point point2 = new Point(3, 4);
        Assert.assertEquals(point1.distanceTo(point2),2.8284271247461903);
    }

    @Test
    public void floatPointDistanceTest(){
        Point point1 = new Point(0.01, 3.2);
        Point point2 = new Point(5.6, 1.3);
        Assert.assertEquals(point1.distanceTo(point2),5.904074864023999);
    }

    @Test
    public void zeroPointDistanceTest(){
        Point point1 = new Point(0, 0);
        Point point2 = new Point(3, 4);
        Assert.assertEquals(point1.distanceTo(point2),5);
    }

    @Test
    public void negativePointDistanceTest(){
        Point point1 = new Point(-1, -2);
        Point point2 = new Point(3, 4);
        Assert.assertEquals(point1.distanceTo(point2),7.211102550927978);
    }

    @Test
    public void samePointDistanceTest(){
        Point point1 = new Point(1, 2);
        Assert.assertEquals(point1.distanceTo(point1),0);
    }
}
