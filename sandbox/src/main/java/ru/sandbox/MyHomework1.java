package ru.sandbox;

public class MyHomework1 {

    public static void main(String[] args) {
        homework(1);

        Square square = new Square(5);
        System.out.println("Площадь квадрата со стороной " + square.size + " = " + squareMethod(square));

        Rectangle rectangle = new Rectangle(4, 6);
        System.out.println("Площадь прямоугольника со сторонами " + rectangle.a + " и " + rectangle.b + " = " + squareMethod(rectangle));

    }

    public static void homework(int number) {
        System.out.println("Задание №" + number);
    }

    public static double squareMethod(Square square) {
        return Math.pow(square.size, 2);
    }

    public static double squareMethod(Rectangle rectangle) {
        return rectangle.a * rectangle.b;
    }
}
