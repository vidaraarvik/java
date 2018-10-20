package kap17;

/**
 * Klasse som representerer en Binærnode.
 * @author Vidar Årvik
 */
class BinaryNode<E> {

    E element;
    BinaryNode parent;
    BinaryNode left;
    BinaryNode right;
    int x;
    int y;
    int height;

    static int travNr = 1;

    /**
     * Konstruktør
     * @param element elementet noden representerer
     */
    public BinaryNode(E element) {
        this.element = element;
        left = right = null;
    }

    /**
     * Metode som regner ut høyden til en node (fra nederste blad opp til noden)
     * @param t noden som skal regnes ut
     * @return høyde på node t
     */
    public static int height(BinaryNode t) {
        if (t == null) {
            return -1;
        } else {
            return 1 + Math.max(height(t.left), height(t.right));
        }
    }

    /**
     * Metode som regner ut dybden til en node (fra tre-rot ned til noden)
     * @param t noden som skal regnes ut
     * @return dybden til noden
     */
    public static int depth(BinaryNode t) {
        if (t == null) {
            return -1;
        } else {
            return 1 + depth(t.parent);
        }
    }

    /**
     * Metode som finner forelder til enhver node under node t
     * @param t noden som det skal finnes forelder til
     * @param parent forelder til node t
     */
    public void setParents(BinaryNode t, BinaryNode parent) {
        if (t.left != null) {
            t.left.setParents(t.left, t);   // traverse left subtree
        }

        t.parent = parent;

        if (t.right != null) {
            t.right.setParents(t.right, t); // traverse right subtree
        }
    }

    /**
     * Metode som finner X og Y koordinater til alle noder under og inkludert t
     * @param t noden det skal finnes X og Y koordinater til
     */
    public void setXY(BinaryNode t) {
        if (t.left != null) {
            t.left.setXY(t.left);   // traverse left subtree
        }

        // sets XY values for node
        t.x = travNr++;
        t.y = depth(t);

        if (t.right != null) {
            t.right.setXY(t.right); // traverse right subtree
        }
    }

    /**
     * AVL Metode
     * left left case (1)
     * @param k2 noden som er feilbalansert og skal roteres
     * @return ny node
     */
    static BinaryNode rotateWithLeftChild(BinaryNode k2) {
        BinaryNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }


    /**
     * AVL Metode
     * left right case (2)
     * @param k3 noden som er feilbalansert og skal roteres
     * @return ny node
     */
    static BinaryNode doubleRotateWithLeftChild(BinaryNode k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }


    /**
     * AVL Metode
     * right left case (3)
     * @param k1 noden som er feilbalansert og skal roteres
     * @return ny node
     */
    static BinaryNode doubleRotateWithRightChild(BinaryNode k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }


    /**
     * AVL Metode
     * right right case (4)
     * @param k1 noden som er feilbalansert og skal roteres
     * @return ny node
     */
    static BinaryNode rotateWithRightChild(BinaryNode k1) {
        BinaryNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        return k2;
    }


}
