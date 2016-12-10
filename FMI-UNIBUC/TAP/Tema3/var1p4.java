/*
  Vezi: http://www.infoarena.ro/problema/cmap
*/

import java.io.*;
import java.util.*;

public class var1p4 {
  private static class Point implements Comparable<Point> {
    int x, y;

    public Point() {}

    public Point(int X, int Y) {
      x = X;
      y = Y;
    }

    public int compareTo(Point other) {
      return x - other.x;
    }
  }

  static Point[] points;

  private static double dist(Point A, Point B) {
    return Math.sqrt((A.x - B.x) * (A.x - B.x) + (A.y - B.y) * (A.y - B.y));
  }

  private static double solve(int left, int right) {
    double d = Integer.MAX_VALUE;

    if(right - left < 3) {
      for(int i = left; i < right ; ++i)
        for(int j = i + 1; j <= right; ++j) {
          d = Math.min(dist(points[i], points[j]), d);
        }
    }
    else {
      int mid = (left + right) / 2;
      d = Math.min(solve(left, mid), solve(mid + 1, right));

      int first = -1, last = -1;
      for(int i = left; i <= right; ++i) {
        if(Math.abs(points[i].x - points[mid].x) <= d) {
          if(first == -1)
            first = i;
          last = i;
        }
      }

      for(int i = first; i <= last; ++i) {
        for(int j = i + 1; j <= Math.min(last, first + 7); ++j)
          d = Math.min(d, dist(points[i], points[j]));
      }
    }

    return d;
  }

  public static void main(String[] args) {
    Scanner scanner = null;
    try {
        scanner = new Scanner(new File("var1p4.in"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    int n = scanner.nextInt();
    points = new Point[n];

    for(int i = 0; i < n; ++i) {
      points[i] = new Point();
      points[i].x = scanner.nextInt();
      points[i].y = scanner.nextInt();
    }

    Arrays.sort(points);

    System.out.println(solve(0, n - 1));
  }
}
