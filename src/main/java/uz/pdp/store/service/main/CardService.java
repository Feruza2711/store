package uz.pdp.store.service.main;


import uz.pdp.store.dto.CardDto;
import uz.pdp.store.dto.ResponseDto;

public interface CardService {

    ResponseDto<CardDto> addCard(CardDto cardDto);

    ResponseDto<CardDto> updateCard(CardDto cardDto);

    ResponseDto<CardDto> getCard(Integer id);

    ResponseDto<Boolean> deleteCard(Integer id);

    CardDto getByUserId(Integer userId);
}
