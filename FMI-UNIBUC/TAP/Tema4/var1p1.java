/*
  Definim:
    D[i] lungimea maxima a unei secvente ce se termina cu piesa i
    ap[i] numarul de moduri pentru obtinerea acestei secvente

  D[i] va fi maximul pe D[j] + 1, unde j este o piesa compatibila cu piesa i si j < i.
  ap[i] va fi suma tuturor ap[j], pentru care j este compatibila cu i, iar D[j] + 1 = D[i].

  Este usor de observat complexitatea de O(n^2).
*/

import java.io.*;
import java.util.*;
import java.lang.*;

public class var1p1 {
  public static void main(String[] args) {
    Scanner scanner = null;
    try {
        scanner = new Scanner(new File("var1p1.in"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    int n = scanner.nextInt();
    int[] fst = new int[n];
    int[] snd = new int[n];

    for(int i = 0; i < n; ++i) {
      fst[i] = scanner.nextInt();
      snd[i] = scanner.nextInt();
    }

    int mx = -1;
    int[] D = new int[n];
    int[] ap = new int[n];
    int[] prev = new int[n];
    for(int i = 0; i < n; ++i) {
      D[i] = 1;
      ap[i] = 1;
      for(int j = 0; j < i; ++j) {
        if(fst[i] == snd[j]) {
          if(D[j] + 1 > D[i]) {
            D[i] = D[j] + 1;
            ap[i] = ap[j];
            prev[i] = j;
          }
          else
            if(D[j] + 1 == D[i]) {
              ap[i] += ap[j];
            }
        }
      }
      if(mx < D[i])
        mx = D[i];
    }

    int tot = 0, j = -1;
    for(int i = 0; i < n; ++i) {
      if(D[i] == mx) {
        tot += ap[i];
        j = i;
      }
    }

    int[] sol = new int[mx];
    for(int i = 0; i < mx; ++i) {
      sol[i] = j;
      j = prev[j];
    }

    for(int i = mx - 1; i >= 0; --i)
      System.out.println(fst[sol[i]] + " " + snd[sol[i]]);
    System.out.println(tot);
  }
}
