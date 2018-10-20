package kap17;

public class ItemNotFoundException extends Exception {
    public ItemNotFoundException() {
    }

    @Override
    public String toString() {
        return "ItemNotFound";
    }
}
