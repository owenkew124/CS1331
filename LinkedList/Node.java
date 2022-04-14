/**
 * Represents an element in a Linked List with data
 * and a next node.
 * @author okew3
 * @version 11.0.9.1
 * @param <T> Takes in a generic type
 */
public class Node<T> {
    private T data;
    private Node next;

    /**
     * Creates a node with data and a next node.
     * @param data the data inside the node
     * @param next the next node
     */
    public Node(T data, Node next) {
        this.data = data;
        this.next = next;
    }
    /**
     * Creates a node with data and a next node of null.
     * @param data the data inside the node
     */
    public Node(T data) {
        this(data, null);
    }

    /**
     * @return the data of the node
     */
    public T getData() {
        return data;
    }

    /**
     * @return the node after this node
     */
    public Node getNext() {
        return next;
    }

    /**
     * Sets the data of the node.
     * @param data the data to be set as the data of the node
     */
    public void setData(T data) {
        this.data = data;
    }
    /**
     * Sets the next node of the node.
     * @param next the next node to be set as
     *             the next node of the node
     */
    public void setNext(Node next) {
        this.next = next;
    }
}