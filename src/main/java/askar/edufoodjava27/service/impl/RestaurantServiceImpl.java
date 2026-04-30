package askar.edufoodjava27.service.impl;

import askar.edufoodjava27.exception.RestaurantNotFoundException;
import askar.edufoodjava27.model.Restaurant;
import askar.edufoodjava27.repository.RestaurantRepository;
import askar.edufoodjava27.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Override
    public Page<Restaurant> findAll(String query, Pageable pageable) {
        log.info("findAll {}", query);
        if (query != null && !query.isBlank()) {
            return restaurantRepository.findAllByNameContainingIgnoreCase(query, pageable);
        }
        return restaurantRepository.findAll(pageable);
    }

    @Override
    public Restaurant findById(Integer id) {
        log.info("findById {}", id);
        return restaurantRepository.findById(id)
                .orElseThrow(RestaurantNotFoundException::new);
    }

}
