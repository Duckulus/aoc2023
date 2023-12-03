package de.duckulus.aoc.days;

import de.duckulus.aoc.util.Day;

public class Day1 extends Day {

    private final String[] digits = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    public Day1() {
        super("day1");
    }

    @Override
    public String part1(String input) {
        String[] lines = input.split("\n");
        int sum = 0;
        for (String line : lines) {
            char[] chars = line.toCharArray();
            char first = 0;
            char last = 0;
            for (char c : chars) {
                if (Character.isDigit(c)) {
                    last = c;
                    if (first == 0) {
                        first = c;
                    }
                }
            }
            sum += Character.getNumericValue(first) * 10 + Character.getNumericValue(last);
        }
        return String.valueOf(sum);
    }

    @Override
    public String part2(String input) {
        String[] lines = input.split("\n");
        int sum = 0;
        char[] buffer = new char[5];
        int bufferIndex;
        for (String line : lines) {
            bufferIndex = 0;
            char[] chars = line.toCharArray();
            char first = 0;
            char last = 0;
            for (char c : chars) {
                if (Character.isDigit(c)) {
                    bufferIndex = 0;
                    last = c;
                    if (first == 0) {
                        first = c;
                    }
                } else {
                    if (bufferIndex < buffer.length) {
                        buffer[bufferIndex] = c;
                        bufferIndex++;
                    } else {
                        for (int i = 0; i < buffer.length - 1; i++) {
                            buffer[i] = buffer[i + 1];
                        }
                        buffer[buffer.length - 1] = c;
                    }
                    for (int i = 0; i < digits.length; i++) {
                        if (bufferIndex >= digits[i].length()) {
                            String bufferString = new String(buffer, bufferIndex - digits[i].length(), digits[i].length());
                            if (bufferString.equals(digits[i])) {
                                last = (char) (i + '0');
                                if (first == 0) {
                                    first = (char) (i + '0');
                                }
                            }
                        }
                    }
                }
            }
            sum += Character.getNumericValue(first) * 10 + Character.getNumericValue(last);
        }
        return String.valueOf(sum);
    }
}
