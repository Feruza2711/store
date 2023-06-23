package uz.pdp.store.service.main;


import uz.pdp.store.dto.CityDto;
import uz.pdp.store.dto.ResponseDto;

public interface CityService {
    ResponseDto<CityDto> addCity(CityDto cityDto);

    ResponseDto<CityDto> updateCity(CityDto cityDto);

    ResponseDto<CityDto> getCity(Integer id);

    ResponseDto<Boolean> deleteCity(Integer id);
}
