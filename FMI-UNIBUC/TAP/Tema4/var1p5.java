/*
  Sortam intervalele dupa capatul din dreapta.

  Definim D[i] ca fiind profitul maxim ce se poate obtine folosind doar intervalele de la 1 la i.

  Atunci pentru fiecare i cautam j astfel incat capatul din dreapta a lui j sa fie mai mic sau egal
  cu capatul din stanga a lui i. Cum intervalele sunt sortate dupa capatul din dreapta, vom folosi
  cautarea binara, deci complexitatea va fi O(logn) (valorile lui D sunt si ele ordonate crescator).

  Prin urmare, D[i] va fi ori D[j] + profitul lui i, ori D[i - 1].

  Repetand procedeul pentru numerele de la 1 la n, obtinem complexitatea finala O(nlogn).
*/

import java.io.*;
import java.util.*;

public class var1p5 {
  private static class triplet implements Comparable<triplet> {
    int x, y, z;

    public int compareTo(triplet t) {
      return y - t.y;
   }
  }

  public static void main(String[] args) {
    Scanner scanner = null;
    try {
        scanner = new Scanner(new File("var1p5.in"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    int n = scanner.nextInt();

    triplet[] numbers = new triplet[n];

    for(int i = 0; i < n; ++i) {
      numbers[i] = new triplet();
      numbers[i].x = scanner.nextInt();
      numbers[i].y = scanner.nextInt();
      numbers[i].z = scanner.nextInt();
    }

    Arrays.sort(numbers);

    int[] D = new int[n];

    D[0] = numbers[0].z;
    for(int i = 1; i < n; ++i) {
        D[i] = D[i - 1];

        int st = 0, dr = i - 1;
        int sol = 0;
        while(st <= dr) {
          int mij = (st + dr) / 2;
          if(numbers[mij].y <= numbers[i].x) {
            sol = D[mij];
            st = mij + 1;
          }
          else {
            dr = mij - 1;
          }
        }

        if(sol + numbers[i].z > D[i]) {
          D[i] = sol + numbers[i].z;
        }
    }

    System.out.println(D[n - 1]);

    int val = D[n - 1];
    for(int i = n - 1; i >= 0; --i) {
      if(i > 0 && D[i] == D[i-1])
        continue;

      if(D[i] == val) {
        val -= numbers[i].z;
        System.out.println(numbers[i].x + " " + numbers[i].y);
      }
    }
  }
}
