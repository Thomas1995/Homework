import java.io.*;
import java.util.*;

public class var2p1 {
  private static class Cube implements Comparable<Cube> {
    int lat, col, index;

    public Cube(int i, int l, int c) {
      lat = l;
      col = c;
      index = i;
    }

    public int compareTo(Cube other) {
      return other.lat - lat;
    }
  }

  public static void main(String[] args) {
    Scanner scanner = null;
    try {
        scanner = new Scanner(new File("var2p1.in"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    int n = scanner.nextInt();
    int p = scanner.nextInt();

    Cube[] cubes = new Cube[n];
    ArrayList<Integer> indexes = new ArrayList<Integer>();

    int idx = 0;

    for(int i = 0; i < n; ++i) {
      int l = scanner.nextInt();
      int c = scanner.nextInt();
      cubes[i] = new Cube(++idx, l, c);
    }

    Arrays.sort(cubes);

    int total = 0;
    int lastCol = 0;
    for(Cube cube : cubes) {
      if(cube.col != lastCol) {
        lastCol = cube.col;
        total += cube.lat;
        indexes.add(cube.index);
      }
    }

    System.out.println(total);

    for(int i : indexes) {
      System.out.print(i + " ");
    }

    System.out.println();
  }
}
