package com.projectFortech.ProjectFortech.dto;

import com.projectFortech.ProjectFortech.domain.MoviesProgram;
import com.projectFortech.ProjectFortech.domain.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TicketDTO {

    private Integer ticketId;

    private int seatNumber;

    private int userId;

    private int programId;


}
