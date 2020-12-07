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
        System.out.println("Ulrike: " + calc2(allLines));
        allLines = Files.readAllLines(Paths.get("data/peter/day3input"));
        System.out.println("Peter: " + calc1(allLines));
        System.out.println("Peter: " + calc2(allLines));
    }

    public int calc1(List<String> allLines) {
        return check(allLines, 1, 3);
    }

    public int check(List<String> allLines, int down, int right) {
        int count = 0;
        int pos = 0;
        for (int i = 0; i < allLines.size(); i+=down) {
            String line = allLines.get(i);
            int realPos = 0;
            if (pos != 0) {
                realPos = pos % line.length();
            }
            if (line.charAt(realPos) == '#') {
                count++;
            }
            pos += right;
        }
        return count;
    }

    public int calc2(List<String> allLines) {
        int oneOne = check(allLines, 1, 1);
        int threeOne = check(allLines, 1, 3);
        int fiveOne = check(allLines, 1, 5);
        int sevenOne = check(allLines, 1, 7);
        int oneTwo = check(allLines, 2, 1);
        int result = oneOne*threeOne*fiveOne*sevenOne*oneTwo;
        return result;
    }
}
