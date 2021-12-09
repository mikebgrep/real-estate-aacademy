package com.academy.realestate.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CityDetachNeighborhoodDto {

    private Long cityId;

    private Set<Long> neighborhoodsIds;
}
