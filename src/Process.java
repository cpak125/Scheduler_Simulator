import java.util.*;

public class Process implements Comparable<Process> {
    int pid;
    long burstTime;
    int memSize;

    public Process(int pid, long burstTime, int memSize) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.memSize = memSize;
    }

    public long getBurstTime() {
        return burstTime;
    }

    public int getPid() {
        return pid;
    }

    public int getMemSize() {
        return memSize;
    }

    @Override
    public String toString() {
        return "Process{" +
                "pid=" + pid +
                ", burstTime=" + burstTime +
                ", memSize=" + memSize +
                '}';
    }

    @Override
    public int compareTo(Process p) {
        if (this.getBurstTime() > p.getBurstTime())
            return 1;
        else if (this.getBurstTime() == p.getBurstTime())
            return 0;
        else
            return -1;
    }
}
