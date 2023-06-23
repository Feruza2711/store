package uz.pdp.store.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.pdp.store.dto.OrderItemsDto;
import uz.pdp.store.helper.DateUtil;
import uz.pdp.store.mapper.OrderItemMapper;
import uz.pdp.store.model.OrderItems;
import uz.pdp.store.repository.OrderItemsRepository;
import uz.pdp.store.service.main.OrderItemsService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderItemsServiceImpl implements OrderItemsService {

    private final OrderItemsRepository orderItemsRepository;

    @Override
    public void addOrderItems(OrderItemsDto orderitemsDto) {
        try {
            orderitemsDto.setCreatedAt(DateUtil.getCurrentDate());
            OrderItems orderItems = OrderItemMapper.toEntity(orderitemsDto);
            orderItemsRepository.save(orderItems);
        }catch (Exception e){
            log.error("Error while saving order items into database !!!");
        }
    }


    @Override
    public void deleteByOrderDetailsId(Integer orderDetailsId) {
        try {
            orderItemsRepository.deleteByOrderDetailsId(orderDetailsId);
        }catch (Exception e){
            log.error("Error while deleting order items by order details id id !!!");
        }
    }

    @Override
    public List<OrderItemsDto> findMostSoldProducts() {
        return orderItemsRepository.findByOrderByAmountAsc()
                .stream().map(OrderItemMapper::toDto).toList();
    }


}
