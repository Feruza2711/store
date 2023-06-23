package uz.pdp.store.dto;

import jakarta.validation.constraints.NotBlank;
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
public class DiscountDto {
    private Integer id;

    @NotBlank(message = "Must not be blank !!!")
    private String name;

    @NotBlank(message = "Must not be blank !!!")
    private String description;

    @NotNull(message = "Must not be null !!!")
    private Double discountPercent;

    private List<ProductDto> products;

    private Date createdAt;

    private Date modifiedAt;
}
