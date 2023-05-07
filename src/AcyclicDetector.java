//import packages
import java.util.ArrayList;
import java.util.List;

class AcyclicDetector {
    public static boolean graphType(DirectedGraph graph) {
        System.out.print("Sink Removed : ");
        int sinkCount = 0;
        ArrayList<Integer> outDegree = new ArrayList<Integer>();
        DirectedGraph copyGraph = graph.clone();

        for (int u = 0; u < copyGraph.getNumVertices(); u++) {
            outDegree.add(copyGraph.getOutDegree(u));
        }

        while (true){
            for (int u = 0; u < copyGraph.getNumVertices(); u++) {
                if (!(outDegree.get(u)==-1)){
                    outDegree.set(u,copyGraph.getOutDegree(u));
                }
            }
            int sink = findSink(outDegree);

            boolean isAcyclic = true;
            for (int degree: outDegree) {
                if (degree != -1) {
                    isAcyclic = false;
                    break;
                }
            }

            if (isAcyclic) {
                // Found a sink with no outgoing edges, graph is acyclic
                if (sinkCount==0){
                    System.out.println("No found !");
                }else {
                    System.out.println();
                }
                return true;
            }

            if (sink!=-1){
                // Remove edges from the sink and update outDegree array
                for (int v = 0; v < copyGraph.getNumVertices() ; v++) {
                    // check if there is an edge from vertex u to vertex v
                    if (copyGraph.getGraphAdjMatrix()[v][sink]) {
                        copyGraph.getGraphAdjMatrix()[v][sink]=false;
                    }
                }
                // Mark sink as removed
                System.out.print(sink+1+ " ");
                sinkCount++;
                outDegree.set(sink,-1);
            }else {
                // No sink found, graph is cyclic
                if (sinkCount==0){
                    System.out.println("No found !");
                }else {
                    System.out.println();
                }
                return false;
            }
        }
    }

    public static int[] findCycleInGraph(DirectedGraph graph) {
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

        for (int v : graph.getNeighborValues(u)) {
            if (!visited[v]) {
                if (findCycleHelper(graph, v, visited, onStack, cycle)) {
                    return true;
                }
            } else if (onStack[v]) {
                // Cycle detected
                int idx = cycle.indexOf(v);
                cycle.add(v);
                int[] cycleArr = cycle.subList(idx, cycle.size()).stream().mapToInt(i -> i).toArray();
                cycle.clear();
                for (int i : cycleArr) {
                    cycle.add(i);
                }
                return true;
            }
        }
        cycle.remove(cycle.size() - 1);
        onStack[u] = false;

        return false;
    }

    public static int findSink(ArrayList<Integer> outDegree) {
        for (int u = outDegree.size()-1; u>=0; u--) {
            if (outDegree.get(u) == 0) {
                return u; // Found a sink
            }
        }
        return -1; // No sink found
    }
}
