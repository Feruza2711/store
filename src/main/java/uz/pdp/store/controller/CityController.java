package uz.pdp.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.store.dto.CityDto;
import uz.pdp.store.dto.ResponseDto;
import uz.pdp.store.service.main.CityService;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping("/add")
    public ResponseDto<CityDto> addCity(@RequestBody @Valid CityDto cityDto){
        return cityService.addCity(cityDto);
    }

    @PutMapping("/update")
    public ResponseDto<CityDto> updateCity(@RequestBody @Valid CityDto cityDto){
        return cityService.updateCity(cityDto);
    }

    @GetMapping("/get")
    public ResponseDto<CityDto> getCity(@RequestParam @Valid Integer id){
        return cityService.getCity(id);
    }

    @DeleteMapping("/delete")
    public ResponseDto<Boolean> deleteCity(@RequestParam @Valid Integer id){
        return cityService.deleteCity(id);
    }

}
