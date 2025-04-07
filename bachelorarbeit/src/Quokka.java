import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quokka {
    private List<Integer> tourIndex = new ArrayList<>();
    private List<Edge> position = new ArrayList<>();
    private double fitness;
    private Edge[][] adjacencyMatrix;
    private double[] drought;

    public Quokka(WeightedGraph graph) {
        adjacencyMatrix = graph.getAdjacencyMatrix();
        drought = new double[adjacencyMatrix.length];
        computeTour(graph);
        computeFitness();
    }

    public void computeTour(WeightedGraph graph) {
        //Edge[][] adjacencyMatrix = graph.getAdjacencyMatrix();
        for(int i = 0; i < adjacencyMatrix.length; i++) {
            tourIndex.add(i);
        }
        Collections.shuffle(tourIndex);
        int cnt = 0;
        for(int i : tourIndex) {
            try {
                position.add(cnt, adjacencyMatrix[i][tourIndex.get(cnt+1)]);
                
                cnt++;
            } catch(IndexOutOfBoundsException e) {
                position.add(cnt, adjacencyMatrix[i][tourIndex.get(0)]);
            } 
        }
    }

    public void computeFitness() {
        double f = 0;
        for(Edge e : position) {
            f += e.getWeight();
            
        }
        setFitness(f);
    }







    
    public void printTour() {
        for(Edge e : position) {
            System.out.println(e.getSourceName() + " -> " + e.getDestName() + " | Gewicht: " + e.getWeight());
        }
        System.out.println("Gesamtgewicht: " + fitness + "\n");
    }

    public List<Edge> getPosition() {
        return position;
    }

    public void setPosition(List<Edge> position) {
        this.position = position;
    }


    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double[] getDrought() {
        return drought;
    }

    public void setDrought(double[] drought) {
        this.drought = drought;
    }

   
}
