package uz.pdp.store.mapper;


import uz.pdp.store.dto.ShoppingSessionDto;
import uz.pdp.store.model.ShoppingSession;

import static uz.pdp.store.mapper.CartItemMapper.cartItemDtoToEntityList;
import static uz.pdp.store.mapper.CartItemMapper.cartItemToDtoList;

public class ShoppingSessionMapper {

    static public ShoppingSessionDto toDto(ShoppingSession shoppingSession){
        return ShoppingSessionDto.builder()
                .id(shoppingSession.getId())
                .user(UserCustomMapper.toDtoWithId(shoppingSession.getUser()))
                .total(shoppingSession.getTotal())
                .items(cartItemToDtoList(shoppingSession.getItems()))
                .createdAt(shoppingSession.getCreatedAt())
                .modifiedAt(shoppingSession.getModifiedAt())
                .build();
    }

    static public ShoppingSession toEntity(ShoppingSessionDto shoppingSessionDto){
        return ShoppingSession.builder()
                .id(shoppingSessionDto.getId())
                .user(UserCustomMapper.toEntityWithId(shoppingSessionDto.getUser()))
                .total(shoppingSessionDto.getTotal())
                .items(cartItemDtoToEntityList(shoppingSessionDto.getItems()))
                .createdAt(shoppingSessionDto.getCreatedAt())
                .modifiedAt(shoppingSessionDto.getModifiedAt())
                .build();
    }

    static public ShoppingSessionDto toDtoWithId(ShoppingSession shoppingSession){
        return ShoppingSessionDto.builder()
                .id(shoppingSession.getId())
                .build();
    }

    static public ShoppingSession toEntityWithId(ShoppingSessionDto shoppingSessionDto){
        return ShoppingSession.builder()
                .id(shoppingSessionDto.getId())
                .build();
    }


}
