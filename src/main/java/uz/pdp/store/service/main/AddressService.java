package uz.pdp.store.service.main;


import uz.pdp.store.dto.AddressDto;
import uz.pdp.store.dto.ResponseDto;

public interface AddressService {
    ResponseDto<AddressDto> addAddress(AddressDto addressDto);

    ResponseDto<AddressDto> updateAddress(AddressDto addressDto);

    ResponseDto<AddressDto> getAddress(Integer id);

    ResponseDto<Boolean> deleteAddress(Integer id);
}
