import java.util.*;

public class WriteCSV {
    public static List<Process> generateProcesses() {
        List<Process> processes = new ArrayList<>();
        for (int i = 1; i <= 250; i++) {

            // generate random cycles
            double minCycles = 10 * Math.pow(10, 6), maxCycles = 10 * Math.pow(10, 12);
            long cycles = (long) Math.floor(Math.random() * (maxCycles - minCycles + 1) + minCycles);

            // generate random memory size
            double minSize = 1.0, maxSize = 16000.0;
            int memSize = (int) Math.floor(Math.random() * (maxSize - minSize + 1) + minSize);

//          add new process into processes list
            processes.add(new Process(i, cycles, memSize));
        }
        return processes;
    }
}
