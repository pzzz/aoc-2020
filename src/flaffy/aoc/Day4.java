package flaffy.aoc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Day4 {
    public static void main(String[] args) throws Exception {
        Day4 d4 = new Day4();
        d4.doTasks();
    }

    public void doTasks() throws Exception {
        List<String> allLines = Files.readAllLines(Paths.get("data/ulrike/day4input"));        
        //allLines = Files.readAllLines(Paths.get("data/ulrike/day4testinput"));
        System.out.println("Ulrike: " + calc1(allLines));
        // System.out.println("Ulrike: " + calc2(allLines));
        //allLines = Files.readAllLines(Paths.get("data/peter/day3input"));
        //System.out.println("Peter: " + calc1(allLines));
        // System.out.println("Peter: " + calc2(allLines));
        analyse("bright indigo bags contain 4 shiny turquoise bags, 3 wavy yellow bags.","shiny gold");
        
    }
    

    public int calc1(List<String> allLines) {
        iter (allLines, "shiny gold");
        return possibleWrappings.size();
    } 
    

    public int calc2(List<String> allLines) {
        int count = 0;
        return count;
    }

    List<String> possibleWrappings = new LinkedList<String>();

    public void iter (List<String> allLines, String color){
        for (String line: allLines) {
            String result = analyse(line, color);
            System.out.println(result);
            if(result != " "){
                if(!possibleWrappings.contains(result)){
                    possibleWrappings.add(result);
                    iter (allLines, result);
                }
            }
        }
    }

    //auswerten: die ersten 2 Wörter (äußere Farbe), die 2 Worte nach  jeder Zahl (erlaubte Inhalte), 
    //funktioniert zwar für keine Zahl --> keine Inhalte, ist aber hässlich gelößt
    public String analyse(String line, String color){
        line.substring(0, line.indexOf(' ', line.indexOf(' '))); //die ersten beiden Wörter
        String [] wrappingAndContent = line.split(" bags contain "); //Verpackung und alle Inhalte trennen
        String wrapping = wrappingAndContent[0];
        String [] contents = wrappingAndContent[1].split(" bag[s]?(,[\s]|[.])"); //Inhalte voneinander teilen (jeweils Zahl und Farbe in einer Zelle)
        String [][] contents2 = new String [contents.length][2]; //(Zahlen von Farben trennen)
        for(int i = 0; i<contents.length;i++){ //wenn color in Inhalten vorkommt, wird Verpackungsfarbe zurückgegeben.
            contents2 [i][0] =contents[i].substring(0,1);
            contents2 [i][1] = contents[i].substring(2);
            if(contents2[i][1].equals(color)){ 
                return wrapping;
            }
        }
        return " ";
    }
}
