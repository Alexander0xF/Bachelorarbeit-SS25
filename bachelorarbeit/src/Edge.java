public class Edge {
    Vertex source;
    Vertex dest;
    double weight;

    public Edge(Vertex source, Vertex dest, double weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }

    public String getSourceName() {
        return this.source.getName();
    }

    public String getDestName() {
        return this.dest.getName();
    }

    public double getWeight() {
        return this.weight;
    }
}
