import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class ReadCSV {
    public static List<Process> readCSV(String filename) {

        List<Process> processes = new ArrayList<>();
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
        } catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                assert fileReader != null;
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }
        return processes;
    }
}
