import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class QSO {
    private WeightedGraph graph;
    private List<Quokka> population = new ArrayList<>();
    private Quokka leader;
    private int numCitys;
    private double lastFitness;
    private static final int MAX_ITERATIONS = 10001;
    private static final int MAX_POPULATION = 10;

    public QSO() {
        graph = new WeightedGraph();
        numCitys = graph.getAdjacencyMatrix().length;
    }

    public void run() {
        generatePopulation(); //Fitness wird hier schon für jeden Quokka berechnet
        selectLeader();
        lastFitness = leader.getFitness();
        System.out.print("Aktuell beste Tour: ");
        leader.printTour();
        System.out.print(" | in Runde 0" + "\n");
        double T = 0.2;
        double H = 0.3;
        for(int i = 1; i < MAX_ITERATIONS; i++) {
            
            selectLeader();
            if(leader.getFitness() < lastFitness) {
                lastFitness = leader.getFitness();
                System.out.print("Aktuell beste Tour: ");
                leader.printTour();
                System.out.print(" | in Runde " + i + "\n");
            }
            for(Quokka q : population) {
                if(q != leader) {
                int deltaX = computeHammingDistance(q);
                getProbability(q, deltaX, T, H);
                updatePosition(q);
                q.computeFitness();
                }
            }
        }
    }

    public void updatePosition(Quokka q) {
        Random rand = new Random();
        double[] drought = q.getDrought();
        List<Integer> position = q.getPosition();
        List<Integer> leaderPosition = leader.getPosition();
        for(int i = 0; i < drought.length; i++) {
            if(drought[i] != 0) {
                if(rand.nextDouble()<=drought[i]) { 
                    Collections.swap(position, position.indexOf(leaderPosition.get(i)), position.indexOf(position.get(i)));
                }
            }
        }
        q.setPosition(position);
        q.swapRandom();
        //leader.swapRandom();        
    }
 

    private void generatePopulation() {
        for(int i = 0; i < MAX_POPULATION; i++) {
            population.add(new Quokka(graph, i));
        }      
    }

    public void getProbability(Quokka q, int deltaX, double T, double H) {
        double f0 = q.getFitness() - leader.getFitness();
        if(f0<1) f0 =1;
        double deltaW = leader.getFitness()/f0;
        double zwischen = ((((T+H)/0.8)+(deltaX*deltaW*0.2))/100);
        if(zwischen >= 100) zwischen = 0.01;
        double prob = 1 - zwischen;
        double[] drought = q.getDrought();
        for(int i = 0; i < drought.length-1; i++) {
            if(drought[i] == 1) {
                drought[i] = prob;
            }
        }
        q.setDrought(drought);
    }

    public int computeHammingDistance(Quokka q) {
        int dist = 0;
        double[] drought = q.getDrought();
        for(int i = 0; i < numCitys; i++) {
            if(q.getPosition().get(i) != leader.getPosition().get(i)) {
                dist++;
                drought[i] = 1;
            } else {
                drought[i] = 0;
            }
        }
        q.setDrought(drought);
        return dist;
    }

    public void selectLeader() {
        
        double fitness = population.get(0).getFitness();
        for(Quokka q : population) {
            if(Math.min(q.getFitness(), fitness) == q.getFitness()) {
                fitness = q.getFitness();
                this.leader = q;
            }
        }
    }


    public void test() {
        graph.printGraph();
        for(Quokka q : population) {
            q.printTour();
        }
    }
}
