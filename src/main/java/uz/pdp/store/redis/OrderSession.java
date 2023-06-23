package uz.pdp.store.redis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(timeToLive = 60 * 10)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderSession {
    @Id
    private Integer userId;
    private Integer sessionId;
}
