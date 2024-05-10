package com.corporation.doctorsappointment.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeneralResponse {
    private int status;
    private String message;
}
