package flaffy.aoc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Day14 {
    public static void main(String[] args) throws Exception {
        Day14 d14 = new Day14();
        d14.doTasks();
    }

    public void doTasks() throws Exception {
        List<String> allLines = Files.readAllLines(Paths.get("data/test/day14input"));
        System.out.println("Test: " + calc1(allLines));
        System.out.println("Test: " + calc2(allLines));
        allLines = Files.readAllLines(Paths.get("data/ulrike/day14input"));
        System.out.println("Ulrike: " + calc1(allLines));
        System.out.println("Ulrike: " + calc2(allLines));
        allLines = Files.readAllLines(Paths.get("data/peter/day14input"));
        System.out.println("Peter: " + calc1(allLines));
        System.out.println("Peter: " + calc2(allLines));
    }

    public long calc1(List<String> allLines) {
        Map<Integer, Long> memory = new HashMap<>();
        String mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
        for (String line: allLines) {
            if (line.startsWith("mask")) {
                mask = line.substring(line.lastIndexOf(' ') + 1);
            } else {
                int address = Integer.parseInt(line.substring(line.indexOf('[') + 1, line.indexOf(']')));
                long value = Long.parseLong(line.substring(line.lastIndexOf(' ') + 1));
                memory.put(address, applyMask(value, mask));
            }
        }
        long result = 0;
        for (Long value: memory.values()) {
            result += value;
        }
        return result;
    }

    public Long applyMask(long value, String mask) {
        String binary = Long.toBinaryString(value);
        binary = String.format("%36s", binary).replaceAll(" ", "0");
        char[] maskValues = mask.toCharArray();
        char[] result = new char[binary.length()];
        for(int i = 0; i < maskValues.length; i++) {
              if (maskValues[i] == '1') {
                  result[i]='1';
              }else if(maskValues[i]=='0'){
                      result[i]='0';
              } else{
                  result[i]=binary.charAt(i);
              }
        }
        return Long.parseLong(new String(result), 2);
    }

    public int calc2(List<String> allLines) {
        return 0;
    }
}
