package uz.pdp.store.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.store.dto.*;
import uz.pdp.store.helper.AppCode;
import uz.pdp.store.helper.AppMessage;
import uz.pdp.store.helper.DateUtil;
import uz.pdp.store.mapper.OrderDetailsMapper;
import uz.pdp.store.mapper.ShoppingSessionMapper;
import uz.pdp.store.model.OrderDetails;
import uz.pdp.store.model.ShoppingSession;
import uz.pdp.store.redis.OrderSession;
import uz.pdp.store.redis.OrderSessionRepository;
import uz.pdp.store.repository.OrderDetailsRepository;
import uz.pdp.store.service.main.*;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderDetailsServiceImpl implements OrderDetailsService {
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderSessionRepository orderSessionRepository;

    private final ShoppingSessionService shoppingSessionService;

    private final OrderItemsService orderItemsService;
    private final CardService cardService;
    private final PaymentDetailsService paymentDetailsService;

    @Transactional
    @Override
    public ResponseDto<OrderDetailsDto> addOrderDetails() {
        try {
            OrderDetails orderDetails = new OrderDetails();
            ShoppingSessionDto shoppingSessionDto = retriveSession();

            if(shoppingSessionDto == null)
                return ResponseDto.buildResponse(null, AppCode.NOT_FOUND, AppMessage.NOT_FOUND, false);

            CardDto cardDto = cardService.getByUserId(shoppingSessionDto.getUser().getId());

            if(shoppingSessionDto.getTotal() > cardDto.getAmount())
                return ResponseDto.buildResponse(null, AppCode.NOT_ENOUGH, AppMessage.NOT_ENOUGH, false);

            cardDto.setAmount(cardDto.getAmount() - shoppingSessionDto.getTotal());
            cardService.updateCard(cardDto);

            setOrderFields(ShoppingSessionMapper.toEntity(shoppingSessionDto), orderDetails);

            orderDetailsRepository.save(orderDetails);

            OrderDetailsDto orderDetailsDto = OrderDetailsMapper.toDto(orderDetails);
            setOrderItems(shoppingSessionDto, orderDetailsDto);

            setPayment(orderDetailsDto);
            shoppingSessionService.deleteShoppingSession(shoppingSessionDto.getId());
            return ResponseDto.buildResponse(orderDetailsDto, AppCode.OK, AppMessage.OK, true);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.buildResponse(null, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }


    private void setPayment(OrderDetailsDto orderDetailsDto){
        PaymentDetailsDto paymentDetailsDto = PaymentDetailsDto.builder()
                .amount(orderDetailsDto.getTotal())
                .createdAt(DateUtil.getCurrentDate())
                .status(true)
                .orderDetails(orderDetailsDto)
                .build();

        paymentDetailsService.addPaymentDetails(paymentDetailsDto);
    }
    private void setOrderFields(ShoppingSession shoppingSession, OrderDetails orderDetails) {
        orderDetails.setTotal(shoppingSession.getTotal());
        orderDetails.setCreatedAt(DateUtil.getCurrentDate());
        orderDetails.setUser(shoppingSession.getUser());
    }

    private void setOrderItems(ShoppingSessionDto shoppingSessionDto, OrderDetailsDto orderDetailsDto) {
        shoppingSessionDto.getItems()
                .forEach(cartItemDto -> {
                    OrderItemsDto orderItems = OrderItemsDto.builder()
                            .amount(cartItemDto.getAmount())
                            .orderDetails(orderDetailsDto)
                            .product(cartItemDto.getProduct())
                            .createdAt(DateUtil.getCurrentDate())
                            .build();
                    orderItemsService.addOrderItems(orderItems);
                });
    }

    private ShoppingSessionDto retriveSession() {
        try {
            if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof
                    UserDto userDto){
                Optional<OrderSession> optional = orderSessionRepository.findById(userDto.getId());
                if(optional.isPresent())
                    return shoppingSessionService.getShoppingSession(optional.get().getSessionId()).getData();
            }
        }catch (Exception e){
            log.error("Error while getting order details by id !!!");
        }
        return null;
    }

    @Override
    public ResponseDto<OrderDetailsDto> updateOrderDetails(OrderDetailsDto orderDetailsDto) {
        if(!orderDetailsRepository.existsById(orderDetailsDto.getId()))
            return ResponseDto.buildResponse(null, AppCode.NOT_FOUND, AppMessage.NOT_FOUND, false);
        try {
            OrderDetails orderDetails = OrderDetailsMapper.toEntity(orderDetailsDto);
            orderDetails.setModifiedAt(DateUtil.getCurrentDate());
            orderDetailsRepository.save(orderDetails);
            orderDetails.setModifiedAt(orderDetails.getModifiedAt());
            return ResponseDto.buildResponse(orderDetailsDto, AppCode.OK, AppMessage.OK, true);
        }catch (Exception e){
            e.printStackTrace();
            log.error("Error while updating order details !!!");
            return ResponseDto.buildResponse(null, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

    @Override
    public ResponseDto<OrderDetailsDto> getOrderDetails(Integer id) {
        try {
            Optional<OrderDetails> optional = orderDetailsRepository.findById(id);
            if(optional.isPresent()){
                OrderDetailsDto orderDetailsDto = optional.map(OrderDetailsMapper::toDto).get();
                return ResponseDto.buildResponse(orderDetailsDto, AppCode.OK, AppMessage.OK, true);
            }else {
                return ResponseDto.buildResponse(null, AppCode.NOT_FOUND, AppMessage.NOT_FOUND, false);
            }
        }catch (Exception e){
            log.error("Error while getting order details by id !!!");
            return ResponseDto.buildResponse(null, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

    @Override
    public ResponseDto<Boolean> deleteOrderDetails(Integer id) {
        try {
            if(!orderDetailsRepository.existsById(id))
                return ResponseDto.buildResponse(false, AppCode.NOT_FOUND, AppMessage.NOT_FOUND, false);
            paymentDetailsService.deletePaymentDetails(id);
            orderDetailsRepository.deleteById(id);
            return ResponseDto.buildResponse(true, AppCode.OK, AppMessage.OK, true);
        }catch (Exception e){
            e.printStackTrace();
            log.error("Error while deleting order details by id !!!");
            return ResponseDto.buildResponse(false, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

    @Override
    public ResponseDto<List<OrderItemsDto>> findMostSoldProducts() {
        try{
            return ResponseDto.buildResponse(orderItemsService.findMostSoldProducts(),
                    AppCode.OK, AppMessage.OK, true);
        }catch (Exception e){
            log.error("Error while getting order items sorting by amount");
            return ResponseDto.buildResponse(null, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

}
