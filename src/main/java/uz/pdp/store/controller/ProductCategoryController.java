package uz.pdp.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.store.dto.ProductCategoryDto;
import uz.pdp.store.dto.ResponseDto;
import uz.pdp.store.service.main.ProductCategoryService;

@RestController
@RequestMapping("/p-category")
@RequiredArgsConstructor
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @PostMapping("/add")
    public ResponseDto<ProductCategoryDto> addProductCategory(@RequestBody @Valid ProductCategoryDto proCatDto){
        return productCategoryService.addProductCategory(proCatDto);
    }

    @PutMapping("/update")
    public ResponseDto<ProductCategoryDto> updateProductCategory(@RequestBody @Valid  ProductCategoryDto proCatDto){
        return productCategoryService.updateProductCategory(proCatDto);
    }

    @PutMapping("/set-discount")
    public ResponseDto<Boolean> discountCategory(@RequestParam Integer disId,
                                                 @RequestParam Integer categoryId,
                                                 @RequestParam Integer day){
        return productCategoryService.discountCategory(disId, categoryId, day);
    }

    @GetMapping("/get")
    public ResponseDto<ProductCategoryDto> getProductCategory(@RequestParam Integer id){
        return productCategoryService.getProductCategory(id);
    }

    @DeleteMapping("/delete")
    public ResponseDto<Boolean> deleteProductCategory(@RequestParam Integer id){
        return productCategoryService.deleteProductCategory(id);
    }

}
