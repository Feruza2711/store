package uz.pdp.store.service.main;




import uz.pdp.store.dto.OrderItemsDto;

import java.util.List;

public interface OrderItemsService{
    void addOrderItems(OrderItemsDto orderitemsDto);

    void deleteByOrderDetailsId(Integer orderDetailsId);

    List<OrderItemsDto> findMostSoldProducts();
}
