package uz.pdp.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProductDto {

    private Integer id;

    @NotBlank(message = "Must not be blank !!!")
    private String name;

    @NotBlank(message = "Must not be blank !!!")
    private String description;

    @NotNull(message = "Must not null !!!")
    private Double price;

    @NotNull(message = "Must not null !!!")
    private Integer amount;

    private DiscountDto discount;

    @NotNull(message = "Must not null !!!")
    private ProductCategoryDto productCategory;

    private Date createdAt;

    private Date modifiedAt;
}
