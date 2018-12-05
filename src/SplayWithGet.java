public class SplayWithGet<E extends Comparable<? super E>>
        extends BinarySearchTree<E>
        implements CollectionWithGet<E> {

    public SplayWithGet() {
        super();
    }

    protected Entry find(E elem, Entry t){
        if ( t == null )
            return null;
        else {
            int jfr = elem.compareTo( t.element );
            if ( jfr  < 0 )
                if (t.left == (null)) {
                    return t;
                }else {
                    return find(elem, t.left);
                }
            else if ( jfr > 0 )
                if(t.right == (null)){
                    return t;
                }else {
                    return find(elem, t.right);
                }
            else
                return t;
        }
    }

    public E get(E e) {
        Entry t = find(e, root);
        if (t != null) {
            splay(t);
            return t.element != e ? null : t.element;
        }
        return null;
    }  // get



    private boolean splay(Entry t){
        while (!t.equals(root) ){
            if (t.parent.equals(null)){
                t = root;
            }else {
                if (t.parent.equals(root) || !t.parent.parent.equals( root)) {
                    if (t.equals( t.parent.left)) {
                        zig(t.parent);
                        t = t.parent;
                    } else {
                        zag(t.parent);
                        t = t.parent;
                    }
                } else if (t.parent.parent .equals( root)) {
                    if (t.parent.parent.left != null && t.equals(t.parent.parent.left.left)) {
                        zigzig(t.parent.parent);
                        t = t.parent.parent;
                    } else if (t.parent.parent.left != null && t.equals(t.parent.parent.left.right)) {
                        zigzag(t.parent.parent);
                        t = t.parent;
                    } else if (t.parent.parent.right != null && t.equals( t.parent.parent.right.right)) {
                        zagzag(t.parent.parent);
                        t = t.parent.parent;
                    } else if (t.parent.parent.right != null && t.equals( t.parent.parent.right.left )) {
                        zagzig(t.parent.parent);
                        t = t.parent;
                    }
                }
            }
        }
        return true;
    }


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


        Entry p = x.right;
        x.right = x.left;
        x.left = z.left;
        if (x.left != null) {
            x.left.parent = x;
        }
        z.left = y.right;
        if (z.left != null) {
            z.left.parent = z;
        }
        y.right = y.left;
        y.left = z.right;
        if (y.left != null) {
            y.left.parent = y;
        }
        z.right = p;
        if (z.right != null) {
            z.right.parent = z;
        }
    } // zigzig


    private void zagzag(Entry x) {
        Entry y = x.right,
                z = x.right.right;
        E e = x.element;
        x.element = z.element;
        z.element = e;


        Entry p = x.left;
        x.left = x.right;
        x.right = z.right;
        if (x.right != null) {
            x.right.parent = x;
        }
        z.right = y.left;
        if (z.right != null) {
            z.right.parent = z;
        }
        y.left = y.right;
        y.right = z.left;
        if (y.right != null) {
            y.right.parent = y;
        }
        z.left = p;
        if (z.left != null) {
            z.left.parent = z;
        }
    } // zagzag

}
