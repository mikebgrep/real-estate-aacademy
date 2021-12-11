package com.academy.realestate.service.impl;

import com.academy.realestate.exception.DuplicateResourceException;
import com.academy.realestate.exception.ResourceNotFoundException;
import com.academy.realestate.model.Floor;
import com.academy.realestate.repository.FloorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FloorServiceImplTest {

    @Mock
    private FloorRepository floorRepository;

    @InjectMocks
    private FloorServiceImpl floorServiceImpl;

    private Floor floor;

    @Test
    public void verifyFinaAll() {
        floor = mock(Floor.class);
        when(floorRepository.findAll())
                .thenReturn(Collections.singletonList(floor));

        floorServiceImpl.findAll();
        verify(floorRepository, times(1)).findAll();

    }

    @Test
    public void verifySave() {
        when(floorRepository.save(any(Floor.class))).thenReturn(floor);
        Floor floorSave = Floor.builder().build();
        floorServiceImpl.save(floorSave);
        verify(floorRepository, times(1)).save(floorSave);

    }

    @Test(expected = DuplicateResourceException.class)
    public void verifySaveDuplicateException() {
        when(floorRepository.save(any(Floor.class)))
                .thenThrow(DataIntegrityViolationException.class);

        floorServiceImpl.save(Floor.builder().build());

    }

    @Test
    public void verifyFindByNumber() {
        when(floorRepository.findByNumber(any(Integer.class)))
                .thenReturn(Optional.of(Floor.builder().build()));
        floorServiceImpl.findByNumber(1);
        verify(floorRepository, times(1)).findByNumber(1);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void verifyFindByNumberException() {
        when(floorRepository.findByNumber(any(Integer.class)))
                .thenReturn(Optional.empty());
        floorServiceImpl.findByNumber(1);
    }

    @Test
    public void verifyFindById() {
        when(floorRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(Floor.builder().build()));
        floorServiceImpl.findById(1L);
        verify(floorRepository, times(1)).findById(1L);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void verifyFindByIdException() {
        when(floorRepository.findById(any(Long.class)))
                .thenReturn(Optional.empty());
        floorServiceImpl.findById(1L);
    }


    @Test
    public void verifyDelete(){
        doNothing().when(floorRepository).deleteById(any(Long.class));
        floorServiceImpl.delete(1L);
        verify(floorRepository, times(1)).deleteById(1L);
    }

}
