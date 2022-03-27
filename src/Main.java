import java.util.*;


public class Main {
    public static void main(String[] args) {

        // Create process objects as lines from CSV file
        List<Process> processesCSV = ReadCSV.readCSV("processes.csv");

        List<Processor> cpu = new ArrayList<>();
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

        // update null processes within each processor, with processes from CSV file
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


        // Part 1
        System.out.println("PART 1 \n");
        System.out.println("Starting FIFO Scheduler......\n");
        FIFO.FIFOScheduler(cpu);

        System.out.println("\n_____________________________________________________________");

        System.out.println("Starting SJF Scheduler......\n");
        SJF.SJFScheduler(cpu);

        System.out.println("\n______________________________________________________________");

        System.out.println("Starting RR Scheduler - time quantum = 5E+12 cpu cycles......\n");
        RoundRobin.RRScheduler(cpu);


        /* PART 2 */
        List<Processor> cpu2 = new ArrayList<>();

        // Create CPU containing the 6 processors
        for (int i = 0; i < 6; i++) {
            cpu2.add(new Processor());
        }
        // create two pointers to indicate which 2GHz and 4GHz processor is next up
        int twoGHz = 0;
        int fourGHz = 3;
        // iterate through all processes and add each to appropriate processor
        for (Process process : processesCSV) {
            if (twoGHz > 2) twoGHz = 0;
            if (fourGHz > 5) fourGHz = 3;


            long currBurst = process.getBurstTime();

            // if current process's burst time is <= 1E+12 (sweet-spot)
            // and current 2GHz processor size is less <= to current 4GHz processor size
            // then add  current process to 2GHz processor
            // sweet-spot => assigns 15% less processes to slower processes
            // 10, 10, 9, 74, 74, 73
            if (currBurst <= Math.pow(10, 12) &&
                    (cpu2.get(twoGHz).getProcesses().size() <= cpu2.get(fourGHz).getProcesses().size())) {
                cpu2.get(twoGHz).getProcesses().add(process);
                twoGHz++;
            } else {
                cpu2.get(fourGHz).getProcesses().add(process);
                fourGHz++;
            }
        }
        System.out.println("\n=================================================================");
        System.out.println("PART 2");
        System.out.println("SJF with heterogeneous CPU.\n");
        SJF.SJFScheduler2(cpu2);

        /* PART 3 */
        List<Processor> cpu3 = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            cpu3.add(new Processor());
        }

        // iterate through all processes and add each to appropriate processor
        for (Process process : processesCSV) {
            if (twoGHz > 2) twoGHz = 0;
            if (fourGHz > 5) fourGHz = 3;


            int currMemSize = process.getMemSize();

            // if current process's memory is <= 8000 MB
            // and current 2GHz processor size is less <= to current 4GHz processor size
            // then add  current process to 2GHz processor
            // 41,41,41,43,42,43
            if (currMemSize <= 8000 &&
                    (cpu3.get(twoGHz).getProcesses().size() <= cpu3.get(fourGHz).getProcesses().size())) {
                cpu3.get(twoGHz).getProcesses().add(process);
                twoGHz++;
            } else {
                cpu3.get(fourGHz).getProcesses().add(process);
                fourGHz++;
            }
        }

        System.out.println("\n=================================================================");
        System.out.println("PART 3");

        System.out.println("SJF with memory requirements.\n");
        SJF.SJFScheduler2(cpu3);


        /* PART 4 */
        List<Processor> cpu4 = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            cpu4.add(new Processor());
        }

        for (Process process : processesCSV) {
            if (twoGHz > 2) twoGHz = 0;
            if (fourGHz > 5) fourGHz = 3;

            long currBurst = process.getBurstTime();
            int currMemSize = process.getMemSize();

            // if current process's memory is <= 8000 MB && current process's burst time is <= 1E+12
            // && current 2GHz processor size is less <= to current 4GHz processor size
            // then add  current process to 2GHz processor
            // 5,6,5,78,78,78
            if (currMemSize <= 8000 && currBurst <= Math.pow(10, 12) &&
                    cpu4.get(twoGHz).getProcesses().size() <= cpu4.get(fourGHz).getProcesses().size()) {
                cpu4.get(twoGHz).getProcesses().add(process);
                twoGHz++;
            } else {
                cpu4.get(fourGHz).getProcesses().add(process);
                fourGHz++;
            }
        }

        System.out.println("\n=================================================================");
        System.out.println("PART 4");

        System.out.println("FIFO with heterogeneous CPU and memory requirements.\n");
        FIFO.FIFOScheduler2(cpu4);

    }
}
