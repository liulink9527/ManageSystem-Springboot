package com.link.exception;

import com.link.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author Link
 * @Description
 * @date 2022-08-05 11:07
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public Result handle(ServiceException e) {
        return Result.error(e.getCode(), e.getMessage());
    }
}
