package uz.pdp.store.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.store.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    Card findByUserId(Integer userId);

}
