package com.corporation.doctorsappointment.service.exceptions;

import lombok.Data;

@Data
public class BadRequestException extends RuntimeException {
    private int status;
    private String message;

    public BadRequestException(int status, String message) {
        super(message);
        this.message = message;
        this.status = status;
    }
}
