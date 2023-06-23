package uz.pdp.store.mapper;



import uz.pdp.store.dto.CartItemDto;
import uz.pdp.store.model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CartItemMapper {

    static public CartItemDto toDto(CartItem cartItem){
        return CartItemDto.builder()
                .id(cartItem.getId())
                .product(ProductCustomMapper.toDtoWithId(cartItem.getProduct()))
                .amount(cartItem.getAmount())
                .shoppingSession(ShoppingSessionMapper.toDtoWithId(cartItem.getShoppingSession()))
                .createdAt(cartItem.getCreatedAt())
                .modifiedAt(cartItem.getModifiedAt())
                .build();
    }


    static public CartItem toEntity(CartItemDto cartItemDto){
        return CartItem.builder()
                .id(cartItemDto.getId())
                .product(ProductCustomMapper.toEntityWithId(cartItemDto.getProduct()))
                .amount(cartItemDto.getAmount())
                .shoppingSession(ShoppingSessionMapper.toEntityWithId(cartItemDto.getShoppingSession()))
                .createdAt(cartItemDto.getCreatedAt())
                .modifiedAt(cartItemDto.getModifiedAt())
                .build();
    }


    public static List<CartItemDto> cartItemToDtoList(List<CartItem> list){
        if(list == null) return null;
        List<CartItemDto> dtoList = new ArrayList<>();

        for (CartItem c : list) {
            dtoList.add(toDto(c));
        }
        return dtoList;
    }


    public static List<CartItem> cartItemDtoToEntityList(List<CartItemDto> listDto){
        if(listDto == null) return null;
        List<CartItem> list = new ArrayList<>();

        for (CartItemDto c : listDto){
            list.add(toEntity(c));
        }

        return list;
    }


}
















