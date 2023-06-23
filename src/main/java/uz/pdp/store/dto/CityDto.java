package uz.pdp.store.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CityDto {
    private Integer id;

    @NotBlank(message = "Must not be blank !!!")
    private String name;
}
