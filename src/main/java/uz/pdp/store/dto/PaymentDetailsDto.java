package uz.pdp.store.dto;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PaymentDetailsDto {

    private Integer id;

    private Double amount;

    private OrderDetailsDto orderDetails;

    private Boolean status;

    private Date createdAt;
}
