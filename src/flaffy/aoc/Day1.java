package flaffy.aoc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day1 {
    public static void main(String[] args) throws Exception {
        Day1 d1 = new Day1();
        d1.task1();
    }

    public void task1() throws Exception {
        List<String> allLines = Files.readAllLines(Paths.get("data/peter/day1input"));
        int[] allExpenses = new int[allLines.size()];
        for (int i = 0; i < allExpenses.length; i++) {
            allExpenses[i] = Integer.parseInt(allLines.get(i));
        }
        System.out.println(calc(allExpenses));
    }

    public int calc(int[] numbers) throws Exception {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == 2020) {
                    return numbers[i] * numbers[j];
                }
            }
        }
        throw new Exception("No matching expenses found!");
    }

}
