package flaffy.aoc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Day4 {
    public static void main(String[] args) throws Exception {
        Day4 d4 = new Day4();
        d4.doTasks();
    }

    public void doTasks() throws Exception {
        // List<String> allLines = Files.readAllLines(Paths.get("data/ulrike/day4input"));
        // System.out.println("Ulrike: " + calc1(allLines));
        // System.out.println("Ulrike: " + calc2(allLines));
        List<String> allLines = Files.readAllLines(Paths.get("data/peter/day4input"));
        System.out.println("Peter: " + calc1(allLines));
        System.out.println("Peter: " + calc2(allLines));
    }

    public int calc1(List<String> allLines) {
        int validPassports = 0;
        String currentPassport = "";
        for (int i = 0; i < allLines.size(); i++) {
            String line = allLines.get(i);
            if (line.length() > 1 && i < allLines.size() - 1) {
                currentPassport += " " + line;
            } else {
                if (i == allLines.size() - 1) {
                    currentPassport += " " + line;
                }
                if (isValid(currentPassport)) {
                    validPassports++;
                }
                currentPassport = "";
            }
        }
        return validPassports;
    }

    public boolean isValid(String currentPassport) {
        return (currentPassport.contains("byr")
        && currentPassport.contains("iyr")
        && currentPassport.contains("eyr")
        && currentPassport.contains("hgt")
        && currentPassport.contains("hcl")
        && currentPassport.contains("ecl")
        && currentPassport.contains("pid"));
    }

    public int calc2(List<String> allLines) {
        int validPassports = 0;
        String currentPassport = "";
        for (int i = 0; i < allLines.size(); i++) {
            String line = allLines.get(i);
            if (line.length() > 1 && i < allLines.size() - 1) {
                currentPassport += " " + line;
            } else {
                if (i == allLines.size() - 1) {
                    currentPassport += " " + line;
                }
                if (isValid2(currentPassport)) {
                    validPassports++;
                }
                currentPassport = "";
            }
        }
        return validPassports;
    }

    public boolean isValid2(String currentPassport) {
        String[] pairs = currentPassport.split(" ");
        Map<String, String> kvPairs = new HashMap<>();
        for (String pair: pairs) {
            if (pair.contains(":")) {
                kvPairs.put(pair.substring(0, pair.indexOf(":")), pair.substring(pair.indexOf(":") + 1));
            }
        }
        if (!checkInRange(kvPairs, "byr", 1920, 2002)) {
            return false;
        }
        if (!checkInRange(kvPairs, "iyr", 2010, 2020)) {
            return false;
        }
        if (!checkInRange(kvPairs, "eyr", 2020, 2030)) {
            return false;
        }
        if (!checkHgt(kvPairs)) {
            return false;
        }
        if (!checkHcl(kvPairs)) {
            return false;
        }
        if (!checkEcl(kvPairs)) {
            return false;
        }
        if (!checkPid(kvPairs)) {
            return false;
        }
        return true;
    }

    public boolean checkInRange(Map<String, String> kvPairs, String key, int lowerBorder, int upperBorder) {
        if (!kvPairs.containsKey(key)) {
            return false;
        }
        int value = Integer.parseInt(kvPairs.get(key));
        return lowerBorder <= value && upperBorder >= value;
    }

    private boolean checkHgt(Map<String, String> kvPairs) {
        if (!kvPairs.containsKey("hgt")) {
            return false;
        }
        String hgt = kvPairs.get("hgt");
        if (hgt.endsWith("cm")) {
            int cm = Integer.parseInt(hgt.substring(0, hgt.indexOf("cm")));
            return 150 <= cm && 193 >= cm;
        }
        if (hgt.endsWith("in")) {
            int in = Integer.parseInt(hgt.substring(0, hgt.indexOf("in")));
            return 59 <= in && 76 >= in;
        }
        return false;
    }

    private boolean checkHcl(Map<String, String> kvPairs) {
        if (!kvPairs.containsKey("hcl")) {
            return false;
        }
        return kvPairs.get("hcl").matches("#([a-f0-9]{6})");
    }

    private boolean checkEcl(Map<String, String> kvPairs) {
        if (!kvPairs.containsKey("ecl")) {
            return false;
        }
        String ecl = kvPairs.get("ecl");
        return ecl.contains("amb") || ecl.contains("blu") || ecl.contains("brn") || ecl.contains("gry") || ecl.contains("grn") || ecl.contains("hzl") || ecl.contains("oth");
    }

    private boolean checkPid(Map<String, String> kvPairs) {
        if (!kvPairs.containsKey("pid")) {
            return false;
        }
        return kvPairs.get("pid").matches("([0-9]{9})");
    }
}
