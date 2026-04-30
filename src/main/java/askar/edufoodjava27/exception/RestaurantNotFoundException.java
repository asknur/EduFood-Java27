package askar.edufoodjava27.exception;

public class RestaurantNotFoundException extends NotFoundException {
    public RestaurantNotFoundException() {
        super("Restaurant");
    }
}
