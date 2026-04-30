package askar.edufoodjava27.repository;

import askar.edufoodjava27.model.Order;
import askar.edufoodjava27.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUserIdOrderByOrderDateDesc(Integer id);
}
