package com.academy.realestate.service.impl;

import com.academy.realestate.exception.ResourceNotFoundException;
import com.academy.realestate.model.City;
import com.academy.realestate.model.Neighborhood;
import com.academy.realestate.repository.CityRepository;
import com.academy.realestate.service.CityService;
import com.academy.realestate.service.NeighborhoodService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CityServiceImpl implements CityService {

    private  final CityRepository cityRepository;
    private final NeighborhoodService neighborhoodService;

    public CityServiceImpl(CityRepository cityRepository, NeighborhoodService neighborhoodService) {
        this.cityRepository = cityRepository;
        this.neighborhoodService = neighborhoodService;
    }

    @Override
    public City findById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("City with that id: %d not exists", id)));
    }

    @Override
    public City save(City city) {
        Set<Neighborhood> neighborhoods = new HashSet<>();
        for(Neighborhood neighborhoodId: city.getNeighborhoods()){
            Neighborhood foundNeighborhood = neighborhoodService.findById(neighborhoodId.getId());
            neighborhoods.add(foundNeighborhood);
        }

        return cityRepository.save(City.builder()
                .name(city.getName())
                .neighborhoods(neighborhoods)
                .build());
    }

    @Override
    public Set<City> findAll() {
        return null;
    }

    @Override
    public City update(City city, Long id) {
        City foundCity = findById(id);
        City cityToUpdate = City.builder()
                .id(foundCity.getId())
                .name(city.getName())
                .neighborhoods(city.getNeighborhoods())
                .build();
        return cityRepository.save(cityToUpdate);
    }

    @Override
    public void detachNeighborhood(Long cityId, Set<Long> neighborhoodIds) {
        City foundCity = findById(cityId);

        foundCity.getNeighborhoods()
                .removeIf(neighborhood -> neighborhoodIds.contains(neighborhood.getId()));

        cityRepository.save(foundCity);
    }
}
