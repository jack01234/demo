package com.example.demo.exception;

import com.system.commons.exception.BaseException;
import com.system.commons.exception.ErrorCode;

/**
 * Biz层异常
 *
 * @author thank_wd
 * @version 1.0.0
 * @date 2018/4/2
 */
public class DemoException extends BaseException {

    public DemoException(ErrorCode errorCode, Throwable cause) {
        this(errorCode, null, cause);
    }

    public DemoException(ErrorCode errorCode, String extraMsg, Throwable cause) {
        super(errorCode, extraMsg, cause);
    }

    public DemoException(ErrorCode errorCode) {
        this(errorCode, null, null);
    }

    public DemoException(ErrorCode errorCode, String extraMsg) {
        this(errorCode, extraMsg, null);
    }

}
