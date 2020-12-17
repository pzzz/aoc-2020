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
        //allLines = Files.readAllLines(Paths.get("data/test/day16input"));
        //System.out.println("Ulrike: " + calc1(allLines));
        System.out.println("Ulrike: " + calc2(allLines));
        allLines = Files.readAllLines(Paths.get("data/peter/day16input"));
        //System.out.println("Peter: " + calc1(allLines));
        System.out.println("Peter: " + calc2(allLines));
        //System.out.println("--->>>" + allLines.get(19) + " // " + allLines.get(25));
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
            //System.out.println("Line: " + line);
            int[] rule1 = new int[2];
            int minusLocation = line.indexOf('-');
            rule1[0] = Integer.parseInt(line.substring(line.indexOf(':') + 2, minusLocation));
            int orLocation = line.indexOf(" or ");
            rule1[1] = Integer.parseInt(line.substring(minusLocation + 1, orLocation));
            rules[position] = rule1;
            int[] rule2 = new int[2];
            rule2[0] = Integer.parseInt(line.substring(orLocation + 4, line.lastIndexOf('-')));
            rule2[1] = Integer.parseInt(line.substring(line.lastIndexOf('-')+1));
            rules[position + 1] = rule2;
        }
        return rules;
    }

    public long calc2(List<String> allLines) {
        int[][] rules = getRules(allLines);
        List<Integer [] > numbers = new ArrayList<>();
        for (int i = 25; i < allLines.size(); i++) {
            String[] nums = allLines.get(i).split(",");
            try{
                numbers.add(checkLine(nums, rules));
                }
            catch (Exception e) {
                //ticket fehlerhaft --> wird nicht zugefügt
                }
            }
        //numbers enthält alle gültigen Tickets
        List<Integer> ruleOrder = new ArrayList<>(); //an Stelle 0: gültige Regel für erste Zahl in Tickets, usw.
        for(int i = 0; i<20; i++){
            ruleOrder.add(-1);
        }
        for(int k = 0; k<20; k++){            
        for(int i = 0; i<20; i++){ //eigentlich 20!
            if(ruleOrder.get(i)==-1){
                int[][] possibleRules = rules.clone(); //Regeln die für diesen Index noch möglich sind
                for(Integer[] line: numbers){
                        List <Integer> matchingRules = matchingRules(line[i], possibleRules);
                        if(matchingRules.size()==1){
                            ruleOrder.set(i, matchingRules.get(0));
                            rules[matchingRules.get(0)*2]=null;
                            rules[matchingRules.get(0)*2+1] = null;
                            break;
                        }else{
                            for(int j = 0; j<20; j++){
                                if(!matchingRules.contains(j)){
                                    possibleRules[j*2]=null;
                                    possibleRules[j*2+1]=null;
                                }
                            }
                        }
                    }                    
            //System.out.println(ruleOrder);
                }
            }
        }
        long result=1;
        String[] myTicket = allLines.get(22).split(",");

        for(int i = 0; i<ruleOrder.size(); i++){
            String completeRule = allLines.get(ruleOrder.get(i));
            String ruleStart = completeRule.substring(0, completeRule.indexOf(' '));
            if(ruleStart.equals("departure")){
                result *= Integer.parseInt(myTicket[i]);
            }
           
        }
        return result;
        }

    public Integer[] checkLine (String[] line, int[][] rules) throws Exception{
        Integer[] intLine = new Integer[20];
        for (int i = 0; i < line.length; i++ ) {
            intLine[i] = Integer.parseInt(line[i]);
            if (!checkNumber(intLine[i], rules)) {
                throw new Exception("wrong ticket");           
            }
        }
        return intLine;
    }

    public List<Integer> matchingRules(int number, int[][]rules){
        List<Integer> matchingRules = new ArrayList <>();
        //achtung: in rules gibt es zwei zeilen pro regel!
        for(int i = 0; i < rules.length; i+=2){
            if(rules[i]!=null){
                if(number >= rules[i][0] && number <= rules[i][1] || number >= rules[i+1][0] && number <= rules[i+1][1]){
                    matchingRules.add(i/2);
                }//else{ System.out.println("regelnummer "+ i/2 + " ist null");}
            }
        }
        //System.out.println(matchingRules);
        return matchingRules;
    }
}





