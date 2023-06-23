package uz.pdp.store.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CartItemDto {

    private Integer id;

    private ShoppingSessionDto shoppingSession;

    @NotNull(message = "Must not be null!")
    private ProductDto product;

    @NotNull(message = "Must not be null!")
    private Integer amount;

    private Date createdAt;

    private Date modifiedAt;
}
