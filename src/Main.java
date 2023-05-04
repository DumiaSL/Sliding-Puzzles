import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String filename = "input files/input_4de.txt";
        int numVertices = 6;

        try {
            DirectedGraph graph = GraphParser.parseGraph(filename, numVertices);

            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            if (AcyclicDetector.isAcyclic(graph)) {
                System.out.println("Graph is acyclic");
            } else {
                System.out.println("Graph is cyclic");
                int[] cycle = AcyclicDetector.findCycle(graph);
                System.out.print("Cycle: ");
                for (int i = 0; i < cycle.length; i++) {
                    System.out.print(cycle[i] + 1);
                    if (i < cycle.length - 1) {
                        System.out.print(" -> ");
                    }
                }
                System.out.println();
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }
    }


}
