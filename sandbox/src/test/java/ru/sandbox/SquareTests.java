package ru.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class SquareTests {

    @Test
    public void testSquareMethod() {
        Square square = new Square(5);
        Assert.assertEquals(square.squareMethod(), 25);
    }

}
