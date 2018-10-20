package objektsamling;

public class Person implements Element {

    private int pnr;
    private String fornavn;

    public Person(int pnr, String fornavn) {
        this.pnr = pnr;
        this.fornavn = fornavn;
    }

    public int getPnr() {
        return pnr;
    }

    public void setPnr(int pnr) {
        this.pnr = pnr;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (this.pnr != other.pnr) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Person{" + "pnr=" + pnr + ", fornavn=" + fornavn + '}';
    }

    @Override
    public String nøkkel() {
        return Integer.toString(pnr);
    }

}
