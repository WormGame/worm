package com.game.worm.service.exception;

import com.game.worm.utils.eErrorInfo;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class ApiException extends RuntimeException{
    private final String message;
    private final HttpStatus httpStatus;
    private final boolean success;
    private final JsonObject result;

    public ApiException(eErrorInfo e, boolean success) {
        this.httpStatus = e.getHttpStatus();
        this.message = e.getMessage();
        this.result = null;
        this.success = success;
    }

    public ApiException(final eErrorInfo e, final boolean success, final JsonObject result) {
        this.httpStatus = e.getHttpStatus();
        this.message = e.getMessage();
        this.result = result;
        this.success = success;
    }


}
