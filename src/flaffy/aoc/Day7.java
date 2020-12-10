package flaffy.aoc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Day7 {
    public static void main(String[] args) throws Exception {
        Day7 d7 = new Day7();
        d7.doTasks();
    }

    //possibleWrappings wird nur einmal initialisiert, deshalb können nicht mehrere Inputs in einem Durchlauf ausgewertet werden. (TODO)
    public void doTasks() throws Exception {
        List<String> allLines = Files.readAllLines(Paths.get("data/ulrike/day7input"));        
        //allLines = Files.readAllLines(Paths.get("data/ulrike/day7testinput"));
        //System.out.println("Ulrike: " + calc1(allLines));
        System.out.println("Ulrike: " + calc2(allLines));
        allLines = Files.readAllLines(Paths.get("data/peter/day7input"));
        //System.out.println("Peter: " + calc1(allLines));
        System.out.println("Peter: " + calc2(allLines));
        analyse("bright indigo bags contain 4 shiny turquoise bags, 3 wavy yellow bags.","shiny gold");
        
    }
    

    public int calc1(List<String> allLines) {
        recursiveCheck
     (allLines, "shiny gold");
        return possibleWrappings.size();
    } 
    

    

    List<String> possibleWrappings = new LinkedList<String>();

    public void recursiveCheck
 (List<String> allLines, String color){
        for (String line: allLines) {
            String result = analyse(line, color);
            System.out.println(result);
            if(result != " "){
                if(!possibleWrappings.contains(result)){
                    possibleWrappings.add(result);
                    recursiveCheck(allLines, result);
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

    public int calc2(List<String> allLines) {
        Line [] allRegulations = new Line[allLines.size()];
        for (int i = 0; i<allLines.size(); i++) {
            allRegulations[i] = new Line(allLines.get(i));            
        }
        return contentNumber ("shiny gold", allRegulations);       
        
        
    }

    //rekursive Funktion zur Ermittlung der enthaltenen Gepäckstücke
    public int contentNumber (String color, Line[]allRegulations){

        Line line = getRightLine(allRegulations, color); //Zeile die beschreibt, was Farbe enthält
        int length;
        try{
            length = line.contents.length; //Anzahl unterschieliche Inhalte
        }catch (Exception e){
            System.out.println("für die Farbe "+color+" scheint es keine Definition zu geben");
            return 0;
        }

        int [] countBags = new int[length]; //Zähler pro Zweig

        for(int i = 0; i < length; i++){ 
            String [] content = line.contents[i]; //eine Farbe + Anzahl
            try{     
            countBags[i]=Integer.parseInt(content[0]);
            int innerResult = contentNumber (content[1], allRegulations);
            if(innerResult!=1){
                countBags[i] = countBags[i] + countBags[i]*innerResult;
            }else{ //keine weitere Verzweigung
                countBags[i]*=innerResult;
            }
            }catch (Exception e){
                //enthält keine Taschen
                countBags[i]=1;
            }            
        }

        int result=0;
        for(int i = 0; i<length; i++){
            result+=countBags[i];
        }
        System.out.println(result);
        return result;
    }

    
    public Line getRightLine(Line[]allRegulations, String color){
        //System.out.println("Farbe "+color);
        for(Line l: allRegulations){
            //System.out.println(l.wrapping);            
            if(l.wrapping.equals(color)){
                return l;
            }
        }
        System.out.println("das sollte höchstens am ende der rekursion ausgegeben werden");
        return null;
    }

    private class Line {
        private String wrapping;
        String [][] contents; 

    public Line(String line) {
        wrapping = line.split(" bags contain ")[0];
        String [] mixedContent = line.split(" bags contain ")[1].split(" bag[s]?(,[\s]|[.])"); //Inhalte voneinander teilen (jeweils Zahl und Farbe in einer Zelle)
        contents = new String [mixedContent.length][2];
        for(int i = 0; i<mixedContent.length;i++){
            contents [i][0] =mixedContent[i].substring(0,1);
            contents [i][1] = mixedContent[i].substring(2);            
        }
        //System.out.println(contents.length);
    }
    }
}
