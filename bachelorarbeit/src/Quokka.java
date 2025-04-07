import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
public class Quokka {
    private List<Integer> position = new ArrayList<>();
    private double fitness;
    private double[][] adjacencyMatrix;
    private double[] drought;
    int identifier;
    Random rand = new Random();

    public int getIdentifier() {
        return identifier;
    }

    public Quokka(WeightedGraph graph, int ident) {
        adjacencyMatrix = graph.getAdjacencyMatrix();
        drought = new double[adjacencyMatrix.length];
        identifier = ident;
        computeTour(graph);
        computeFitness();
    }

    public void computeTour(WeightedGraph graph) {
        //Edge[][] adjacencyMatrix = graph.getAdjacencyMatrix();
        for(int i = 0; i < adjacencyMatrix.length; i++) {
            position.add(i);
        }
        Collections.shuffle(position);
        //position.add(position.get(0));
    }

    public void computeFitness() {
        double f = 0;
        int cnt = 0;
        for(cnt = 0; cnt < adjacencyMatrix.length-2; cnt++) {
            if(position.get(cnt+1) != null) {
                f += adjacencyMatrix[position.get(cnt)][position.get(cnt+1)];
                //System.out.println("Von " + position.get(cnt) + " nach " + position.get(cnt+1) + " = " + adjacencyMatrix[position.get(cnt)][position.get(cnt+1)]);
            } else {
                f += adjacencyMatrix[position.get(cnt)][position.get(0)];
                break;
            }
        }
        f += adjacencyMatrix[position.get(cnt)][position.get(0)];
        //System.out.println("Von " + position.get(cnt) + " nach " + position.get(0) + " = " + adjacencyMatrix[position.get(cnt)][position.get(0)]);
        setFitness(f);
    }







    
    public void printTour() {
        System.out.print("Quokka Nummer " + identifier + " | ");
        for(int e : position) {
            System.out.print(e + " ");
        }
        System.out.print(position.get(0) + "  ");
        System.out.print("Gesamtgewicht: " + String.format("%.4f", fitness));
    }

    public List<Integer> getPosition() {
        return position;
    }

    public void setPosition(List<Integer> position) {
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
    public void swapRandom() {
        int range = position.size();
        int i = rand.nextInt(0, range);
        int j = rand.nextInt(0, range);
        Collections.swap(position, i, j);
    }

   
}
