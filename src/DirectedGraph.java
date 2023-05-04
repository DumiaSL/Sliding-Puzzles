public class DirectedGraph {
    private int numVertices;
    private boolean[][] adjMatrix;

    public DirectedGraph(int numVertices) {
        this.numVertices = numVertices;
        adjMatrix = new boolean[numVertices][numVertices];
    }

    public void addEdge(int u, int v) {
        adjMatrix[u][v] = true;
    }

    public boolean hasSink(int u) {
        for (int v = 0; v < numVertices; v++) {
            if (adjMatrix[u][v]) {
                return false;
            }
        }
        return true;
    }

    public void removeVertex(int u) {
        for (int i = 0; i < numVertices; i++) {
            adjMatrix[i][u] = false;
        }
        adjMatrix[u] = new boolean[numVertices];
    }

    public int getNumVertices() {
        return numVertices;
    }

    public int getOutDegree(int u) {
        int count = 0;
        for (int v = 0; v < numVertices; v++) {
            if (adjMatrix[u][v]) {
                count++;
            }
        }
        return count;
    }

    public int[] getNeighbors(int u) {
        int[] neighbors = new int[getOutDegree(u)];
        int index = 0;
        for (int v = 0; v < numVertices; v++) {
            if (adjMatrix[u][v]) {
                neighbors[index] = v;
                index++;
            }
        }
        return neighbors;
    }
}
