package com.academy.realestate.controler;

import com.academy.realestate.converter.CityConverter;
import com.academy.realestate.dto.CityDetachNeighborhoodDto;
import com.academy.realestate.dto.CityDto;
import com.academy.realestate.model.City;
import com.academy.realestate.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cities")
public class CityController {

    private final CityService cityService;
    private final CityConverter cityConverter;

    public CityController(CityService cityService, CityConverter cityConverter) {
        this.cityService = cityService;
        this.cityConverter = cityConverter;
    }

    @PostMapping
    public ResponseEntity<CityDto> save(@RequestBody CityDto cityDto){
        City city = cityConverter.toCity(cityDto);
        City saveCity = cityService.save(city);
        CityDto cityDtoResponse = cityConverter.toCityDto(saveCity);
        return ResponseEntity.ok(cityDtoResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CityDto> findById(@PathVariable Long id){
        City foundCity = cityService.findById(id);
        CityDto cityDto = cityConverter.toCityDto(foundCity);

        return ResponseEntity.ok(cityDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CityDto>  update(@RequestBody CityDto cityDto, @PathVariable Long id) {
        City city = cityConverter.toCity(cityDto);
        City saveCity = cityService.update(city, id);
        CityDto cityDtoResponse = cityConverter.toCityDto(saveCity);
        return ResponseEntity.ok(cityDtoResponse);
    }

    @PutMapping(value = "/detach")
    public ResponseEntity<HttpStatus> detach(@RequestBody CityDetachNeighborhoodDto cityDetachNeighborhoodDto) {
        cityService.detachNeighborhood(cityDetachNeighborhoodDto.getCityId(),cityDetachNeighborhoodDto.getNeighborhoodsIds());
        return ResponseEntity.ok().build();
    }
}
