package com.formssi.zengzl.base.exception;

import com.formssi.zengzl.base.enums.ResultCode;
import lombok.Getter;

/**
 * @author: zilong
 * @description: 自定义异常
 * @created: 2020-10-12
 **/
@Getter
public class APIException extends RuntimeException {
    private Integer code;
    private String msg;

    public APIException() {
        this(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMsg());
    }

    public APIException(String msg) {
        this(ResultCode.FAILED.getCode(), msg);
    }

    public APIException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
