import java.util.ArrayList;
import java.util.List;
public class PermutationGenerator {
    private Edge[][] adjacencyMatrix;
    private int cityCount;
    private List<List<Edge>> permutations = new ArrayList<>();

    public PermutationGenerator(Edge[][] adjacenyMatrix) {
        this.adjacencyMatrix = adjacenyMatrix;
        cityCount = adjacenyMatrix.length;
    }

    
}
