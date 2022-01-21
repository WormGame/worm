package com.game.worm.interceptor;

import com.game.worm.service.exception.ApiException;
import com.game.worm.service.response.ResponseFormat;
import com.game.worm.utils.CommonUtils;
import com.game.worm.utils.eErrorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.validation.BindValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;





@RestControllerAdvice
@Slf4j
public class ErrorController {


    @ExceptionHandler({BindValidationException.class, BindException.class})
    private ResponseEntity<ResponseFormat> paramError(BindValidationException e){
        log.error(e.getMessage());
        log.error(CommonUtils.getStackTraceElements(e));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(new ResponseFormat(
                eErrorInfo.BAD_PARAMETER.getMessage(),
                false,
                null
        ));
    }

    @ExceptionHandler(ApiException.class)
    private ResponseEntity<ResponseFormat> apiError(ApiException e){
        log.error(e.getMessage());
        log.error(CommonUtils.getStackTraceElements(e));
        return ResponseEntity.status(e.getHttpStatus()).
                body(new ResponseFormat(
                        e.getMessage(),
                        false,
                        null
                ));
    }
}
