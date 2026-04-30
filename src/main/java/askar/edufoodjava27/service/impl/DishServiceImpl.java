package askar.edufoodjava27.service.impl;

import askar.edufoodjava27.model.Dish;
import askar.edufoodjava27.repository.DishRepository;
import askar.edufoodjava27.service.DishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;

    @Override
    public Page<Dish> findByRestaurantId(Integer restaurantId, Pageable pageable) {
        log.info("findByRestaurantId {}", restaurantId);
        return dishRepository.findByRestaurant_Id(restaurantId, pageable);
    }
}
