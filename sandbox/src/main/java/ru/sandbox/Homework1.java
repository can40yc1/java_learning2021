package ru.sandbox;

public class Homework1 {

    public static void main(String[] args) {
        homework(1);

        Square square = new Square(5);
        System.out.println("Площадь квадрата со стороной " + square.size + " = " + square.squareMethod());

        Rectangle rectangle = new Rectangle(4, 6);
        System.out.println("Площадь прямоугольника со сторонами " + rectangle.a + " и " + rectangle.b + " = " + rectangle.squareMethod());

    }

    public static void homework(int number) {
        System.out.println("Задание №" + number);
    }

}
