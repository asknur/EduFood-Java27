package askar.edufoodjava27.service;

import askar.edufoodjava27.dto.CartItemDto;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {
    Integer getItemCount(String cartCookie);

    String addDish(String cartCookie, Integer dishId);

    String removeDish(String cartCookie, Integer dishId);

    List<CartItemDto> getItems(String cartCookie);

    BigDecimal calculateTotalAmount(List<CartItemDto> cartItems);
}
