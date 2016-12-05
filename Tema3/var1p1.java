/*
  Dupa ce se citeste vectorul, se va folosi cautarea binara: la fiecare pas verificam daca mijlocul intervalului in care ne uitam
  este egal cu pozitia la care acesta se afla. Daca da, am gasit solutia. Daca nu, alegem sa pastram jumatate de interval, dupa
  caz.

  Din Teorema Master avem: T(n) = T(n/2) + O(1) => complexitatea este O(log n)
*/

import java.io.*;
import java.util.*;

public class var1p1 {
  public static void main(String[] args) {
    Scanner scanner = null;
    try {
        scanner = new Scanner(new File("var1p1.in"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    int n = scanner.nextInt();
    int[] numbers = new int[n];

    for(int i = 0; i < n; ++i) {
      int x = scanner.nextInt();
      numbers[i] = x;
    }

    int left = 0, right = n - 1, mid, sol = -1;
    while(left <= right) {
      mid = (left + right) / 2;
      if(numbers[mid] == mid) {
        sol = mid;
        break;
      }

      if(numbers[mid] < mid) {
        left = mid + 1;
      }
      else {
        right = mid - 1;
      }
    }

    System.out.println(sol);
  }
}
