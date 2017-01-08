/*
  Definim D[i][j] ca suma maxima ce se poate obtine pana in punctul de coordonate (i,j).
  Cum robotul poate calatori doar in sud si est, D[i][j] = valoarea din (i,j) + max(D[i-1][j],D[i][j-1]),
  ce se poate calcula in O(1).

  Prin urmare, complexitatea finala va fi O(nm).
*/

import java.io.*;
import java.util.*;
import java.lang.*;

public class var1p3 {
  public static void main(String[] args) {
    Scanner scanner = null;
    try {
        scanner = new Scanner(new File("var1p3.in"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    int n = scanner.nextInt();
    int m = scanner.nextInt();
    int[][] D = new int[n + 1][m + 1];
    int[][] previ = new int[n + 1][m + 1];
    int[][] prevj = new int[n + 1][m + 1];

    for(int i = 1; i <= n; ++i)
      for(int j = 1; j <= m; ++j) {
        D[i][j] = scanner.nextInt();
        previ[i][j] = i;
        prevj[i][j] = j;

        if(D[i - 1][j] >= D[i][j - 1]) {
          D[i][j] += D[i - 1][j];
          previ[i][j] = i - 1;
        }
        else {
          D[i][j] += D[i][j - 1];
          prevj[i][j] = j - 1;
        }
      }

    int[] soli = new int[n + m + 1];
    int[] solj = new int[n + m + 1];

    int cnt = 0;
    int i = n, j = m;

    soli[cnt] = n;
    solj[cnt] = m;
    ++cnt;

    while(previ[i][j] != 1 || prevj[i][j] != 1) {
      soli[cnt] = previ[i][j];
      solj[cnt] = prevj[i][j];
      int x = previ[i][j];
      int y = prevj[i][j];
      i = x;
      j = y;
      ++cnt;
    }

    soli[cnt] = 1;
    solj[cnt] = 1;

    System.out.println(D[n][m]);
    for(i = cnt; i >= 0; --i)
      System.out.println(soli[i] + " " + solj[i]);
  }
}
