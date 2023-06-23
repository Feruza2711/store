package uz.pdp.store.service.main;



import uz.pdp.store.dto.CartItemDto;
import uz.pdp.store.dto.ResponseDto;
import uz.pdp.store.dto.ShoppingSessionDto;

import java.util.List;

public interface ShoppingSessionService{
    ResponseDto<Boolean> addShoppingSession(List<CartItemDto> list);

    ResponseDto<ShoppingSessionDto> getShoppingSession(Integer id);

    ResponseDto<Boolean> deleteShoppingSession(Integer id);
}
