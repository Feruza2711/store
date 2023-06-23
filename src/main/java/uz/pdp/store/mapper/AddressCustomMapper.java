package uz.pdp.store.mapper;


import uz.pdp.store.dto.AddressDto;
import uz.pdp.store.model.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressCustomMapper {

    public static AddressDto toDto(Address address){
        return AddressDto.builder()
                .id(address.getId())
                .fullName(address.getFullName())
                .city(CityCustomMapper.toDto(address.getCity()))
                .region(address.getRegion())
                .landMark(address.getLandMark())
                .street(address.getStreet())
                .homeNumber(address.getHomeNumber())
                .build();
    }

    public static Address toEntity(AddressDto addressDto){
        return Address.builder()
                .id(addressDto.getId())
                .fullName(addressDto.getFullName())
                .city(CityCustomMapper.toEntity(addressDto.getCity()))
                .region(addressDto.getRegion())
                .landMark(addressDto.getLandMark())
                .street(addressDto.getStreet())
                .homeNumber(addressDto.getHomeNumber())
                .build();
    }



    public static List<AddressDto> AddressListToDtoList(List<Address> list){
        if(list == null) return null;
        List<AddressDto> listDto = new ArrayList<>();
        for(Address a : list){
            listDto.add(AddressCustomMapper.toDto(a));
        }
        return listDto;
    }

    public static List<Address> AddressDtoListToEntityList(List<AddressDto> list){
        if(list == null) return null;
        List<Address> listEntity = new ArrayList<>();
        for(AddressDto a : list){
            listEntity.add(AddressCustomMapper.toEntity(a));
        }
        return listEntity;
    }
}

