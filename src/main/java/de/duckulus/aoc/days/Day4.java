package de.duckulus.aoc.days;

import de.duckulus.aoc.util.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Day4 extends Day {

    public Day4() {
        super("day4");
    }

    @Override
    public String part1(String input) {
        String[] lines = input.replaceAll(" +", " ").trim().split("\n");
        int sum = 0;
        for (String line : lines) {
            ArrayList<Integer> winning = new ArrayList<>();
            ArrayList<Integer> owned = new ArrayList<>();
            String[] tokens = line.split(" ");

            boolean ownedPart = false;
            for (int i = 2; i < tokens.length; i++) {
                if (tokens[i].equals("|")) {
                    ownedPart = true;
                } else {
                    if (!ownedPart) {
                        winning.add(Integer.parseInt(tokens[i].trim()));
                    } else {
                        owned.add(Integer.parseInt(tokens[i].trim()));
                    }
                }
            }

            int score = 0;
            for (int n : owned) {
                if (winning.contains(n)) {
                    score = score == 0 ? 1 : score * 2;
                }
            }
            sum += score;
        }
        return String.valueOf(sum);
    }

    @Override
    public String part2(String input) {
        String[] lines = input.replaceAll(" +", " ").trim().split("\n");
        int[] cards = new int[lines.length];
        LinkedList<Integer> cardQueue = new LinkedList<>();
        for (int i = 0; i < lines.length; i++) {
            cardQueue.offer(i);
        }

        while (!cardQueue.isEmpty()) {
            int card = cardQueue.poll();
            String line = lines[card];
            ArrayList<Integer> winning = new ArrayList<>();
            ArrayList<Integer> owned = new ArrayList<>();
            String[] tokens = line.split(" ");

            boolean ownedPart = false;
            for (int i = 2; i < tokens.length; i++) {
                if (tokens[i].equals("|")) {
                    ownedPart = true;
                } else {
                    if (!ownedPart) {
                        winning.add(Integer.parseInt(tokens[i].trim()));
                    } else {
                        owned.add(Integer.parseInt(tokens[i].trim()));
                    }
                }
            }

            cards[card]++;

            int matches = 0;
            for (int n : owned) {
                if (winning.contains(n)) {
                    matches++;
                }
            }

            for (int i = 0; i < matches; i++) {
                cardQueue.offer(card + i + 1);
            }
        }
        return String.valueOf(Arrays.stream(cards).sum());
    }

}
