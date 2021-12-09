package com.academy.realestate.service.impl;

import com.academy.realestate.exception.ResourceNotFoundException;
import com.academy.realestate.model.Neighborhood;
import com.academy.realestate.repository.NeighborhoodRepostiory;
import com.academy.realestate.service.NeighborhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class NeighborhoodServiceImpl implements NeighborhoodService {

    private final NeighborhoodRepostiory neighborhoodRepostiory;

    @Autowired
    public NeighborhoodServiceImpl(NeighborhoodRepostiory neighborhoodRepostiory) {
        this.neighborhoodRepostiory = neighborhoodRepostiory;
    }

    @Override
    public Neighborhood findByName(String name) {
        return neighborhoodRepostiory.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Neighborhood with name: %s do not exist", name)));
    }

    @Override
    public Set<Neighborhood> findAll() {
        return new HashSet<>(neighborhoodRepostiory.findAll());
    }

    @Override
    public Neighborhood findById(Long id) {
        return neighborhoodRepostiory.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Neighborhood with id: %d do not exists",id)));
    }

    @Override
    public Neighborhood save(Neighborhood neighborhood) {
        return neighborhoodRepostiory.save(neighborhood);
    }
}
