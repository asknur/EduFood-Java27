package askar.edufoodjava27.controller;

import askar.edufoodjava27.model.Order;
import askar.edufoodjava27.model.User;
import askar.edufoodjava27.service.OrderService;
import askar.edufoodjava27.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final OrderService orderService;

    @GetMapping("profile")
    public String showProfilePage(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        if (currentUser == null) {
            return "redirect:/login";
        }
        User user = userService.findByEmail(currentUser.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("orders", orderService.findByUser(user));

        return "auth/profile";
    }

    @GetMapping("order")
    public String orders(@AuthenticationPrincipal User user, Model model) {
        List<Order> orders = orderService.getUserOrders(user.getId());
        BigDecimal total = orderService.getTotal(orders);

        model.addAttribute("orders", orders);
        model.addAttribute("total", total);
        return "auth/profile";
    }
}
