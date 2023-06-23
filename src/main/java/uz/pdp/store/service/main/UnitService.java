package uz.pdp.store.service.main;


import uz.pdp.store.dto.ResponseDto;
import uz.pdp.store.dto.UnitDto;

public interface UnitService {
    ResponseDto<UnitDto> addUnit(UnitDto unitDto);

    ResponseDto<UnitDto> updateUnit(UnitDto unitDto);

    ResponseDto<UnitDto> getUnit(Integer id);

    ResponseDto<Boolean> deleteUnit(Integer id);
}
