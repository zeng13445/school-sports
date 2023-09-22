package com.formssi.zengzl.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MatchItemDTO {

    @ApiModelProperty("比赛名称")
    @NotBlank(message = "matchName不为空")
    private String matchName;

    @ApiModelProperty("报名开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date applyStartTime;

    @ApiModelProperty("报名结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date applyEndTime;

}
