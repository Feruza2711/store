package uz.pdp.store.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.pdp.store.dto.PaymentDetailsDto;
import uz.pdp.store.dto.ResponseDto;
import uz.pdp.store.helper.AppCode;
import uz.pdp.store.helper.AppMessage;
import uz.pdp.store.mapper.PaymentDetailsMapper;
import uz.pdp.store.model.PaymentDetails;
import uz.pdp.store.repository.PaymentDetailRepository;
import uz.pdp.store.service.main.PaymentDetailsService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentDetailsServiceImpl implements PaymentDetailsService {

    private final PaymentDetailRepository paymentDetailRepository;

    @Override
    public ResponseDto<PaymentDetailsDto> addPaymentDetails(PaymentDetailsDto paymentDetailsDto) {
        try {
            PaymentDetails paymentDetails = PaymentDetailsMapper.toEntity(paymentDetailsDto);
            paymentDetailRepository.save(paymentDetails);
            paymentDetailsDto.setId(paymentDetails.getId());

            return ResponseDto.buildResponse(paymentDetailsDto, AppCode.OK, AppMessage.OK, true);
        }catch (Exception e){
            log.error("Error while saving payment details into database !!!");
            return ResponseDto.buildResponse(null, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

    @Override
    public ResponseDto<PaymentDetailsDto> updatePaymentDetails(PaymentDetailsDto paymentDetailsDto) {
        if(!paymentDetailRepository.existsById(paymentDetailsDto.getId()))
            return ResponseDto.buildResponse(null, AppCode.NOT_FOUND, AppMessage.NOT_FOUND, false);
        try {
            PaymentDetails paymentDetails = PaymentDetailsMapper.toEntity(paymentDetailsDto);
            paymentDetailRepository.save(paymentDetails);
            return ResponseDto.buildResponse(paymentDetailsDto, AppCode.OK, AppMessage.OK, true);
        }catch (Exception e){
            log.error("Error while updating payment details !!!");
            return ResponseDto.buildResponse(null, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

    @Override
    public ResponseDto<PaymentDetailsDto> getPaymentDetails(Integer id) {
        try {
            Optional<PaymentDetails> optional = paymentDetailRepository.findById(id);
            if(optional.isPresent()){
                PaymentDetailsDto proCatDto = optional.map(PaymentDetailsMapper::toDto).get();
                return ResponseDto.buildResponse(proCatDto, AppCode.OK, AppMessage.OK, true);
            }else {
                return ResponseDto.buildResponse(null, AppCode.NOT_FOUND, AppMessage.NOT_FOUND, false);
            }
        }catch (Exception e){
            log.error("Error while getting payment details by id !!!");
            return ResponseDto.buildResponse(null, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

    @Override
    public ResponseDto<Boolean> deletePaymentDetails(Integer id) {
        try {
            if(!paymentDetailRepository.existsById(id))
                return ResponseDto.buildResponse(false, AppCode.NOT_FOUND, AppMessage.NOT_FOUND, false);

            paymentDetailRepository.deleteById(id);
            return ResponseDto.buildResponse(true, AppCode.OK, AppMessage.OK, true);
        }catch (Exception e){
            log.error("Error while deleting payment details by id !!!");
            return ResponseDto.buildResponse(false, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

    @Override
    public void deleteByOrderId(Integer orderDetailsId) {
        try {
            paymentDetailRepository.deleteByOrderDetailsId(orderDetailsId);
        }catch (Exception e){
            log.error("Error while deleting payment details by id !!!");
        }
    }


}
