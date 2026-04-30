package askar.edufoodjava27.service;

import askar.edufoodjava27.model.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DishService {
    Page<Dish> findByRestaurantId(Integer restaurantId, Pageable pageable);
}
