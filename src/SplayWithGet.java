public class SplayWithGet<E extends Comparable<? super E>>
        extends BinarySearchTree<E>
        implements CollectionWithGet<E> {

    public SplayWithGet() {
        super();
    }

    public E get(E e) {
        Entry t = find(e, root);
        if (t != null) {
            splay(t);
        }
        return t == null ? null : t.element;
    }  // get

    /*public boolean add(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        if (root == null){
            root = new Entry(e, null);
        } else {
            addInSplay(e, root);
        }
        size++;
        return true;
    }*/

    private boolean splay(Entry t){
        if ( t == null){

        }
        while (t != root ){
            if (t.parent == null){
                t = root;
            }
            if (t.parent.parent != root){
                if (t.parent.left == t){
                    zig(t);
                }else {
                    zag(t);
                }
            }else if (t.parent.parent == root){
                if (t.parent.parent.left.left == t){
                    zigzig(t);
                }else if (t.parent.parent.left.right == t){
                    zigzag(t);
                }else if (t.parent.parent.right.right == t){
                    zagzag(t);
                }else if (t.parent.parent.right.left == t) {
                    zagzig(t);
                }
            }
        }
        return true;
    }

    /*while (x.parent)
    {
        if (!x.parent.parent)
        {
            if (x.parent.left == x)
            {
                right_rotate(x.parent);
            }
            else
            {
                left_rotate(x.parent);
            }
        }
        else if (x.parent.left == x && x.parent.parent.left == x.parent)
        {
            right_rotate(x.parent.parent);
            right_rotate(x.parent);
        }
        else if (x.parent.right == x && x.parent.parent.right == x.parent)
        {
            left_rotate(x.parent.parent);
            left_rotate(x.parent);
        }
        else if (x.parent.left == x && x.parent.parent.right == x.parent)
        {
            right_rotate(x.parent);
            left_rotate(x.parent);
        }
        else
        {
            left_rotate(x.parent);
            right_rotate(x.parent);
        }
    }*/




    /* Rotera 1 steg i hogervarv, dvs
               x'                 y'
              / \                / \
             y'  C   -->        A   x'
            / \                    / \
           A   B                  B   C
     */
    private void zig(Entry x) {
        Entry y = x.left;
        E temp = x.element;
        x.element = y.element;
        y.element = temp;
        x.left = y.left;
        if (x.left != null)
            x.left.parent = x;
        y.left = y.right;
        y.right = x.right;
        if (y.right != null)
            y.right.parent = y;
        x.right = y;
    } //   zig
    // ========== ========== ========== ==========

    /* Rotera 1 steg i vanstervarv, dvs
              x'                 y'
             / \                / \
            A   y'  -->        x'  C
               / \            / \
              B   C          A   B
    */
    private void zag(Entry x) {
        Entry y = x.right;
        E temp = x.element;
        x.element = y.element;
        y.element = temp;
        x.right = y.right;
        if (x.right != null)
            x.right.parent = x;
        y.right = y.left;
        y.left = x.left;
        if (y.left != null)
            y.left.parent = y;
        x.left = y;
    } //   zag
    // ========== ========== ========== ==========

    /* Rotera 2 steg i hogervarv, dvs
              x'                  z'
             / \                /   \
            y'  D   -->        y'    x'
           / \                / \   / \
          A   z'             A   B C   D
             / \
            B   C
    */
    private void zigzag(Entry x) {
        Entry y = x.left,
                z = x.left.right;
        E e = x.element;
        x.element = z.element;
        z.element = e;
        y.right = z.left;
        if (y.right != null)
            y.right.parent = y;
        z.left = z.right;
        z.right = x.right;
        if (z.right != null)
            z.right.parent = z;
        x.right = z;
        z.parent = x;
    }  //  zigzag
    // ========== ========== ========== ==========

    /* Rotera 2 steg i vanstervarv, dvs
               x'                  z'
              / \                /   \
             A   y'   -->       x'    y'
                / \            / \   / \
               z   D          A   B C   D
              / \
             B   C
     */
    private void zagzig(Entry x) {
        Entry y = x.right,
                z = x.right.left;
        E e = x.element;
        x.element = z.element;
        z.element = e;
        y.left = z.right;
        if (y.left != null)
            y.left.parent = y;
        z.right = z.left;
        z.left = x.left;
        if (z.left != null)
            z.left.parent = z;
        x.left = z;
        z.parent = x;
    } //  zagzig


    private void zigzig(Entry x) {
        Entry y = x.left,
                z = x.left.left;
        E e = x.element;
        x.element = z.element;
        z.element = e;


        Entry t = z.right;
        z.right = x.left;
        if (z.right != null) {
            z.right.parent = z;
        }
        y.left = t;
        if (y.left != null) {
            y.left.parent = y;
        }
        t = y.right;
        y.right = x;
        if (y.right != null) {
            z.parent = x.parent;
            y.right.parent = y;
        }
        x.left = t;
        if (x.left != null) {
            x.left.parent = x;
        }

    } // zigzig


    private void zagzag(Entry x) {
        Entry y = x.right,
                z = x.right.right;
        E e = x.element;
        x.element = z.element;
        z.element = e;


        Entry t = z.left;
        z.left = x.right;
        if (z.left != null) {
            z.left.parent = z;
        }
        y.right = t;
        if (y.right != null) {
            y.right.parent = y;
        }
        t = y.left;
        y.left = x;
        if (y.left != null) {
            z.parent = x.parent;
            y.left.parent = y;
        }
        x.right = t;
        if (x.right != null) {
            x.right.parent = x;
        }
    } // zagzag
}
