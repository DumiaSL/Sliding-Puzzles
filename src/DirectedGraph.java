public class DirectedGraph implements Cloneable{
    private int numVertices;
    private boolean[][] adjMatrix;

    public DirectedGraph(int numVertices) {
        this.numVertices = numVertices;
        adjMatrix = new boolean[numVertices][numVertices];
    }

    public int getNumVertices() {
        return numVertices;
    }

    //Adding edge to matrix
    public void addEdge(int u, int v) {
        adjMatrix[u][v] = true;
    }

    public int getOutDegree(int u) {
        int numberOfOutDegrees = 0;
        for (int v = 0; v < numVertices; v++) {
            // check if there is an edge from vertex u to vertex v
            if (adjMatrix[u][v]) {
                numberOfOutDegrees++;
            }
        }
        return numberOfOutDegrees; // return the out-degree of vertex u
    }

    public int[] getNeighborValues(int u) {
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

    public boolean[][] getGraphAdjMatrix() {
        return adjMatrix;
    }

    @Override
    public DirectedGraph clone() {
        try {
            return (DirectedGraph) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
