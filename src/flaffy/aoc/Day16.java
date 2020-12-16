package flaffy.aoc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day16 {
    public static void main(String[] args) throws Exception {
        Day16 d16 = new Day16();
        d16.doTasks();
    }

    public void doTasks() throws Exception {
        List<String> allLines = Files.readAllLines(Paths.get("data/ulrike/day16input"));
        System.out.println("Ulrike: " + calc1(allLines));
        System.out.println("Ulrike: " + calc2(allLines));
        allLines = Files.readAllLines(Paths.get("data/peter/day16input"));
        System.out.println("Peter: " + calc1(allLines));
        System.out.println("Peter: " + calc2(allLines));
        System.out.println("--->>>" + allLines.get(19) + " // " + allLines.get(25));
    }

    public long calc1(List<String> allLines) {
        int[][] rules = getRules(allLines);
        List<Integer> wrongNumbers = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 25; i < allLines.size(); i++) {
            String[] nums = allLines.get(i).split(",");
            for (int j = 0; j < nums.length; j++ ) {
                int number = Integer.parseInt(nums[j]);
                if (!checkNumber(number, rules)) {
                    wrongNumbers.add(number);
                }
                if (!numbers.contains(number)) {
                    numbers.add(number);
                }
            }
        }
        int result = 0;
        for (int wrongNumber: wrongNumbers) {
            result += wrongNumber;
        }
        return result;
    }
    public boolean checkNumber(int number, int[][]rules){
        for(int i = 0; i < rules.length; i++){
            if(number >= rules[i][0] && number <= rules[i][1]){
                return true;
            }
        }
        return false;
    }

    public int[][] getRules(List<String> allLines) {
        int[][] rules = new int[40][];
        for (int i = 0; i < 20; i++) {
            int position = 2 * i;
            String line = allLines.get(i);
            System.out.println("Line: " + line);
            int[] rule1 = new int[2];
            int minusLocation = line.indexOf('-');
            rule1[0] = Integer.parseInt(line.substring(line.indexOf(':') + 2, minusLocation));
            int orLocation = line.indexOf(" or ");
            rule1[1] = Integer.parseInt(line.substring(minusLocation + 1, orLocation));
            //System.out.println("Regel "+i+" Teil 1:"+rule1[0]+','+rule1[1]);
            rules[position] = rule1;
            int[] rule2 = new int[2];
            rule2[0] = Integer.parseInt(line.substring(orLocation + 4, line.lastIndexOf('-')));
            rule2[1] = Integer.parseInt(line.substring(line.lastIndexOf('-')+1));
            //System.out.println("Regel "+i+" Teil 2:"+rule2[0]+','+rule2[1]);
            rules[position + 1] = rule2;
        }
        return rules;
    }

    public int calc2(List<String> allLines) {
        return 0;
    }
}




