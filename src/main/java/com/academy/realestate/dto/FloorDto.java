package com.academy.realestate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class FloorDto {

    private Long id;

    @Range(min = 1, max = 137, message = "Floor number must be between 1 and 138")
    private Integer number;

}
