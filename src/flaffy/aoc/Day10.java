package flaffy.aoc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

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
        // System.out.println("Ulrike: " + calc1(allNumbers));
        // System.out.println("Ulrike: " + calc2(allNumbers));
        allLines = Files.readAllLines(Paths.get("data/peter/day10input"));
        allNumbers = new ArrayList<>();
        for (String line : allLines) {
            allNumbers.add(Integer.parseInt(line));
        }
        // System.out.println("Peter: " + calc1(allNumbers));
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

    public int calc2(List<Integer> allNumbers) {
        allNumbers.add(0);
        Collections.sort(allNumbers);
        allNumbers.add(allNumbers.get(allNumbers.size() - 1) + 3);
        System.out.println(allNumbers);

        List<Integer> solutions = new ArrayList<>();
        solutions.add(0);
        for (int i = 1; i < allNumbers.size() - 1; i++) {
                List<Integer> newSolutions = new ArrayList<>();
                for (Integer solution: solutions) {
                    if (isValid(allNumbers, solution, i)) {
                        newSolutions.add(solution);
                    }
                    newSolutions.add(allNumbers.get(i));
                }
                solutions = newSolutions;
                System.out.println(solutions);
            }
        return solutions.size();
    }

    public boolean isValid(List<Integer> allNumbers, int lastNumber, int toCheck) {
        return (allNumbers.get(toCheck + 1) - lastNumber) <= 3;
    }
}
