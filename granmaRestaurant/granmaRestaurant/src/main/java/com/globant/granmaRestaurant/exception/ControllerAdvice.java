package com.globant.granmaRestaurant.exception;

import com.globant.granmaRestaurant.exception.custonException.CreateException;
import com.globant.granmaRestaurant.exception.DTO.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(CreateException.class)
    public ResponseEntity<ErrorDTO> createExceptionHandler(CreateException ex) {
        // Capturar el stack trace en un String
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String stackTrace = sw.toString();

        ex.setException(stackTrace);

        ErrorDTO error = ErrorDTO.builder()
                .code(ex.getCode().getCode())
                .timestamp(LocalDateTime.now())
                .description(ex.getMessage())
                .exception(stackTrace)
                .build();

        log.error("Detalle de excepcion:", ex);
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> genericExceptionHandler(Exception ex){
        ErrorDTO error  = ErrorDTO
                .builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .timestamp(LocalDateTime.now())
                .description(ex.getMessage())
                .exception("ex.getException()")
                .build();

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
