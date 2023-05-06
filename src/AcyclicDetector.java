import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AcyclicDetector {
    public static boolean isGraphAcyclic(DirectedGraph graph) {
        ArrayList<Integer> outDegree = new ArrayList<Integer>();
        for (int u = 0; u < graph.getNumVertices(); u++) {
            outDegree.add(graph.getOutDegree(u));
        }

        boolean[][] copyMatrix = graph.getAdjMatrix().clone();

        while (true) {
            System.out.println(outDegree);
            int sink = findSink(outDegree);
            System.out.println(sink);

            if (sink!=0){
                if ((outDegree.get(sink) == 0)){
                    // Remove edges from the sink and update outDegree array
                    System.out.println(Arrays.toString(graph.getNeighbors(sink)));
                    for (int index=0; index<graph.getNeighbors(sink).length;index++){
                        int neighborValue = graph.getNeighbors(sink)[index]--;
                        outDegree.set(index,neighborValue);
                    }
                // Mark sink as removed
                outDegree.remove(sink);
                }else {
                    // Found a sink with no outgoing edges, graph is acyclic
                    return true;
                }
            }else {
                // No sink found, graph is cyclic
                return false;
            }





//            if (sink == -1) {
//                // No sink found, graph is cyclic
//                return false;
//            } else if (outDegree[sink] == 0) {
//                // Found a sink with no outgoing edges, graph is acyclic
//                return true;
//            } else {
//                // Remove edges from the sink and update outDegree array
//                for (int v : graph.getNeighbors(sink)) {
//                    outDegree[v]--;
//                }
//                // Mark sink as removed
//                outDegree[sink] = -1;
//            }
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

    public static int findSink(ArrayList<Integer> outDegree) {
        for (int u = 0; u < outDegree.size(); u++) {
            if (outDegree.get(u) == 0) {
                System.out.println("reaced "+u);
                return u; // Found a sink
            }
        }
        return -1; // No sink found
    }
}
