package uz.pdp.store.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.store.model.OrderItems;

import java.util.List;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {
    void deleteByOrderDetailsId(Integer orderDetailsId);

    List<OrderItems> findByOrderByAmountAsc();

}
