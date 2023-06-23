package uz.pdp.store.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.store.model.OrderDetails;

import java.util.Date;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
    OrderDetails findByUserIdAndCreatedAtAfter(Integer userId, Date after);

}
