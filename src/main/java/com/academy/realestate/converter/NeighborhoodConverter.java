package com.academy.realestate.converter;

import com.academy.realestate.dto.FloorDto;
import com.academy.realestate.dto.NeighborhoodDto;
import com.academy.realestate.model.Floor;
import com.academy.realestate.model.Neighborhood;
import org.springframework.stereotype.Component;

@Component
public class NeighborhoodConverter {

    public NeighborhoodDto toNeighborhoodDto(Neighborhood neighborhood){
        return NeighborhoodDto.builder()
                .id(neighborhood.getId())
                .name(neighborhood.getName())
                .build();
    }

    public Neighborhood toNeighborhood(NeighborhoodDto neighborhoodDto) {
        return Neighborhood.builder()
                .id(neighborhoodDto.getId())
                .name(neighborhoodDto.getName())
                .build();
    }
}
