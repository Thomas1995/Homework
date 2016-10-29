import java.util.LinkedList;

public class problema2 {

  private static class GraphNode<T> {
    public T value;
    public LinkedList< GraphNode<T> > neighbors;

    public GraphNode(T o) {
      value = o;
      neighbors = new LinkedList< GraphNode<T> >();
    }

    public void connectTo(GraphNode<T> other) {
      this.neighbors.add(other);
      other.neighbors.add(this);
    }
  }

  public static void main(String[] args) {
    // create a simple graph
    GraphNode<Integer> node1 = new GraphNode<Integer>(1);
    GraphNode<Integer> node2 = new GraphNode<Integer>(2);
    GraphNode<Integer> node3 = new GraphNode<Integer>(3);
    GraphNode<Integer> node4 = new GraphNode<Integer>(4);
    GraphNode<Integer> node5 = new GraphNode<Integer>(5);

    // create some edges
    node1.connectTo(node2);
    node1.connectTo(node4);
    node2.connectTo(node5);
    node4.connectTo(node5);
    node5.connectTo(node3);
  }
}
