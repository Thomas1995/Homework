import java.io.*;
import java.util.Scanner;

public class problema4 {

  private static class BinarySearchTree<K extends Comparable<K>, V> {
    private class Node<K, V> {
      K key;
      V value;
      Node<K,V> left, right;

      public Node(K k, V v) {
        key = k;
        value = v;
        left = right = null;
      }
    }

    Node<K,V> root;

    public BinarySearchTree() {
      root = null;
    }

    public boolean add(K key, V val) {
      Node<K,V> node = root, ant = null;

      while(node != null) {
        if(node.key.compareTo(key) == 0)
          return false;

        if(node.key.compareTo(key) > 0) {
          ant = node;
          node = node.left;
        }
        else {
          ant = node;
          node = node.right;
        }
      }

      if(ant == null) {
        root = new Node<K,V>(key, val);
      }
      else {
        if(ant.key.compareTo(key) > 0)
          ant.left = new Node<K,V>(key, val);
        else
          ant.right = new Node<K,V>(key, val);
      }

      return true;
    }

    private String inorder(Node<K,V> node) {
      if(node == null)
        return "";

      return inorder(node.left) + node.value + " " + inorder(node.right);
    }

    @Override
    public String toString() {
      return inorder(root);
    }
  }

  private static class CompClass {
    int a, b;

    public CompClass(int x, int y) {
      a = x;
      b = y;
    }
  }

  private static class CompClass2 extends CompClass implements Comparable<CompClass2> {
    public CompClass2(int x, int y) {
      super(x, y);
    }

    public int compareTo(CompClass2 c) {
      return (a + b) - (c.a + c.b);
    }
  }

  public static void main(String[] args) {
    BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();

    Scanner scanner = null;
    try {
      scanner = new Scanner(new File("problema4f"));
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    while(scanner.hasNextInt()) {
      int i = scanner.nextInt();
      bst.add(i, i);
    }

    System.out.println(bst.toString());

    BinarySearchTree<CompClass2, Integer> bst2 = new BinarySearchTree<CompClass2, Integer>();

    for(int i = 0; i < 10; ++i) {
      for(int j = 3; j >= 0; --j) {
        bst2.add(new CompClass2(i, j), i + j);
      }
    }

    System.out.println(bst2.toString());
  }
}
