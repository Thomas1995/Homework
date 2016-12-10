/*
  Initial vom porni cu cei doi vectori in intregime. La fiecare pas il alegem pe cel de lungime mai mica pe care il vom
  injumatatii. Alegerea partilor din vectori pe care le pastram se face in O(1), iar daca vectorul de lungime minima devine nul,
  atunci putem da raspunsul in O(1).

  Din Teorema Master avem: T(min{n,m}) = T(min{n,m} / 2) + O(1) => complexitatea totala va fi O(log min{n,m}).
*/

import java.io.*;
import java.util.*;

public class var1p3 {
  public static int solve(int[] nums1, int l1, int r1, int[] nums2, int l2, int r2, int pos) {
    if(r1 - l1 > r2 - l2) {
      return solve(nums2, l2, r2, nums1, l1, r1, pos);
    }

    if(l1 > r1) {
      return nums2[pos - 1 + l2];
    }

    int len = r1 - l1 + 1 + r2 - l2 + 1;
    if(pos == len) {
      return Math.max(nums1[r1], nums2[r2]);
    }

    int p1 = (l1 + r1) / 2;
    int p2 = pos - (p1 - l1 + 1) + l2;

    if(pos + 1 == len) {
      p1 = r1;
      p2 = r2;
    }

    if(nums1[p1] > nums2[p2]) {
      return solve(nums1, l1, p1 - 1, nums2, p2, r2, pos - p2);
    }
    else {
      return solve(nums1, p1, r1, nums2, l2, p2 - 1, pos - p1);
    }
  }

  public static void main(String[] args) {
    Scanner scanner = null;
    try {
        scanner = new Scanner(new File("var1p3.in"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    int n = scanner.nextInt();
    int[] nums1 = new int[n];
    for(int i = 0; i < n; ++i) {
      nums1[i] = scanner.nextInt();
    }

    int m = scanner.nextInt();
    int[] nums2 = new int[m];
    for(int i = 0; i < m; ++i) {
      nums2[i] = scanner.nextInt();
    }

    double sol = solve(nums1, 0, n - 1, nums2, 0, m - 1, (m + n) / 2 + 1);

    if((m + n) % 2 == 0) {
      sol = (sol + solve(nums1, 0, n - 1, nums2, 0, m - 1, (m + n) / 2)) / 2.0;
    }

    System.out.println(sol);
  }
}
