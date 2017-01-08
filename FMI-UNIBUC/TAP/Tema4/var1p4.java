/*
*/

import java.io.*;
import java.util.*;

public class var1p4 {
  public static void main(String[] args) {
    int n = 0, k = 0;
    int[][] numbers = new int[1][];

    int cnt = 0;
    String  thisLine = null;
    try {
       File file = new File("var1p4.in");
       BufferedReader br = new BufferedReader(new FileReader(file));
       while ((thisLine = br.readLine()) != null) {
          String[] parts = thisLine.split(" ");
          if(++cnt == 1) {
            n = Integer.parseInt(parts[0]);
            k = Integer.parseInt(parts[1]);
            numbers = new int[n][];
          }
          else {
            numbers[cnt - 2] = new int[parts.length];
            for(int i = 0; i < parts.length; ++i) {
              numbers[cnt - 2][i] = Integer.parseInt(parts[i]);
            }
          }
       }
    } catch(Exception e) {
       e.printStackTrace();
    }

    int[][] D = new int[n][k + 1];

    for(int i = 0; i < n; ++i)
      for(int j = 0; j <= k; ++j)
        D[i][j] = -1;

    for(int i = 0; i < n; ++i) {
      for(int j = 0; j <= k; ++j) {
        if((i == 0 && j == 0) || (i != 0 && D[i - 1][j] != -1)) {
          for(int q = 0; q < numbers[i].length; ++q)
            if(j + numbers[i][q] <= k)
              D[i][j + numbers[i][q]] = numbers[i][q];
        }
      }
    }

    int[] sol = new int[n];

    for(int i = n - 1; i >= 0; --i) {
      sol[i] = D[i][k];
      k -= sol[i];
    }

    for(int i = 0; i < n; ++i)
      System.out.print(sol[i] + " ");
    System.out.println();
  }
}
