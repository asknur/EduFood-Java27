package askar.edufoodjava27.exception;

public class OrderNotFoundException extends NotFoundException {
    public OrderNotFoundException() {
        super("Order not found");
    }
}
