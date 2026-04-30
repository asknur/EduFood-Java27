package askar.edufoodjava27.controller;

import askar.edufoodjava27.model.Dish;
import askar.edufoodjava27.model.Restaurant;
import askar.edufoodjava27.service.DishService;
import askar.edufoodjava27.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final DishService dishService;

    @GetMapping()
    public String showHomePage(Model model,
                               @PageableDefault(size = 5, sort = "name") Pageable pageable,
                               @RequestParam(name = "query", required = false) String query) {

        Page<Restaurant> restaurantPage = restaurantService.findAll(query, pageable);

        model.addAttribute("restaurant", restaurantPage.getContent());
        model.addAttribute("totalPages", restaurantPage.getTotalPages());
        model.addAttribute("currentPage", pageable.getPageNumber() + 1);
        model.addAttribute("query", query);

        return "index";
    }

    @GetMapping("restaurant/{id}")
    public String showRestaurantPage(@PathVariable("id") Integer restaurantId,
                                     @PageableDefault(size = 10, sort = "name") Pageable pageable,
                                     Model model) {
        Page<Dish> dishesPage = dishService.findByRestaurantId(restaurantId, pageable);

        model.addAttribute("restaurant", restaurantService.findById(restaurantId));
        model.addAttribute("dishesPage", dishesPage);

        return "restaurant/restaurant";
    }
}
