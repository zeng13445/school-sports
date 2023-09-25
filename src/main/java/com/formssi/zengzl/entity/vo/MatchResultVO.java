package com.formssi.zengzl.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

@Data
public class MatchResultVO {

    @ApiModelProperty("成绩ID")
    private Long matchResultId;

    @ApiModelProperty("比赛名称")
    private String matchName;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("用户Name")
    private String userName;

    @ApiModelProperty("用户成绩")
    private String score;

    @ApiModelProperty("用户成绩")
    private Integer round;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

}
