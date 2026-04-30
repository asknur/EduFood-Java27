package askar.edufoodjava27.controller;

import askar.edufoodjava27.model.User;
import askar.edufoodjava27.service.OrderService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("create")
    public String createOrder(@AuthenticationPrincipal User user,
                              @CookieValue(name = "cart-cookie", required = false) String cartCookie,
                              HttpServletResponse response,
                              RedirectAttributes redirectAttributes) {

        orderService.create(user, cartCookie);

        Cookie cookie = new Cookie("cart-cookie", "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        redirectAttributes.addFlashAttribute("successMessage", "Ваш заказ успешно оформлен!");

        return "redirect:/profile";
    }
}
