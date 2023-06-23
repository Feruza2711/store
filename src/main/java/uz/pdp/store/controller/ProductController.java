package uz.pdp.store.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.store.dto.ProductDto;
import uz.pdp.store.dto.ResponseDto;
import uz.pdp.store.service.main.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseDto<ProductDto> addProduct(@RequestBody @Valid ProductDto productDto){
        return productService.addProduct(productDto);
    }

    @PutMapping("/update")
    public ResponseDto<ProductDto> updateProduct(@RequestBody @Valid ProductDto productDto){
        return productService.updateProduct(productDto);
    }

    @GetMapping("/get")
    public ResponseDto<ProductDto> getProduct(@RequestParam @Valid Integer id){
        return productService.getProduct(id);
    }


    @DeleteMapping("/delete")
    public ResponseDto<Boolean> deleteProduct(@RequestParam @Valid Integer id){
        return productService.deleteProduct(id);
    }


    @PutMapping("/set-discount")
    public ResponseDto<Boolean> setDiscount(@RequestParam List<Integer> list,
                                            @RequestParam Integer discountId,
                                            @RequestParam Integer amountDay){
        return productService.setDiscount(list, discountId, amountDay);
    }

}



