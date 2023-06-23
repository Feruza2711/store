package uz.pdp.store.controller;

import uz.pdp.store.dto.AddressDto;
import uz.pdp.store.dto.ResponseDto;
import uz.pdp.store.service.main.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/add")
    public ResponseDto<AddressDto> addAddress(@RequestBody @Valid AddressDto addressDto){
        return addressService.addAddress(addressDto);
    }

    @PutMapping("/update")
    public ResponseDto<AddressDto> updateAddress(@RequestBody @Valid AddressDto addressDto){
        return addressService.updateAddress(addressDto);
    }

    @GetMapping("/get")
    public ResponseDto<AddressDto>  getAddress(@RequestParam Integer id){
        return addressService.getAddress(id);
    }

    @DeleteMapping("/delete")
    public ResponseDto<Boolean> deleteAddress(@RequestParam Integer id){
        return addressService.deleteAddress(id);
    }

}
