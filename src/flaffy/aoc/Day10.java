package flaffy.aoc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class Day10 {
    public static void main(String[] args) throws Exception {
        Day10 d10 = new Day10();
        d10.doTasks();
    }

    public void doTasks() throws Exception {
        List<String> allLines = Files.readAllLines(Paths.get("data/ulrike/day10input"));
        List<Integer> allNumbers = new ArrayList<>();
        for (String line : allLines) {
            allNumbers.add(Integer.parseInt(line));
        }
        System.out.println("Ulrike: " + calc1(allNumbers));
        allNumbers = new ArrayList<>();
        for (String line : allLines) {
            allNumbers.add(Integer.parseInt(line));
        }
        System.out.println("Ulrike: " + calc2(allNumbers));
        allLines = Files.readAllLines(Paths.get("data/peter/day10input"));
        allNumbers = new ArrayList<>();
        for (String line : allLines) {
            allNumbers.add(Integer.parseInt(line));
        }
        System.out.println("Peter: " + calc1(allNumbers));
        allNumbers = new ArrayList<>();
        for (String line : allLines) {
            allNumbers.add(Integer.parseInt(line));
        }
        System.out.println("Peter: " + calc2(allNumbers));
    }

    public int calc1(List<Integer> allNumbers) throws Exception {
        allNumbers.add(0);
        Collections.sort(allNumbers);
        int diffOne = 0;
        int diffThree = 1; // start with one for last diff
        for (int i = 0; i < allNumbers.size() - 1; i++) {
            int diff = allNumbers.get(i + 1) - allNumbers.get(i);
            if (diff == 1) {
                diffOne++;
            } else if (diff == 3) {
                diffThree++;
            }
        }
        return diffOne * diffThree;
    }

    public long calc2(List<Integer> allNumbers) {
        allNumbers.add(0);
        Collections.sort(allNumbers);
        allNumbers.add(allNumbers.get(allNumbers.size() - 1) + 3);

        Map<Integer, Long> paths = new HashMap<>();
        paths.put(0, 1L);
        for (int i = 1; i < allNumbers.size() - 1; i++) {
            Map<Integer, Long> newPaths = new HashMap<>();
            for (Integer lastNumber: paths.keySet()){
                if(isValid(allNumbers, lastNumber, i)) {
                    addToMap(newPaths, lastNumber, paths.get(lastNumber));
                }
                addToMap(newPaths, allNumbers.get(i), paths.get(lastNumber));
            }
            paths = newPaths;
        }
        long sum = 0;
        for (Long value: paths.values()) {
            sum += value;
        }
        return sum;
    }

    public boolean isValid(List<Integer> allNumbers, int lastNumber, int toCheck) {
        return (allNumbers.get(toCheck + 1) - lastNumber) <= 3;
    }

    public void addToMap(Map<Integer, Long> paths, int key, long value) {
        if (paths.containsKey(key)) {
            paths.put(key, paths.get(key) + value);
        } else {
            paths.put(key, value);
        }
    }
}
