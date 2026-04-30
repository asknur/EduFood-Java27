package askar.edufoodjava27.exception;

public class CartItemNotFoundException extends NotFoundException {
    public CartItemNotFoundException() {
        super("Cart item not found");
    }
}
