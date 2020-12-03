package flaffy.aoc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day3 {
    public static void main(String[] args) throws Exception {
        Day3 d3 = new Day3();
        d3.doTasks();
    }

    public void doTasks() throws Exception {
        List<String> allLines = Files.readAllLines(Paths.get("data/ulrike/day3input"));
        System.out.println("Ulrike: " + calc1(allLines));
        // System.out.println("Ulrike: " + calc2(allLines));
        allLines = Files.readAllLines(Paths.get("data/peter/day3input"));
        System.out.println("Peter: " + calc1(allLines));
        // System.out.println("Peter: " + calc2(allLines));
    }

    public int calc1(List<String> allLines) {
        int count = 0;
        int pos = 0;
        for (String line: allLines) {
            if (line.charAt(line % pos) == '#') {
                count++;
            }
            pos += 3;
        }
        return count;
    }

    public int calc2(List<String> allLines) {
        int count = 0;
        return count;
    }
}
