/*
  Initial vom porni cu intervalul intreg. Retinem atat intervalul din parcurgerea inordine, cat si radacina. Radacina se va afla
  mereu pe ultima pozitie in postordine.

  Pentru intervalul curent, folosind un vector cu toate pozitiile numereleor in vectorul parcugerii inordine, gasim in O(1)
  pozitia radacinii. Apoi repetam acest proces pentru intervalul din stanga radacinii si pentru cel din dreapta. In postordine,
  radacina intervalului drept se va afla la pozitia anterioara - 1, iar pentru cel stang la pozitia anterioara - 1 - lungimea
  intervalului drept.

  Prin urmare, pentru a construi arborele in memorie, complexitatea timpului va fi O(n), intrucat alegem cate un nod pe rand in
  O(1) in n pasi.

  Pentru calcularea parcugerii preordine (daca arborele este valid) vom face o simpla parcugere in O(n).

  Astfel, complexitatea totala a algoritmului va fi O(n).
*/

import java.io.*;
import java.util.*;

public class var1p2 {
  static int[] postord;
  static int[] inord;
  static int[] preord;
  static int[] posin;
  static boolean treeIsValid = true;
  static int index = 0;

  private static class Node {
    int val;
    Node left, right;

    public Node(int x) {
      val = x;
      left = null;
      right = null;
    }
  }

  private static Node solve(int left, int right, int pos) {
    if(left > right)
      return null;

    int root = postord[pos];

    // check if the root is in the interval
    if(posin[root - 1] >= left && posin[root - 1] <= right) {
      Node node = new Node(root);

      if(left < right) {
        // divide in two intervals depending on the root position
        node.left = solve(left, posin[root - 1] - 1, pos - right + posin[root - 1] - 1);
        node.right = solve(posin[root - 1] + 1, right, pos - 1);
      }

      return node;
    }
    else {
      treeIsValid = false;
      return null;
    }
  }

  private static void pretraversal(Node node) {
    if(node == null)
      return;

    preord[index++] = node.val;
    pretraversal(node.left);
    pretraversal(node.right);
  }

  public static void main(String[] args) {
    Scanner scanner = null;
    try {
        scanner = new Scanner(new File("var1p2.in"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    int n = scanner.nextInt();

    postord = new int[n];
    inord = new int[n];
    preord = new int[n];
    posin = new int[n];

    for(int i = 0; i < n; ++i) {
      int x = scanner.nextInt();
      postord[i] = x;
    }

    for(int i = 0; i < n; ++i) {
      int x = scanner.nextInt();
      inord[i] = x;
      posin[x - 1] = i;
    }

    Node root = solve(0, n - 1, n - 1);

    if(treeIsValid) {
      pretraversal(root);

      for(int i = 0; i < n; ++i)
        System.out.print(preord[i] + " ");
      System.out.println();

      for(int i = 0; i < n; ++i)
        System.out.print(inord[i] + " ");
      System.out.println();

      for(int i = 0; i < n; ++i)
        System.out.print(postord[i] + " ");
      System.out.println();
    }
    else {
      System.out.println("nu");
    }
  }
}
