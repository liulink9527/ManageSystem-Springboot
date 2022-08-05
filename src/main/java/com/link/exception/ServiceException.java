package com.link.exception;

import com.link.common.Result;
import lombok.Getter;

/**
 * @author Link
 * @Description
 * @date 2022-08-05 11:10
 */
@Getter
public class ServiceException extends RuntimeException {
    private String code;

    public ServiceException(String code, String msg) {
        super(msg);
        this.code = code;
    }
}
