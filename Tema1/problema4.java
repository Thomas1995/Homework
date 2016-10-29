import java.util.LinkedList;
import java.util.HashSet;
import java.util.ArrayDeque;
import java.util.ListIterator;

public class problema4 {

  private static class BinarySearchTree<K extends Comparable<K>, V> {
    private class Node<K extends Comparable<K>, V> {
      V value;
      Node<K,V> left, right;

      public Node(V o) {
        value = o;
        left = right = null;
      }
    }

    Node<K,V> root;

    public BinarySearchTree() {
      root = null;
    }

    public boolean add(K key, V val) {
      
    }

    @Override
    public String toString() {
      return "";
    }
  }

  public static void main(String[] args) {
    BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();
  }
}
