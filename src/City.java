import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class City {
	private List<Edge> edges;

	private boolean isVisited;
	private int id = -1;

	public City(int id) {
		this.edges = new ArrayList<>();
		this.setId(id);
	}

	public boolean connect(City node) {
		if (hasEdge(node)) {
			return false;
		}
		double cost = CommunicationNetwork.costs[id][node.getId()];
		double reliability = CommunicationNetwork.reliabilities[id][node.getId()];
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

	public List<City> getNeighbors() {
		Set<City> neighbors = new HashSet<City>();
		for (Edge e : edges) {
			neighbors.add(e.getCityFrom());
			neighbors.add(e.getCityTo());
		}
		return new ArrayList<City>(neighbors);
	}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}