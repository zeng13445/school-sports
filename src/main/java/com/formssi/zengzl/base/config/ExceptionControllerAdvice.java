package com.formssi.zengzl.base.config;

import com.formssi.zengzl.base.enums.ResultCode;
import com.formssi.zengzl.base.exception.APIException;
import com.formssi.zengzl.base.exception.ValidateException;
import com.formssi.zengzl.base.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: zilong
 * @description: 全局异常处理
 * @created: 2020-10-12
 **/
@RestControllerAdvice
public class ExceptionControllerAdvice {
    private final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    /**
     * 自定义API异常
     */
    @ExceptionHandler(APIException.class)
    public ResultVO<String> APIExceptionHandler(APIException e) {
        logger.error("APIExceptionHandler,{}", e);

        return new ResultVO<>(e.getCode(), e.getMessage());
    }

    /**
     * 自定义校验异常
     */
    @ExceptionHandler(ValidateException.class)
    public ResultVO<String> ValidateException(ValidateException e) {
        logger.error("ValidateException,{}", e);

        return new ResultVO<>(e.getCode(), e.getMessage());
    }

    /**
     * @RequestBody 加了后抛出MethodArgumentNotValidException异常
     * getFieldErrors();一次性抛出所有字段校验异常
     * getAllErrors();获取所有异常检验信息，不仅仅是字段。
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 获取校验字段时抛出的异常信息
        FieldError fieldError = e.getBindingResult().getFieldError();
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, fieldError.getDefaultMessage());
    }

    /**
     * @RequestBody 没加时抛出BindingResult异常
     */
    @ExceptionHandler(BindException.class)
    public ResultVO<String> BindingResult(BindException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, fieldError.getDefaultMessage());
    }

    /**
     * @RequestParam 当为true时，若参数为空，捕获参数异常
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultVO<String> MissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, e.getMessage());
    }

    /**
     * 非法参数异常，主要用于使用spring断言和非空判断等抛出的
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResultVO<String> IllegalArgumentException(IllegalArgumentException e) {
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, e.getMessage());
    }

    /**
     * 空指针
     */
    @ExceptionHandler(NullPointerException.class)
    public ResultVO<String> NullPointerException(NullPointerException e) {
        logger.error("NullPointerException,{}", e);

        return new ResultVO<>(ResultCode.NULL_POINTER, e.getMessage());
    }

    /**
     * 算术运算异常
     */
    @ExceptionHandler(ArithmeticException.class)
    public ResultVO<String> ArithmeticException(ArithmeticException e) {
        logger.error("ArithmeticException,{}", e);

        return new ResultVO<>(ResultCode.ARITHMETIC, e.getMessage());
    }

}
