package uz.pdp.store.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.store.model.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount,Integer> {

}
