import java.text.NumberFormat;
import java.util.*;

public class SJF extends FIFO {

    static void SJFScheduler(List<Processor> cpu) {

        for (Processor p : cpu) {
            Collections.sort(p.getProcesses());
        }

        FIFOScheduler(cpu);
    }

    static void SJFScheduler2(List<Processor> cpu) {
        NumberFormat outputCommas = NumberFormat.getInstance(Locale.US);
        long totalTurnaroundTime = 0;

        for (Processor p : cpu) {
            Collections.sort(p.getProcesses());
        }

        for(int i =0; i < cpu.size();i++) {
            if(i < 3) {
                totalTurnaroundTime += findAvgTurnaroundTime(cpu.get(i).getProcesses(), (long) (2 * Math.pow(10, 6)));
            } else {
                totalTurnaroundTime += findAvgTurnaroundTime(cpu.get(i).getProcesses(), (long) (4 * Math.pow(10, 6)));
            }
        }

        System.out.printf("Avg turnaround time is: %s seconds", outputCommas.format(totalTurnaroundTime / 6));
    }

    static long[] findWaitingTimes(List<Process> processes, long speed) {
        long[] waitTimes = new long[processes.size()];
        waitTimes[0] = 0;
        for (int i = 1; i < processes.size(); i++) {
            // waitTimes[i] = previous process's burst time + previous process's wait time
            Process prev = processes.get(i - 1);
            waitTimes[i] = (prev.getBurstTime() / speed) + waitTimes[i - 1];
        }

        return waitTimes;
    }

    static long[] findTurnaroundTimes(List<Process> processes, long speed) {
        long[] turnaroundTimes = new long[processes.size()];
        long[] waitTimes = findWaitingTimes(processes, speed);
        for (int i = 0; i < processes.size(); i++) {
            // turnaroundTimes[i] =  burst time + wait time
            Process curr = processes.get(i);
            turnaroundTimes[i] = (curr.getBurstTime()/ speed) + waitTimes[i];
        }

        return turnaroundTimes;
    }

    static double findAvgTurnaroundTime(List<Process> processes, long speed) {
        long totalTurnaroundTime = 0;
        long[] turnaroundTimes = findTurnaroundTimes(processes, speed);

        for (int i = 0; i < turnaroundTimes.length; i++) {
            totalTurnaroundTime += turnaroundTimes[i];
        }
        return (double) totalTurnaroundTime / processes.size();
    }


}
