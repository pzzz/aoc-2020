package flaffy.aoc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day9 {
    public static void main(String[] args) throws Exception {
        Day9 d9 = new Day9();
        d9.doTasks();
    }

    public void doTasks() throws Exception {
        List<String> allLines = Files.readAllLines(Paths.get("data/ulrike/day9input"));
        long[] allNumbers = new long[allLines.size()];
        for (int i = 0; i < allNumbers.length; i++) {
            allNumbers[i] = Long.parseLong(allLines.get(i));
        }
        long wrongNumber = calc1(allNumbers);
        System.out.println("Ulrike: " + wrongNumber);
        System.out.println("Ulrike: " + calc2(allNumbers, wrongNumber));
        allLines = Files.readAllLines(Paths.get("data/peter/day9input"));
        allNumbers = new long[allLines.size()];
        for (int i = 0; i < allNumbers.length; i++) {
            allNumbers[i] = Long.parseLong(allLines.get(i));
        }
        wrongNumber = calc1(allNumbers);
        System.out.println("Peter: " + wrongNumber);
        System.out.println("Peter: " + calc2(allNumbers, wrongNumber));
    }

    public long calc1(long[] allNumbers) throws Exception {
        int pointerStart = 0;
        int pointerEnd = 24;
        for (int i = pointerEnd + 1; i < allNumbers.length; i++) {
            if (!checkNumber(pointerStart, pointerEnd, allNumbers, i)) {
                return allNumbers[i];
            }
            pointerStart++;
            pointerEnd++;
        }
        throw new Exception();
    }

    public boolean checkNumber(int pointerStart, int pointerEnd, long[] allNumbers, int toCheck) {
        for (int j = pointerStart; j < pointerEnd; j++) {
            for (int k = j + 1; k <= pointerEnd; k++) {
                if (allNumbers[j] + allNumbers[k] == allNumbers[toCheck]) {
                    return true;
                }
            }
        }
        return false;
    }

    public long calc2(long[] allLines, long wrongNumber) {
        for(int i = 0; i < allLines.length; i++){
            try {
                int end = getPosEnd(allLines, wrongNumber, i);
                long smallest = allLines[i];
                long biggest = allLines[i];
                for (int j = i + 1; j <= end; j++){
                    if(allLines[j]<smallest){
                        smallest = allLines[j];
                    }
                    if(allLines[j]>biggest){
                        biggest = allLines[j];
                    }
                }
                return smallest + biggest;
            } catch (Exception e) {
                // use other start...
            }
        }
        return 0;
    }

    public int getPosEnd(long[] allLines, long wrongNumber, int startPos) throws Exception {
        long count = 0;
        for (int i = startPos; i < allLines.length; i++) {
            count += allLines[i];
            if (count == wrongNumber) {
                return i;
            }
            if (count > wrongNumber) {
                throw new Exception();
            }
        }
        throw new Exception();
    }
}
