package com.corporation.doctorsappointment.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentRequestDTO {

    @NotNull(message = "Start date ca not be empty")
    private LocalDateTime startTime;

    @NotNull(message = "End date ca not be empty")
    private LocalDateTime endTime;

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());
    }
}
