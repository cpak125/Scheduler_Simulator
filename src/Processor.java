import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Processor {
    ArrayList<Process> processes;

    public Processor(int numProcesses) {
        this.processes = new ArrayList<>();
//        generateProcesses(numProcesses);
//        writeCSV("processes.csv");
    }

    public ArrayList<Process> getProcesses() {
        return processes;
    }

    // generate processes
//    public void generateProcesses(int n) {
//
//        for (int i = 1; i <= n; i++) {
//
//
////            // generate random cycles
////            double minCycles = 10 * Math.pow(10, 6), maxCycles = 10 * Math.pow(10, 12);
////            long cycles = (long) Math.floor(Math.random() * (maxCycles - minCycles + 1) + minCycles);
////
////            // generate random memory size
////            double minSize = 1.0, maxSize = 16000.0;
////            int memSize = (int) Math.floor(Math.random() * (maxSize - minSize + 1) + minSize);
//
//            // add new process into processes list
////            processes.add(new Process(i, cycles, memSize));
//        }
//    }

//    public void writeCSV(String filename) {
//        final String COMMA_DELIMITER = ",";
//        final String NEW_LINE_SEPARATOR = "\n";
//        FileWriter fileWriter = null;
//        try {
//            fileWriter = new FileWriter(filename, true);
//
//            for (Process p : processes) {
//                fileWriter.append(String.valueOf(p.getPid()));
//                fileWriter.append(COMMA_DELIMITER);
//                fileWriter.append(String.valueOf(p.getBurstTime()));
//                fileWriter.append(COMMA_DELIMITER);
//                fileWriter.append(String.valueOf(p.getMemSize()));
//                fileWriter.append(NEW_LINE_SEPARATOR);
//            }
//            System.out.println("CSV file was created successfully !!!");
//        } catch (Exception e) {
//            System.out.println("Error in CsvFileWriter !!!");
//            e.printStackTrace();
//        } finally {
//            try {
//                assert fileWriter != null;
//                fileWriter.flush();
//                fileWriter.close();
//            } catch (IOException e) {
//                System.out.println("Error while flushing/closing fileWriter !!!");
//                e.printStackTrace();
//            }
//        }
//    }
}



