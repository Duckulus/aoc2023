package de.duckulus.aoc.days;

import de.duckulus.aoc.util.Day;

import java.util.HashMap;
import java.util.HashSet;

public class Day3 extends Day {
    public Day3() {
        super("day3");
    }

    @Override
    public String part1(String input) {
        String[] lines = input.split("\n");
        int sum = 0;
        for (int i = 0; i < lines.length; i++) {
            int numIndex = -1;
            int numLength = -1;
            for (int charIndex = 0; charIndex < lines[i].length(); charIndex++) {
                int c = lines[i].charAt(charIndex);
                if (Character.isDigit(c) && numIndex == -1) {
                    numIndex = charIndex;
                    numLength = 1;
                } else if (Character.isDigit(c)) {
                    numLength++;
                } else {
                    if (numIndex != -1) {
                        boolean hasAdjacentSymbol = false;
                        if (isSymbol(lines[i].charAt(numIndex + numLength))) { //right
                            hasAdjacentSymbol = true;
                        } else if (numIndex - 1 >= 0 && isSymbol(lines[i].charAt(numIndex - 1))) { // left
                            hasAdjacentSymbol = true;
                        } else {
                            if (i > 0) { // above
                                String lineAbove = lines[i - 1];
                                for (int index = numIndex - 1; index < numIndex + numLength + 1; index++) {
                                    if (index >= 0 && index < lineAbove.length() && isSymbol(lineAbove.charAt(index))) {
                                        hasAdjacentSymbol = true;
                                    }
                                }
                            }
                            if (i < lines.length - 1) { //below
                                String lineBelow = lines[i + 1];
                                for (int index = numIndex - 1; index < numIndex + numLength + 1; index++) {
                                    if (index >= 0 && index < lineBelow.length() && isSymbol(lineBelow.charAt(index))) {
                                        hasAdjacentSymbol = true;
                                    }
                                }
                            }
                        }
                        if (hasAdjacentSymbol) {
                            sum += Integer.parseInt(lines[i].substring(numIndex, numIndex + numLength));
                        }
                        numIndex = -1;
                    }
                }
            }
        }
        return String.valueOf(sum);
    }

    @Override
    public String part2(String input) {
        String[] lines = input.split("\n");
        HashMap<Integer, int[]> neighbors = new HashMap<>();
        HashSet<Integer> overflownStars = new HashSet<>();
        for (int i = 0; i < lines.length; i++) {
            int numIndex = -1;
            int numLength = -1;
            for (int charIndex = 0; charIndex < lines[i].length(); charIndex++) {
                int c = lines[i].charAt(charIndex);
                if (Character.isDigit(c) && numIndex == -1) {
                    numIndex = charIndex;
                    numLength = 1;
                } else if (Character.isDigit(c)) {
                    numLength++;
                } else {
                    if (numIndex != -1) {
                        int number = Integer.parseInt(lines[i].substring(numIndex, numIndex + numLength));
                        if (lines[i].charAt(numIndex + numLength) == '*') { //right
                            int starIndex = i * lines[i].length() + numIndex + numLength;
                            addNeighbor(neighbors, overflownStars, number, starIndex);

                        } else if (numIndex - 1 >= 0 && lines[i].charAt(numIndex - 1) == '*') { // left
                            int starIndex = i * lines[i].length() + (numIndex - 1);
                            addNeighbor(neighbors, overflownStars, number, starIndex);
                        } else {
                            if (i > 0) { // above
                                String lineAbove = lines[i - 1];
                                for (int index = numIndex - 1; index < numIndex + numLength + 1; index++) {
                                    if (index >= 0 && index < lineAbove.length() && lineAbove.charAt(index) == '*') {
                                        int starIndex = (i - 1) * lines[i - 1].length() + index;
                                        addNeighbor(neighbors, overflownStars, number, starIndex);
                                    }
                                }
                            }
                            if (i < lines.length - 1) { //below
                                String lineBelow = lines[i + 1];
                                for (int index = numIndex - 1; index < numIndex + numLength + 1; index++) {
                                    if (index >= 0 && index < lineBelow.length() && lineBelow.charAt(index) == '*') {
                                        int starIndex = (i + 1) * lines[i + 1].length() + index;
                                        addNeighbor(neighbors, overflownStars, number, starIndex);
                                    }
                                }
                            }
                        }
                        numIndex = -1;
                    }
                }
            }
        }

        int sum = 0;
        for (Integer i : neighbors.keySet()) {
            int[] array = neighbors.get(i);
            if (array != null && array[0] != 0 && array[1] != 0 && !overflownStars.contains(i)) {
                sum += array[0] * array[1];
            }
        }
        return String.valueOf(sum);
    }

    private void addNeighbor(HashMap<Integer, int[]> neighbors, HashSet<Integer> overflownStars, int number, int starIndex) {
        int[] neighborArray = neighbors.get(starIndex);
        if (neighborArray == null) {
            neighborArray = new int[2];
            neighborArray[0] = number;
            neighbors.put(starIndex, neighborArray);
        } else if (neighborArray[1] == 0) {
            neighborArray[1] = number;
        } else {
            overflownStars.add(starIndex);
        }
    }

    private boolean isSymbol(char c) {
        return !Character.isDigit(c) && c != '.' && !Character.isWhitespace(c);
    }

}
