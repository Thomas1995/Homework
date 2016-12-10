import java.util.Iterator;

public class problema3 {
  static private class Queue<T> implements Iterable<T> {
    static private class Node<T> {
      T value;
      Node<T> next;

      public Node(T val) {
        next = null;
        value = val;
      }
    }

    private Node<T> firstNode, lastNode;
    int queueSize;

    public Queue() {
      lastNode = firstNode = null;
      queueSize = 0;
    }

    @Override
    public Iterator<T> iterator() {
      Iterator<T> it = new Iterator<T>() {
        private Node<T> crtNode = firstNode;

        @Override
        public boolean hasNext() {
          return crtNode.next != null;
        }

        @Override
        public T next() {
          crtNode = crtNode.next;
          return crtNode.value;
        }

        @Override
        public void remove() {
          throw new UnsupportedOperationException();
        }
      };
      return it;
    }

    public boolean isEmpty() {
      return queueSize == 0;
    }

    public T get() {
      T ret = firstNode.value;
      firstNode = firstNode.next;
      --queueSize;
      return ret;
    }

    public void put(T o) {
      Node<T> node = new Node<T>(o);
      if(queueSize == 0) {
        firstNode = lastNode = node;
      }
      else {
        lastNode.next = node;
        lastNode = node;
      }
      ++queueSize;
    }

    public T peek() {
      return firstNode.value;
    }

    public int size() {
      return queueSize;
    }
  }

  public static void main(String[] args) {
    Queue<Integer> Q = new Queue<Integer>();
    for(int i = 1; i <= 10; ++i) {
      Q.put(i);
    }
    for(int i = 1; i <= 3; ++i) {
      Q.get();
    }

    Iterator it = Q.iterator();
    while(it.hasNext()) {
      System.out.print(it.next() + " ");
    }
    System.out.println();

    Queue<Character> Q2 = new Queue<Character>();
  }
}
