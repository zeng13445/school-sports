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
    public ResultVO<String> apiExceptionHandler(APIException e) {
        logger.error("apiExceptionHandler:{}", e);

        return new ResultVO<>(e.getCode(), e.getMessage());
    }

    /**
     * 自定义校验异常
     */
    @ExceptionHandler(ValidateException.class)
    public ResultVO<String> validateException(ValidateException e) {
        logger.error("validateException:{}", e);

        return new ResultVO<>(e.getCode(), e.getMessage());
    }

    /**
     * @RequestBody 加了后抛出MethodArgumentNotValidException异常
     * getFieldErrors();一次性抛出所有字段校验异常
     * getAllErrors();获取所有异常检验信息，不仅仅是字段。
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        logger.error("methodArgumentNotValidExceptionHandler:{}", e);

        // 获取校验字段时抛出的异常信息
        FieldError fieldError = e.getBindingResult().getFieldError();
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, fieldError.getDefaultMessage());
    }

    /**
     * @RequestBody 没加时抛出BindingResult异常
     */
    @ExceptionHandler(BindException.class)
    public ResultVO<String> bindingResult(BindException e) {
        logger.error("bindingResult:{}", e);

        FieldError fieldError = e.getBindingResult().getFieldError();
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, fieldError.getDefaultMessage());
    }

    /**
     * @RequestParam 当为true时，若参数为空，捕获参数异常
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultVO<String> missingServletRequestParameterException(MissingServletRequestParameterException e) {
        logger.error("missingServletRequestParameterException:{}", e);

        return new ResultVO<>(ResultCode.VALIDATE_FAILED, e.getMessage());
    }

    /**
     * 非法参数异常，主要用于使用spring断言和非空判断等抛出的
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResultVO<String> illegalArgumentException(IllegalArgumentException e) {
        logger.error("illegalArgumentException:{}", e);

        return new ResultVO<>(ResultCode.VALIDATE_FAILED, e.getMessage());
    }

    /**
     * 空指针
     */
    @ExceptionHandler(NullPointerException.class)
    public ResultVO<String> nullPointerException(NullPointerException e) {
        logger.error("nullPointerException:{}", e);

        return new ResultVO<>(ResultCode.NULL_POINTER, e.getMessage());
    }

    /**
     * 算术运算异常
     */
    @ExceptionHandler(ArithmeticException.class)
    public ResultVO<String> arithmeticException(ArithmeticException e) {
        logger.error("arithmeticException:{}", e);

        return new ResultVO<>(ResultCode.ARITHMETIC, e.getMessage());
    }

    /**
     * 未知异常.
     */
    @ExceptionHandler(Exception.class)
    public ResultVO<String> handleException(Exception e) {
        logger.error("handleException:{}", e);

        return new ResultVO<>(ResultCode.ERROR, e.getMessage());
    }
}
