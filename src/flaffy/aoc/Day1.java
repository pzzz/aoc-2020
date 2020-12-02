package flaffy.aoc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day1 {
    public static void main(String[] args) throws Exception {
        Day1 d1 = new Day1();
        d1.doTasks();
    }

    public void doTasks() throws Exception {
        List<String> allLines = Files.readAllLines(Paths.get("data/ulrike/day1input"));
        int[] allExpenses = new int[allLines.size()];
        for (int i = 0; i < allExpenses.length; i++) {
            allExpenses[i] = Integer.parseInt(allLines.get(i));
        }
        System.out.println("Ulrike: " + calc1(allExpenses));
        System.out.println("Ulrike: " + calc2(allExpenses));
        allLines = Files.readAllLines(Paths.get("data/peter/day1input"));
        allExpenses = new int[allLines.size()];
        for (int i = 0; i < allExpenses.length; i++) {
            allExpenses[i] = Integer.parseInt(allLines.get(i));
        }
        System.out.println("Peter: " + calc1(allExpenses));
        System.out.println("Peter: " + calc2(allExpenses));
    }

    public int calc1(int[] numbers) throws Exception {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == 2020) {
                    return numbers[i] * numbers[j];
                }
            }
        }
        throw new Exception("No matching expenses found!");
    }

    public int calc2(int[] numbers) throws Exception {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                for (int k = j + 1; k < numbers.length; k++) {
                    if (numbers[i] + numbers[j] + numbers[k] == 2020) {
                        return numbers[i] * numbers[j] * numbers[k];
                    }
                }
            }
        }
        throw new Exception("No matching expenses found!");
    }

}
