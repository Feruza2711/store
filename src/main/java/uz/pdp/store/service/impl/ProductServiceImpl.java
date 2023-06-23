package uz.pdp.store.service.impl;

import uz.pdp.store.dto.ProductDto;
import uz.pdp.store.dto.ResponseDto;
import uz.pdp.store.helper.AppCode;
import uz.pdp.store.helper.AppMessage;
import uz.pdp.store.helper.DateUtil;
import uz.pdp.store.mapper.ProductCustomMapper;
import uz.pdp.store.model.Discount;
import uz.pdp.store.model.Product;
import uz.pdp.store.repository.DiscountRepository;
import uz.pdp.store.repository.ProductRepository;
import uz.pdp.store.service.main.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final DiscountRepository discountRepository;
    @Override
    public ResponseDto<ProductDto> addProduct(ProductDto productDto) {
        try {

            Product product = ProductCustomMapper.toEntity(productDto);
            product.setCreatedAt(DateUtil.getCurrentDate());
            productRepository.save(product);
            productDto.setId(product.getId());
            productDto.setCreatedAt(product.getCreatedAt());
            return ResponseDto.buildResponse(productDto, AppCode.OK, AppMessage.OK, true);
        }catch (Exception e){
            log.error("Error while saving product into database !!!");
            return ResponseDto.buildResponse(null, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

    @Override
    public ResponseDto<ProductDto> updateProduct(ProductDto productDto) {
        if(!productRepository.existsById(productDto.getId()))
            return ResponseDto.buildResponse(null, AppCode.NOT_FOUND, AppMessage.NOT_FOUND, false);
        try {
            Product product = ProductCustomMapper.toEntity(productDto);
            productDto.setModifiedAt(DateUtil.getCurrentDate());
            productRepository.save(product);
            return ResponseDto.buildResponse(productDto, AppCode.OK, AppMessage.OK, true);
        }catch (Exception e){
            log.error("Error while updating product !!!");
            return ResponseDto.buildResponse(null, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

    @Override
    public ResponseDto<ProductDto> getProduct(Integer id) {
        try {
            Optional<Product> optional = productRepository.findById(id);
            if(optional.isPresent()){
                ProductDto productDto = optional.map(ProductCustomMapper::toDto).get();
                return ResponseDto.buildResponse(productDto, AppCode.OK, AppMessage.OK, true);
            }else {
                return ResponseDto.buildResponse(null, AppCode.NOT_FOUND, AppMessage.NOT_FOUND, false);
            }
        }catch (Exception e){
            log.error("Error while getting product by id !!!");
            return ResponseDto.buildResponse(null, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

    @Override
    public ResponseDto<Boolean> deleteProduct(Integer id) {
        try {
            if(!productRepository.existsById(id))
                return ResponseDto.buildResponse(false, AppCode.NOT_FOUND, AppMessage.NOT_FOUND, false);

            productRepository.deleteById(id);
            return ResponseDto.buildResponse(true, AppCode.OK, AppMessage.OK, true);
        }catch (Exception e){
            log.error("Error while deleting product by id !!!");
            return ResponseDto.buildResponse(false, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

    @Override
    public ResponseDto<Boolean> setDiscount(List<Integer> list, Integer discountId, Integer amountDay) {
        Optional<Discount> optional = discountRepository.findById(discountId);
        if(optional.isEmpty() || list.size() == 0)
            return ResponseDto.buildResponse(false, AppCode.NOT_FOUND, AppMessage.NOT_FOUND, false);

        Discount discount = optional.get();

        productRepository.findAllByIdIn(list)
                .forEach( p -> {
                    p.setDiscount(discount);
                    productRepository.save(p);
                });

        finishDiscount(amountDay, discountId);
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
