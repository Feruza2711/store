package uz.pdp.store.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.store.dto.PaymentDetailsDto;
import uz.pdp.store.dto.ResponseDto;
import uz.pdp.store.service.main.PaymentDetailsService;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentDetailsController {

    private final PaymentDetailsService paymentDetailsService;

    @GetMapping("/get")
    public ResponseDto<PaymentDetailsDto> getPaymentDetails(@RequestParam Integer id){
        return paymentDetailsService.getPaymentDetails(id);
    }

    @DeleteMapping("/delete")
    public ResponseDto<Boolean> deletePaymentDetails(@RequestParam Integer id){
        return paymentDetailsService.deletePaymentDetails(id);
    }

}
