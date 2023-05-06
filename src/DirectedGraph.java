public class DirectedGraph {
    private int numVertices;
    private boolean[][] adjMatrix;

    public DirectedGraph(int numVertices) {
        this.numVertices = numVertices;
        adjMatrix = new boolean[numVertices][numVertices];
    }

    public int getNumVertices() {
        return numVertices;
    }

    public void addEdge(int u, int v) {
        adjMatrix[u][v] = true;
    }

    public int getOutDegree(int u) {
        int count = 0;
        for (int v = 0; v < numVertices; v++) {
            // check if there is an edge from vertex u to vertex v
            if (adjMatrix[u][v]) {
                count++;
            }
        }
        return count; // return the out-degree of vertex u
    }

    public int[] getNeighbors(int u) {
        int[] neighbors = new int[getOutDegree(u)];
        int index = 0;
        for (int v = 0; v < numVertices; v++) {
            // check if there is an edge from vertex u to vertex v
            if (adjMatrix[u][v]) {
                neighbors[index] = v;
                index++;
            }
        }
        return neighbors;
    }

    public boolean[][] getAdjMatrix() {
        return adjMatrix;
    }
}
