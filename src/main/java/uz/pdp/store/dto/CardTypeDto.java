package uz.pdp.store.dto;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CardTypeDto {
    private Integer id;

    private String name;
}
