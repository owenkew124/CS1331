import java.util.Iterator;

/**
 * Interface that represents a list.
 * Keep in mind this is DIFFERENT than the List interface from java.util
 *
 * @author 1331 TAs
 * @version 1.0
 * @param <T> - the generic type of the data to be stored in the list
 */
public interface List<T> extends Iterable<T> {
    /**
     * Inserts a Node with the passed-in data at the passed-in index in this list, if the index is valid.
     *
     * @param index - index at which the data is to be inserted
     * @param data - data to be inserted
     */
    void add(int index, T data);

    /**
     * Appends a Node with the passed-in data to the end of this list.
     *
     * @param data - data to be appended to this list
     */
    void add(T data);

    /**
     * Returns the data at the passed-in index in this list, if the index is valid.
     *
     * @param index - the index of the data to be retreived
     * @return the data at the passed-in index
     */
    T get(int index);

    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements, otherwise false
     */
    boolean isEmpty();

    /**
     * Removes all of the elements from this list.
     */
    void clear();

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    Iterator<T> iterator();
    /**
    public static void main(String[] args) {
        LinkedList<String> l1 = new LinkedList<String>();

        //Test isEmpty()
        System.out.println("Empty?" + l1.isEmpty());

        //Test iterator when empty
        Iterator<String> iter = l1.iterator();
        System.out.println("iter.hasNext()" + iter.hasNext());
        try { //Should throw
            iter.next();
            System.out.println("failed");
        } catch(NoSuchElementException nsee) {
            System.out.println("passed");
        }
        //Test add(index,data)
        l1.add(0,"0");
        l1.print();
        System.out.println("Empty?" + l1.isEmpty());
        l1.add(1,"10");
        l1.print();
        l1.add(2,"20");
        l1.print();
        l1.add(3,"30");
        l1.print();
        l1.add(2,"40");
        l1.print();
        System.out.println("Empty?" + l1.isEmpty());

        //Test add(data)
        l1.add("55");
        l1.print();
        System.out.println("Empty?" + l1.isEmpty());

        //Test get(index)
        try {
            int i = 0;
            while(true) {//iterate until out of bounds
                System.out.printf("l1{%d}: %s\n", i, l1.get(i));
                i++;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Empty?" + l1.isEmpty());
        }

        //Test iterator
        try {
            iter = l1.iterator();
            int i = 0;
            while(iter.hasNext()) {
                System.out.printf("l1(iter %d): %s\n", i++, iter.next());
            }
        } catch (NoSuchElementException nsee) {
            System.out.println("failed");
        }

        // Test clear
        System.out.println("Clearing list...");
        l1.clear();
        l1.print();

        System.out.println("Empty?" + l1.isEmpty());

        //Test add(index,data)
        l1.add(0,"0");
        l1.print();
        System.out.println("Empty?" + l1.isEmpty());
        l1.add(1,"10");
        l1.print();
        l1.add(2,"20");
        l1.print();
        l1.add(3,"30");
        l1.print();
        l1.add(2,"40");
        l1.print();
        System.out.println("Empty?" + l1.isEmpty());

        //Test add(data)
        l1.add("55");
        l1.print();
        System.out.println("Empty?" + l1.isEmpty());


    }
    **/
}
