package uz.pdp.store.service.main;


import uz.pdp.store.dto.PaymentDetailsDto;
import uz.pdp.store.dto.ResponseDto;

public interface PaymentDetailsService {
    ResponseDto<PaymentDetailsDto> addPaymentDetails(PaymentDetailsDto paymentDetailsDto);

    ResponseDto<PaymentDetailsDto> updatePaymentDetails(PaymentDetailsDto paymentDetailsDto);

    ResponseDto<PaymentDetailsDto> getPaymentDetails(Integer id);

    ResponseDto<Boolean> deletePaymentDetails(Integer id);

    void deleteByOrderId(Integer id);
}
