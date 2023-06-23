package uz.pdp.store.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UnitDto {
    private Integer id;

    @NotBlank(message = "Must not be null and blank !!!")
    private String name;

    @NotBlank(message = "Must not be null and blank !!!")
    private String shortName;

    private Date createdAt;

    private Date modifiedAt;
}
