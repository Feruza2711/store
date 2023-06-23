package uz.pdp.store.dto;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class OrderItemsDto {

    private Integer id;

    private ProductDto product;

    private Integer amount;

    private OrderDetailsDto orderDetails;

    private Date createdAt;

}
