package uz.pdp.store.redis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import uz.pdp.store.dto.UserDto;


@RedisHash(timeToLive = 60 * 60 * 24 * 100)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSession {
    @Id
    private Integer id;
    private UserDto userDto;
}
