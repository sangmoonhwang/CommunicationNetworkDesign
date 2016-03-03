public class Edge {

	private City cityFrom;
	private City cityTo;

	private double unitC; // unit cost
	private double unitR; // unit reliability
	private int counter;

	public Edge(City from, City to, int cost, int reliability) {
		this.cityFrom = from;
		this.cityTo = to;
		this.unitC = cost;
		this.unitR = reliability;
	}

	public City getCityFrom() {
		return cityFrom;
	}

	public City getCityTo() {
		return cityTo;
	}

	public boolean isBetween(City node1, City node2) {
		// Undirected edge.
		return (this.cityFrom == node1 && this.cityTo == node2)
				|| (this.cityFrom == node2 && this.cityTo == node1);
	}

	public void addConnection() {
		if (counter < 3)
			counter++;
	}

	public void deleteConnection() {
		if (counter > 0)
			counter--;
	}

	public int getCounter() {
		return counter;
	}

	public double getReliability() {
		// TODO: implement it
		if (counter > 1) {
			// parallel calculation
		} else if (counter == 0) {
			return 0;
		}
		return unitR;
	}

	public double getCost() {
		return unitC * counter;
	}

	public boolean containsCity(City ct) {
		return ct.equals(cityFrom) || ct.equals(cityTo);
	}
}