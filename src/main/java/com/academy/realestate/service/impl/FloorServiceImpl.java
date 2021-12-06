package com.academy.realestate.service.impl;

import com.academy.realestate.exception.DuplicateResourceException;
import com.academy.realestate.exception.ResourceNotFoundException;
import com.academy.realestate.model.Floor;
import com.academy.realestate.repository.FloorRepository;
import com.academy.realestate.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class FloorServiceImpl implements FloorService {

    private final FloorRepository floorRepository;

    @Autowired
    public FloorServiceImpl(FloorRepository floorRepository) {
        this.floorRepository = floorRepository;
    }


    @Override
    public Floor save(Floor floor) {
        try{
            return floorRepository.save(floor);
        }catch (DataIntegrityViolationException exception) {
            throw new DuplicateResourceException(String.format("Floor with number %d already exists", floor.getNumber()));
        }
    }

    @Override
    public Floor findByNumber(Integer number) {
        return floorRepository.findByNumber(number)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Floor with %d not exists.", number)));
    }

    @Override
    public Floor findById(Long id) {
        return floorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Floor with id %d not found", id)));
    }

    @Override
    public Floor update(Floor floor, Long id) {
        Floor foundFloor = findById(id);
        Floor updatedFloor =Floor.builder()
                .id(foundFloor.getId())
                .number(floor.getNumber())
                .build();

        return save(updatedFloor);
    }

    @Override
    public void delete(Long id) {
        floorRepository.deleteById(id);
    }

    @Override
    public Set<Floor> findAll() {
        return new HashSet<>(floorRepository.findAll());
    }
}
