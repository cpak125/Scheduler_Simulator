import java.text.NumberFormat;
import java.util.*;

public class FIFO {

    static void FIFOScheduler(ArrayList<Processor> cpu) {
        NumberFormat outputCommas = NumberFormat.getInstance(Locale.US);
        double totalWaitTime = 0;
        double totalTurnaroundTime = 0;


        for (int i = 0; i < cpu.size(); i++) {
//            System.out.printf("Processor %d contains %d processes:\n", (i + 1), cpu.get(i).getProcesses().size());
//            for (Process p : cpu.get(i).getProcesses()) {
//                System.out.printf("pid = %d ; burstTime = %d ; memSize = %d\n", p.getPid(), p.getBurstTime(), p.getMemSize());
//            }
//            System.out.printf("Processor %d average wait time is %.2f\n", (i + 1), findAvgWaitTime(cpu.get(i).getProcesses()));
            totalWaitTime += findAvgWaitTime(cpu.get(i).getProcesses());

//            System.out.printf("Processor %d average turnaround time is %.2f\n\n", (i + 1), findAvgTurnaroundTime(cpu.get(i).getProcesses()));
            totalTurnaroundTime += findAvgTurnaroundTime(cpu.get(i).getProcesses());
        }

        System.out.println("***********************************************");

        System.out.printf("Avg wait time is: %s\n", outputCommas.format(totalWaitTime / 6));
        System.out.printf("Avg turnaround time is: %s", outputCommas.format(totalTurnaroundTime / 6));

        System.out.println("\n***********************************************\n");
    }

    static long[] findWaitingTimes(List<Process> processes) {
        long[] waitTimes = new long[processes.size()];
        waitTimes[0] = 0;
        for (int i = 1; i < processes.size(); i++) {
            // waitTimes[i] = previous process's burst time + previous process's wait time
            Process prev = processes.get(i - 1);
            waitTimes[i] = prev.getBurstTime() + waitTimes[i - 1];
        }

        return waitTimes;
    }

    static long[] findTurnaroundTimes(List<Process> processes) {
        long[] turnaroundTimes = new long[processes.size()];
        long[] waitTimes = findWaitingTimes(processes);
        for (int i = 0; i < processes.size(); i++) {
            // turnaroundTimes[i] =  burst time + wait time
            Process curr = processes.get(i);
            turnaroundTimes[i] = curr.getBurstTime() + waitTimes[i];
        }

        return turnaroundTimes;
    }

    static double findAvgWaitTime(List<Process> processes) {
        long totalWaitTime = 0;
        long[] waitTimes = findWaitingTimes(processes);

        for (int i = 0; i < waitTimes.length; i++) {
            totalWaitTime += waitTimes[i];
        }

        return (double) totalWaitTime / processes.size();
    }

    static double findAvgTurnaroundTime(List<Process> processes) {
        long totalTurnaroundTime = 0;
        long[] turnaroundTimes = findTurnaroundTimes(processes);

        for (int i = 0; i < turnaroundTimes.length; i++) {
            totalTurnaroundTime += turnaroundTimes[i];
        }
        return (double) totalTurnaroundTime / processes.size();
    }
}
