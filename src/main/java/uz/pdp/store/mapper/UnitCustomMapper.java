package uz.pdp.store.mapper;

import uz.pdp.store.dto.UnitDto;
import uz.pdp.store.model.Unit;

public class UnitCustomMapper {

    static public UnitDto toDto(Unit unit){
        return UnitDto.builder()
                .id(unit.getId())
                .name(unit.getName())
                .shortName(unit.getShortName())
                .createdAt(unit.getCreatedAt())
                .modifiedAt(unit.getModifiedAt())
                .build();
    }


    static public Unit toEntity(UnitDto unit){
        return Unit.builder()
                .id(unit.getId())
                .name(unit.getName())
                .shortName(unit.getShortName())
                .createdAt(unit.getCreatedAt())
                .modifiedAt(unit.getModifiedAt())
                .build();
    }

}
