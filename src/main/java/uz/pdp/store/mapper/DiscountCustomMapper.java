package uz.pdp.store.mapper;


import uz.pdp.store.dto.DiscountDto;
import uz.pdp.store.model.Discount;

public class DiscountCustomMapper {


    public static DiscountDto toDto(Discount discount){
        return DiscountDto.builder()
                .id(discount.getId())
                .name(discount.getName())
                .description(discount.getDescription())
                .discountPercent(discount.getDiscountPercent())
                .products(ProductCustomMapper.productToDtoList(discount.getProducts()))
                .modifiedAt(discount.getModifiedAt())
                .createdAt(discount.getCreatedAt())
                .build();
    }

    public static Discount toEntity(DiscountDto discountDto){
        return Discount.builder()
                .id(discountDto.getId())
                .name(discountDto.getName())
                .description(discountDto.getDescription())
                .discountPercent(discountDto.getDiscountPercent())
                .products(ProductCustomMapper.productDtoToEntityList(discountDto.getProducts()))
                .modifiedAt(discountDto.getModifiedAt())
                .createdAt(discountDto.getCreatedAt())
                .build();
    }


}
