package uz.pdp.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.store.dto.CartItemDto;
import uz.pdp.store.dto.ResponseDto;
import uz.pdp.store.dto.ShoppingSessionDto;
import uz.pdp.store.service.main.ShoppingSessionService;

import java.util.List;

@RestController
@RequestMapping("/shop-session")
@RequiredArgsConstructor
public class ShoppingSessionController{

    private final ShoppingSessionService shoppingSessionService;

    @PostMapping("/add")
    public ResponseDto<Boolean> addShoppingSession(@RequestBody List<CartItemDto> list){
        return shoppingSessionService.addShoppingSession(list);
    }

    @GetMapping("/get")
    public ResponseDto<ShoppingSessionDto> getShoppingSession(@RequestParam Integer id){
        return shoppingSessionService.getShoppingSession(id);
    }

    @DeleteMapping("/delete")
    public ResponseDto<Boolean> deleteShoppingSession(@RequestParam Integer id){
        return shoppingSessionService.deleteShoppingSession(id);
    }

}
