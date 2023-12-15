import java.util.List;

public interface Graph<v> {
    public boolean add(v vertex);
    public boolean connect(v from, v to, String comun);
    public boolean disconnect(v from, v to);
    public boolean contains(v vertex);
    public List<Nodo> getInwardEdges(v to);
    public List<Nodo> getOutwardEdges(v from);
    public List<Nodo> getVerticesConnectedTo(v vertex);
    public List<Nodo> getAllVertices();
    public boolean remove(v vertex);
    public int size();
}