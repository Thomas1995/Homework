import java.io.*;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class problema1 {
  static private class Pair<T> {
    public T[] elem;
  }

  static Map< String, Pair<Integer> > map = new HashMap< String, Pair<Integer> >();

  private static void getWordsFromFile(String path, int pos) {
    Scanner sc = null;
    try {
        sc = new Scanner(new File(path));
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    while(sc.hasNext()) {
      String s = sc.next();

      Pair<Integer> pii = map.get(s);
      if(pii == null) {
        pii = new Pair<Integer>();
        pii.elem = new Integer[2];
        pii.elem[pos] = 1;
        pii.elem[1 - pos] = 0;
        map.put(s, pii);
      }
      else {
        ++pii.elem[pos];
        map.put(s, pii);
      }
    }
  }

  public static void main(String[] args) {
    if(args.length != 2) {
      System.out.println("Invalid arguments.");
      return;
    }

    for(int i = 0; i < 2; ++i)
      getWordsFromFile(args[i], i);

      int sumt, sum1, sum2;
      sumt = sum1 = sum2 = 0;

      for (Map.Entry< String, Pair<Integer> > entry : map.entrySet())
      {
        sum1 += entry.getValue().elem[0] * entry.getValue().elem[0];
        sum2 += entry.getValue().elem[1] * entry.getValue().elem[1];
        sumt += entry.getValue().elem[0] * entry.getValue().elem[1];
      }

      System.out.println( sumt / (Math.sqrt(sum1) * Math.sqrt(sum2)) );
  }
}
