package uz.pdp.store.mapper;


import uz.pdp.store.dto.ProductCategoryDto;
import uz.pdp.store.model.ProductCategory;

public class ProductCategoryCustomMapper {

    static public ProductCategoryDto toDto(ProductCategory productCategory){
        return ProductCategoryDto.builder()
                .id(productCategory.getId())
                .name(productCategory.getName())
                .description(productCategory.getDescription())
                .parentCategory(productCategory.getParentCategory())
                .unit(UnitCustomMapper.toDto(productCategory.getUnit()))
                .products(ProductCustomMapper.productToDtoList(productCategory.getProducts()))
                .createdAt(productCategory.getCreatedAt())
                .modifiedAt(productCategory.getModifiedAt())
                .build();
    }


    static public ProductCategory toEntity(ProductCategoryDto productCategoryDto){
        return ProductCategory.builder()
                .id(productCategoryDto.getId())
                .name(productCategoryDto.getName())
                .description(productCategoryDto.getDescription())
                .parentCategory(productCategoryDto.getParentCategory())
                .unit(UnitCustomMapper.toEntity(productCategoryDto.getUnit()))
                .products(ProductCustomMapper.productDtoToEntityList(productCategoryDto.getProducts()))
                .createdAt(productCategoryDto.getCreatedAt())
                .modifiedAt(productCategoryDto.getModifiedAt())
                .build();
    }


    static public ProductCategoryDto toDtoWithId(ProductCategory productCategory){
        return ProductCategoryDto.builder()
                .id(productCategory.getId())
                .build();
    }


    static public ProductCategory toEntityWithId(ProductCategoryDto productCategoryDto){
        return ProductCategory.builder()
                .id(productCategoryDto.getId())
                .build();
    }



}
