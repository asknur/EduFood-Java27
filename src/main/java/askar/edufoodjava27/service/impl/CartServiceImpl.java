package askar.edufoodjava27.service.impl;

import askar.edufoodjava27.dto.CartItemDto;
import askar.edufoodjava27.model.Dish;
import askar.edufoodjava27.repository.DishRepository;
import askar.edufoodjava27.service.CartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {
    private final DishRepository dishRepository;
    private final ObjectMapper objectMapper;

    @Override
    public Integer getItemCount(String cartCookie) {
        log.info("getting item count for cart cookie {}", cartCookie);
        Map<Integer, Integer> cartMap = getCartMapFromCookie(cartCookie);
        if (cartMap.isEmpty()) {
            return 0;
        }
        return cartMap.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public String addDish(String cartCookie, Integer dishId) {
        log.info("adding dish to cart cookie {}", cartCookie);
        Map<Integer, Integer> cartMap = getCartMapFromCookie(cartCookie);
        cartMap.put(dishId, cartMap.getOrDefault(dishId, 0) + 1);
        return convertCartMapToCookie(cartMap);
    }

    @Override
    public String removeDish(String cartCookie, Integer dishId) {
        log.info("removing dish from cart cookie {}", cartCookie);
        Map<Integer, Integer> cartMap = getCartMapFromCookie(cartCookie);
        cartMap.remove(dishId);
        return convertCartMapToCookie(cartMap);
    }

    @Override
    public List<CartItemDto> getItems(String cartCookie) {
        log.info("getting items from cart cookie {}", cartCookie);
        Map<Integer, Integer> cartMap = getCartMapFromCookie(cartCookie);
        if (cartMap.isEmpty()) {
            return new ArrayList<>();
        }

        List<Integer> dishIds = new ArrayList<>(cartMap.keySet());
        log.info("getting items from cart cookie {}", cartCookie);
        List<Dish> dishes = dishRepository.findAllById(dishIds);

        return dishes.stream()
                .map(dish -> new CartItemDto(dish, cartMap.get(dish.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal calculateTotalAmount(List<CartItemDto> cartItems) {
        log.info("calculating total amount for cart items {}", cartItems);
        return cartItems.stream()
                .map(item -> item.getDish().getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Map<Integer, Integer> getCartMapFromCookie(String cartCookie) {
        log.info("getting cart map from cart cookie {}", cartCookie);
        if (!StringUtils.hasText(cartCookie)) {
            return new HashMap<>();
        }
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(cartCookie);
            String json = new String(decodedBytes);
            return objectMapper.readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    private String convertCartMapToCookie(Map<Integer, Integer> cartMap) {
        try {
            String json = objectMapper.writeValueAsString(cartMap);
            return Base64.getEncoder().encodeToString(json.getBytes());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

}
