package de.duckulus.aoc.days;

import de.duckulus.aoc.util.Day;

public class Day2 extends Day {
    public Day2() {
        super("day2");
    }

    @Override
    public String part1(String input) {
        String[] lines = input.split("\n");
        int sum = 0;
        for (String line : lines) {
            String[] tokens = line.split(":");

            String[] sets = tokens[1].split("; ");
            boolean possible = true;
            setloop:
            for (String set : sets) {
                String[] cubes = set.split(", ");
                for (String cube : cubes) {
                    String[] parts = cube.trim().split(" ");
                    int amount = Integer.parseInt(parts[0]);
                    String color = parts[1];
                    if (color.equals("red") && amount > 12 || color.equals("green") && amount > 13 || color.equals("blue") && amount > 14) {
                        possible = false;
                        break setloop;
                    }
                }
            }

            if (possible) {
                int gameId = Integer.parseInt(tokens[0].substring(5));
                sum += gameId;
            }
        }
        return String.valueOf(sum);
    }

    @Override
    public String part2(String input) {
        String[] lines = input.split("\n");
        int sum = 0;
        for (String line : lines) {
            String[] tokens = line.split(":");

            String[] sets = tokens[1].split("; ");
            int maxRed = 0;
            int maxGreen = 0;
            int maxBlue = 0;

            for (String set : sets) {
                String[] cubes = set.split(", ");
                for (String cube : cubes) {
                    String[] parts = cube.trim().split(" ");
                    int amount = Integer.parseInt(parts[0]);
                    String color = parts[1];
                    if (color.equals("red") && amount > maxRed) {
                        maxRed = amount;
                    } else if (color.equals("green") && amount > maxGreen) {
                        maxGreen = amount;
                    } else if (color.equals("blue") && amount > maxBlue) {
                        maxBlue = amount;
                    }
                }
            }

            sum += maxRed * maxGreen * maxBlue;
        }
        return String.valueOf(sum);
    }
}
