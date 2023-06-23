package uz.pdp.store.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.store.model.ShoppingSession;

import java.util.Optional;

@Repository
public interface ShoppingSessionRepository extends JpaRepository<ShoppingSession, Integer> {

    Optional<ShoppingSession> findByUserId(Integer integer);

    void deleteByUserId(Integer integer);

}
