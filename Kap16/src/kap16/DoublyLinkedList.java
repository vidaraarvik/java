package kap16;

/**
 * Generisk klasse som representerer en to-veis liste der hver node har en peker til sitt 
 * forrige og neste objekt. Klassen tar vare på gjeldende node "current".
 *
 * @author Vidar Årvik
 * @param <E> type element
 */
public class DoublyLinkedList<E> {

    private Node current;
    private Node head;
    private Node tail;
    private int size;

    /**
     * Konstruktør, initierer initielle noder og flytter current til head.
     */
    public DoublyLinkedList() {
        current = new Node();
        head = new Node();
        tail = new Node();

        head.next = tail;
        tail.prev = head;

        moveToHead();

        this.size = 0;
    }

    /**
     * Klasse som representerer et enkelt node-objekt.
     */
    private class Node {

        E element;
        Node next;
        Node prev;

    }

    /**
     * Metode som flytter current til head.
     */
    public void moveToHead() {
        current = head;
    }

    /**
     * Metode som flytter current til tail.
     */
    public void moveToTail() {
        current = tail;
    }

    /**
     * Metode som flytter current til det først reelle elementet i listen. Dvs objektet etter head.
     *
     * @return første element i listen
     */
    public E first() {
        current = head.next;
        return current.element;
    }

    /**
     * Metode som flytter current til det siste reelle elementet i listen. Dvs objektet før tail.
     *
     * @return siste element i listen
     */
    public E last() {
        current = tail.prev;
        return current.element;
    }

    /**
     * Metode som flytter current til neste element.
     *
     * @return neste element
     */
    public E advance() {
        current = current.next;
        return current.element;
    }

    /**
     * Metode som flytter current til forrige element.
     *
     * @return forrige element
     */
    public E retreat() {
        current = current.prev;
        return current.element;
    }

    /**
     * Metode som setter inn et element før current. Current flyttes til det nye elementet.
     *
     * @param element elementet som skal settes inn i listen
     */
    public void insertBefore(E element) {
        Node newNode = new Node();
        newNode.element = element;

        if (head.next == tail) {    // lista er tom
            newNode.next = tail;
            newNode.prev = head;
            head.next = newNode;
            tail.prev = newNode;
        } else {
            newNode.next = current;
            newNode.prev = current.prev;
            current.prev.next = newNode;
            current.prev = newNode;
        }
        current = newNode;
        size++;
        System.out.println("added bc: " + element);
    }

    /**
     * Metode som setter inn et element etter current. Current flyttes til det nye elementet.
     *
     * @param element elementet som skal settes inn i listen
     */
    public void insertAfter(E element) {
        Node newNode = new Node();
        newNode.element = element;

        if (head.next == tail) {    // lista er tom
            newNode.next = tail;
            newNode.prev = head;
            head.next = newNode;
            tail.prev = newNode;
        } else {
            newNode.next = current.next;
            newNode.prev = current;
            current.next.prev = newNode;
            current.next = newNode;
        }
        current = newNode;
        size++;
        System.out.println("added ac: " + element);
    }

    /**
     * Metode som returnerer current elementet.
     *
     * @return current element
     */
    public E retrieve() {
        return current.element;
    }

    /**
     * Metode som sletter current elementet.
     *
     * @return elementet som ble slettet, null hvis listen er tom
     */
    public E remove() {
        if (head.next == tail) {
            return null;
        } else {
            Node tmp = current;
            current.prev.next = current.next;
            current.next.prev = current.prev;
            retreat();
            size--;
            return tmp.element;
        }
    }

    /**
     * Metode som avgjør om current elementet er et reelt objekt. Dvs, ikke null.
     *
     * @return true hvis reelt objekt, ellers false
     */
    public boolean isValid() {
        return current.element != null;
    }

    /**
     * Metode som returnerer størrelsen på listen.
     *
     * @return antall reelle element i listen
     */
    public int size() {
        return size;
    }

    /**
     * Metode som returnerer en String-representasjon av elementene listen.
     *
     * @return String med alle elementene i listen
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("{");

        first();
        while (isValid()) {
            s.append(current.element.toString() + ";");
            advance();
        }
        s.append("}");
        return s.toString();
    }

    /**
     *  ************TEST METODE*************
     * @param args
     */
    public static void main(String[] args) {
        DoublyLinkedList<Integer> dl = new DoublyLinkedList();

        dl.insertAfter(1);
        dl.insertAfter(2);
        dl.insertAfter(3);
        dl.insertAfter(4);
        dl.insertAfter(5);
        dl.insertAfter(6);
        dl.remove();    // sletter 6
        dl.remove();    // sletter 5
        dl.insertAfter(7);
        dl.insertAfter(8);
        dl.insertAfter(9);
        dl.insertAfter(10);
        dl.insertBefore(11);
        dl.insertBefore(12);
        dl.moveToHead();
        System.out.println();

        Integer i = dl.advance();
        int j = 0;
        while (dl.isValid()) {
            System.out.println("List index: " + j + "    current: " + i);

            i = dl.advance();
            j++;
        }

        System.out.println();
        System.out.println("Første element: " + dl.first());
        System.out.println("Siste element: " + dl.last());

        System.out.println();
        System.out.println(dl.toString());
    }

}
