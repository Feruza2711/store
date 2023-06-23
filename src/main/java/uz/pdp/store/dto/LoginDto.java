package uz.pdp.store.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class LoginDto {

    @NotBlank(message = "Must not be blank !!!")
    private String email;
    @NotBlank(message = "Must not be blank !!!")
    private String password;

}
