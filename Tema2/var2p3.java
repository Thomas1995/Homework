import java.io.*;
import java.util.*;

public class var2p3 {

  static private class Action implements Comparable<Action>{
    int profit, index;

    public Action(int i, int p) {
      profit = p;
      index = i;
    }

    public int compareTo(Action other) {
      return other.profit - profit;
    }
  }

  public static void main(String[] args) {
    Scanner scanner = null;
    try {
        scanner = new Scanner(new File("var2p3.in"));
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    int n = scanner.nextInt();
    ArrayList<ArrayList<Action>> actions = new ArrayList<ArrayList<Action>>();

    for(int i = 0; i < n; ++i) {
      actions.add(new ArrayList<Action>());
    }

    int idx = 0;
    while(scanner.hasNextInt()) {
      int p = scanner.nextInt();
      int d = scanner.nextInt();
      actions.get(d-1).add(new Action(++idx, p));
    }

    scanner.close();

    PriorityQueue<Action> Q = new PriorityQueue<Action>();
    ArrayList<Integer> indexes = new ArrayList<Integer>();

    int total = 0;

    for(int i = n - 1; i >= 0; --i) {
      for(Action item : actions.get(i)){
        Q.add(item);
      }

      if(Q.size() > 0) {
        Action chosen = Q.poll();
        total += chosen.profit;
        indexes.add(chosen.index);
      }
    }

    System.out.println(total);

    for(int i = indexes.size() - 1; i >= 0; --i) {
      System.out.print(indexes.get(i) + " ");
    }

    System.out.println();
  }
}
