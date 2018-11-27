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
            if (t.element == element) {
                return t.element;
            } else if (t != element) {
                t = t.next;
            }
        }
        return null;
    }


    @Override
    public boolean add(E element){
        Entry t = head;
        Entry a = null;
        if (element == null) {
            throw new NullPointerException();
        }else {
            while (t.element.compareTo(element) < 0 && t != null){
                a = t;
                t = t.next;
            }
            Entry e =new Entry(element, t);

            if(t == head){
                head = e;
            }else {
                a.next = e;
            }
        }
        return true;
    }
}




//Done: Created class SLCWithGet (this class). Class head done by looking at picture
// and comparing to BSTwithGet class.


