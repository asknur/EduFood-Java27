package askar.edufoodjava27.repository;

import askar.edufoodjava27.model.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {
    Page<Dish> findByRestaurant_Id(Integer restaurantId, Pageable pageable);
}
