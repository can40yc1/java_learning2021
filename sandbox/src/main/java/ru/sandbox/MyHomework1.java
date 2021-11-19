package ru.sandbox;

public class MyHomework1 {

    public static void main(String[] args) {
        homework(1);

        double size = 5;
        System.out.println("Площадь квадрата со стороной " + size + " = " + squareMethod(size));

        double a = 4;
        double b = 6;
        System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " = " + squareMethod(a, b) );

    }

    public static void homework(int number) {
        System.out.println("Задание №" + number);
    }

    public static double squareMethod(double size) {
        return Math.pow(size, 2);
    }

    public static double squareMethod(double a, double b){
        return a*b;
    }
}
