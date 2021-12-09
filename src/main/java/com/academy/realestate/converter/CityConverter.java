package com.academy.realestate.converter;

import com.academy.realestate.dto.CityDto;
import com.academy.realestate.dto.FloorDto;
import com.academy.realestate.model.City;
import com.academy.realestate.model.Floor;
import com.academy.realestate.model.Neighborhood;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CityConverter {



    public CityDto toCityDto(City city){
        /*
        Set<Long> neighborhoodsIds = new HashSet<>();
        for(Neighborhood neighborhood: city.getNeighborhoods()) {
            neighborhoodsIds.add(neighborhood.getId());
        }*/ //same as after ruturn

            return CityDto.builder()
                .id(city.getId())
                .name(city.getName())
                .neighborhoodIds(city.getNeighborhoods()
                        .stream()
                        .map(Neighborhood::getId)
                        .collect(Collectors.toSet()))
                .build();
    }

    public City toCity(CityDto cityDto) {
        return City.builder()
                .id(cityDto.getId())
                .name(cityDto.getName())
                .neighborhoods(cityDto.getNeighborhoodIds()
                        .stream()
                        .map((neighborhoodId) -> Neighborhood
                                .builder()
                                .id(neighborhoodId)
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
