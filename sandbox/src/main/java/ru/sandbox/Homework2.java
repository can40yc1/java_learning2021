package ru.sandbox;

public class Homework2 {

    public static void main(String[] args) {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);

        //Используем функцию
        System.out.println("Расстояние между точками p1 (" + p1.x + ", " + p1.y + ") и p2 (" + p2.x + ", " + p2.y + ") = " + distance(p1, p2));

        //Используем метод
        System.out.println("Расстояние от точки p1 (" + p1.x + ", " + p1.y + ") до точки p2 (" + p2.x + ", " + p2.y + ") = " + p1.distanceTo(p2));
        System.out.println("Расстояние от точки p2 (" + p2.x + ", " + p2.y + ") до точки p1 (" + p1.x + ", " + p1.y + ") = " + p2.distanceTo(p1));
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
}
