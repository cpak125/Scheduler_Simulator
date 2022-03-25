import java.util.*;


public class Main {
    public static void main(String[] args) {

        // Read processes from CSV file
        List<Process> processesCSV = ReadCSV.readCSV("processes.csv");

        ArrayList<Processor> cpu = new ArrayList<>();
        int processesRemain = 250;
        int processorRemain = 6;

        // Create CPU containing the 6 processors
        for (int i = 0; i < 6; i++) {
            cpu.add(new Processor(processesRemain / processorRemain));
            processesRemain -= cpu.get(i).getProcesses().size();
            processorRemain--;
        }

        int startIndex = 0;
        int endIndex = 41;

        for (int i = 0; i < cpu.size(); i++) {
            List<Process> sublist = processesCSV.subList(startIndex, endIndex);
            int index = 0;

            for (Process process : sublist) {
//                System.out.println(process);
                cpu.get(i).getProcesses().set(index, process);
                index++;
            }
            startIndex = endIndex;
            if (i < 5) {
                endIndex += (cpu.get(i + 1).getProcesses().size());
            }

        }

        System.out.println("Starting FIFO Scheduler......\n");
        FIFO.FIFOScheduler(cpu);

        System.out.println("Starting SJF Scheduler......\n");
        SJF.SJFScheduler(cpu);

        System.out.println("Starting RR Scheduler - time quantum = 5E+12 cpu cycles......\n");
        RoundRobin.RRScheduler(cpu);

    }
}
