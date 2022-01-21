package com.game.worm.service.exception;

import com.game.worm.utils.eErrorInfo;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class ApiException extends RuntimeException{
    private final String message;
    private final HttpStatus httpStatus;
    private final boolean success = false;

    public ApiException(eErrorInfo e) {
        this.httpStatus = e.getHttpStatus();
        this.message = e.getMessage();
    }
}
