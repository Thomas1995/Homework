import java.io.*;
import java.util.*;

public class var2p2b {

  private static class Text implements Comparable<Text> {
    int len, index;

    public Text(int i, int l) {
      len = l;
      index = i;
    }

    public int compareTo(Text other) {
      return len - other.len;
    }
  }

  public static void main(String[] args) {
    Scanner scanner = null;
    try {
        scanner = new Scanner(new File("var2p2b.in"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    int n = scanner.nextInt();
    int p = scanner.nextInt();

    Text[] texts = new Text[n];
    ArrayList<ArrayList<Integer>> indexes = new ArrayList<ArrayList<Integer>>();

    for(int i = 0; i < n; ++i) {
      int l = scanner.nextInt();
      texts[i] = new Text(i+1, l);
    }

    for(int i = 0; i < p; ++i) {
      indexes.add(new ArrayList<Integer>());
    }

    Arrays.sort(texts);

    int b = 0;
    for(Text t : texts) {
      indexes.get(b).add(t.index);
      b = (b + 1) % p;
    }

    for(int i = 0; i < p; ++i) {
      System.out.print((i+1) + ": ");

      for(int j : indexes.get(i)) {
        System.out.print(j + " ");
      }

      System.out.println();
    }
  }
}
