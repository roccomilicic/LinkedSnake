package Question_2;

/**
 *
 * @author Rocco Milicic (21151140)
 * @param <E>
 */
public class LinkedList<E extends Comparable<E>> {

    public int size = 0;
    public Node<E> head;

    public Node getHead() {
        return null;
    }

    public void add(E data) {
        /* Adds a new node at the end of a linked list */
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode; // if linked list is empty, set newNode to the head
        } else {
            add(head, newNode); // start recursive addition from the head
        }
        size++;
    }

    private void add(Node current, Node node) {
        /* Adds a new node at the end of a linked list */
        if (current.next == null) {
            current.next = node; // add node to the end of the list
        } else {
            add(current.next, node); // recurse through the next node
        }
    }

    public void addHead(E data) {
        /* Adds a new node at the head of a linked list */
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode; // if linked list is empty, set newNode to the head
        } else {
            newNode.next = head; // Set the new node's next to the current head
            head = newNode; // Update the head to the new node
        }
        size++;
    }

    public void printLinkedList() {
        /* Prints the elements in a linked list */

        if (head != null) {
            printLinkedList(head);
        }
    }

    private void printLinkedList(Node node) {
        /* Prints the elements in a linked list (calls recursively) */

        if (node != null) {
            System.out.println(node.data);
            printLinkedList(node.next); // recurse through the next node
        }
    }

    public boolean contains(Node node) {
        if (head == null) {
            return false;
        }

        return contains(head, node);
    }

    private boolean contains(Node head, Node node) {
        if (head == null) {
            return false; // Reached the end of the list, target not found
        }

        if (head.compareTo(node) == 0) {
            return true; // Found the target node
        }

        return contains(head.next, node); // Recurse through the next nod
    }

    public void remove(Node node) {
        /* Remove a node from the middle of a linked list  */
        removeFromBody(head, node);
    }

    private void removeFromBody(Node head, Node node) {
        /* Remove a node from the middle of a linked list 
        Will call recursively if current node isnt the node to remove*/

        if (head == null) {
            return;
        }
        if (head.next.compareTo(node) == 0) {
            size--;
            head.next = head.next.next;
        } else {
            removeFromBody(head.next, node);
        }
    }

    public void remove(int position) {
        /* Removes a node from linked list based on index */
        if (position < 0) {
            System.out.println("Invalid index");
            return;
        }

        if (position == 0) {
            head = head.next; // if removing the head node, update the head reference
            size--;
            return;
        }

        if (position >= size) {
            System.out.println("Number larger than snake size, removing last node.");
            removeFromTail();
        }
        
        removeByIndex(head, position - 1); // start removing from the node before the specified index
    }

    private void removeByIndex(Node head, int position) {
        /* Removes a node from linked list based on index 
        Will call recursively till that node is found in list, then removs it*/
        if (head == null || head.next == null) {
            System.out.println("Node not found at the specified index");
            return;
        }

        if (position == 0) {
            head.next = head.next.next; // Update previous node's next reference to skip the node to remove
            size--;
            return;
        }

        removeByIndex(head.next, position - 1); // Recurse through the next node
    }

    public Node removeFromHead() {
        /* Removes first node of a linked list, if it exists */

        if (head == null) {
            return null;
        }

        Node removedNode = head;
        head = head.next;
        size--;

        return removedNode;
    }

    public Node removeFromTail() {
        /* Removes a node from the end of a linked list
        Checks if linked list has 0 or 1 node(s), otherwise call removeFromTail(head) to find end of linked list and remove end node*/

        if (head == null) { // if linked list is empty, theres no node to remove
            return null;
        }

        if (head.next == null) { // if linked list has only one node, the first node gets removed
            Node removedNode = head;
            head = null; // if there's only one node, set head to null
            size--;
            return removedNode;
        }

        return removeFromTail(head); // if there is more than 1 node, call method
    }

    private Node removeFromTail(Node node) {
        /* Removes a node from the end of a linked list if the linked list has more than 1 node
        Will recursively call itself until the at the end of the list and then remove last node*/

        if (node.next.next != null) { // if in middle of the list, call itself
            return removeFromTail(node.next);
        } else { // if at the end of linked list
            Node removedNode = node.next;
            node.next = null; // remove the last node
            size--;
            return removedNode;
        }
    }

    public void addInOrder(E data) {
        /* Adds a new node in ascending order into a linked list */
        Node<E> newNode = new Node<>(data);

        if (head == null || newNode.data.compareTo(head.data) <= 0) {
            newNode.next = head;
            head = newNode;
        } else {
            addInOrder(head, newNode);
        }
        size++;
    }

    private void addInOrder(Node<E> currentNode, Node<E> newNode) {
        /* Adds a new node in ascending order into a linked list */
        if (currentNode.next == null) {
            currentNode.next = newNode;
        } else if (newNode.data.compareTo(currentNode.next.data) <= 0) {
            newNode.next = currentNode.next;
            currentNode.next = newNode;
        } else {
            addInOrder(currentNode.next, newNode);
        }
    }

    public Node getNode(int index) {
        /* Returns current node */
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        return getNode(index, head);
    }

    private Node getNode(int index, Node head) {
        /* Returns current node (calls recursively */
        if (index == 0) {
            return head;  // Base case: reached the desired node
        }

        // Recurse on the next node and decrease the index
        return getNode(index - 1, head.next);
    }

    public E getData(int index) {
        /* Returns the data of the node dependant on index */
        if (getNode(index) == null) {
            return null;
        }

        return getData(index, getNode(index));
    }

    private E getData(int index, Node head) {
        /* Returns the data of the node dependant on index */
        if (head == null) {
            return null; // if linked list is empty, set newNode to the head
        }

        return (E) head.data;
    }

    public int getSize() {
        /* Returns the size */
        return size;
    }
}
