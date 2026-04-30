package askar.edufoodjava27.controller;

import askar.edufoodjava27.dto.CartItemDto;
import askar.edufoodjava27.exception.DishNotFoundException;
import askar.edufoodjava27.model.Dish;
import askar.edufoodjava27.repository.DishRepository;
import askar.edufoodjava27.service.CartService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final DishRepository dishRepository;

    @GetMapping
    public String showCartPage(@CookieValue(name = "cart-cookie", required = false) String cartCookie,
                               Model model) {
        List<CartItemDto> cartItems = cartService.getItems(cartCookie);
        BigDecimal totalAmount = cartService.calculateTotalAmount(cartItems);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", totalAmount);

        return "cart/cart";
    }

    @PostMapping("/add")
    public String addDishToCart(@RequestParam("dishId") Integer dishId,
                                @CookieValue(name = "cart-cookie", required = false) String cartCookie,
                                HttpServletResponse response) {

        Dish dish = dishRepository.findById(dishId).orElseThrow(DishNotFoundException::new);
        Integer restaurantId = dish.getRestaurant().getId();

        String newCartCookie = cartService.addDish(cartCookie, dishId);

        Cookie cookie = new Cookie("cart-cookie", newCartCookie);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(cookie);

        return "redirect:/restaurant/" + restaurantId;
    }

    @PostMapping("/remove")
    public String removeDishFromCart(@RequestParam("dishId") Integer dishId,
                                     @CookieValue(name = "cart-cookie", required = false) String cartCookie,
                                     HttpServletResponse response) {

        String newCartCookie = cartService.removeDish(cartCookie, dishId);

        Cookie cookie = new Cookie("cart-cookie", newCartCookie);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(cookie);

        return "redirect:/cart";
    }
}
