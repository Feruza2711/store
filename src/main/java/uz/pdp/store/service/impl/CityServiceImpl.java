package uz.pdp.store.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.pdp.store.dto.CityDto;
import uz.pdp.store.dto.ResponseDto;
import uz.pdp.store.helper.AppCode;
import uz.pdp.store.helper.AppMessage;
import uz.pdp.store.mapper.CityCustomMapper;
import uz.pdp.store.model.City;
import uz.pdp.store.repository.CityRepository;
import uz.pdp.store.service.main.CityService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public ResponseDto<CityDto> addCity(CityDto cityDto) {
        try {
            City city = CityCustomMapper.toEntity(cityDto);
            cityRepository.save(city);
            cityDto.setId(city.getId());

            return ResponseDto.buildResponse(cityDto, AppCode.OK, AppMessage.OK, true);
        }catch (Exception e){
            log.error("Error while saving city into database !!!");
            return ResponseDto.buildResponse(null, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

    @Override
    public ResponseDto<CityDto> updateCity(CityDto cityDto) {
        if(!cityRepository.existsById(cityDto.getId()))
            return ResponseDto.buildResponse(null, AppCode.NOT_FOUND, AppMessage.NOT_FOUND, false);
        try {
            City city = CityCustomMapper.toEntity(cityDto);
            cityRepository.save(city);
            return ResponseDto.buildResponse(cityDto, AppCode.OK, AppMessage.OK, true);
        }catch (Exception e){
            log.error("Error while updating city !!!");
            return ResponseDto.buildResponse(null, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

    @Override
    public ResponseDto<CityDto> getCity(Integer id) {
        try {
            Optional<City> optional = cityRepository.findById(id);
            if(optional.isPresent()){
                CityDto cityDto = optional.map(CityCustomMapper::toDto).get();
                return ResponseDto.buildResponse(cityDto, AppCode.OK, AppMessage.OK, true);
            }else {
                return ResponseDto.buildResponse(null, AppCode.NOT_FOUND, AppMessage.NOT_FOUND, false);
            }
        }catch (Exception e){
            log.error("Error while getting city by id !!!");
            return ResponseDto.buildResponse(null, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

    @Override
    public ResponseDto<Boolean> deleteCity(Integer id) {
        try {
            if(!cityRepository.existsById(id))
                return ResponseDto.buildResponse(false, AppCode.NOT_FOUND, AppMessage.NOT_FOUND, false);

            cityRepository.deleteById(id);
            return ResponseDto.buildResponse(true, AppCode.OK, AppMessage.OK, true);
        }catch (Exception e){
            log.error("Error while deleting city by id !!!");
            return ResponseDto.buildResponse(false, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }
}
