package com.corporation.doctorsappointment.utils;

import com.corporation.doctorsappointment.service.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionTranslator {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BadRequestException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(BadRequestException ex) {
        return new ResponseEntity<>(new GeneralResponse(ex.getStatus(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
