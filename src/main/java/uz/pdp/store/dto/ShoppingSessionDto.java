package uz.pdp.store.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ShoppingSessionDto {

    private Integer id;

    private UserDto user;

    private Double total;

    @NotBlank(message = "This field must not be null!")
    private List<CartItemDto> items;

    private Date createdAt;

    private Date modifiedAt;

}
