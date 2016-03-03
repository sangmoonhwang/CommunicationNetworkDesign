public class CommunicationNetwork {

	static int numberOfCities = 0;
	static double[][] costs;
	static double[][] reliabilities;
	static int a_b = -1;
	static double req_R = 0;
	static double req_C = 0;

	public static void main(String[] args) {
		initialize();
		if (numberOfCities == 0)
			return;
		City[] cities = new City [numberOfCities];
		for (int i = 0; i < cities.length; i++) {
			cities[i] = new City();
		}
	}

	private static void initialize() {
		//TODO: read inputs from files and initialize variables.

		// Scanner keyboardReader = new Scanner(System.in);
		// System.out.println("Enter the number of the cities: ");
		// int n = keyboardReader.nextInt();
		// System.out.println("Enter the budget ");
		// double budget = keyboardReader.nextDouble();
		// System.out.println("Enter the cost between each cities");
		//
		// int m = n * 3;
		// double[] cost = new double[n];
		// for (int i = 0; i < n; i++) {
		// cost[i] = keyboardReader.nextInt();
		// }
		// System.out.println("Enter the reliability for each segments ");
		// double[] reliability = new double[n];
		// for (int j = 0; j < n; j++) {
		// reliability[j] = keyboardReader.nextInt();
		// }
		// System.out.println(Arrays.toString(cost));
		// System.out.println(Arrays.toString(reliability));
		// // double [] list = new double [n];
		// double[] newList = new double[m];
		//
		// for (int i = 0; i < n; i++) {
		// for (int j = 0; j < 3; j++) {
		// newList[n + j] = cost[i];
		// }
		// }
		// // Instead of copying and pasting the actual cost variables, we need
		// to
		// // copy and paste the indices of the matrix inputs for three times.
		// // because it is the indices and NOT the costs that indicate the
		// wires
		// // between two cities.
		// System.out.println(Arrays.toString(newList));
	}
	// max = maximum number of connections = (number of diagonals + number of
	// cities) * 3
	// min = minimum number of connections -1
	// public static int max(int n) {
	// return ((((n * (n - 3)) / 2) + n) * 3);
	// }
	//
	// public static int min(int n) {
	// return (n - 1);
	// }
}
