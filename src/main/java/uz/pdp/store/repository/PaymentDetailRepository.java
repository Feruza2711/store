package uz.pdp.store.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.store.model.PaymentDetails;

@Repository
public interface PaymentDetailRepository extends JpaRepository<PaymentDetails, Integer> {
    void deleteByOrderDetailsId(Integer orderDetailsId);
}
