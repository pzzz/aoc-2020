package flaffy.aoc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day8 {
    public static void main(String[] args) throws Exception {
        Day8 d8 = new Day8();
        d8.doTasks();
    }

    public void doTasks() throws Exception {
        List<String> allLines = Files.readAllLines(Paths.get("data/ulrike/day8input"));
        System.out.println("Ulrike: " + calc1(allLines));
        System.out.println("Ulrike: " + calc2(allLines));
        allLines = Files.readAllLines(Paths.get("data/peter/day8input"));
        System.out.println("Peter: " + calc1(allLines));
        System.out.println("Peter: " + calc2(allLines));
    }

    public int calc1(List<String> allLines) {
        return new Handheld(allLines).execUntilLoop();
    }

    public int calc2(List<String> allLines) {
        for (int i = 0; i < allLines.size(); i++) {
            if (allLines.get(i).startsWith("jmp")) {
                allLines.set(i, allLines.get(i).replace("jmp", "nop"));
                try {
                    return new Handheld(allLines).execWithoutLoop();
                } catch (Exception e) {
                    // Loop detected!
                }
                allLines.set(i, allLines.get(i).replace("nop", "jmp"));
            } else if (allLines.get(i).startsWith("nop")) {
                allLines.set(i, allLines.get(i).replace("nop", "jmp"));
                try {
                    return new Handheld(allLines).execWithoutLoop();
                } catch (Exception e) {
                    // Loop detected!
                }
                allLines.set(i, allLines.get(i).replace("jmp", "nop"));
            }
        }
        return 0;
    }

    private class Handheld {
        private int accumulator = 0;
        private int opPointer = 0;
        private boolean[] isExecuted;
        private final List<String> bootCode;

        public Handheld(final List<String> bootCode) {
            this.bootCode = bootCode;
            isExecuted = new boolean[bootCode.size()];
        }

        public int execUntilLoop() {
            while (!isExecuted[opPointer]) {
                exec();
            }
            return accumulator;
        }

        public int execWithoutLoop() throws Exception {
            while (opPointer != bootCode.size()) {
                if (isExecuted[opPointer]) {
                    throw new Exception("Loop detected!");
                }
                exec();
            }
            return accumulator;
        }

        public void exec() {
            String line = bootCode.get(opPointer);
            String opCode = line.substring(0, 3);
            int value = Integer.parseInt(line.substring(4));
            isExecuted[opPointer] = true;
            if (opCode.equals("nop")) {
                opPointer++;
            } else if (opCode.equals("acc")) {
                accumulator += value;
                opPointer++;
            } else if (opCode.equals("jmp")) {
                opPointer += value;
            } else {
                System.out.println("Invalid line " + opPointer + ": " + line);
            }
        }
    }
}
