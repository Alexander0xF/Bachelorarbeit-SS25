public class WeightedGraph {
    private Edge[][] adjacencyMatrix = GraphBuilder();
    private int numNodes = adjacencyMatrix.length;

    public void printGraph() {
        for(int i = 0; i < numNodes; i++) {
            for(int j = 0; j < numNodes; j++) {
                Edge e = adjacencyMatrix[i][j];
                System.out.println("Stadt " + e.getSourceName() + " ist verbunden mit Stadt " + e.getDestName() + 
                " und hat eine Kantenlänge von " + e.getWeight());
            }
            System.out.println("===================================================================");
        }
    }

    /**
     * Baut aus einer Adjazentmatrix mit Kosten eine Matrix aus Edge-Objects, welche Quelle, Ziel und Distanz enthalten
     * Adjazenzmatrix mit Kosten ist aktuell noch hardcoded im costs-Array
     * Städtenamen auch Hardcoded, eventuell andere Lösung 
     * @return
     */
    private Edge[][] GraphBuilder() {
        double[][] costs = { 
            { 0, 10, 15, 20 },
            { 10, 0, 35, 25 },
            { 15, 35, 0, 30 },
            { 20, 25, 30, 0 } };
        Vertex[] cities = {new Vertex("Hannover"), new Vertex("Berlin"), new Vertex("Biberach"), new Vertex("Hameln")};
        Edge[][] adjacencyMatrix = new Edge[4][4];
        for(int i = 0; i < costs.length; i++) {
            for(int j = 0; j < costs.length; j++) {
                adjacencyMatrix[i][j] = new Edge(cities[i], cities[j], costs[i][j]);
            }
        }
        return adjacencyMatrix;
    }

    public Edge[][] getAdjacencyMatrix() {
        return this.adjacencyMatrix;
    }

}