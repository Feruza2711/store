package uz.pdp.store.mapper;


import uz.pdp.store.dto.CityDto;
import uz.pdp.store.model.City;

public class CityCustomMapper {

    public static CityDto toDto(City city) {
        return new CityDto(city.getId(), city.getName());
    }

    public static City toEntity(CityDto cityDto) {
        return new City(cityDto.getId(), cityDto.getName());
    }


}
