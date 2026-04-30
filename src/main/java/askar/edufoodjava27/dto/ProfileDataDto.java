package askar.edufoodjava27.dto;

import askar.edufoodjava27.model.Order;
import askar.edufoodjava27.model.User;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProfileDataDto {
    private User user;
    private List<Order> orders;
}
