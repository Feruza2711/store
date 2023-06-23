package uz.pdp.store.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Date;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
@JsonIgnoreProperties(value = {"password", "authorities"}, allowSetters = true)
public class UserDto  {

    private Integer id;

    @NotBlank(message = "Must not be blank !!!")
    @Pattern(regexp = "^\\w{3,40}@gmail\\.com?", message = "Email must contain from 3-30 characters and have <<@>>")
    private String email;

    @NotBlank(message = "Must not be blank !!!")
    private String password;

    @NotBlank(message = "Must not be blank !!!")
    private String lastname;

    @NotBlank(message = "Must not be blank !!!")
    private String firstname;

    @NotBlank(message = "Must not be blank !!!")
    @Pattern(regexp = "^(\\+?\\d{3}[\\s-]|)\\d{2}[\\s-]\\d{3}[\\s-]\\d{2}[\\s-]\\d{2}?",
            message = "Phone number is incorrect !!!")
    private String phoneNumber;

    private List<AddressDto> address;

    private Set<SimpleGrantedAuthority> authorities;

    private List<CardDto> cards;

    private Date createdAt;

    private Date modifiedAt;


}
