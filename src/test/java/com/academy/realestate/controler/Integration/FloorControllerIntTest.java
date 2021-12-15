package com.academy.realestate.controler.Integration;

import com.academy.realestate.dto.FloorDto;
import com.academy.realestate.model.Floor;
import com.academy.realestate.repository.FloorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class FloorControllerIntTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FloorRepository floorRepository;

    @Test
    public void saveFloor() throws JsonProcessingException {

        FloorDto floorDto = FloorDto.builder().number(5).build();
        String reqJson = objectMapper.writeValueAsString(floorDto);
        given()
                .contentType(ContentType.JSON.toString())
                .body(reqJson)
                    .when()
                        .post("http://localhost:8080/floors")
                    .then()
                        .statusCode(200)
                        .body("id", equalTo(1))
                        .body("number", equalTo(5));
    }

    @Test
    public void updateFloor() throws Exception {
        floorRepository.save(Floor.builder().number(1).build());
        FloorDto floorDto = FloorDto.builder().id(1L).number(5).build();
        String reqJson = objectMapper.writeValueAsString(floorDto);

        given()
                .contentType(ContentType.JSON.toString())
                .body(reqJson)
                .when()
                .put("http://localhost:8080/floors/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("number", equalTo(5));
    }

    @Test
    public void findByNumber() throws Exception {
        floorRepository.save(Floor.builder().number(1).build());

        given()
                .contentType(ContentType.JSON.toString())
                .when()
                .get("http://localhost:8080/floors/number/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("number", equalTo(1));
    }
}
