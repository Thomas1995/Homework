import java.util.LinkedList;
import java.util.HashSet;
import java.util.ArrayDeque;
import java.util.ListIterator;

public class problema4 {

  private static class BinarySearchTree<K extends Comparable<K>, V> {
    private class Node<K extends Comparable<K>, V> {
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

    @Override
    public String toString() {
      return "";
    }
  }

  public static void main(String[] args) {
    BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();
  }
}
