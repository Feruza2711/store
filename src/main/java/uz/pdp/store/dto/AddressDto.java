package uz.pdp.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AddressDto {
    private Integer id;

    @NotBlank(message = "Must not be blank !!!")
    private String fullName;

    @NotBlank(message = "Must not be blank !!!")
    @Pattern(regexp = "^(\\+?\\d{3}[\\s-]|)\\d{2}[\\s-]\\d{3}[\\s-]\\d{2}[\\s-]\\d{2}?",
            message = "Phone number is incorrect!")
    private String phoneNumber;

    @NotNull(message = "Must not be null !!!")
    private CityDto city;

    @NotBlank(message = "Must not be blank !!!")
    private String region;

    @NotBlank(message = "Must not be blank !!!")
    private String street;

    @NotNull(message = "Must not be null !!!")
    private Integer homeNumber;

    private String landMark;

    private Date createdAt;

    private Date modifiedAt;
}
