package uz.pdp.store.service.main;


import uz.pdp.store.dto.DiscountDto;
import uz.pdp.store.dto.ResponseDto;

public interface DiscountService {
    ResponseDto<DiscountDto> addDiscount(DiscountDto discountDto);

    ResponseDto<DiscountDto> updateDiscount(DiscountDto discountDto);

    ResponseDto<DiscountDto> getDiscount(Integer id);

    ResponseDto<Boolean> deleteDiscount(Integer id);

}
