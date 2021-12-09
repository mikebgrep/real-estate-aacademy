package com.academy.realestate.service;

import com.academy.realestate.model.City;
import java.util.Set;

public interface CityService {

    City findById(Long id);

    City save(City city);

    Set<City> findAll();

    City update(City city, Long id);

    void detachNeighborhood(Long cityId, Set<Long> neighborhoodIds);
}
