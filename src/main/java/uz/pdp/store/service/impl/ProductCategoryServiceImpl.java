package uz.pdp.store.service.impl;

import uz.pdp.store.dto.ProductCategoryDto;
import uz.pdp.store.dto.ResponseDto;
import uz.pdp.store.helper.AppCode;
import uz.pdp.store.helper.AppMessage;
import uz.pdp.store.helper.DateUtil;
import uz.pdp.store.mapper.ProductCategoryCustomMapper;
import uz.pdp.store.model.ProductCategory;
import uz.pdp.store.repository.DiscountRepository;
import uz.pdp.store.repository.ProductCategoryRepository;
import uz.pdp.store.repository.ProductRepository;
import uz.pdp.store.model.Discount;
import uz.pdp.store.service.main.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;
    private final DiscountRepository discountRepository;

    @Override
    public ResponseDto<ProductCategoryDto> addProductCategory(ProductCategoryDto productCategoryDto) {
        try {
            ProductCategory productCategory = ProductCategoryCustomMapper.toEntity(productCategoryDto);
            productCategory.setCreatedAt(DateUtil.getCurrentDate());
            productCategoryRepository.save(productCategory);
            productCategoryDto.setId(productCategory.getId());
            productCategoryDto.setCreatedAt(productCategory.getCreatedAt());
            return ResponseDto.buildResponse(productCategoryDto, AppCode.OK, AppMessage.OK, true);
        }catch (Exception e){
            log.error("Error while saving product category into database !!!");
            return ResponseDto.buildResponse(null, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

    @Override
    public ResponseDto<ProductCategoryDto> updateProductCategory(ProductCategoryDto proCatDto) {
        if(!productCategoryRepository.existsById(proCatDto.getId()))
            return ResponseDto.buildResponse(null, AppCode.NOT_FOUND, AppMessage.NOT_FOUND, false);
        try {
            ProductCategory productCategory = ProductCategoryCustomMapper.toEntity(proCatDto);
            productCategory.setModifiedAt(DateUtil.getCurrentDate());
            productCategoryRepository.save(productCategory);
            proCatDto.setModifiedAt(productCategory.getModifiedAt());
            return ResponseDto.buildResponse(proCatDto, AppCode.OK, AppMessage.OK, true);
        }catch (Exception e){
            log.error("Error while updating product category !!!");
            return ResponseDto.buildResponse(null, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

    @Override
    public ResponseDto<ProductCategoryDto> getProductCategory(Integer id) {
        try {
            Optional<ProductCategory> optional = productCategoryRepository.findById(id);
            if(optional.isPresent()){
                ProductCategoryDto proCatDto = optional.map(ProductCategoryCustomMapper::toDto).get();
                return ResponseDto.buildResponse(proCatDto, AppCode.OK, AppMessage.OK, true);
            }else {
                return ResponseDto.buildResponse(null, AppCode.NOT_FOUND, AppMessage.NOT_FOUND, false);
            }
        }catch (Exception e){
            log.error("Error while getting product category by id !!!");
            return ResponseDto.buildResponse(null, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

    @Override
    public ResponseDto<Boolean> deleteProductCategory(Integer id) {
        try {
            if(!productCategoryRepository.existsById(id))
                return ResponseDto.buildResponse(false, AppCode.NOT_FOUND, AppMessage.NOT_FOUND, false);

            productCategoryRepository.deleteById(id);
            return ResponseDto.buildResponse(true, AppCode.OK, AppMessage.OK, true);
        }catch (Exception e){
            log.error("Error while deleting product category by id !!!");
            return ResponseDto.buildResponse(false, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }


    @Override
    public ResponseDto<Boolean> discountCategory(Integer discountId, Integer productCategoryId, Integer day) {
        Optional<ProductCategory> optionalProductCategory = productCategoryRepository.findById(productCategoryId);
        Optional<Discount> optionalDiscount = discountRepository.findById(discountId);

        if(optionalProductCategory.isEmpty() || optionalDiscount.isEmpty())
            return ResponseDto.buildResponse(false, AppCode.NOT_FOUND, AppMessage.NOT_FOUND, false);

        Integer catId = optionalProductCategory.get().getId();
        Discount discount = optionalDiscount.get();

        productRepository.findAllByProductCategoryId(catId)
                .forEach(p -> {
                    p.setDiscount(discount);
                    productRepository.save(p);
                });

        finishDiscount(day, discountId);
        return ResponseDto.buildResponse(true, AppCode.OK, AppMessage.OK, true);
    }



    public void finishDiscount(Integer amountDay, Integer discountId){
        Optional<Discount> optionalDiscount = discountRepository.findById(discountId);

        if(optionalDiscount.isEmpty())
            return;

        Discount discount = optionalDiscount.get();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                productRepository.findAllByDiscount(discount)
                        .forEach(p -> {
                            p.setDiscount(null);
                            productRepository.save(p);
                        });
            }
        };

        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_YEAR, amountDay);

        Timer timer = new Timer();
        timer.schedule(timerTask, calendar.getTime());
    }




}
