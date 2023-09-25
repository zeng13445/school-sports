package com.formssi.zengzl.base.enums;

import lombok.Getter;

/**
 * @author: zilong
 * @description: 响应码枚举
 * @created: 2020-10-12
 **/
@Getter
public enum ResultCode {

    SUCCESS(1000, "操作成功"),

    FAILED(1001, "响应失败"),

    VALIDATE_FAILED(1002, "参数校验失败"),

    NULL_POINTER(1003, "空指针异常"),

    ARITHMETIC(1004, "算术运算异常"),

    CREATE_FAILED(1005, "创建失败"),

    DATA_EXISTED(1006, "数据已存在"),

    APPLY_NOT_START(1007, "报名未开始"),

    APPLY_HAS_ENDED(1008, "报名已结束"),

    JOIN_EXISTED(1009, "用户已参赛"),

    ERROR(5000, "未知错误");

    private Integer code;
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
