package kap17;

/**
 * Klasse som representerer et Binært-søketre.
 * @author Vidar Årvik
 */
public class BinarySearchTree<E extends Comparable> {

    BinaryNode root;

    public BinarySearchTree() {
        root = null;
    }

    /**
     * Public metode som setter inn et element i treet
     * @param element elementet som skal settes innn
     * @throws Exception feilmelding dersom elementet finnes fra før
     */
    public void insert(E element) throws Exception {
        root = insert(element, root);
    }

    /**
     * Public metode som fjerner et element fra treet
     * @param element elementet som skal fjernes
     * @throws Exception feilmelding dersom elementet ikke finnes i treet
     */
    public void remove(E element) throws Exception {
        root = remove(element, root);
    }

    /**
     * Metode som finner X og Y variabler for alle nodene i treet.
     * Finner først foreldrene til hver node, trengs for å finne rett dybde (y-variabel)
     */
    public void setXY() {
        root.setParents(root, null);

        BinaryNode.travNr = 1;
        root.setXY(root);
    }

    protected BinaryNode findMin(BinaryNode t) {
        if (t != null) {
            while (t.left != null) {
                t = t.left;
            }
        }
        return t;
    }


    protected BinaryNode insert(E x, BinaryNode t) throws Exception {
        // 1. Perform recursive insert routine
        if (t == null) {
            t = new BinaryNode(x);
        } else if (x.compareTo(t.element) < 0) {
            t.left = insert(x, t.left);
        } else if (x.compareTo(t.element) > 0) {
            t.right = insert(x, t.right);
        } else {
            throw new DuplicateItemException(x.toString());
        }

        // 2. Update the height of the node
        t.height = BinaryNode.height(t);

        // 3. Get balance factor of the node
        int balance = BinaryNode.height(t.left) - BinaryNode.height(t.right);

        // 4. Check cases:
        // left left case (1)
        if (balance > 1 && x.compareTo(t.left.element) < 0) {
            return BinaryNode.rotateWithLeftChild(t);
        }

        // right right case (4)
        if (balance < -1 && x.compareTo(t.right.element) > 0) {
            return BinaryNode.rotateWithRightChild(t);
        }

        // left right case (2)
        if (balance > 1 && x.compareTo(t.left.element) > 0) {
            return BinaryNode.doubleRotateWithLeftChild(t);
        }

        // right left case (3)
        if (balance < -1 && x.compareTo(t.right.element) < 0) {
            return BinaryNode.doubleRotateWithRightChild(t);
        }

        return t;
    }


    protected BinaryNode removeMin(BinaryNode t) throws Exception {
        if (t == null) {
            throw new ItemNotFoundException();
        } else if (t.left != null) {
            t.left = removeMin(t.left);
            return t;
        } else {
            return t.right;
        }
    }


    protected BinaryNode remove(E x, BinaryNode t) throws Exception {
        if (t == null) {
            throw new ItemNotFoundException();
        } else if (x.compareTo(t.element) < 0) {
            t.left = remove(x, t.left);
        } else if (x.compareTo(t.element) > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = removeMin(t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }

}