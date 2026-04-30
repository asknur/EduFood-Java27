package askar.edufoodjava27.service;

import askar.edufoodjava27.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RestaurantService {
    Page<Restaurant> findAll(String query, Pageable pageable);
    Restaurant findById(Integer id);
}
