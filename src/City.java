import java.util.ArrayList;
import java.util.List;


public class City {
	private List<Edge> edges;

    private boolean isVisited;

    public City() {
        this.edges = new ArrayList<>();
    }

    public boolean connect(City node, int cost, int reliability) {
        if (hasEdge(node)) {
            return false;
        }
        Edge newEdge = new Edge(this, node, cost, reliability);
        node.addEdge(newEdge);
        return edges.add(newEdge);
    }
    
    public void addEdge(Edge e) {
    	edges.add(e);
    }

    public boolean removeEdge(City node) {
        Edge optional = findEdge(node);
        if (optional != null) {
            return edges.remove(optional);
        }
        return false;
    }

    private Edge findEdge(City node) {
    	for (Edge e : edges) {
    		if (e.containsCity(node))
    			return e;
    	}
    	return null;
    }
    
    private boolean hasEdge(City node) {
    	for (Edge e : edges) {
    		if (e.containsCity(node))
    			return true;
    	}
    	return false;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getEdgeCount() {
        return edges.size();
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }
}