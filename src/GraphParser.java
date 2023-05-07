//import packages
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GraphParser {
    DirectedGraph graph;
    public GraphParser(File file) throws IOException {
        // Create a new buffered reader to read the file
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line= reader.readLine();
        this.graph = new DirectedGraph(Integer.parseInt(line));
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(" ");
            try {
                int u = Integer.parseInt(tokens[0]) - 1;
                int v = Integer.parseInt(tokens[1]) - 1;
                graph.addEdge(u, v);
            } catch (Exception ignored) {
            }
        }
        reader.close();
    }

    public DirectedGraph getGraph() {
        return graph;
    }
}
