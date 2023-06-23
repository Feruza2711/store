package uz.pdp.store.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.store.dto.OrderDetailsDto;
import uz.pdp.store.dto.OrderItemsDto;
import uz.pdp.store.dto.ResponseDto;
import uz.pdp.store.service.main.OrderDetailsService;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;

    @PostMapping("/add")
    public ResponseDto<OrderDetailsDto> addOrderDetails(){
        return orderDetailsService.addOrderDetails();
    }

    @PutMapping("/update")
    public ResponseDto<OrderDetailsDto> updateOrderDetails(@RequestBody @Valid OrderDetailsDto orderDetailsDto){
        return orderDetailsService.updateOrderDetails(orderDetailsDto);
    }

    @GetMapping("/get")
    public ResponseDto<OrderDetailsDto> getOrderDetails(@RequestParam Integer id){
        return orderDetailsService.getOrderDetails(id);
    }

    @DeleteMapping("/delete")
    public ResponseDto<Boolean> deleteOrderDetails(@RequestParam Integer id){
        return orderDetailsService.deleteOrderDetails(id);
    }

    @GetMapping("/sort")
    public ResponseDto<List<OrderItemsDto>> getMostSoldProducts(){
        return orderDetailsService.findMostSoldProducts();
    }


}
