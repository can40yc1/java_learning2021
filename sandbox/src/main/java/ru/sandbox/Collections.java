package ru.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main(String[] args) {
        String[] langs = {"Java", "C#", "Python", "PHP"};

        List<String> languages1 = new ArrayList<String>();
        languages1.add("Java");
        languages1.add("Python");

        List<String> languages2 = Arrays.asList("Java", "Python", "PHP");

        for (int i = 0; i < langs.length; i++) {
            System.out.println("Я слышал о языке " + langs[i]);
        }

        for (String l : languages1) {
            System.out.println("Я хочу выучить " + l);
        }

        for (int i = 0; i < languages2.size(); i++) {
            System.out.println("Я скоро выучу " + languages2.get(i));
        }
    }
}
