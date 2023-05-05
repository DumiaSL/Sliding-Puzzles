import java.util.ArrayList;
import java.util.List;

class AcyclicDetector {
    public static boolean isAcyclic(DirectedGraph graph) {
        int[] outDegree = new int[graph.getNumVertices()];
        for (int u = 0; u < graph.getNumVertices(); u++) {
            outDegree[u] = graph.getOutDegree(u);
        }

        while (true) {
            int sink = findSink(outDegree);
            if (sink == -1) {
                // No sink found, graph is cyclic
                return false;
            } else if (outDegree[sink] == 0) {
                // Found a sink with no outgoing edges, graph is acyclic
                return true;
            } else {
                // Remove edges from the sink and update outDegree array
                for (int v : graph.getNeighbors(sink)) {
                    outDegree[v]--;
                }
                outDegree[sink] = -1; // Mark sink as removed
            }
        }
    }

    public static int[] findCycle(DirectedGraph graph) {
        int numVertices = graph.getNumVertices();
        boolean[] visited = new boolean[numVertices];
        boolean[] onStack = new boolean[numVertices];
        List<Integer> cycle = new ArrayList<>();

        for (int u = 0; u < numVertices; u++) {
            if (!visited[u] && findCycleHelper(graph, u, visited, onStack, cycle)) {
                int[] result = new int[cycle.size()];
                for (int i = 0; i < cycle.size(); i++) {
                    result[i] = cycle.get(i);
                }
                return result;
            }
        }

        return null; // No cycle found
    }

    private static boolean findCycleHelper(DirectedGraph graph, int u, boolean[] visited, boolean[] onStack, List<Integer> cycle) {
        visited[u] = true;
        onStack[u] = true;
        cycle.add(u);

        for (int v : graph.getNeighbors(u)) {
            if (!visited[v]) {
                if (findCycleHelper(graph, v, visited, onStack, cycle)) {
                    return true;
                }
            } else if (onStack[v]) {
                // Cycle detected
                cycle.add(v);
                return true;
            }
        }

        cycle.remove(cycle.size() - 1);
        onStack[u] = false;

        return false;
    }

    public static int findSink(int[] outDegree) {
        for (int u = 0; u < outDegree.length; u++) {
            if (outDegree[u] == 0) {
                return u; // Found a sink
            }
        }
        return -1; // No sink found
    }
}
