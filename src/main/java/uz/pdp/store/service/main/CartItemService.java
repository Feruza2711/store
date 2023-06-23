package uz.pdp.store.service.main;




import uz.pdp.store.dto.CartItemDto;
import uz.pdp.store.dto.ResponseDto;

import java.util.List;

public interface CartItemService {

    void addCartItem(CartItemDto cartItemDto);


    CartItemDto getCartItem(Integer id);

    ResponseDto<Boolean> deleteCartItem(Integer id);

    void deleteBySessionId(Integer sessionId);

    List<CartItemDto> getBySessionId(Integer sessionId);
}
