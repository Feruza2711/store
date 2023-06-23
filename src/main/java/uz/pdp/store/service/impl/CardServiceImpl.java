package uz.pdp.store.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.store.dto.CardDto;
import uz.pdp.store.dto.ResponseDto;
import uz.pdp.store.dto.UserDto;
import uz.pdp.store.helper.AppCode;
import uz.pdp.store.helper.AppMessage;
import uz.pdp.store.mapper.CardCustomMapper;
import uz.pdp.store.mapper.CardTypeMapper;
import uz.pdp.store.model.Card;
import uz.pdp.store.repository.CardRepository;
import uz.pdp.store.service.main.CardService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    @Override
    public ResponseDto<CardDto> addCard(CardDto cardDto) {
        try {
            if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof
                    UserDto userDto){
                cardDto.setUser(userDto);
            }

            Card card = CardCustomMapper.toEntity(cardDto);
            cardRepository.save(card);

            cardDto.setId(card.getId());
            cardDto.setCardType(CardTypeMapper.toDto(card.getCardType()));

            return ResponseDto.buildResponse(cardDto, AppCode.OK, AppMessage.OK, true);
        }catch (Exception e){
            log.error("Error while saving card into database !!!");
            return ResponseDto.buildResponse(null, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

    @Override
    public ResponseDto<CardDto> updateCard(CardDto cardDto) {
        if(!cardRepository.existsById(cardDto.getId()))
            return ResponseDto.buildResponse(null, AppCode.NOT_FOUND, AppMessage.NOT_FOUND, false);
        try {
            if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof
                    UserDto userDto){
                cardDto.setUser(userDto);
            }
            Card card = CardCustomMapper.toEntity(cardDto);
            cardRepository.save(card);
            cardDto.setCardType(CardTypeMapper.toDto(card.getCardType()));
            return ResponseDto.buildResponse(cardDto, AppCode.OK, AppMessage.OK, true);
        }catch (Exception e){
            log.error("Error while updating card !!!");
            return ResponseDto.buildResponse(null, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

    @Override
    public ResponseDto<CardDto> getCard(Integer id) {
        try {
            Optional<Card> optional = cardRepository.findById(id);
            if(optional.isPresent()){
                CardDto cardDto = optional.map(CardCustomMapper::toDto).get();
                return ResponseDto.buildResponse(cardDto, AppCode.OK, AppMessage.OK, true);
            }else {
                return ResponseDto.buildResponse(null, AppCode.NOT_FOUND, AppMessage.NOT_FOUND, false);
            }
        }catch (Exception e){
            log.error("Error while getting card by id !!!");
            return ResponseDto.buildResponse(null, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

    @Override
    public ResponseDto<Boolean> deleteCard(Integer id) {
        try {
            if(!cardRepository.existsById(id))
                return ResponseDto.buildResponse(false, AppCode.NOT_FOUND, AppMessage.NOT_FOUND, false);

            cardRepository.deleteById(id);
            return ResponseDto.buildResponse(true, AppCode.OK, AppMessage.OK, true);
        }catch (Exception e){
            log.error("Error while deleting card by id !!!");
            return ResponseDto.buildResponse(false, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

    @Override
    public CardDto getByUserId(Integer userId) {
        try {
            Card card = cardRepository.findByUserId(userId);
            return CardCustomMapper.toDto(card);
        }catch (Exception e){
            log.error("Error while getting card by user id !!!");
        }
        return null;
    }
}
