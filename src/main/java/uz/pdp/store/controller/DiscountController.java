package uz.pdp.store.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.store.dto.DiscountDto;
import uz.pdp.store.dto.ResponseDto;
import uz.pdp.store.service.main.DiscountService;

@RestController
@RequestMapping("/discount")
@RequiredArgsConstructor
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @PostMapping("/add")
    public ResponseDto<DiscountDto> addDiscount(@RequestBody @Valid DiscountDto DiscountDto){
        return discountService.addDiscount(DiscountDto);
    }

    @PutMapping("/update")
    public ResponseDto<DiscountDto> updateDiscount(@RequestBody @Valid DiscountDto discountDto){
        return discountService.updateDiscount(discountDto);
    }

    @GetMapping("/get")
    public ResponseDto<DiscountDto> getDiscount(@RequestParam Integer id){
        return discountService.getDiscount(id);
    }

    @DeleteMapping("/delete")
    public ResponseDto<Boolean> deleteDiscount(@RequestParam Integer id){
        return discountService.deleteDiscount(id);
    }

}
