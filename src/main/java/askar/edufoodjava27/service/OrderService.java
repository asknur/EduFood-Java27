package askar.edufoodjava27.service;

import askar.edufoodjava27.model.Order;
import askar.edufoodjava27.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    @Transactional
    void create(User user, String cartCookie);

    List<Order> findByUser(User user);

    List<Order> getUserOrders(Integer userId);

    BigDecimal getTotal(List<Order> orders);
}
