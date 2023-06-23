package uz.pdp.store.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.pdp.store.dto.CartItemDto;
import uz.pdp.store.dto.ResponseDto;
import uz.pdp.store.helper.AppCode;
import uz.pdp.store.helper.AppMessage;
import uz.pdp.store.helper.DateUtil;
import uz.pdp.store.mapper.CartItemMapper;
import uz.pdp.store.model.CartItem;
import uz.pdp.store.repository.CartItemRepository;
import uz.pdp.store.service.main.CartItemService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    @Override
    public void addCartItem(CartItemDto cartItemDto) {
        try {
            CartItem cartItem = CartItemMapper.toEntity(cartItemDto);
            cartItem.setCreatedAt(DateUtil.getCurrentDate());
            cartItemRepository.save(cartItem);

        }catch (Exception e){
            log.error("Error while saving card into database !!!");
        }
    }


    @Override
    public CartItemDto getCartItem(Integer id) {
        try {
            Optional<CartItem> optional = cartItemRepository.findById(id);
            return optional.map(CartItemMapper::toDto).orElse(null);
        }catch (Exception e){
            log.error("Error while deleting cart item by id !!!");
        }
        return null;
    }

    @Override
    public ResponseDto<Boolean> deleteCartItem(Integer id) {
        try {
            cartItemRepository.deleteById(id);
            return ResponseDto.buildResponse(true, AppCode.OK, AppMessage.OK, true);
        }catch (Exception e){
            log.error("Error while deleting cart item by id !!!");
            return ResponseDto.buildResponse(false, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

    @Override
    public void deleteBySessionId(Integer sessionId) {
        try {
            cartItemRepository.deleteByShoppingSessionId(sessionId);
        }catch (Exception e){
            log.error("Error while deleting cart item by session id !!!");
        }
    }

    @Override
    public List<CartItemDto> getBySessionId(Integer sessionId) {
        return cartItemRepository.findAllByShoppingSessionId(sessionId)
                .stream().map(CartItemMapper::toDto).collect(Collectors.toList());
    }


}
