package uz.pdp.store.service.main;


import uz.pdp.store.dto.ProductCategoryDto;
import uz.pdp.store.dto.ResponseDto;

public interface ProductCategoryService {
    ResponseDto<ProductCategoryDto> addProductCategory(ProductCategoryDto proCatDto);

    ResponseDto<ProductCategoryDto> updateProductCategory(ProductCategoryDto proCatDto);

    ResponseDto<ProductCategoryDto> getProductCategory(Integer id);

    ResponseDto<Boolean> deleteProductCategory(Integer id);

    ResponseDto<Boolean> discountCategory(Integer discountId, Integer productCategoryId, Integer day);
}
