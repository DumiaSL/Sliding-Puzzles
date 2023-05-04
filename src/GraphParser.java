import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class GraphParser {
    public static DirectedGraph parseGraph(String filename, int numVertices) throws IOException {
        DirectedGraph graph = new DirectedGraph(numVertices);
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(" ");
            int u = Integer.parseInt(tokens[0]) - 1;
            int v = Integer.parseInt(tokens[1]) - 1;
            graph.addEdge(u, v);
        }
        reader.close();
        return graph;
    }
}
