package io.ankburov.codewars;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ADFadgf {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // game loop
        while (true) {
            List<Integer> mountains = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                int mountainH = in.nextInt(); // represents the height of one mountain.
                mountains.add(mountainH);
            }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            List<Integer> sortedMountains = mountains.stream()
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());

//            sortedMountains

            System.out.println("4"); // The index of the mountain to fire on.
        }
    }
}
