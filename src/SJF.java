import java.util.*;

public class SJF extends FIFO {

    static void SJFScheduler (ArrayList<Processor> cpu) {

        for(Processor p : cpu) {
            Collections.sort(p.getProcesses());
        }

        FIFOScheduler(cpu);
    }
}
