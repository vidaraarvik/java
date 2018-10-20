package kap17;

public class DuplicateItemException extends Exception {
    public DuplicateItemException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "DuplicateItem: " + this.getMessage();
    }
}
