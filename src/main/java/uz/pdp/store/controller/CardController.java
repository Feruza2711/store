package uz.pdp.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.store.dto.CardDto;
import uz.pdp.store.dto.ResponseDto;
import uz.pdp.store.service.main.CardService;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping("/add")
    public ResponseDto<CardDto> addCard(@RequestBody @Valid CardDto cardDto){
        return cardService.addCard(cardDto);
    }

    @PutMapping("/update")
    public ResponseDto<CardDto> updateCard(@RequestBody @Valid CardDto cardDto){
        return cardService.updateCard(cardDto);
    }

    @GetMapping("/get")
    public ResponseDto<CardDto> getCard(@RequestParam Integer id){
        return cardService.getCard(id);
    }

    @DeleteMapping("/delete")
    public ResponseDto<Boolean> deleteCard(@RequestParam @Valid Integer id){
        return cardService.deleteCard(id);
    }

}
