package uz.pdp.store.service.main;



import uz.pdp.store.dto.ProductDto;
import uz.pdp.store.dto.ResponseDto;

import java.util.List;

public interface ProductService {
    ResponseDto<ProductDto> addProduct(ProductDto productDto);

    ResponseDto<ProductDto> updateProduct(ProductDto productDto);

    ResponseDto<ProductDto> getProduct(Integer id);

    ResponseDto<Boolean> deleteProduct(Integer id);

    ResponseDto<Boolean> setDiscount(List<Integer> list, Integer discountId, Integer amountDay);

    void finishDiscount(Integer amountDay, Integer discountId);
}
