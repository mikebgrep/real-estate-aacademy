package com.academy.realestate.controler;

import com.academy.realestate.converter.FloorConverter;
import com.academy.realestate.dto.FloorDto;
import com.academy.realestate.model.Floor;
import com.academy.realestate.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/floors")
public class FloorController {

    private final FloorService floorService;
    private final FloorConverter floorConverter;


    @Autowired
    public FloorController(FloorService floorService, FloorConverter floorConverter) {
        this.floorService = floorService;
        this.floorConverter = floorConverter;
    }

    @GetMapping
    public ResponseEntity<Set<FloorDto>> findAll(){
 /*       Set<FloorDto> floorDtos = new HashSet<>();
        Set<Floor> floors = floorService.findAll();

        for(Floor floor: floors) {
            FloorDto floorDto = floorConverter.toFloorDto(floor);
            floorDtos.add(floorDto);
        }
        //same as return ResponseEntity.ok(floorDto) with the rest of the code*/
        return ResponseEntity.ok(floorService.findAll()
                .stream()
                .map(floorConverter::toFloorDto)
                .collect(Collectors.toSet()));
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<FloorDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(floorConverter.toFloorDto(floorService.findById(id)));
    }

    @GetMapping(value = "/number/{number}")
    public ResponseEntity<FloorDto> findByNumber(@PathVariable Integer number) {
        return ResponseEntity.ok(floorConverter.toFloorDto(floorService.findByNumber(number)));
    }

    @PostMapping
    public ResponseEntity<FloorDto> save(@RequestBody @Valid FloorDto floorDto){
        Floor floor = floorConverter.toFloor(floorDto);
        Floor savedFloor = floorService.save(floor);
        return ResponseEntity.ok(floorConverter.toFloorDto(savedFloor));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FloorDto> update(@RequestBody @Valid FloorDto floorDto, @PathVariable Long id) {
        Floor floor = floorConverter.toFloor(floorDto);
        Floor updateFloor = floorService.update(floor, id);
        return ResponseEntity.ok(floorConverter.toFloorDto(updateFloor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(value = "id") Long id1){
        floorService.delete(id1);
        return ResponseEntity.ok().build();
    }
}
