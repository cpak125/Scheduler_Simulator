import java.util.*;

public class Processor {
    List<Process> processes;

    public Processor() {
        this.processes = new ArrayList<>();
    }

    public Processor(int numProcesses) {
        this.processes = new ArrayList<>();

        for (int i = 0; i < numProcesses; i++) {
            processes.add(null);
        }
    }

    public List<Process> getProcesses() {
        return processes;
    }

}



