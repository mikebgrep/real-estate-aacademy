package com.academy.realestate.controler;

import com.academy.realestate.converter.FloorConverter;
import com.academy.realestate.dto.FloorDto;
import com.academy.realestate.model.Floor;
import com.academy.realestate.service.FloorService;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = FloorController.class)
public class FloorControllerTest extends BaseControllerTest {

    @MockBean
    private FloorService floorService;

    @MockBean
    private FloorConverter floorConverter;

    @Test
    public void save() throws Exception {
        FloorDto floorDto = FloorDto.builder().number(1).build();
        String reqJson = objectMapper.writeValueAsString(floorDto);

        when(floorConverter.toFloor(any(FloorDto.class))).thenReturn(Floor.builder().build());
        when(floorService.save(any(Floor.class))).thenReturn(Floor.builder().build());
        when(floorConverter.toFloorDto(any(Floor.class))).thenReturn(FloorDto.builder().id(1L).number(1).build());

        mockMvc.perform(post("/floors")
                        .content(reqJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.number", is(1)));

    }

    @Test
    public void findById()  throws Exception{

        when(floorService.findById(any(Long.class))).thenReturn(Floor.builder().build());
        when(floorConverter.toFloorDto(any(Floor.class))).thenReturn(FloorDto.builder().id(1L).number(5).build());

        mockMvc.perform(get("/floors/id/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id" , is(1)))
                .andExpect(jsonPath("$.number" , is(5)));

    }

    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(delete("/floors/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception {
        FloorDto floorDto = FloorDto.builder().id(1L).number(5).build();
        String reqJson = objectMapper.writeValueAsString(floorDto);

        when(floorConverter.toFloorDto(any())).thenReturn(floorDto);

        mockMvc.perform(put("/floors/1")
                .content(reqJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.number", is(5)));
    }


}