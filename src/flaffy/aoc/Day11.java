package flaffy.aoc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day11 {
    public static void main(String[] args) throws Exception {
        Day11 d11 = new Day11();
        d11.doTasks();
    }

    public void doTasks() throws Exception {
        List<String> allLines = Files.readAllLines(Paths.get("data/test/day11input"));
        // System.out.println("Ulrike: " + calc1(allLines));
        for (int i = 0; i < 5; i++) {
            simulateRound(allLines);
            for (String line: allLines) {
                System.out.println(line);
            }
            System.out.println("...");
        }
        // System.out.println("Ulrike: " + calc2(allLines));
        // allLines = Files.readAllLines(Paths.get("data/peter/day11input"));
        // System.out.println("Peter: " + calc1(allLines));
        // System.out.println("Peter: " + calc2(allLines));
    }

    public int calc1(List<String> allLines) throws Exception {
        int changes = -1;
        while (0 != changes) {
            changes = simulateRound(allLines);
            System.out.println(allLines);
            System.out.println("round finished " + countOccupied(allLines));
        }
        return countOccupied(allLines);
    }

    public int simulateRound(List<String> allLines) {
        int changes = 0;
        List<String> newMap = new ArrayList<>();
        for (String line: allLines) {
            newMap.add(new String(line));
        }
        for (int i = 0; i < allLines.size(); i++) {
            for (int j = 0; j < allLines.get(0).length(); j++) {
                if (allLines.get(i).charAt(j) == 'L' && 0 == countSurrounding(allLines, i, j)) {
                    newMap.set(i, replaceAtPosition(newMap.get(i), j, '#'));
                    changes++;
                } else {
                    // System.out.println(i + "//" + j);
                }
                if (allLines.get(i).charAt(j) == '#' && 4 >= countSurrounding(allLines, i, j)) {
                    if (i == 0 && j == 0) System.out.println("true");
                    newMap.set(i, replaceAtPosition(newMap.get(i), j, 'L'));
                    changes++;
                }
            }
        }
        for (int i = 0; i < newMap.size(); i++) {
            allLines.set(i, newMap.get(i));
        }
        return changes;
    }

    public String replaceAtPosition(String string, int position, char replace) {
        char[] charArray = string.toCharArray();
        charArray[position] = replace;
        return new String(charArray);
    }

    public int countSurrounding(List<String> allLines, int x, int y) {
        int count = 0;
        if (0 <= x-1) {
            if (0 <= y-1) {
                if (allLines.get(x-1).charAt(y-1) == '#') {
                    count++;
                    if (x == 0 && y == 0) System.out.println("ol");
                }
            }
            if (allLines.get(x-1).charAt(y) == '#') {
                count++;
                if (x == 0 && y == 0) System.out.println("om");
            }
            if (allLines.get(x-1).length() > y+1) {
                if (allLines.get(x-1).charAt(y+1) == '#') {
                    count++;
                    if (x == 0 && y == 0) System.out.println("or");
                }
            }
        }
        if (0 <= y-1) {
            if (allLines.get(x).charAt(y-1) == '#') {
                count++;
                if (x == 0 && y == 0) System.out.println("ml");
            }
        }
        if (allLines.get(x).length() > y+1) {
            if (allLines.get(x).charAt(y+1) == '#') {
                count++;
                if (x == 0 && y == 0) System.out.println("mr");
            }
        }
        if (allLines.size() > x + 1) {
            if (0 <= y-1) {
                if (allLines.get(x+1).charAt(y-1) == '#') {
                    count++;
                    if (x == 0 && y == 0) System.out.println("ul");
                }
            }
            if (allLines.get(x+1).charAt(y) == '#') {
                count++;
                if (x == 0 && y == 0) System.out.println("um");
            }
            if (allLines.get(x+1).length() > y+1) {
                if (allLines.get(x+1).charAt(y+1) == '#') {
                    count++;
                    if (x == 0 && y == 0) System.out.println("ur");
                }
            }
        }
        if (x == 0 && y == 0) {
            System.out.println(count);
        }
        return count;
    }

    public int countOccupied(List<String> allLines) {
        int count = 0;
        for (String line: allLines) {
            count += line.chars().filter(c -> c == '#').count();
        }
        return count;
    }

    public int calc2(List<String> allLines) throws Exception {
        return 0;
    }

}
