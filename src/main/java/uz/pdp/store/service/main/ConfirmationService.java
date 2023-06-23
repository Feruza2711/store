package uz.pdp.store.service.main;




import uz.pdp.store.model.ConfirmationToken;
import uz.pdp.store.model.User;

import java.time.LocalDateTime;

public interface ConfirmationService {
    String saveConfirmationToken(User user);

    ConfirmationToken getConfirmationByToken(String token);

    void setConfirmationTime(LocalDateTime date, String token);

    void deleteConfirmationByUserId(Integer userId);
}
