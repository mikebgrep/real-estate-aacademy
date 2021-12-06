package com.academy.realestate.repository;

import com.academy.realestate.model.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NeighborhoodRepostiory extends JpaRepository<Neighborhood, Long> {

    Optional<Neighborhood> findByName(String name);
}
