public class Vertex {
    private String city;
    int index;
    Vertex(String city, int index) {
        this.city = city;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return this.city;
    }
}
