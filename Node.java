package Question_2;

/**
 *
 * @author Rocco Milicic (21151140)
 * @param <E>
 */

public class Node<E> {
    E data;
    Node<E> next;
    
    public Node() {
        this.data = null;
        this.next = null;
    }
    
    public Node(E data) {
        this.data = data;
        this.next = null;
    }
    
    public boolean equals(Node<E> otherNode) {
        /* Checks if data in current node holds equal data as another node */
        if (otherNode == null) {
            return false;
        }
        return this.data.equals(otherNode.data);
    }

    public int compareTo(Node<E> otherNode) {
        /* Compares if data in current node to data in another node
        Returns a negative int when current node is less than compared node
        Returns a positive int when current node is greater than compared node
        Returns 0 if both objects are equal */
        
        if (otherNode == null) {
            throw new NullPointerException("Comparing with a null Node");
        }
        
        if (this.data instanceof Comparable && otherNode.data instanceof Comparable) { // checks if both objs are valid datatypes to compare
            Comparable<E> thisDataComparable = (Comparable<E>) this.data;
            return thisDataComparable.compareTo(otherNode.data); // returns compared int
        } else { 
            throw new UnsupportedOperationException("Data type does not support comparison"); 
        }
    }
    
    @Override
    public String toString() {
        return data.toString();
    }

    public static void main(String[] args) {
        Node<Integer> node1 = new Node<>(2);
        Node<Integer> node2 = new Node<>(5);

        System.out.println("Node 1 data: " + node1.data);
        System.out.println("Node 2 data: " + node2.data);

        node1.next = node2;

        Node<Integer> node3 = new Node<>(5);
        System.out.println("Node 3 data: " + node3.data);

        System.out.println("Node 1 equals Node 3: " + node2.equals(node3));
        System.out.println("Node 1 compareTo Node 2: " + node1.compareTo(node2));
    }
}

