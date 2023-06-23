package uz.pdp.store.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.pdp.store.dto.DiscountDto;
import uz.pdp.store.dto.ResponseDto;
import uz.pdp.store.helper.AppCode;
import uz.pdp.store.helper.AppMessage;
import uz.pdp.store.helper.DateUtil;
import uz.pdp.store.mapper.DiscountCustomMapper;
import uz.pdp.store.model.Discount;
import uz.pdp.store.repository.DiscountRepository;
import uz.pdp.store.service.main.DiscountService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;


    @Override
    public ResponseDto<DiscountDto> addDiscount(DiscountDto discountDto) {
        try {
            Discount discount = DiscountCustomMapper.toEntity(discountDto);
            discount.setCreatedAt(DateUtil.getCurrentDate());

            discountRepository.save(discount);
            discountDto.setId(discount.getId());
            discount.setCreatedAt(discount.getCreatedAt());

            return ResponseDto.buildResponse(discountDto, AppCode.OK, AppMessage.OK, true);
        }catch (Exception e){
            log.error("Error while saving discount into database !!!");
            return ResponseDto.buildResponse(null, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

    @Override
    public ResponseDto<DiscountDto> updateDiscount(DiscountDto discountDto) {
         if(!discountRepository.existsById(discountDto.getId()))
             return ResponseDto.buildResponse(null, AppCode.NOT_FOUND, AppMessage.NOT_FOUND, false);
         try {
             Discount discount = DiscountCustomMapper.toEntity(discountDto);
             discount.setModifiedAt(DateUtil.getCurrentDate());
             discountRepository.save(discount);

             discountDto.setModifiedAt(discount.getModifiedAt());
             return ResponseDto.buildResponse(discountDto, AppCode.OK, AppMessage.OK, true);
         }catch (Exception e){
             log.error("Error while updating discount !!!");
             return ResponseDto.buildResponse(null, AppCode.ERROR, AppMessage.ERROR, false);
         }
    }

    @Override
    public ResponseDto<DiscountDto> getDiscount(Integer id) {
        try {
            Optional<Discount> optional = discountRepository.findById(id);
            if(optional.isPresent()){
                DiscountDto discountDto = optional.map(DiscountCustomMapper::toDto).get();
                return ResponseDto.buildResponse(discountDto, AppCode.OK, AppMessage.OK, true);
            }else {
                return ResponseDto.buildResponse(null, AppCode.NOT_FOUND, AppMessage.NOT_FOUND, false);
            }
        }catch (Exception e){
            log.error("Error while getting discount by id !!!");
            return ResponseDto.buildResponse(null, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

    @Override
    public ResponseDto<Boolean> deleteDiscount(Integer id) {
        try {
            if(!discountRepository.existsById(id))
                return ResponseDto.buildResponse(false, AppCode.NOT_FOUND, AppMessage.NOT_FOUND, false);

            discountRepository.deleteById(id);
            return ResponseDto.buildResponse(true, AppCode.OK, AppMessage.OK, true);
        }catch (Exception e){
            log.error("Error while deleting discount by id !!!");
            return ResponseDto.buildResponse(false, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }



}
