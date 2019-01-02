import static java.lang.System.out;

/**
 * This is a single linked list
 *
 * @param <E> parameter E is the element type of the list.
 * @author Jonathan Carbol
 * @author Maria Fornmark
 * @version 2018.01
 */
public class SLCWithGet<E extends Comparable<? super E>>
        extends LinkedCollection<E>
        implements CollectionWithGet<E> {

    /**
     * Constructor for for objects of class BSTwithGet
     */
    public SLCWithGet() {
        super();
    }

    /**
     * Find the first occurence of an element
     * in the collection that is equal to the argument
     * <tt>e</tt> with respect to its natural order.
     *
     * @param element
     * @return The element found is returned.
     * If not found, return null.
     */
    @Override
    public E get(E element) {
        Entry t = head;
        while (t != null) {
            if (t.element.compareTo(element) == 0) {
                return t.element;
            } else {
                t = t.next;
            }
        }
        return null;
    }
    /**
     * Adding an element into the list.
     * The list is traversed until right place is found.
     *
     * @param element the object to add into the list
     * @return true if successful insertion.
     */
    @Override
    public boolean add(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (head == null) {
            head = new Entry(element, null);
        } else {
            Entry t = head;
            Entry a = null;

            while (t != null && t.element.compareTo(element) > 0) {
                a = t;
                t = t.next;
            }
            Entry e = new Entry(element, t);
            if (a == null) {
                head = e;
            } else {
                a.next = e;
            }
        }
        return true;
    }
}


