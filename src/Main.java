
//Maddumage Dumidu Thanushka Fernando
// w1870557 / 20200515

//import packages
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        String inputDirectory = "input files";
        File folder = new File(inputDirectory);
        // Get a list of all the files in the directory
        File[] files = folder.listFiles();

        ArrayList<File> inputFiles = new ArrayList<File>();
        for (File file : files) {
            // Check if the file is a regular file and has the extension ".txt"
            if (file.isFile() && file.getName().endsWith(".txt")) {
                inputFiles.add(file);
            }
        }

        // Shuffle the list of input files
        Collections.shuffle(inputFiles);
        for (File file: inputFiles) {
            process_graph(file);
        }
    }

    private static void process_graph(File file) {
        try {
            // Parse the input file into a directed graph
            GraphParser graphParser = new GraphParser(file);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line=reader.readLine();
            System.out.println("Number of vertices : "+ line);
            System.out.print("Graph edges are : { ");
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                // If there are more lines to read, print the edge and a comma to the console
                if (reader.ready()) {
                    System.out.print("["+tokens[0]+","+ tokens[1] +"]" + ", ");
                } else {
                    System.out.println("["+tokens[0]+","+ tokens[1] +"]" +" }");
                }
            }

            // Check if the graph is acyclic
            if (AcyclicDetector.graphType(graphParser.getGraph())) {
                System.out.println("Graph is acyclic");
            } else {
                // If the graph is cyclic, print a message to the console and find a cycle
                System.out.println("Graph is cyclic");
                int[] cycle = AcyclicDetector.findCycleInGraph(graphParser.getGraph());
                System.out.print("Cycle: ");
                for (int i = 0; i < cycle.length; i++) {
                    System.out.print(cycle[i] + 1);
                    if (i < cycle.length - 1) {
                        System.out.print(" -> ");
                    }
                }
                System.out.println();
            }
            System.out.println();

        } catch (IOException e) {
            // If there was an error reading the file, print an error message to the console and exit the program
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }
    }
}
