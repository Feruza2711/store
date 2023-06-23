package uz.pdp.store.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.store.model.ConfirmationToken;

import java.time.LocalDateTime;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {

    ConfirmationToken findByToken(String token);

    @Transactional
    @Modifying
    @Query(value = "update ConfirmationToken c set c.confirmedAt = :date where token = :token")
    void setConfirmedAt(LocalDateTime date, String token);

    @Transactional
    @Modifying
    void deleteByUserId(Integer userId);

}
