package com.projectFortech.ProjectFortech.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class TicketsUserDTO {
    private int seat;
    private LocalDateTime buyDate;
    private String movieName;
    private String roomName;
    private LocalTime startTime;
    private LocalDate date;

}
