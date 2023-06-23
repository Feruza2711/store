package uz.pdp.store.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmationTokenDto {
    private Integer id;

    private LocalDateTime createdAt;

    private String token;

    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    private UserDto user;

}
