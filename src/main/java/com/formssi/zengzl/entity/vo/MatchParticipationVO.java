package com.formssi.zengzl.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

@Data
public class MatchParticipationVO {

    @ApiModelProperty("报名ID")
    private Long participationId;

    @ApiModelProperty("比赛名称")
    private String matchName;

    @ApiModelProperty("比赛id")
    private Long matchId;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("用户Name")
    private String userName;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

}
