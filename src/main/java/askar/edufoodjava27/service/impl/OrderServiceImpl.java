package askar.edufoodjava27.service.impl;

import askar.edufoodjava27.dto.CartItemDto;
import askar.edufoodjava27.model.Order;
import askar.edufoodjava27.model.OrderItem;
import askar.edufoodjava27.model.User;
import askar.edufoodjava27.repository.OrderRepository;
import askar.edufoodjava27.service.CartService;
import askar.edufoodjava27.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;

    @Transactional
    @Override
    public void create(User user, String cartCookie) {
        log.info("create order cookie {}", cartCookie);
        List<CartItemDto> cartItems = cartService.getItems(cartCookie);
        BigDecimal totalPrice = cartService.calculateTotalAmount(cartItems);

        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(totalPrice);

        List<OrderItem> orderItems = cartItems.stream().map(cartItem -> {
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setDish(cartItem.getDish());
            item.setQuantity(cartItem.getQuantity());
            return item;
        }).collect(Collectors.toList());

        order.setOrderItems(orderItems);

        orderRepository.save(order);
    }

    @Override
    public List<Order> findByUser(User user) {
        log.info("findByUser {}", user);
        return orderRepository.findByUserIdOrderByOrderDateDesc(user.getId());
    }

    @Override
    public List<Order> getUserOrders(Integer userId) {
        log.info("getUserOrders {}", userId);
        return orderRepository.findByUserIdOrderByOrderDateDesc(userId);
    }

    @Override
    public BigDecimal getTotal(List<Order> orders) {
        log.info("getTotal {}", orders);
        BigDecimal total = BigDecimal.ZERO;
        for (Order order : orders) {
            total = total.add(order.getTotalPrice());
        }
        return total;
    }


}
