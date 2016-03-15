import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

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
    HashSet<Integer> citiesConnected = new HashSet<Integer>();
    if (numberOfCities == -1) return;
    System.out.println(numberOfCities);
    System.out.println(req_R);
    System.out.println(req_C);

    int[][] connections = new int[numberOfCities][numberOfCities];
    City[] cities = new City[numberOfCities];
    for (int i = 0; i < cities.length; i++) {
      cities[i] = new City(i);
    }
    printMatrix(costs);
    printMatrix(reliabilities);
    for (int i = 0; i < numberOfCities; i++) {
      for (int j = i; j < numberOfCities; j++) {
        connections[i][j] = 0;
      }
    }
    if ("0".equals(a_b)) {
      // case a Min cost
      int colIndex;
      for (int i = 0; i < numberOfCities; i++) {
        if (!citiesConnected.contains(cities[i])) {
          citiesConnected.add(i);
        }
        colIndex = 0;
        for (int j = i; j < numberOfCities; j++) {
          if (i == j || citiesConnected.contains(cities[j])) continue;
          if (costs[i][colIndex] >= costs[i][j]) {
            System.out.println("GOTMIN");
            if (connections[i][j] < 3) {
              connections[i][j]++;
              if (getTotalReliability(connections) < req_R) {
                System.out.println("RESET");
              } else {
                colIndex = j;
              }
              connections[i][j]--;
            }
          }
        }
        connections[i][colIndex]++;
        citiesConnected.add(colIndex);
      }
      System.out.println(citiesConnected.size());
      printMatrix(connections);
      System.out.println(getTotalCost(connections));
      System.out.println(getTotalReliability(connections));

    } else if ("1".equals(a_b)) {
      // case b Max Rel.
    } else {
      System.out.println("Wrong parameter a_b. It should either be 0 or 1");
    }
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

  private static void printMatrix(int[][] m) {
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
          numberOfCities =
              Integer.parseInt(line.substring(line.indexOf('=') + 1).trim());
        }
        if (line.startsWith("C")) {
          String[] token =
              line.substring(line.indexOf('=') + 1).replace("[", "")
                  .replace("]", "").split(",");
          if (numberOfCities * (numberOfCities - 1) / 2 != token.length) {
            System.out.println("Wrong C parameter");
            numberOfCities = -1;
            bufferedReader.close();
            return;
          }
          costs = new double[numberOfCities][numberOfCities];
          initMatrix(costs, token);
        } else if (line.startsWith("Req_Reliability")) {
          req_R =
              Double.parseDouble(line.substring(line.indexOf('=') + 1).trim());
        } else if (line.startsWith("Req_Cost")) {
          req_C =
              Double.parseDouble(line.substring(line.indexOf('=') + 1).trim());
        } else if (line.startsWith("R")) {
          String[] token =
              line.substring(line.indexOf('=') + 1).replace("[", "")
                  .replace("]", "").split(",");
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
    for (int r = 0; r < (numberOfCities); r++) {
      for (int c = 0; c < numberOfCities; c++) {
        if (c < r) continue;
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

  private static double getTotalCost(int[][] connections) {
    double result = 0;
    for (int i = 0; i < numberOfCities; i++) {
      for (int j = i; j < numberOfCities; j++) {
        if (i == j || connections[i][j] == 0) continue;
        result += costs[i][j] * connections[i][j];
      }
    }
    return result;
  }

  private static double getTotalReliability(int[][] connections) {
    double result = 1;
    for (int i = 0; i < numberOfCities; i++) {
      for (int j = i; j < numberOfCities; j++) {
        if (i == j || connections[i][j] == 0) continue;
        switch (connections[i][j]) {
          case 1:
            result *= reliabilities[i][j];
            break;
          case 2:
            result *= (1 - Math.pow(1 - reliabilities[i][j], 2));
            break;
          case 3:
            result *= (1 - Math.pow(1 - reliabilities[i][j], 3));
            break;
        }
      }
    }
    return result;
  }
}
