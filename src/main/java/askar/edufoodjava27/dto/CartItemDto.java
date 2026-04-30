package askar.edufoodjava27.dto;

import askar.edufoodjava27.model.Dish;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartItemDto {
    private Dish dish;
    private Integer quantity;

}
