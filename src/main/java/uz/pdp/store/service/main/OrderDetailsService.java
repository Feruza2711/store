package uz.pdp.store.service.main;



import uz.pdp.store.dto.OrderDetailsDto;
import uz.pdp.store.dto.OrderItemsDto;
import uz.pdp.store.dto.ResponseDto;

import java.util.List;

public interface OrderDetailsService {
    ResponseDto<OrderDetailsDto> addOrderDetails();

    ResponseDto<OrderDetailsDto> updateOrderDetails(OrderDetailsDto orderDetailsDto);

    ResponseDto<OrderDetailsDto> getOrderDetails(Integer id);

    ResponseDto<Boolean> deleteOrderDetails(Integer id);

    ResponseDto<List<OrderItemsDto>> findMostSoldProducts();
}
