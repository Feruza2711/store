package uz.pdp.store.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import uz.pdp.store.dto.CardDto;
import uz.pdp.store.model.Card;

import java.util.ArrayList;
import java.util.List;

public class CardCustomMapper {

    @Autowired
    private CardTypeMapper cardTypeMapper;
    public static CardDto toDto(Card card){
        return CardDto.builder()
                .id(card.getId())
                .cardNumber(card.getCardNumber())
                .cardType(CardTypeMapper.toDto(card.getCardType()))
                .user(UserCustomMapper.toDtoWithId(card.getUser()))
                .cvc(card.getCvc())
                .amount(card.getAmount())
                .validity(card.getValidity())
                .ownerName(card.getOwnerName())
                .build();
    }


    public static Card toEntity(CardDto card){
        return Card.builder()
                .id(card.getId())
                .cardNumber(card.getCardNumber())
                .cardType(CardTypeMapper.toEntity(card.getCardType()))
                .cvc(card.getCvc())
                .amount(card.getAmount())
                .user(UserCustomMapper.toEntityWithId(card.getUser()))
                .validity(card.getValidity())
                .ownerName(card.getOwnerName())
                .build();
    }

    public static List<CardDto> CardListToDtoList(List<Card> list){
        if(list == null) return null;
        List<CardDto> listDto = new ArrayList<>();
        for(Card a : list){
            listDto.add(CardCustomMapper.toDto(a));
        }
        return listDto;
    }

    public static List<Card> CardDtoListToEntityList(List<CardDto> list){
        if(list == null) return null;
        List<Card> listEntity = new ArrayList<>();
        for(CardDto a : list){
            listEntity.add(CardCustomMapper.toEntity(a));
        }
        return listEntity;
    }

}
