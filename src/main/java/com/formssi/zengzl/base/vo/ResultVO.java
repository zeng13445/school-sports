package com.formssi.zengzl.base.vo;

import com.formssi.zengzl.base.enums.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * @author: zilong
 * @description: 自定义统一响应体
 * @created: 2020-10-12
 **/
@Getter
@ApiModel
public class ResultVO<T> {
    @ApiModelProperty(value = "状态码", notes = "默认1000是成功")
    private Integer code;
    @ApiModelProperty(value = "响应信息", notes = "来说明响应情况")
    private String msg;
    @ApiModelProperty(value = "响应的具体数据")
    private T data;

    public ResultVO(T data) {
        this(ResultCode.SUCCESS, data);
    }

    public ResultVO(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

    public ResultVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
