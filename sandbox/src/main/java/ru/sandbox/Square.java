package ru.sandbox;

public class Square {
    public double size;

    public Square(double size) {
        this.size = size;
    }

    public double squareMethod() {
        return Math.pow(this.size, 2);
    }
}
