package com.formssi.zengzl.base.exception;

import com.formssi.zengzl.base.enums.ResultCode;
import lombok.Getter;

/**
 * 验证异常
 */
@Getter
public class ValidateException extends RuntimeException {

    private Integer code;
    private String msg;

    public ValidateException() {
        this(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMsg());
    }

    public ValidateException(String msg) {
        this(ResultCode.FAILED.getCode(), msg);
    }

    public ValidateException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public ValidateException(ResultCode code) {
        super(code.getMsg());
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

}
