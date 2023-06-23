package uz.pdp.store.service.impl;

import uz.pdp.store.dto.ResponseDto;
import uz.pdp.store.dto.UnitDto;
import uz.pdp.store.helper.AppCode;
import uz.pdp.store.helper.AppMessage;
import uz.pdp.store.helper.DateUtil;
import uz.pdp.store.mapper.UnitCustomMapper;
import uz.pdp.store.model.Unit;
import uz.pdp.store.repository.UnitRepository;
import uz.pdp.store.service.main.UnitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;

    @Override
    public ResponseDto<UnitDto> addUnit(UnitDto unitDto) {
        try {
            unitDto.setCreatedAt(DateUtil.getCurrentDate());
            Unit unit = UnitCustomMapper.toEntity(unitDto);
            unitRepository.save(unit);
            unitDto.setId(unit.getId());

            return ResponseDto.buildResponse(unitDto, AppCode.OK, AppMessage.OK, true);
        }catch (Exception e){
            log.error("Error while saving unit into database !!!");
            return ResponseDto.buildResponse(null, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

    @Override
    public ResponseDto<UnitDto> updateUnit(UnitDto unitDto) {
        if(!unitRepository.existsById(unitDto.getId()))
            return ResponseDto.buildResponse(null, AppCode.NOT_FOUND, AppMessage.NOT_FOUND, false);
        try {
            unitDto.setModifiedAt(DateUtil.getCurrentDate());
            Unit unit = UnitCustomMapper.toEntity(unitDto);
            unitRepository.save(unit);
            return ResponseDto.buildResponse(unitDto, AppCode.OK, AppMessage.OK, true);
        }catch (Exception e){
            log.error("Error while updating unit !!!");
            return ResponseDto.buildResponse(null, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

    @Override
    public ResponseDto<UnitDto> getUnit(Integer id) {
        try {
            Optional<Unit> optional = unitRepository.findById(id);
            if(optional.isPresent()){
                UnitDto unitDto = optional.map(UnitCustomMapper::toDto).get();
                return ResponseDto.buildResponse(unitDto, AppCode.OK, AppMessage.OK, true);
            }else {
                return ResponseDto.buildResponse(null, AppCode.NOT_FOUND, AppMessage.NOT_FOUND, false);
            }
        }catch (Exception e){
            log.error("Error while getting unit by id !!!");
            return ResponseDto.buildResponse(null, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

    @Override
    public ResponseDto<Boolean> deleteUnit(Integer id) {
        try {
            if(!unitRepository.existsById(id))
                return ResponseDto.buildResponse(false, AppCode.NOT_FOUND, AppMessage.NOT_FOUND, false);

            unitRepository.deleteById(id);
            return ResponseDto.buildResponse(true, AppCode.OK, AppMessage.OK, true);
        }catch (Exception e){
            log.error("Error while deleting unit by id !!!");
            return ResponseDto.buildResponse(false, AppCode.ERROR, AppMessage.ERROR, false);
        }
    }

}
