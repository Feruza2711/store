package uz.pdp.store.mapper;


import uz.pdp.store.dto.ProductDto;
import uz.pdp.store.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductCustomMapper {

    static public ProductDto toDto(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .amount(product.getAmount())
                .modifiedAt(product.getModifiedAt())
                .createdAt(product.getCreatedAt())
                .productCategory(ProductCategoryCustomMapper.toDtoWithId(product.getProductCategory()))
                .build();
    }


    static public Product toEntity(ProductDto productDto){

        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .amount(productDto.getAmount())
                .modifiedAt(productDto.getModifiedAt())
                .createdAt(productDto.getCreatedAt())
                .productCategory(ProductCategoryCustomMapper.toEntityWithId(productDto.getProductCategory()))
                .build();
    }

    static public ProductDto toDtoWithId(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .build();
    }


    static public Product toEntityWithId(ProductDto productDto){

        return Product.builder()
                .id(productDto.getId())
                .build();
    }


    public static List<ProductDto> productToDtoList(List<Product> list){
        if(list == null) return null;
        List<ProductDto> dtoList = new ArrayList<>();
        for(Product p : list){
            dtoList.add(ProductCustomMapper.toDto(p));
        }
        return dtoList;
    }


    public static List<Product> productDtoToEntityList(List<ProductDto> listDto){
        if(listDto == null) return null;
        List<Product> list = new ArrayList<>();
        for(ProductDto p : listDto){
            list.add(ProductCustomMapper.toEntity(p));
        }
        return list;
    }


}
