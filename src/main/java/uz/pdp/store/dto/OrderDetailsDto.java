package uz.pdp.store.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class OrderDetailsDto {

    private Integer id;

    @NotNull(message = "Must not be null !!!")
    private Double total;

    private UserDto user;

    @NotEmpty(message = "Must not be empty !!!")
    private List<OrderItemsDto> orderItemsList;

    private Date createdAt;

    private Date modifiedAt;

}
