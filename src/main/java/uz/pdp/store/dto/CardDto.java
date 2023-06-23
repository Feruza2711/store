package uz.pdp.store.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CardDto {
    private Integer id;

    @NotBlank(message = "Must not be null !!!")
    private String ownerName;

    @NotBlank(message = "Must not be null !!!")
    @Pattern(regexp = "^\\d{4} \\d{4} \\d{4} \\d{4}", message = "Must have four four-digit numbers separated by a space !!!")
    private String cardNumber;

    @NotBlank(message = "Must not be null !!!")
    @Pattern(regexp = "^([1-9]|1[012])/2[34567]$")
    private String validity;

    @NotNull(message = "Must not be null !!!")
    @Min(value = 0, message = "Must at least 0")
    private Double amount;

    @NotNull(message = "Must not be null !!!")
    @Max(value = 999, message = "Maximum 999")
    @Min(value = 100, message = "Minimum 100")
    private Integer cvc;

    private UserDto user;

    @NotNull(message = "Must not be null !!!")
    private CardTypeDto cardType;

}
