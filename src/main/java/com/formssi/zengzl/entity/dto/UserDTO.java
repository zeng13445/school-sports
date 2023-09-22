package com.formssi.zengzl.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDTO {

    @ApiModelProperty("用户id")
    @NotNull(message = "userId不为空")
    private Long userId;

}
