import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RoundRobin {

    static void RRScheduler(List<Processor> cpu) {
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

        System.out.printf("Avg wait time is: %s cycles\n", outputCommas.format(totalWaitTime / 6));
        System.out.printf("Avg turnaround time is: %s cycles", outputCommas.format(totalTurnaroundTime / 6));

    }

    static long[] findWaitingTimes(List<Process> processes) {
        // Make a copy of burst times bt[] to store remaining burst times.
        long[] remainBurstTime = new long[processes.size()];
        for (int i = 0 ; i < processes.size() ; i++) {
            remainBurstTime[i] = processes.get(i).getBurstTime();
        }

        long timeElapsed = 0; // Current time elapsed
        double quantum = 5 * Math.pow(10, 12); // sweet-spot = avg burst time
        long[] waitTimes = new long[processes.size()];

        // Keep traversing processes in round-robin manner until all of them are not done.
        while(true) {
            boolean done = true;

            // Traverse all processes one by one repeatedly
            for (int i = 0 ; i < processes.size(); i++)
            {
                // If burst time of a process is greater than 0 then  need to process further
                if (remainBurstTime[i] > 0) {
                    done = false; // There is a pending process

                    if (remainBurstTime[i] > quantum) {
                        // Increase the value of timeElapsed i.e. shows how much time a process has been processed
                        timeElapsed += quantum;

                        // Decrease the burst_time of current process by quantum
                        remainBurstTime[i] -= quantum;
                    }

                    // If burst time is smaller than or equal to quantum. Last cycle for this process
                    else {
                        // Increase the value of t i.e. shows how much time a process has been processed
                        timeElapsed = timeElapsed + remainBurstTime[i];

                        // Waiting time is current time minus time used by this process
                        waitTimes[i] = timeElapsed - processes.get(i).getBurstTime();

                        // when process is finished executing set its remaining burst time = 0
                        remainBurstTime[i] = 0;
                    }
                }
            }

            // If all processes are done
            if (done)
                break;
        }

        return waitTimes;
    }

    static long[] findTurnaroundTimes(List<Process> processes) {
        long[] turnaroundTimes = new long[processes.size()];
        long[] waitTimes = findWaitingTimes(processes);

        // calculating turnaround time by adding bt[i] + wt[i]
        for (int i = 0; i < processes.size(); i++) {
            // turnaroundTimes[i] =  burst time + wait time
            turnaroundTimes[i] = processes.get(i).getBurstTime() + waitTimes[i];
        }

        return turnaroundTimes;
    }

    private static double findAvgWaitTime(List<Process> processes) {
        long totalWaitTime = 0;
        long[] waitTimes = findWaitingTimes(processes);

        for (long waitTime : waitTimes) {
            totalWaitTime += waitTime;
        }

        return (double) totalWaitTime / processes.size();
    }

    private static double findAvgTurnaroundTime(List<Process> processes) {
        long totalTurnaroundTime = 0;
        long[] turnaroundTimes = findTurnaroundTimes(processes);

        for (long turnaroundTime : turnaroundTimes) {
            totalTurnaroundTime += turnaroundTime;
        }
        return (double) totalTurnaroundTime / processes.size();
    }

}

