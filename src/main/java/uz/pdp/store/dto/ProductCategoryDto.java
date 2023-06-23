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
public class ProductCategoryDto {
    private Integer id;

    @NotBlank(message = "Must not be null and blank !!!")
    private String name;

    private Integer parentCategory;

    @NotBlank(message = "Must not be null and blank !!!")
    private String description;

    private List<ProductDto> products;

    @NotNull(message = "Must not be null !!!")
    private UnitDto unit;

    private Date createdAt;

    private Date modifiedAt;
}
