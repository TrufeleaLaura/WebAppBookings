package com.projectFortech.ProjectFortech.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class FilterTimesDTO {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDate date;

}
