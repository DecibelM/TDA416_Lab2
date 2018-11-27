public class SLCWithGet<E extends Comparable<? super E>>
        extends LinkedCollection<E>
        implements CollectionWithGet<E> {



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
    public boolean add(E e){



        return true;
    }
}




//Done: Created class SLCWithGet (this class). Class head done by looking at picture
// and comparing to BSTwithGet class.

//TODO: Write method add, LinkedCollection is not sorted. Least element should be first.
//TODO: How to know what element is least?
//TODO: Write method get.
