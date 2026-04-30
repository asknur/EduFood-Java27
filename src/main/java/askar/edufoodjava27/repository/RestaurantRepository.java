package askar.edufoodjava27.repository;

import askar.edufoodjava27.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    Page<Restaurant> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
