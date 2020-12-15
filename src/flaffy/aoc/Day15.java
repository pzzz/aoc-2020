package flaffy.aoc;

import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day15 {
    public static void main(String[] args) throws Exception {
        Day15 d15 = new Day15();
        d15.doTasks();
    }

    public void doTasks() throws Exception {
        List<Integer> startNumbers = Arrays.asList(0,3,6);
        System.out.println("Test: " + calc1(startNumbers));
        System.out.println("Test: " + calc2(startNumbers));
        startNumbers = Arrays.asList(18,8,0,5,4,1,20);
        System.out.println("Ulrike: " + calc1(startNumbers));
        System.out.println("Ulrike: " + calc2(startNumbers));
        startNumbers = Arrays.asList(10,16,6,0,1,17);
        System.out.println("Peter: " + calc1(startNumbers));
        System.out.println("Peter: " + calc2(startNumbers));
    }

    public int calc1(List<Integer> startNumbers) {
        return doCalc(startNumbers, 2020);
    }

    public int doCalc(List<Integer> startNumbers, int turns) {
        Map<Integer, Integer> lastTurns = new HashMap<>();//Wert und Runde
        for (int i = 0; i < startNumbers.size() - 1; i++) {
            lastTurns.put(startNumbers.get(i), i);
        }
        int lastNumberSpoken = startNumbers.get(startNumbers.size() - 1);
        for (int i = startNumbers.size(); i < turns; i++) {
            if (!lastTurns.containsKey(lastNumberSpoken)) {
                lastTurns.put(lastNumberSpoken, i - 1);
                lastNumberSpoken = 0;
            } else {
                int diff = i - 1 - lastTurns.get(lastNumberSpoken);
                lastTurns.put(lastNumberSpoken, i - 1);
                lastNumberSpoken = diff;
            }
        }
        return lastNumberSpoken;
    }

    public int calc2(List<Integer> startNumbers) {
        return doCalc(startNumbers, 30000000);
    }
}
