public class Edge {
    Vertex source;
    Vertex dest;
    double weight;

    public Edge(Vertex source, Vertex dest, double weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }
    @Override
    public String toString() {
        String ret = this.source.getIndex() + " -> " + this.dest.getIndex();
        return ret;
    }

    public String getSourceName() {
        return this.source.getName();
    }

    public Vertex getSource() {
        return source;
    }

    public void setSource(Vertex source) {
        this.source = source;
    }

    public Vertex getDest() {
        return dest;
    }

    public void setDest(Vertex dest) {
        this.dest = dest;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDestName() {
        return this.dest.getName();
    }

    public double getWeight() {
        return this.weight;
    }
}
