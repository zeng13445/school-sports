package com.formssi.zengzl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ApiModel("用户")
@TableName("sys_user")
public class SysUser {
    @ApiModelProperty("用户id")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("用户账号")
    @NotBlank(message = "用户账号不能为空")
    private String userName;

    @ApiModelProperty("用户密码")
    @NotNull(message = "用户密码不能为空")
    private String password;

    @ApiModelProperty("0运动员1数据录入员2学校管理员3体育总局超管")
    private Integer userType;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

}
