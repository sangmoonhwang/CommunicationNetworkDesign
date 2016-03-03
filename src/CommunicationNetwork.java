import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CommunicationNetwork {

	final static String FILENAME = "input.txt";

	static int numberOfCities = -1;
	static double[][] costs;
	static double[][] reliabilities;
	static double req_R = 0;
	static double req_C = 0;
	static String a_b = "-1";

	public static void main(String[] args) {
		initialize();
		if (numberOfCities == -1)
			return;
		System.out.println(numberOfCities);
		City[] cities = new City[numberOfCities];
		for (int i = 0; i < cities.length; i++) {
			cities[i] = new City();
		}
		printMatrix(costs);
		printMatrix(reliabilities);
	}

	private static void printMatrix(double[][] m) {
		for (int i = 0; i < numberOfCities; i++) {
			for (int j = 0; j < numberOfCities; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void initialize() {
		String line = null;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(FILENAME);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				if (line.startsWith("N")) {
					numberOfCities = Integer.parseInt(line.substring(
							line.indexOf('=') + 1).trim());
				}
				if (line.startsWith("C")) {
					String[] token = line.substring(line.indexOf('=') + 1)
							.split(",");
					if (numberOfCities * (numberOfCities - 1) / 2 != token.length) {
						System.out.println("Wrong C parameter");
						numberOfCities = -1;
						bufferedReader.close();
						return;
					}
					costs = new double[numberOfCities][numberOfCities];
					initMatrix(costs, token);
				} else if (line.startsWith("Req_Reliability")) {
					req_R = Double.parseDouble(line.substring(
							line.indexOf('=') + 1).trim());
				} else if (line.startsWith("Req_Cost")) {
					req_C = Double.parseDouble(line.substring(
							line.indexOf('=') + 1).trim());
				} else if (line.startsWith("R")) {
					String[] token = line.substring(line.indexOf('=') + 1)
							.split(",");
					if (numberOfCities * (numberOfCities - 1) / 2 != token.length) {
						System.out.println("Wrong R parameter");
						numberOfCities = -1;
						bufferedReader.close();
						return;
					}
					reliabilities = new double[numberOfCities][numberOfCities];
					initMatrix(reliabilities, token);
				} else if (line.startsWith("a_b")) {
					a_b = line.substring(line.indexOf('=') + 1).trim();
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + FILENAME + "'");
			ex.printStackTrace();
		} catch (IOException ex) {
			System.out.println("Error reading file '" + FILENAME + "'");
			ex.printStackTrace();
		}
	}

	private static void initMatrix(double[][] m, String[] token) {
		int k = 0;
		for (int r = 0; r < (numberOfCities + 1) / 2; r++) {
			for (int c = 0; c < numberOfCities; c++) {
				if (c < r)
					continue;
				if (c == r)
					m[r][r] = 0;
				else {
					m[r][c] = Double.parseDouble(token[k].trim());
					m[c][r] = m[r][c];
					k++;
				}
			}
		}
	}
}
