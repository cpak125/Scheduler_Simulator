import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        // CPU contains the 6 processors
        ArrayList<Processor> cpu = new ArrayList<>();
        int processesRemain = 250;
        int processorRemain = 6;
        ArrayList<Process> processesCSV = readCSV("processes.csv");
        System.out.println(processesCSV);

        // generate the processes for each processor
        // 4 contain 42 processes and 2 contain 41, for a total of 250
        for (int i = 0; i < 6; i++) {
            cpu.add(new Processor((processesRemain / processorRemain)));
            for(int j = 0; j < cpu.get(i).getProcesses().size();j++){
                Process curr = processesCSV.get(j);
                cpu.get(i).getProcesses().set(j,curr);
            }
            processesRemain -= cpu.get(i).getProcesses().size();
            processorRemain--;
        }

        System.out.println("Starting FIFO Scheduler......\n");
        FIFO.FIFOScheduler(cpu);

        System.out.println("Starting SJF Scheduler......\n");
        SJF.SJFScheduler(cpu);

        System.out.println("Starting RR Scheduler......\n");
//        RR.RRScheduler(cpu);

    }

    public static ArrayList<Process> readCSV(String filename) {

        ArrayList<Process> processes = new ArrayList<>();
        BufferedReader fileReader = null;

        try {
            String line = "";

            //Create the file reader
            fileReader = new BufferedReader(new FileReader(filename));

            //Read the CSV file header to skip it
            fileReader.readLine();

            //Read the file line by line starting from the second line
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(",");
                if (tokens.length > 0) {
                    //Create a new process object and fill its data
                    Process process = new Process(Integer.parseInt(tokens[0]), Long.parseLong(tokens[1]), Integer.parseInt(tokens[2]));
                    processes.add(process);
                }
            }

            //Print the processes
            for (Process process : processes) {
                System.out.println(process.toString());
            }
        } catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }
        return processes;
    }
}
