import static java.lang.System.out;
public class SLCWithGet<E extends Comparable<? super E>>
        extends LinkedCollection<E>
        implements CollectionWithGet<E> {


    public SLCWithGet() {
        super();
    }

    @Override
    public E get(E element) {
        Entry t = head;
        while (t != null) {
            if (t.element.compareTo(element) == 0) { //Här hade vi t.element == element, och det funkar inte för elements.
                return t.element;
            } else {
                t = t.next;
            }
        }
        return null;
    }


    @Override
    public boolean add(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (head == null) {
            Entry e = new Entry(element, null);
            head = e;
        } else {
            Entry t = head;
            Entry a = null;

            while (t != null && t.element.compareTo(element) > 0 ) {
                a = t;
                t = t.next;
            }
            Entry e = new Entry(element, t);
            if (a == null){
                head = e;
            }else {
                a.next = e;
            }
        }
        //out.println(element.toString());
        return true;
}
}


//Done: Created class SLCWithGet (this class). Class head done by looking at picture
// and comparing to BSTwithGet class.


