package com.globant.granmaRestaurant.exception.custonException;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.globant.granmaRestaurant.exception.enums.ExceptionCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private ExceptionCode code;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime timestamp;
    @Setter
    private String exception;
    private HttpStatus status;

    public CustomException(ExceptionCode code, LocalDateTime timestamp, HttpStatus status, String message) {
        super(message);
        this.code = code;
        this.timestamp = timestamp;
        this.status = status;
    }

}
