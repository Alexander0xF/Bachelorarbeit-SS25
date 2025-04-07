import java.util.ArrayList;
import java.util.List;

public class WeightedGraph {
    
    
    private List<Vertex> cities = new ArrayList<>();
    private double[][] adjacencyMatrix = GraphBuilder();
    private int numNodes = adjacencyMatrix.length;

    public void printGraph() {
        for(int i = 0; i < numNodes; i++) {
            for(int j = 0; j < numNodes; j++) {
                System.out.println("Stadt " + cities.get(i).getName() + " ist verbunden mit Stadt " + cities.get(j).getName() + 
                " und hat eine Kantenlänge von " + adjacencyMatrix[i][j]);
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
    private double[][] GraphBuilder() {
        double[][] costs = {
            { 0, 234.5, 876.3, 543.2, 678.9, 432.1, 345.6, 987.6, 654.3, 789.1 },
            { 234.5, 0, 345.2, 654.1, 432.5, 765.4, 543.2, 876.5, 321.6, 678.4 },
            { 876.3, 345.2, 0, 987.6, 543.3, 789.4, 234.2, 456.7, 654.9, 321.5 },
            { 543.2, 654.1, 987.6, 0, 345.1, 432.2, 678.8, 123.4, 876.2, 456.3 },
            { 678.9, 432.5, 543.3, 345.1, 0, 987.1, 321.8, 789.3, 456.1, 876.7 },
            { 432.1, 765.4, 789.4, 432.2, 987.1, 0, 654.7, 345.3, 876.5, 234.6 },
            { 345.6, 543.2, 234.2, 678.8, 321.8, 654.7, 0, 789.2, 987.4, 456.8 },
            { 987.6, 876.5, 456.7, 123.4, 789.3, 345.3, 789.2, 0, 543.1, 321.9 },
            { 654.3, 321.6, 654.9, 876.2, 456.1, 876.5, 987.4, 543.1, 0, 678.2 },
            { 789.1, 678.4, 321.5, 456.3, 876.7, 234.6, 456.8, 321.9, 678.2, 0 }
        };
        Vertex[] city = {new Vertex("Hannover", 0), 
        new Vertex("Berlin", 1), 
        new Vertex("Biberach", 2), 
        new Vertex("Hameln", 3)
        , new Vertex("Hamburg",4), 
        new Vertex("Rostock",5), 
        new Vertex("Dortmund",6), 
        new Vertex("Bielefeld",7), 
        new Vertex("Bremen",8),
        new Vertex("Stuttgart", 9),
        new Vertex("Potsdam", 10)};
        for(int i = 0; i < city.length; i++) {
            //cities.add(city[i]);
            cities.add(i, city[i]);
        }
        
        return costs;
    }

    public double[][] getAdjacencyMatrix() {
        return this.adjacencyMatrix;
    }

}