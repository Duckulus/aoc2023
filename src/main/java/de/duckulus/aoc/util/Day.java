package de.duckulus.aoc.util;

import java.io.IOException;

public abstract class Day {

    private String name;

    public Day(String name) {
        this.name = name;
    }

    public abstract String part1(String input);

    public abstract String part2(String input);

    public void run() {
        String input;
        try {
            input = IOUtil.resourceToString("inputs/" + name + ".txt");
        } catch (IOException e) {
            System.err.println("Error reading input for " + name + ": " + e.getMessage());
            return;
        }

        System.out.println("Results for " + name);

        long startTime = System.currentTimeMillis();
        String result = part1(input);
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Part 1: " + result + " (took " + duration + "ms)");
        startTime = System.currentTimeMillis();
        result = part2(input);
        duration = System.currentTimeMillis() - startTime;
        System.out.println("Part 2: " + result + " (took " + duration + "ms)");
    }

}
