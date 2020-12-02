package flaffy.aoc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day2 {
    public static void main(String[] args) throws Exception {
        Day2 d2 = new Day2();
        d2.doTasks();
    }

    public void doTasks() throws Exception {
        List<String> allLines = Files.readAllLines(Paths.get("data/ulrike/day2input"));
        System.out.println("Ulrike: " + calc1(allLines));
        System.out.println("Ulrike: " + calc2(allLines));
        allLines = Files.readAllLines(Paths.get("data/peter/day2input"));
        System.out.println("Peter: " + calc1(allLines));
        System.out.println("Peter: " + calc2(allLines));
    }

    public int calc1(List<String> allLines) {
        int count = 0;
        for (String line: allLines) {
            if (isValid(line)) {
                count++;
            }
        }
        return count;
    }

    public boolean isValid(String line) {
        PwEntry pw = new PwEntry(line);
        int count = 0;
        for (char c : pw.pw.toCharArray()) {
            if (c == pw.character) {
                count++;
            }
        }
        if (count >= pw.lowerLimit && count <= pw.upperLimit) {
            return true;
        }

        return false;
    }

    public int calc2(List<String> allLines) {
        int count = 0;
        for (String line: allLines) {
            if (isValid2(line)) {
                count++;
            }
        }
        return count;
    }

    public boolean isValid2(String line) {
        PwEntry pw = new PwEntry(line);
        boolean valid = pw.pw.length() >= pw.lowerLimit && pw.pw.charAt(pw.lowerLimit - 1) == pw.character;
        boolean valid2 = pw.pw.length() >= pw.upperLimit && pw.pw.charAt(pw.upperLimit - 1) == pw.character;
        return valid != valid2;
    }

    private class PwEntry {
        final int lowerLimit;
        final int upperLimit;
        final char character;
        final String pw;

        public PwEntry(String line) {
            lowerLimit = Integer.parseInt(line.substring(0, line.indexOf('-')));
            upperLimit = Integer.parseInt(line.substring(line.indexOf('-') + 1, line.indexOf(' ')));
            character = line.charAt(line.indexOf(' ') + 1);
            pw = line.substring(line.lastIndexOf(' ') + 1);
        }
    }
}
