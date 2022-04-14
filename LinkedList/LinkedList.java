import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents a Linked List that has a head (the front
 * of the list) and a size.
 * @author okew3
 * @version 11.0.9.1
 * @param <T> Takes in a generic type
 */
public class LinkedList<T> implements List<T> {
    private Node head;
    private int size;

    /**
     * Creates a new Linked list with a head of null and size of 0.
     */
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * The Iterator for the LinkedList Class.
     */
    private class LinkedListIterator implements Iterator<T> {
        private Node nextNode;

        /**
         * Creates a LinkedList Iterator with the head of the LinkedList
         * as the next node.
         */
        private LinkedListIterator() {
            this.nextNode = head;
        }

        /**
         * Checks if there is a next node in the list.
         * @return if there is a next node in the list
         */
        public boolean hasNext() {
            return nextNode != null;
        }

        /**
         * Iterates through a list once.
         * @return the data of the next node
         * @throws NoSuchElementException if there is no next node
         */
        public T next() throws NoSuchElementException {
            if (nextNode != null) {
                T next = (T) nextNode.getData();
                this.nextNode = nextNode.getNext();
                return next;
            } else {
                throw new NoSuchElementException();
            }
        }
    }
    /**
     * Inserts a Node with the passed-in data at the passed-in index in this list, if the index is valid.
     *
     * @param index - index at which the data is to be inserted
     * @param data - data to be inserted
     */
    public void add(int index, T data) {
        Node n = head;
        if (data == null) {
            throw new IllegalArgumentException();
        }
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (this.isEmpty()) {
            this.head = new Node(data);
            size++;
        } else {
            int curInd = 0;
            while (n.getNext() != null && curInd < index) {
                n = n.getNext();
                curInd++;
            }
            if (n.getNext() == null) {
                n.setNext(new Node(data));
                size++;
            } else {
                Node next = n.getNext();
                n.setNext(new Node(data, next));
                size++;
            }
        }
    }

    /**
     * Appends a Node with the passed-in data to the end of this list.
     *
     * @param data - data to be appended to this list
     */
    public void add(T data) {
        Node n = head;
        if (data == null) {
            throw new IllegalArgumentException();
        }
        if (this.isEmpty()) {
            this.head = new Node(data);
            size++;
        } else {
            while (n.getNext() != null) {
                n = n.getNext();
            }
            n.setNext(new Node(data));
            size++;
        }
    }

    /**
     * Returns the data at the passed-in index in this list, if the index is valid.
     *
     * @param index - the index of the data to be retreived
     * @return the data at the passed-in index
     */
    public T get(int index) throws IndexOutOfBoundsException {
        Node n = head;
        if (index < size) {

            while (n.getNext() != null && index > 0) {
                n = n.getNext();
                index--;
            }
            return (T) n.getData();
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements, otherwise false
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Creates an iterator for the certain type of LinkedList.
     * @return the iterator for that list
     */
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }
    /**
     * Removes all of the elements from this list.
     */
    public void clear() {
        head.setNext(null);
        head = null;
        size = 0;
    }
    /**
     * @return the size of the LinkedList
     */
    public int getSize() {
        return size;
    }

    /**
     * Prints out a string representation of the linked list.
     */
    public void print() {
        Node<T> node = head;
        System.out.printf("<%d elems: ", size);
        for (int i = 0; i < size; ++i) {
            System.out.printf("%s: ", node.getData());
            node = node.getNext();
        }
        System.out.println(">");
    }
}