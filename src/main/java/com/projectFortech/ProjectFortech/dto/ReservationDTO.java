package com.projectFortech.ProjectFortech.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ReservationDTO {

    private Integer id;
    private String email;
    private List<Integer> seats;
    private String movieName;
    private LocalDate movieDate;
    private LocalTime movieStartTime;

}
