import java.io.*;
import java.util.*;

public class var2p2a {

  private static class Text implements Comparable<Text> {
    int frecv, len, index;
    double prio;

    public Text(int i, int f, int l) {
      frecv = f;
      len = l;
      index = i;
      prio = (double)frecv / len;
    }

    public int compareTo(Text other) {
      if(prio < other.prio)
        return 1;
      return -1;
    }
  }

  public static void main(String[] args) {
    Scanner scanner = null;
    try {
        scanner = new Scanner(new File("var2p2a.in"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    int n = scanner.nextInt();
    Text[] texts = new Text[n];

    for(int i = 0; i < n; ++i) {
      int l = scanner.nextInt();
      int f = scanner.nextInt();

      texts[i] = new Text(i+1, f, l);
    }

    Arrays.sort(texts);

    for(Text t : texts) {
      System.out.print(t.index + " ");
    }

    System.out.println();
  }
}
