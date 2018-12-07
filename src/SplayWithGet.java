/**
 * This is a Splay tree which extends the BST but also includes some extra
 * functions such as find, get and splay.
 *
 * @author Jonathan Carbol & Maria Fornmark
 * @date 2018-12-07
 * @version 2018.01
 */

public class SplayWithGet<E extends Comparable<? super E>>
        extends BinarySearchTree<E>
        implements CollectionWithGet<E> {


    /**
     * The constructor for objects of the class Splay tree.
     * It inherits all of it from BST.
     */
    public SplayWithGet() {
        super();
    }//constructor

    /**
     * The find method is used to find the Entry which has a certain element in it.
     * @param elem The element to find in the tree.
     * @param t Dummy entry to compare to.
     * @return The Entry t which has the same element if found or closest Entry if not found.
     */
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
    }//find

    /**
     * Gets the element from the tree by splaying its Entry or the closest Entry to the root.
     * If the element is found it returns it and if not found, the method returns null.
     * @param e The dummy element to compare to.
     * @return The element of t if e is found in the tree, else null.
     */
    public E get(E e) {
        Entry t = find(e, root);
        if (t != null) {
            splay(t);
            return e != t.element ? null : t.element;
        } else {
            return null;
        }
    }  // get

    /**
     * The splay method is used to splay the entry we are looking for to the root of the tree.
     * @param t Dummy Entry t to compare to.
     * @return true
     */
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

    /**
     * Moves the entries in the tree according to the "zig" movement.
     * Can be compared to right-rotation in a AVL tree.
     * @param x Dummy Entry used to move the entries in the tree.
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

    /**
     * Moves the entries in the tree according to the "zag" movement.
     * Can be compared to left-rotation in a AVL tree.
     * @param x Dummy Entry used to move the entries in the tree.
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


    /**
     * Moves the entries in the tree according to the "zigzag" movement.
     * Can be compared to double right rotation in a AVL tree.
     * @param x Dummy Entry used to move the entries in the tree.
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

    /**
     * Moves the entries in the tree according to the "zagzig" movement.
     * Can be compared to double left rotation in a AVL tree.
     * @param x Dummy Entry used to move the entries in the tree.
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


    /**
     * Moves the entries in the tree according to the "zigzig" movement.
     * @param x Dummy Entry used to move the entries in the tree.
     */
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

    /**
     * Moves the entries in the tree according to the "zagzag" movement.
     * @param x Dummy Entry used to move the entries in the tree.
     */
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
