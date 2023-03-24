package com.projectFortech.ProjectFortech.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MovieProgramDTO {
    private int programId;

    private LocalTime startTime;

    private LocalTime endTime;

    private LocalDate date;
    private int movieId;
    private int roomId;
    private List<Integer> seats;

    public MovieProgramDTO() {
    }


}
