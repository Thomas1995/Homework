public class problema5 {

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

    private boolean checkEq(Node<K,V> node1, Node<K,V> node2) {
      if(node1 == null && node2 == null)
        return true;

      return node1.key.compareTo(node2.key) == 0 &&
       checkEq(node1.left, node2.left) && checkEq(node1.right, node2.right);
    }

    @Override
    public boolean equals(Object other) {
      BinarySearchTree<K,V> otherBst = (BinarySearchTree<K,V>)other;
      return checkEq(root, otherBst.root);
    }
  }

  public static void main(String[] args) {
    BinarySearchTree<Integer, Integer> bstInt1 = new BinarySearchTree<Integer, Integer>();
    BinarySearchTree<Integer, Integer> bstInt2 = new BinarySearchTree<Integer, Integer>();
    BinarySearchTree<Character, Character> bstChar1 = new BinarySearchTree<Character, Character>();
    BinarySearchTree<Character, Character> bstChar2 = new BinarySearchTree<Character, Character>();

    for(int i = 0; i < 10; ++i) {
      bstInt1.add(i, i);
      bstInt2.add(10 - i, 10 - i);
    }

    for(char c = 'a'; c < 'k'; ++c) {
      bstChar1.add(c, c);
      bstChar2.add(c, c);
    }

    System.out.println(bstInt1.equals(bstInt2));
    System.out.println(bstChar1.equals(bstChar2));
  }
}
