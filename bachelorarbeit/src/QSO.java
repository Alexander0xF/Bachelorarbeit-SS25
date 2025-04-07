import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class QSO {
    private WeightedGraph graph;
    private List<Quokka> population = new ArrayList<>();
    private Quokka leader;
    private int numCitys;
    //TODO 
    //DIE DRECKS MATRIX UMBAUEN, WAR NICHT SO KLUG VON 
    //WAHRSCHEINLICHKEITEN SIND AUCH MANCHMAL NEGATIV!!!!
    public QSO() {
        graph = new WeightedGraph();
        numCitys = graph.getAdjacencyMatrix().length;
    }

    public void run() {
        generatePopulation(); //Fitness wird hier schon für jeden Quokka berechnet
        
        double T = 0.2;
        double H = 0.3;
        for(int i = 0; i < 1000; i++) {
            selectLeader();
            System.out.println(i + ". Aktuell beste Tour: " + String.format("%.4f", leader.getFitness()));
            //leader.printTour();
            for(Quokka q : population) {
                if(q != leader) {

                
                int deltaX = computeHammingDistance(q);
                getProbability(q, deltaX, T, H);
                updatePosition(q);
                q.computeFitness();
                //System.out.println("Hamming-Distanz: " + computeHammingDistance(q));
                }
            }
        }
    }

    public void updatePosition(Quokka q) {
        Random rand = new Random();
        double[] drought = q.getDrought();
        List<Edge> position = q.getPosition();
        List<Edge> leaderPosition = leader.getPosition();
        /**System.out.println("Vorher:");
        System.out.println(position);
        System.out.println(leaderPosition);
        System.out.println("Nachher:");*/
        for(int i = 0; i < drought.length-1; i++) {
            if(drought[i] != 0) {
                if(rand.nextDouble()<=drought[i]) {
                    Edge temp = position.get(i);
                    position.set(i, leaderPosition.get(i));
                    leaderPosition.set(i, temp);
                }
            }
        }
        /**System.out.println(position);
        System.out.println(leaderPosition);
        System.out.println("\n");*/
        q.setPosition(position);
        leader.setPosition(leaderPosition);
        //System.out.println("Position geupdatet");
        fixHamiltonCirlce(q);
        //fixHamiltonCirlce(leader);
    }
    public void fixHamiltonCirlce(Quokka q) {
        /**
         * letzter hat source vom vorletzten und dest vom ersten
         */
        List<Edge> tour = q.getPosition();
        List<Edge> newTour = new ArrayList<>();
        Edge[][] adjacencyMatrix = graph.getAdjacencyMatrix();
        int cnt = 0;
        for(Edge e : tour) {
            try {
                newTour.add(cnt, adjacencyMatrix[e.getSource().getIndex()][e.getDest().getIndex()]);
                
                cnt++;
            } catch(IndexOutOfBoundsException f) {
                newTour.add(cnt, adjacencyMatrix[e.getSource().getIndex()][tour.get(0).getSource().getIndex()]);
            } 
        }
        q.setPosition(newTour);
        /**tour.get(numCitys-1).setSource(tour.get(numCitys-2).getSource());
        tour.get(numCitys-1).setDest(tour.get(0));
        tour.set(numCitys-1,  graph.getAdjacencyMatrix());
        q.setPosition(tour);*/
    }

    private void generatePopulation() {
        for(int i = 0; i < 5; i++) {
            population.add(new Quokka(graph));
        }      
    }

    public void getProbability(Quokka q, int deltaX, double T, double H) {
        double f0 = leader.getFitness() - q.getFitness();
        //System.out.println("f0: " + f0);
        if(f0<1) f0 =1;
        double deltaW = leader.getFitness()/f0;
        //System.out.println("deltaW: " + deltaW);
        //System.out.println("deltaX: " + deltaX);
        double zwischen = ((((T+H)/0.8)+(deltaX*deltaW*0.2))/100);
        //System.out.println("Zwischenergebnis: " + zwischen);
        if(zwischen >= 100) zwischen = 0.01;
        double prob = 1 - zwischen;
        double[] drought = q.getDrought();
        for(int i = 0; i < drought.length-1; i++) {
            if(drought[i] == 1) {
                drought[i] = prob;
            }
        }
        //System.out.println("Wahrscheinlichkeit: " + prob);
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
